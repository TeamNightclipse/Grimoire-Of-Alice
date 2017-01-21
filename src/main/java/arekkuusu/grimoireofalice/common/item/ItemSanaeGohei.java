/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

import static arekkuusu.grimoireofalice.common.item.ItemSanaeGohei.Miracles.*;

public class ItemSanaeGohei extends ItemGohei<ItemSanaeGohei.Miracles> {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemSanaeGohei() {
		super(LibItemName.SANAE_GOHEI, Miracles.values());
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sanae_gohei_header.name"));
		if (GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sanae_gohei_use.name"));
			list.add(TextFormatting.AQUA + I18n.format("grimoire.tooltip.sanae_gohei_charge.name") + " " + getCharge(stack));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sanae_gohei_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (playerIn.isSneaking()) {
			increaseType(stack);
			if (worldIn.isRemote) {
				String modeName = getType(stack).toString() + ".name";
				ITextComponent text = new TextComponentTranslation("grimoire.tooltip.sanae_gohei_mode_header.name");
				text.appendSibling(new TextComponentTranslation("grimoire.tooltip.sanae_gohei_mode_" + modeName));

				GrimoireOfAlice.proxy.displayRecordText(text);
			}
		}
		else {
			playerIn.setActiveHand(hand);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase livingBase, int count) {
		if (count % 35 == 0 && livingBase instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) livingBase;

			if(!player.worldObj.isRemote && getCharge(stack) < 30) {
				if (hasFaith(player) && consumeFaith(player)) {
					addCharge(stack, 5);
				}
				else if (!player.capabilities.isCreativeMode && player.getFoodStats().getFoodLevel() > 10) {
					int food = player.getFoodStats().getFoodLevel();
					player.getFoodStats().setFoodLevel(food - 5);
					addCharge(stack, 1);
				}
			}
		}
	}

	private boolean hasFaith(EntityPlayer player) {
		//noinspection ConstantConditions
		return player.inventory.hasItemStack(new ItemStack(ModItems.FAITH));
	}

	@SuppressWarnings("ConstantConditions")
	private boolean consumeFaith(EntityPlayer player) {
		if (player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler capability = player.getCapability(ITEM_HANDLER_CAPABILITY, null);

			for (int i = 0; i < capability.getSlots(); ++i) {
				ItemStack stack = capability.getStackInSlot(i);
				if (stack != null && stack.getItem() == ModItems.FAITH && stack.stackSize > 0) {
					if (!player.capabilities.isCreativeMode) {
						capability.extractItem(i, 1, false);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer && !worldIn.isRemote) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (!playerIn.isSneaking()) {
				Miracles mode = getType(stack);
				final int oldCharge = getCharge(stack);
				int charge = oldCharge;
				WorldInfo worldInfo = playerIn.worldObj.getWorldInfo();

				if (mode == RAIN && RAIN.canUse(charge, playerIn)) {
					int time = 400 + itemRand.nextInt(1000) * 20;

					worldInfo.setCleanWeatherTime(0);
					worldInfo.setRainTime(time);
					worldInfo.setThunderTime(time);
					worldInfo.setRaining(true);
					worldInfo.setThundering(false);

					charge -= 4;
				}
				else if (mode == THUNDER && THUNDER.canUse(charge, playerIn)) {
					int time = 400 + itemRand.nextInt(1000) * 20;

					worldInfo.setCleanWeatherTime(0);
					worldInfo.setRainTime(time);
					worldInfo.setThunderTime(time);
					worldInfo.setRaining(true);
					worldInfo.setThundering(true);

					charge -= 5;
				}
				else if (mode == CLEAR && CLEAR.canUse(charge, playerIn)) {
					int time = 400 + itemRand.nextInt(1000) * 20;

					worldInfo.setCleanWeatherTime(time);
					worldInfo.setRainTime(0);
					worldInfo.setThunderTime(0);
					worldInfo.setRaining(false);
					worldInfo.setThundering(false);

					charge -= 1;
				}
				else if (mode == MOSES && MOSES.canUse(charge, playerIn)) {
					//TODO: Add Moses Sea Opening Miracle
					//charge -= 10;
				}
				else if(mode == WIND && WIND.canUse(charge, playerIn)) {
					worldIn.playSound(null, playerIn.getPosition(), GrimoireSoundEvents.WIND, SoundCategory.PLAYERS, 1F, 1F);
					Vec3d vec = playerIn.getLookVec();
					List<EntityLivingBase> list = playerIn.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
							entityLiving.getEntityBoundingBox().offset(vec.xCoord * 2, vec.yCoord * 2, vec.zCoord * 2).expandXyz(3D), entity -> entity != playerIn);
					list.forEach(entity -> {
						if(entity.worldObj instanceof WorldServer) {
							((WorldServer) entity.worldObj).spawnParticle(EnumParticleTypes.CLOUD, entity.posX, entity.posY, entity.posZ, 5, 0, 0, 0, 0.1D);
						}
						entity.motionX = -MathHelper.sin((float) Math.toRadians(playerIn.rotationYaw)) * 4;
						entity.motionY = -MathHelper.sin((float) Math.toRadians(playerIn.rotationPitch)) * 4;
						entity.motionZ = MathHelper.cos((float) Math.toRadians(playerIn.rotationYaw)) * 4;
					});
					charge -= 1;
				}
				else if(mode == HEAL && HEAL.canUse(charge, playerIn)) {
					float health = playerIn.getMaxHealth() - playerIn.getHealth();
					playerIn.heal(health * itemRand.nextFloat());

					charge -= 2;
				}
				else if (mode == POTIONS && POTIONS.canUse(charge, playerIn)) {
					List<PotionEffect> effects = consumePotionEffectsInInventory(playerIn);
					if (!effects.isEmpty()) {
						addPlayerPotionEffects(playerIn, effects);
						playerIn.getCooldownTracker().setCooldown(this, 15);

						charge -= 3;
					}
				}
				else if (mode == CROPS && CROPS.canUse(charge, playerIn)) {
					applyBonemealRandomPos(playerIn, worldIn);

					charge -= 6;
				}
				else if (mode == TIME && TIME.canUse(charge, playerIn)) {
					long time = worldIn.isDaytime() ? 14000 : 0;
					worldIn.setWorldTime(time);

					playerIn.getCooldownTracker().setCooldown(this, 5);

					charge -= 10;
				}

				if (oldCharge != charge) {
					EnumHand hand = playerIn.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
					playerIn.swingArm(hand);

					setCharge(stack, charge);
				}
			}
		}
	}

	private void applyBonemealRandomPos(EntityPlayer player, World world) {
		BlockPos posI = new BlockPos(player.posX - 4 + itemRand.nextInt(4), player.posY - 4 + itemRand.nextInt(4), player.posZ - 4 + itemRand.nextInt(4));
		BlockPos posF = new BlockPos(player.posX + 4 + itemRand.nextInt(4), player.posY + 4 + itemRand.nextInt(4), player.posZ + 4 + itemRand.nextInt(4));

		BlockPos.getAllInBox(posI, posF).forEach(pos -> {
			if (itemRand.nextBoolean() && ItemDye.applyBonemeal(new ItemStack(Items.DYE), world, pos, player)) {
				IBlockState state = world.getBlockState(pos);
				for (int j = 0; j < 15; ++j) {
					double d0 = itemRand.nextGaussian() * 0.02D;
					double d1 = itemRand.nextGaussian() * 0.02D;
					double d2 = itemRand.nextGaussian() * 0.02D;
					world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + itemRand.nextFloat(), pos.getY() + itemRand.nextFloat() * state.getBoundingBox(world, pos).maxY, pos.getZ() + itemRand.nextFloat(), d0, d1, d2);
				}
				if (!world.isRemote) {
					world.playEvent(2005, pos, 0);
				}
			}
		});
	}

	@SuppressWarnings("ConstantConditions")
	private List<PotionEffect> consumePotionEffectsInInventory(EntityPlayer player) {
		IItemHandler capability = player.getCapability(ITEM_HANDLER_CAPABILITY, null);
		List<PotionEffect> potionEffects = new ArrayList<>();
		for (int i = 0; i < capability.getSlots(); ++i) {
			ItemStack stack = capability.getStackInSlot(i);
			if (stack != null && PotionUtils.getPotionFromItem(stack) != PotionTypes.WATER) {
				if (!player.capabilities.isCreativeMode) {
					//noinspection ConstantConditions
					capability.extractItem(i, 1, false);
				}
				PotionUtils.getEffectsFromStack(stack).forEach(potionEffects::add);
			}
		}
		return potionEffects;
	}

	private void addPlayerPotionEffects(EntityPlayer player, List<PotionEffect> potionEffects) {
		potionEffects.forEach(player::addPotionEffect);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format("item.sanaegohei.name") + " :"
				+ I18n.format("grimoire.tooltip.sanae_gohei_mode_" + getType(stack).toString() + ".name");
	}

	private void addCharge(ItemStack itemStack, int charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setInteger("GoheiCharge", Math.min(30, getCharge(itemStack) + charge));
	}

	private void setCharge(ItemStack itemStack, int charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setInteger("GoheiCharge", charge);
	}

	private int getCharge(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger("GoheiCharge");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	public enum Miracles {

		CLEAR(  "clear",  (i, player) -> i >= 1),
		WIND(   "wind",   (i, player) -> i >= 1 && !player.isInWater()),
		HEAL(   "heal",   (i, player) -> i >= 2 && player.shouldHeal()),
		POTIONS("potions",(i, player) -> i >= 3),
		RAIN(   "rain",   (i, player) -> i >= 4),
		THUNDER("thunder",(i, player) -> i >= 5),
		CROPS(  "crops",  (i, player) -> i >= 8),
		TIME(   "time",   (i, player) -> i >= 15),
		MOSES(  "moses",  (i, player) -> i >= 30);

		private final BiPredicate<Integer, EntityPlayer> condition;
		private final String name;

		Miracles(String name, BiPredicate<Integer, EntityPlayer> condition) {
			this.condition = condition;
			this.name = name;
		}

		public boolean canUse(int i, EntityPlayer player) {
			return condition.test(i, player);
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
