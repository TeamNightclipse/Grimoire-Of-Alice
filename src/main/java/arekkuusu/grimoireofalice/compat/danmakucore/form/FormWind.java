/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.form;

import arekkuusu.grimoireofalice.client.render.model.ModelWind;
import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuState;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.IRenderForm;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.RenderingProperty;
import net.katsstuff.teamnightclipse.danmakucore.data.ShotData;
import net.katsstuff.teamnightclipse.danmakucore.impl.form.FormGeneric;
import net.katsstuff.teamnightclipse.mirror.client.shaders.MirrorShaderProgram;
import net.katsstuff.teamnightclipse.mirror.data.Quat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;

public class FormWind extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MOD_ID, "textures/models/entities/Wind.png");
	private ModelBase modelWind = new ModelWind();

	public FormWind() {
		super(LibDanmakuName.WIND);
	}

	@Override
	public ResourceLocation getTexture(DanmakuState danmaku) {
		return TEXTURE;
	}

	@SuppressWarnings("Convert2Lambda")
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderForm createRenderer() {
		return new IRenderForm() {

			@Override
			public void renderLegacy(DanmakuState danmaku, double x, double y, double z, Quat orientation, float partialTicks, RenderManager manager) {
				double pitch = orientation.pitch();
				double yaw = orientation.yaw();
				double roll = orientation.roll();
				ShotData shotData = danmaku.shot();
				float sizeX = shotData.getSizeX();
				float sizeY = shotData.getSizeY();
				float sizeZ = shotData.getSizeZ();
				int color = shotData.coreColor();
				float r = (color >> 16 & 255) / 255.0F;
				float g = (color >> 8 & 255) / 255.0F;
				float b = (color & 255) / 255.0F;
				GlStateManager.rotate((float) (-yaw - 180F), 0F, 1F, 0F);
				GlStateManager.rotate((float) (pitch - 90F), 1F, 0F, 0F);
				GlStateManager.rotate((float) roll, 0F, 0F, 1F);
				GlStateManager.scale(sizeX, sizeY, sizeZ);
				GlStateManager.rotate(danmaku.ticksExisted() * 64F, 0.0F, 1.0F, 0.0F);
				modelWind.render(null, r, g, b, 0.0F, 0.0F, 0.0625F);
			}

			@Override
			public void renderShaders(DanmakuState danmaku, double x, double y, double z, Quat orientation, float partialTicks, RenderManager manager, MirrorShaderProgram shaderProgram) {
				//NO-OP
			}

			@Override
			public Map<String, RenderingProperty> defaultAttributeValues() {
				return Map$.MODULE$.empty();
			}
		};
	}
}
