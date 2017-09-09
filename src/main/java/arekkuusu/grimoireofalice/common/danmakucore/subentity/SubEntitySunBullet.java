/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibSubEntityName;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SubEntitySunBullet extends SubEntityType {

	public SubEntitySunBullet() {
		super(LibSubEntityName.SUN);
	}

	@Override
	public SubEntity instantiate(World world, EntityDanmaku entityDanmaku) {
		return new Sun(world, entityDanmaku);
	}

	public static class Sun extends SubEntityTypeDefault.SubEntityDefault {

		public Sun(World world, EntityDanmaku danmaku) {
			super(world, danmaku);
		}

		@Override
		public void impactEntity(RayTraceResult rayTrace) {
			super.impactEntity(rayTrace);
			rayTrace.entityHit.setFire((int) (danmaku.getShotData().damage() * rand.nextInt(3)));
			danmaku.delete();
		}

		@Override
		protected void impactBlock(RayTraceResult raytrace) {
			EnumFacing facing = raytrace.sideHit;
			Vector3 vec = danmaku.getDirection();
			if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
				danmaku.setDirection(new Vector3(vec.getX(), vec.getY() * -1D, vec.getZ()));
				danmaku.motionY *= -0.5D;
			}
			else {
				double x = vec.getX();
				double z = vec.getZ();
				if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
					danmaku.motionX *= -0.5D;
					x *= -1D;
				}
				else {
					danmaku.motionZ *= -0.5D;
					z *= -1D;
				}

				danmaku.setDirection(new Vector3(x, vec.getY(), z));
			}
		}
	}
}
