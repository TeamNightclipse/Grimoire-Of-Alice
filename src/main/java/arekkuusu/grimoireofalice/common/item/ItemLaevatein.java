/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityFierySword;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.lib.LibColor;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.katsstuff.teamnightclipse.mirror.data.Quat;
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
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;

public class ItemLaevatein extends ItemBaseSword {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemLaevatein(ToolMaterial material) {
		super(material, LibName.LAEVATEIN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer) entity;
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0));
		}
	}

	@SuppressWarnings("ConstantConditions")
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
								.setShot(LibShotData.SHOT_SPHERE_DARK.setMainColor(LibColor.COLOR_SATURATED_RED).setSize(2F))
								.setMovementData(0.8D)
								.build();
						DanmakuCreationHelper.createRandomRingShot(danmaku, 5, 10, 5);
					}
					EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 15);
					world.spawnEntity(circle);
				}
				if(!isCreative && player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
					player.getCapability(ITEM_HANDLER_CAPABILITY, null).extractItem(getSlotFor(player, fireCharge), 1, false);
				}
				stack.damageItem(10, player);
				player.getCooldownTracker().setCooldown(this, 40);
			}
		} else {
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

	private static int getSlotFor(EntityPlayer player, ItemStack stack) {
		for(int i = 0; i < player.inventory.mainInventory.size(); ++i) {
			ItemStack invStack = player.inventory.mainInventory.get(i);
			if(!invStack.isEmpty() && stack.getItem() == invStack.getItem() && ItemStack.areItemStackTagsEqual(stack, invStack)) {
				return i;
			}
		}

		return -1;
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
}
