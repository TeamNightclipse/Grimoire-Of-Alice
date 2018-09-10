/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.form;

import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.danmakucore.client.helper.DanCoreRenderHelper;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuState;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuUpdate;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.IRenderForm;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.RenderingProperty;
import net.katsstuff.teamnightclipse.danmakucore.impl.form.FormGeneric;
import net.katsstuff.teamnightclipse.mirror.client.shaders.MirrorShaderProgram;
import net.katsstuff.teamnightclipse.mirror.data.Quat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;

public class FormWind extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MOD_ID, "textures/model/entity/wind.png");

	public FormWind() {
		super(LibDanmakuName.WIND);
	}

	@Override
	public ResourceLocation getTexture(DanmakuState danmaku) {
		return TEXTURE;
	}

	@Override
	public DanmakuUpdate onTick(DanmakuState danmaku) {
		double posX = danmaku.pos().x();
		double posY = danmaku.pos().y();
		double posZ = danmaku.pos().z();
		danmaku.world().spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
		danmaku.world().playSound(null, posX, posY, posZ, SoundEvents.ITEM_ELYTRA_FLYING, SoundCategory.NEUTRAL, 1.5F, 0F);
		return super.onTick(danmaku);
	}

	@SuppressWarnings("Convert2Lambda")
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderForm createRenderer() {
		return new IRenderForm() {

			@Override
			public void renderLegacy(DanmakuState danmaku, double x, double y, double z, Quat orientation, float partialTicks, RenderManager manager) {
				//NO-OP
			}

			@Override
			public void renderShaders(DanmakuState danmaku, double x, double y, double z, Quat orientation, float partialTicks, RenderManager manager, MirrorShaderProgram shaderProgram) {
				//NO-OP
			}

			@Override
			public Map<String, RenderingProperty> defaultAttributeValues() {
				return Map$.MODULE$.empty();
			}

			@Override
			public ResourceLocation shader(DanmakuState state) {
				return DanCoreRenderHelper.baseDanmakuShaderLoc();
			}
		};
	}
}
