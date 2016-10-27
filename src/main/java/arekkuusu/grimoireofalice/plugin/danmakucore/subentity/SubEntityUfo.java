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
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
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
			if(danmaku.ticksExisted % 10 == 0) {
				danmaku.setAngle(danmaku.getAngle().rotate(Quat.eulerToQuat(45F, 45F, 0F)));
			}
		}

		@Override
		protected void impact(RayTraceResult raytrace) {
			super.impact(raytrace);
			for(int j = 0; j < 8; ++j) {
				world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, danmaku.posX, danmaku.posY, danmaku.posZ, 0.0D, 0.5D, 0.0D);
			}
		}
	}
}
