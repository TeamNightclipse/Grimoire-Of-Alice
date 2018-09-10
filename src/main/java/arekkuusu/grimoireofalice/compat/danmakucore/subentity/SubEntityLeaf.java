package arekkuusu.grimoireofalice.compat.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.compat.danmakucore.LibGOAShotData;
import com.google.common.collect.Lists;
import net.katsstuff.teamnightclipse.danmakucore.DanmakuCore;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuState;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuUpdate;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntity;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.subentity.SubEntityType;
import net.katsstuff.teamnightclipse.danmakucore.impl.subentity.SubEntityDefault;
import net.katsstuff.teamnightclipse.danmakucore.lib.data.LibShotData;
import net.katsstuff.teamnightclipse.danmakucore.scalastuff.DanmakuCreationHelper;
import net.katsstuff.teamnightclipse.mirror.data.Quat;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import scala.Option;
import scala.collection.JavaConversions;

public class SubEntityLeaf extends SubEntityType {

	public SubEntityLeaf() {
		super(LibDanmakuName.LEAF);
	}

	@Override
	public SubEntity instantiate() {
		return new Leaf();
	}

	public static class Leaf extends SubEntityDefault {

		@Override
		public DanmakuUpdate subEntityTick(DanmakuState danmaku) {
			if(danmaku.isShotEndTime() && danmaku.world().isRemote) {
				DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
						.setUser(danmaku.user().get())
						.setPos(danmaku.pos())
						.setShot(LibGOAShotData.LEAF.setSize(danmaku.shot().sizeX() * 0.5F))
						.setMovementData(0.1F);
				if(danmaku.shot().sizeX() * 0.5F >= 0.5F) {
					int rings = (int) (4F * (danmaku.shot().sizeX() / 1F));
					DanmakuCreationHelper.createSphereShot(builder.build(), rings, rings, 0, 0);
				}
			}
			return super.subEntityTick(danmaku);
		}
	}
}
