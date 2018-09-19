/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuState;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuUpdate;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuUpdateSignal;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntity;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityType;
import net.katsstuff.teamnightclipse.danmakucore.impl.subentity.SubEntityDefault;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import scala.Option;

public class SubEntitySunBullet extends SubEntityType {

	public SubEntitySunBullet() {
		super(LibDanmakuName.SUN);
	}

	@Override
	public SubEntity instantiate() {
		return new Sun();
	}

	public static class Sun extends SubEntityDefault {

		@Override
		public DanmakuUpdate subEntityTick(DanmakuState danmaku) {
			if(!danmaku.world().isRemote) {
				danmaku.world().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(danmaku.pos().toBlockPos()).grow(danmaku.shot().sizeX(), danmaku.shot().sizeY(), danmaku.shot().sizeZ()));
			}
			return super.subEntityTick(danmaku);
		}

		@Override
		public DanmakuUpdate impactBlock(DanmakuState danmaku, RayTraceResult raytrace) {
			return DanmakuUpdate.oneUpdate(danmaku, new DanmakuUpdateSignal() {
				@Override
				public Option<DanmakuState> process(DanmakuState state) {
					EnumFacing facing = raytrace.sideHit;
					Vector3 vec = state.direction();
					Vector3 direction;
					Vector3 multiply;
					if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
						direction = new Vector3(vec.getX(), vec.getY() * -0.8D, vec.getZ());
						multiply = new Vector3(1, -0.5D, 1);
					} else {
						double x = vec.getX();
						double z = vec.getZ();
						if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
							multiply = new Vector3(-0.5D, 1, 1);
							x *= -0.8D;
						} else {
							multiply = new Vector3(1, 1, -0.5D);
							z *= -0.8D;
						}
						direction = new Vector3(x, vec.getY(), z);
					}
					state = state.copy(
							state.entity().copy(
									state.entity().id(),
									state.entity().world(),
									state.entity().ticksExisted(),
									state.entity().renderBrightness(),
									state.entity().pos(),
									state.entity().prevPos(),
									state.entity().orientation(),
									state.entity().prevOrientation(),
									state.entity().motion().multiply(multiply),
									direction,
									state.entity().rawBoundingBoxes(),
									state.entity().rawEncompassingAABB()
							),
							state.extra(),
							state.tracking()
					);
					return Option.apply(state);
				}
			});
		}
	}
}
