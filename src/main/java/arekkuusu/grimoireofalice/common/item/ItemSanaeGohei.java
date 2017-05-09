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
import arekkuusu.grimoireofalice.common.entity.EntityMiracleCircle;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.*;
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
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import static arekkuusu.grimoireofalice.common.item.ItemSanaeGohei.Miracles.*;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemSanaeGohei extends ItemGohei<ItemSanaeGohei.Miracles> implements IOwnedBy {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
	private static final Method CONVERT_ZOMBIE = ReflectionHelper.findMethod(EntityZombie.class, null, new String[]{"convertToVillager", "func_82232_p"});

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
			if (!worldIn.isRemote) {
				EntityMiracleCircle miracleCircle = new EntityMiracleCircle(worldIn, playerIn, stack);
				worldIn.spawnEntityInWorld(miracleCircle);
			}
			playerIn.setActiveHand(hand);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		for (int i = 0; i < 2; ++i) {
			player.world.spawnParticle(EnumParticleTypes.REDSTONE,
					player.posX + (itemRand.nextDouble() - 0.5D) * player.width,
					player.posY + itemRand.nextDouble() * player.height - 0.25D,
					player.posZ + (itemRand.nextDouble() - 0.5D) * player.width,
					(itemRand.nextDouble() - 0.5D) * 2.0D, -itemRand.nextDouble(), (itemRand.nextDouble() - 0.5D) * 2.0D);
		}
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		World worldIn = entityLiving.world;
		if (entityLiving instanceof EntityPlayer && !worldIn.isRemote) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (!playerIn.isSneaking() && !playerIn.getCooldownTracker().hasCooldown(this)) {
				Miracles mode = getType(stack);
				final int oldCharge = getCharge(stack);
				int charge = oldCharge;
				WorldInfo worldInfo = playerIn.world.getWorldInfo();

				switch (mode) {
					case RAIN:
						if (RAIN.canUse(charge, playerIn)) {
							int time = 400 + itemRand.nextInt(1000) * 20;

							worldInfo.setCleanWeatherTime(0);
							worldInfo.setRainTime(time);
							worldInfo.setThunderTime(time);
							worldInfo.setRaining(true);
							worldInfo.setThundering(false);

							charge -= 4;
						}
						break;
					case THUNDER:
						if (THUNDER.canUse(charge, playerIn)) {
							int time = 400 + itemRand.nextInt(1000) * 20;

							worldInfo.setCleanWeatherTime(0);
							worldInfo.setRainTime(time);
							worldInfo.setThunderTime(time);
							worldInfo.setRaining(true);
							worldInfo.setThundering(true);

							charge -= 5;
						}
						break;
					case CLEAR:
						if (CLEAR.canUse(charge, playerIn)) {
							int time = 400 + itemRand.nextInt(1000) * 20;

							worldInfo.setCleanWeatherTime(time);
							worldInfo.setRainTime(0);
							worldInfo.setThunderTime(0);
							worldInfo.setRaining(false);
							worldInfo.setThundering(false);

							charge -= 1;
						}
						break;
					case TESTIFICATE:
						if (TESTIFICATE.canUse(charge, playerIn)) {
							if (convertNearZombies(playerIn, worldIn)) {
								charge -= 15;
							}
						}
						break;
					case WIND:
						if (WIND.canUse(charge, playerIn)) {
							worldIn.playSound(null, playerIn.getPosition(), GrimoireSoundEvents.WIND, SoundCategory.PLAYERS, 1F, 1F);
							Vec3d vec = playerIn.getLookVec();
							List<EntityLivingBase> list = playerIn.world.getEntitiesWithinAABB(EntityLivingBase.class,
									entityLiving.getEntityBoundingBox().offset(vec.xCoord * 2, vec.yCoord * 2, vec.zCoord * 2)
											.expandXyz(3D), entity -> entity != playerIn);
							list.forEach(entity -> {
								if (entity.world instanceof WorldServer) {
									((WorldServer) entity.world).spawnParticle(EnumParticleTypes.CLOUD, entity.posX
											, entity.posY, entity.posZ, 5, 0, 0, 0, 0.1D);
								}
								entity.motionX = -MathHelper.sin((float) Math.toRadians(playerIn.rotationYaw)) * 4;
								entity.motionY = -MathHelper.sin((float) Math.toRadians(playerIn.rotationPitch)) * 4;
								entity.motionZ = MathHelper.cos((float) Math.toRadians(playerIn.rotationYaw)) * 4;
							});

							charge -= 1;
						}
						break;
					case HEAL:
						if (HEAL.canUse(charge, playerIn)) {
							float health = playerIn.getMaxHealth() - playerIn.getHealth();
							playerIn.heal(health * itemRand.nextFloat());
							if (worldIn instanceof WorldServer) {
								((WorldServer) worldIn).spawnParticle(EnumParticleTypes.HEART, playerIn.posX, playerIn.posY + 2, playerIn.posZ, 20, 0D, 0D, 0D, 0D);
							}

							charge -= 2;
						}
						break;
					case POTIONS:
						if (POTIONS.canUse(charge, playerIn)) {
							List<PotionEffect> effects = consumePotionEffectsInInventory(playerIn);
							if (!effects.isEmpty()) {
								addPlayerPotionEffects(playerIn, effects);
								playerIn.getCooldownTracker().setCooldown(this, 15);

								charge -= 3;
							}
						}
						break;
					case SOIL:
						if (SOIL.canUse(charge, playerIn)) {
							applyBonemealRandomPos(playerIn, worldIn);

							charge -= 6;
						}
						break;
					case TIME:
						if (TIME.canUse(charge, playerIn)) {
							long time = worldIn.isDaytime() ? 14000 : 0;
							worldIn.setWorldTime(time);

							playerIn.getCooldownTracker().setCooldown(this, 5);

							charge -= 10;
						}
						break;
				}

				if (oldCharge != charge) {
					setCharge(stack, charge);
				}
			}
		}
		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 25);
		}
	}

	private boolean convertNearZombies(EntityPlayer player, World world) {
		List<EntityZombie> list = world.getEntitiesWithinAABB(EntityZombie.class, player.getEntityBoundingBox().expandXyz(10));
		list.forEach(this::convertToVillager);
		return !list.isEmpty();
	}

	private void convertToVillager(EntityZombie zombie) {
		try {
			CONVERT_ZOMBIE.invoke(zombie);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
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
		return 2000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	public enum Miracles {

		CLEAR("clear", (i, player) -> i >= 1),
		WIND("wind", (i, player) -> i >= 1 && !player.isInWater()),
		HEAL("heal", (i, player) -> i >= 2 && player.shouldHeal()),
		POTIONS("potions", (i, player) -> i >= 3),
		RAIN("rain", (i, player) -> i >= 4),
		THUNDER("thunder", (i, player) -> i >= 5),
		SOIL("soil", (i, player) -> i >= 8),
		TIME("time", (i, player) -> i >= 15),
		TESTIFICATE("testificate", (i, player) -> i >= 15);

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

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.SANAE_KOCHIYA;
	}
}
