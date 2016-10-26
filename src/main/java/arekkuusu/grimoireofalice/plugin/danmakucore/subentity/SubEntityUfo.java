/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.lib.LibSubEntityName;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SubEntityUfo extends SubEntityType {

	public SubEntityUfo() {
		super(LibSubEntityName.UFO);
	}

	@Override
	public SubEntity instantiate(World world, EntityDanmaku entityDanmaku) {
		return new Ufo(world, entityDanmaku);
	}

	public static class Ufo extends SubEntityTypeDefault.SubEntityDefault {

		public Ufo(World world, EntityDanmaku danmaku) {
			super(world, danmaku);
		}

		@Override
		public void subEntityTick() {
			super.subEntityTick();
			if (danmaku.ticksExisted == 10 || danmaku.ticksExisted == 20 || danmaku.ticksExisted == 30 || danmaku.ticksExisted == 40) {
				Vec3d vec = danmaku.getForward().rotatePitch(45F).rotateYaw(45F); //These needs to be negative for some reason
				danmaku.setThrowableHeading(vec.xCoord, vec.yCoord, vec.zCoord, 0.1F, 0.0F);
			}
			if (danmaku.ticksExisted >= 50) {
				danmaku.delete();
			}
		}

		@Override
		protected void impact(RayTraceResult raytrace) {
			super.impact(raytrace);
			for (int j = 0; j < 8; ++j) {
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.danmaku.posX, this.danmaku.posY, this.danmaku.posZ, 0.0D, 0.5D, 0.0D);
			}
			raytrace.entityHit.attackEntityFrom(DamageSource.magic, 10);
		}
	}
}
