package arekkuusu.grimoireofalice.common.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibSubEntityName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.LibGOAShotData;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class SubEntityLeaf extends SubEntityType {

	public SubEntityLeaf() {
		super(LibSubEntityName.LEAF);
	}

	@Override
	public SubEntity instantiate(World world, EntityDanmaku entityDanmaku) {
		return new Leaf(world, entityDanmaku);
	}

	public static class Leaf extends SubEntityTypeDefault.SubEntityDefault {

		public Leaf(World world, EntityDanmaku danmaku) {
			super(world, danmaku);
		}

		@Override
		public void subEntityTick() {
			super.subEntityTick();

			if (danmaku.isShotEndTime()) {
				for (int j = 0; j < 8; ++j) {
					danmaku.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, danmaku.posX, danmaku.posY, danmaku.posZ, 0, 0, 0);
				}

				if(!world.isRemote) {
					DanmakuTemplate.Builder newDanmaku = DanmakuTemplate.builder().setShot(LibGOAShotData.UFO).setSource(this.danmaku);
					newDanmaku.setAngle(Vector3.angleRandom());
					world.spawnEntityInWorld(newDanmaku.build().asEntity());
					danmaku.setDead();
				}
			}
		}
	}
}