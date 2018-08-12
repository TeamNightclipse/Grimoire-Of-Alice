/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuState;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuUpdate;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.ExtraDanmakuData;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntity;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityType;
import net.katsstuff.teamnightclipse.danmakucore.impl.subentity.SubEntityDefault;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SubEntityWind extends SubEntityType {

	public SubEntityWind() {
		setRegistryName(LibDanmakuName.WIND);
	}

	@Override
	public SubEntity instantiate() {
		return new Wind();
	}

	public static class Wind extends SubEntityDefault {

		@Override
		public DanmakuUpdate subEntityTick(DanmakuState danmaku) {
			World world = danmaku.world();
			if(!world.isRemote) {
				BlockPos pos = danmaku.pos().toBlockPos();
				if(!world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
					danmaku.shot().end();
				}
				ExtraDanmakuData current = danmaku.extra();
				current.shot().setDamage(current.shot().getDamage() + current.shot().getDamage() * 0.999F);
			} else if(danmaku.ticksExisted() % 4 == 0 && danmaku.shot().delay() == 0) {
				double posX = danmaku.pos().x();
				double posY = danmaku.pos().y();
				double posZ = danmaku.pos().z();
				world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
				world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.5F, 1F);
			}
			return super.subEntityTick(danmaku);
		}

		@Override
		public DanmakuUpdate impactEntity(DanmakuState danmaku, RayTraceResult raytrace) {
			if(!danmaku.world().isRemote && raytrace.entityHit instanceof EntityLiving) {
				Vector3 mobPos = new Vector3.WrappedVec3d(raytrace.entityHit.getPositionVector()).asImmutable();
				double ratio = danmaku.pos().distance(mobPos) / 4;
				Vector3 motion = danmaku.pos().subtract(mobPos).subtract(1 - ratio);
				raytrace.entityHit.motionX = -motion.x() * danmaku.shot().getDamage() / 2;
				raytrace.entityHit.motionY = danmaku.motion().y();
				raytrace.entityHit.motionZ = -motion.z() * danmaku.shot().getDamage() / 2;
			}
			return super.impactEntity(danmaku, raytrace);
		}
	}
}
