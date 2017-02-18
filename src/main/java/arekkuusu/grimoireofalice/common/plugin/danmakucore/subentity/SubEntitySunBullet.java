package arekkuusu.grimoireofalice.common.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibSubEntityName;
import net.katsstuff.danmakucore.data.MovementData;
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
				danmaku.rotationYaw *= -1.0F;
				danmaku.rotationPitch *= -1.0F;
				danmaku.motionX *= 0.8F;
				danmaku.motionY *= -0.8F;
				danmaku.motionZ *= 0.8F;
			}
			else {
				danmaku.rotationYaw *= -1.0F;
				danmaku.motionX *= -0.8F;
				danmaku.motionZ *= -0.8F;
			}
		}
	}
}
