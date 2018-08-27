/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.core.helper.MiscHelper;
import arekkuusu.grimoireofalice.common.entity.EntityNetherSoul;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class ItemSwordOfKusanagi extends ItemSwordOwner  {

	public ItemSwordOfKusanagi(ToolMaterial material) {
		super(material, LibName.SWORD_OF_KUSANAGI);
		setNoRepair();
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer && ((EntityPlayer) entityLiving).getCooldownTracker().hasCooldown(this)) {
			return false;
		}
		entityLiving.playSound(GrimoireSoundEvents.ATTTACK_LONG, 0.2F, itemRand.nextFloat() * 0.4F + 0.8F);
		if(!entityLiving.world.isRemote) {
			Entity lookAt = Vector3.getEntityLookedAt(entityLiving, Entity::canBeCollidedWith, 50D).orElse(null);
			EntityNetherSoul entityNetherSoul = new EntityNetherSoul(entityLiving.world, entityLiving, lookAt);
			entityLiving.world.spawnEntity(entityNetherSoul);
			entityNetherSoul.shoot(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0, 0.1F, 0);
		}
		if(entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 15);
		}
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(isOwner(stack, player)) {
				int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
				float convert = timeUsed * 6 / 20F;
				convert = (convert * convert + convert * 2.0F) / 3F;
				convert *= 1.5F;
				if(convert > 10F) {
					return;
				}
				for(int t = 0; t < convert; t++) {
					for(int u = 0; u < 10; u++) {
						spawnSmoke(player, itemRand.nextDouble(), itemRand.nextDouble());
						spawnSmoke(player, -itemRand.nextDouble(), -itemRand.nextDouble());
						spawnSmoke(player, itemRand.nextDouble(), -itemRand.nextDouble());
						spawnSmoke(player, -itemRand.nextDouble(), itemRand.nextDouble());
					}
				}
				if(!world.isRemote) {
					List<EntityMob> list = world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(4.0D));
					for(EntityMob mob : list) {
						mob.attackEntityFrom(DamageSource.MAGIC, convert);
						MiscHelper.pushEntity(player, mob);
					}
				}
			}
			world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERDRAGON_SHOOT, SoundCategory.PLAYERS, 1F, 1F);
		}
	}

	private static void spawnSmoke(EntityPlayer player, double xVelocity, double zVelocity) {
		player.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, xVelocity, 0, zVelocity);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72_000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
