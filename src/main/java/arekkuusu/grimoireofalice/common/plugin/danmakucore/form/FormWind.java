/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.form;

import arekkuusu.grimoireofalice.client.model.ModelWind;
import arekkuusu.grimoireofalice.common.lib.LibFormName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.form.IRenderForm;
import net.katsstuff.danmakucore.impl.form.FormGeneric;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FormWind extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/Wind.png");
	private ModelBase modelWind = new ModelWind();

	public FormWind() {
		super(LibFormName.WIND);
	}

	@Override
	public ResourceLocation getTexture(EntityDanmaku danmaku) {
		return TEXTURE;
	}

	@SuppressWarnings("Convert2Lambda")
	@Override
	@SideOnly(Side.CLIENT)
	protected IRenderForm createRenderer() {
		return new IRenderForm() {

			@Override
			@SideOnly(Side.CLIENT)
			public void renderForm(EntityDanmaku danmaku, double x, double y, double z, float entityYaw, float partialTicks,
					RenderManager rendermanager) {
				if(modelWind == null) modelWind = new ModelWind();

				float pitch = danmaku.rotationPitch;
				float yaw = danmaku.rotationYaw;
				float roll = danmaku.getRoll();
				ShotData shotData = danmaku.getShotData();
				float sizeX = shotData.getSizeX();
				float sizeY = shotData.getSizeY();
				float sizeZ = shotData.getSizeZ();
				int color = shotData.getColor();
				float r = (color >> 16 & 255) / 255.0F;
				float g = (color >> 8 & 255) / 255.0F;
				float b = (color & 255) / 255.0F;

				GlStateManager.rotate(-yaw - 180F, 0F, 1F, 0F);
				GlStateManager.rotate(pitch - 90F, 1F, 0F, 0F);
				GlStateManager.rotate(roll, 0F, 0F, 1F);
				GlStateManager.scale(sizeX, sizeY, sizeZ);

				GlStateManager.rotate(danmaku.ticksExisted * 64, 0.0F, 1.0F, 0.0F);
				//A bit hacky to pass in the color here, but meh
				modelWind.render(danmaku, r, g, b, 0.0F, 0.0F, 0.0625F);
			}
		};
	}
}
