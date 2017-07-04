/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.entity.EntityFierySword;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.item.ItemModSword;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

public class ItemLaevatein extends ItemModSword implements IOwnedBy {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemLaevatein(ToolMaterial material) {
		super(material, LibItemName.LAEVATEIN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.laevatein_header.name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer) entity;
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		boolean isCreative = player.capabilities.isCreativeMode;

		ItemStack fireCharge = new ItemStack(Items.FIRE_CHARGE);
		if(player.isSneaking()) {
			if(isCreative || player.inventory.hasItemStack(fireCharge)) {
				player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1F, itemRand.nextFloat() * 0.1F + 0.8F);
				if(!world.isRemote) {
					for(int i = 0; i < 3; i++) {
						DanmakuTemplate danmaku = DanmakuTemplate.builder()
								.setUser(player)
								.setShot(LibShotData.SHOT_SPHERE_DARK.setColor(LibColor.COLOR_SATURATED_RED).setSize(2F))
								.build();

						DanmakuCreationHelper.createRandomRingShot(Quat.orientationOf(player), danmaku, 1, 10, 5);
					}

					EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 15);
					world.spawnEntity(circle);
				}

				if(!isCreative) {
					//noinspection ConstantConditions
					if(player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
						//noinspection ConstantConditions
						player.getCapability(ITEM_HANDLER_CAPABILITY, null).extractItem(getSlotFor(player, fireCharge), 1, false);
					}
				}
				stack.damageItem(10, player);
				player.getCooldownTracker().setCooldown(this, 40);
			}
		}
		else {
			player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1F, itemRand.nextFloat() * 0.1F + 0.8F);
			if(!world.isRemote) {
				EntityFierySword fierySword = new EntityFierySword(world, player);
				world.spawnEntity(fierySword);
			}
			stack.damageItem(1, player);
			player.getCooldownTracker().setCooldown(this, 30);
		}
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	private int getSlotFor(EntityPlayer player, ItemStack stack) {
		for(int i = 0; i < player.inventory.mainInventory.size(); ++i) {
			ItemStack invStack = player.inventory.mainInventory.get(i);
			if(!invStack.isEmpty() && stack.getItem() == invStack.getItem() && ItemStack.areItemStackTagsEqual(stack, invStack)) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		BlockPos block = pos.offset(facing);
		ItemStack stack = player.getHeldItem(hand);

		if(!player.canPlayerEdit(block, facing, stack)) {
			return EnumActionResult.PASS;
		}
		else {
			boolean success = false;
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					BlockPos newPos = block.add(i, 0, j);
					if(world.isAirBlock(newPos)) {
						world.setBlockState(newPos, Blocks.FIRE.getDefaultState());
						success = true;
					}
				}
			}

			if(success) {
				player.playSound(SoundEvents.ENTITY_BLAZE_DEATH, 0.5F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
			stack.damageItem(1, player);
			return success ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.setFire(3 + itemRand.nextInt(3));
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.BLAZE_ROD;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public EnumTouhouCharacters character(ItemStack stack) {
		return EnumTouhouCharacters.FLANDRE_SCARLET;
	}
}
