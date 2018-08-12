/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityDragonJewel;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemDragonJewel extends ItemBase {

	public ItemDragonJewel() {
		super(LibName.DRAGON_JEWEL);
		setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if(isSelected && player.getCooldownTracker().hasCooldown(this) && player.ticksExisted % 2 == 0) {
				Vec3d vec = player.getLookVec();
				double x = player.posX;
				double y = player.posY + player.getEyeHeight();
				double z = player.posZ;

				world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.PLAYERS, 1F, 1F);
				for(int i = 0; i < 8; ++i) {
					double randX = x + itemRand.nextGaussian() / 2.0D;
					double randY = y + itemRand.nextGaussian() / 2.0D;
					double randZ = z + itemRand.nextGaussian() / 2.0D;

					for(int j = 0; j < 6; ++j) {
						world.spawnParticle(EnumParticleTypes.FLAME, randX, randY, randZ, vec.x * 0.08D * j, vec.y * 0.6D,
								vec.z * 0.08D * j);
					}
				}

				if(!world.isRemote) {
					List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
							player.getEntityBoundingBox().offset(vec.x * 3, 0, vec.z * 3).grow(4D), entity -> !player.equals(entity));
					list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 6));
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!player.isSneaking()) {
			spawnJewel(stack, world, player);
		} else {
			player.getCooldownTracker().setCooldown(this, 30);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	private static void spawnJewel(ItemStack stack, World world, EntityPlayer player) {
		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.PLAYERS, 1F, 1F);
		if(!world.isRemote) {
			EntityDragonJewel jewel = new EntityDragonJewel(world, player);
			jewel.setPosition(player.posX, player.posY + 2, player.posZ);
			world.spawnEntity(jewel);
		}
		if(!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
									  float hitX, float hitY, float hitZ) {
		spawnJewel(player.getHeldItem(hand), world, player);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
