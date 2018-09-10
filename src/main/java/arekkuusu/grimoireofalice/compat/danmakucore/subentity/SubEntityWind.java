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
				world.getEntitiesInAABBexcluding(danmaku.source().get(), danmaku.encompassingAABB(), e -> e instanceof EntityLiving).forEach(e -> {
					Vector3 vec = new Vector3.WrappedVec3d(e.getPositionVector()).asImmutable();
					double ratio = danmaku.pos().distance(vec) / 4;
					Vector3 motion = danmaku.pos().subtract(vec).subtract(1 - ratio);
					e.motionX = -motion.x() * danmaku.shot().getDamage() / 2;
					e.motionY = danmaku.motion().y();
					e.motionZ = -motion.z() * danmaku.shot().getDamage() / 2;
				});
			}
			return super.subEntityTick(danmaku);
		}

		@Override
		public DanmakuUpdate impactEntity(DanmakuState danmaku, RayTraceResult raytrace) {
			return DanmakuUpdate.noUpdates(danmaku);
		}
	}
}
