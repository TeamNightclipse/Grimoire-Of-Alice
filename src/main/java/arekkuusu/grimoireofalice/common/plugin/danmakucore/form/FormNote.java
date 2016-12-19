package arekkuusu.grimoireofalice.common.plugin.danmakucore.form;

import arekkuusu.grimoireofalice.client.model.ModelNote;
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
import org.lwjgl.opengl.GL11;

public class FormNote extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/Note.png");
	@SideOnly(Side.CLIENT)
	private ModelNote MODEL = new ModelNote();

	public FormNote() {
		super(LibFormName.NOTE);
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
				float pitch = danmaku.rotationPitch;
				float yaw = danmaku.rotationYaw;
				float roll = danmaku.getRoll();
				ShotData shotData = danmaku.getShotData();
				float sizeX = shotData.getSizeX() * 2.5F;
				float sizeY = shotData.getSizeY() * 2.5F;
				float sizeZ = shotData.getSizeZ() * 2.5F;
				int color = shotData.getColor();
				float r = (color >> 16 & 255) / 255.0F;
				float g = (color >> 8 & 255) / 255.0F;
				float b = (color & 255) / 255.0F;

				GlStateManager.enableRescaleNormal();
				GlStateManager.rotate(-yaw - 180F, 0F, 1F, 0F);
				GlStateManager.rotate(pitch - 180F, 1F, 0F, 0F);
				GlStateManager.rotate(roll, 0F, 0F, 1F);
				GlStateManager.translate(0.0F, -0.2F, 0.0F);
				GlStateManager.scale(sizeX, sizeY, sizeZ);

				MODEL.renderInsideForm(0.0625F);

				GlStateManager.pushMatrix();
				MODEL.render(danmaku, r, g, b, 0.0F, 0.0F, 0.0625F);
				GlStateManager.popMatrix();

				GlStateManager.disableRescaleNormal();
			}
		};
	}
}
