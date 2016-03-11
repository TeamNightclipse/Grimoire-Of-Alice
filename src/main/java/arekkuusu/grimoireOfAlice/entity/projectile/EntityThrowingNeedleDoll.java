/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowingNeedleDoll extends EntityThrowingDoll {

	public EntityThrowingNeedleDoll(World world) {
		super(world);
	}

	public EntityThrowingNeedleDoll(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityThrowingNeedleDoll(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {

		if(mop.entityHit != null) {

			float throwDamage = 5;
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), throwDamage);

			if(mop.entityHit instanceof EntityLivingBase) {
				byte b0 = 0;
				
				switch(worldObj.difficultySetting) {
					case NORMAL:
						b0 = 10;
						break;
					case HARD:
						b0 = 40;
						break;
					default:
						break;
				}

				if(b0 > 0) {
					((EntityLivingBase)mop.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 1));
				}
			}

			for(int l = 0; l < 4; ++l) {
				worldObj.spawnParticle("magicCrit", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			}

			if(!worldObj.isRemote) {
				setDead();
			}
		}
	}
}
