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
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SubEntityWind extends SubEntityType {

	public SubEntityWind() {
		setRegistryName(LibSubEntityName.WIND);
	}

	@Override
	public SubEntity instantiate(World world, EntityDanmaku entityDanmaku) {
		return new Wind(world, entityDanmaku);
	}

	public static class Wind extends SubEntityTypeDefault.SubEntityDefault {

		private int timeUsed;

		public Wind(World world, EntityDanmaku danmaku) {
			super(world, danmaku);
			timeUsed = 1; //Prevents divide by 0 stuff
		}

		@Override
		public void subEntityTick() {
			super.subEntityTick();
			World world = danmaku.worldObj;

			if(!world.isRemote) {
				if(danmaku.isInWater() || danmaku.isInLava()) {
					danmaku.setDead();
				}
			}

			if(danmaku.ticksExisted % 4 == 0 && danmaku.getShotData().delay() == 0) {
				double posX = danmaku.posX;
				double posY = danmaku.posY;
				double posZ = danmaku.posZ;
				world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
				world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.5F, 1F);
			}
		}

		@Override
		protected void impactEntity(RayTraceResult result) {
			if(!danmaku.worldObj.isRemote) {
				if(result.entityHit instanceof EntityLiving){
					result.entityHit.attackEntityFrom(DamageSource.magic, timeUsed / 2);
					Vec3d windPos = danmaku.getPositionVector();
					Vec3d mobPos = result.entityHit.getPositionVector();
					double ratio = windPos.distanceTo(mobPos) / 4;
					double scaling = (1 - ratio);
					Vec3d motion = windPos.subtract(mobPos).scale(scaling);
					result.entityHit.motionX = -motion.xCoord * timeUsed / 2;
					result.entityHit.motionY = danmaku.motionY;
					result.entityHit.motionZ = -motion.zCoord * timeUsed / 2;
				}
			}
		}

		public int getTimeUsed() {
			return timeUsed;
		}

		public void setTimeUsed(int timeUsed) {
			this.timeUsed = timeUsed;
			ShotData current = danmaku.getShotData();
			danmaku.setShotData(current.setDamage(current.damage() + timeUsed / 2F));
		}
	}
}
