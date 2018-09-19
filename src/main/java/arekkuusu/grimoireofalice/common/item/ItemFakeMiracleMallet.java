/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ItemFakeMiracleMallet extends ItemBase  {

	public ItemFakeMiracleMallet() {
		super(LibName.FAKE_MIRACLE_MALLET);
		setMaxStackSize(1);
		setMaxDamage(10);
		setNoRepair();
		addPropertyOverride(new ResourceLocation("swinging"), (stack, world, entity) -> entity != null && entity.isSwingInProgress ? 1F : 0F);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).getCooldownTracker().hasCooldown(this)) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			Vec3d vec = player.getLookVec();
			if(!player.world.isRemote) {
				List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
						entityLiving.getEntityBoundingBox().offset(vec.x * 2, vec.y * 2, vec.z * 2).grow(3D), entity -> !player.equals(entity));
				if(!list.isEmpty()) {
					list.forEach(entity -> entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), 10F));
					stack.damageItem(1, player);
				}
			}
			player.getCooldownTracker().setCooldown(this, 10);
		}
		entityLiving.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		return super.onEntitySwing(entityLiving, stack);
	}
}
