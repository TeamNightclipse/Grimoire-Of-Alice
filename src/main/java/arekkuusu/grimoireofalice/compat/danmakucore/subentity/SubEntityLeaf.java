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
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.util.EnumParticleTypes;
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
			return super.subEntityTick(danmaku).andThen(state -> state.isShotEndTime() ? DanmakuUpdate.oneUpdate(state, $ -> {
						for(int j = 0; j < 8; ++j) {
							danmaku.world().spawnParticle(
									EnumParticleTypes.SMOKE_LARGE,
									danmaku.pos().x(),
									danmaku.pos().y(),
									danmaku.pos().z(),
									0,
									0,
									0
							);
						}
						if(!danmaku.world().isRemote) {
							DanmakuTemplate.Builder builder = DanmakuTemplate.builder()
									.setShot(LibGOAShotData.UFO)
									.setSource($.source())
									.setDirection(Vector3.randomDirection());
							DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
						}
						return Option.apply($);
					}) : DanmakuUpdate.noUpdates(state)
			);
		}
	}
}
