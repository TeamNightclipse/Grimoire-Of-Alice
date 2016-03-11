/**
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.world.World;

public abstract class EntityThrowingDoll extends EntityThrowable {

	public EntityThrowingDoll(World world) {
		super(world);
	}

	public EntityThrowingDoll(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityThrowingDoll(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.04F;
	}
}
