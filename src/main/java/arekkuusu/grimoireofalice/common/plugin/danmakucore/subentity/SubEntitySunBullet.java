/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibSubEntityName;
import net.katsstuff.danmakucore.data.MovementData;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
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
		protected void impact(RayTraceResult raytrace) {}

		@Override
		public void impactEntity(RayTraceResult rayTrace) {
			super.impactEntity(rayTrace);
			rayTrace.entityHit.setFire((int)(danmaku.getShotData().damage() * rand.nextInt(3)));
			danmaku.delete();
		}

		@Override
		protected void impactBlock(RayTraceResult raytrace) {
			//TODO: Change MovementData too here?
			EnumFacing facing = raytrace.sideHit;
			if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
				Vec3d vec = danmaku.getLookVec();
				danmaku.setAngle(new Vector3(vec.xCoord, vec.yCoord * -1D, vec.zCoord));
				danmaku.motionY *= -1D;
			}
			else {
				Vec3d vec = danmaku.getLookVec();
				double x = vec.xCoord;
				double z = vec.zCoord;
				if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
					danmaku.motionX *= -1D;
					x *= -1D;
				} else {
					danmaku.motionZ *= -1D;
					z *= -1D;
				}

				danmaku.setAngle(new Vector3(x, vec.yCoord, z));
			}
		}
	}
}
