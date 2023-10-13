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
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntity;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityType;
import net.katsstuff.teamnightclipse.danmakucore.impl.subentity.SubEntityDefault;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;

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
		        return super.subEntityTick(danmaku)
		                .addCallbackIf(!danmaku.world().isRemote, () -> {
		                    AxisAlignedBB bb = new AxisAlignedBB(danmaku.pos().toBlockPos()).grow(danmaku.shot().sizeX(),
		                            danmaku.shot().sizeY(), danmaku.shot().sizeZ());
		                    danmaku.world().getEntitiesWithinAABB(EntityLivingBase.class, bb).forEach(e -> {
		                        if (!danmaku.user().isEmpty()) {
		                            if (e != danmaku.user().get()) {
		                                e.setFire(5);
		                            }
		                        } else {
		                            e.setFire(5);
		                        }
		                    });
		                });
		}

		@Override
		public DanmakuUpdate impactBlock(DanmakuState state, RayTraceResult raytrace) {
			EnumFacing facing = raytrace.sideHit;
			Vector3 vec = state.direction();
			Vector3 direction;
			if(facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
				direction = new Vector3(vec.getX(), vec.getY() * -0.8D, vec.getZ());
			} else {
				double x = vec.getX();
				double z = vec.getZ();
				if(facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
					x *= -0.8D;
				} else {
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
							state.entity().motion(),
							direction,
							state.entity().rawBoundingBoxes(),
							state.entity().rawEncompassingAABB()
					),
					state.extra(),
					state.tracking()
			);
			return DanmakuUpdate.noUpdates(state);
		}
	}
}
