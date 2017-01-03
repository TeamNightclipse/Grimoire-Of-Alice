package arekkuusu.grimoireofalice.common.plugin.danmakucore.form;

import arekkuusu.grimoireofalice.common.lib.LibFormName;
import net.katsstuff.danmakucore.client.helper.RenderHelper;
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.form.IRenderForm;
import net.katsstuff.danmakucore.impl.form.FormGeneric;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

//TODO: Useless? Does exactly the same as FormSphere
public class FormSunBullet extends FormGeneric {

	public FormSunBullet() {
		super(LibFormName.SUN);
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
				float sizeX = shotData.getSizeX();
				float sizeY = shotData.getSizeY();
				float sizeZ = shotData.getSizeZ();
				int color = shotData.getColor();
				float alpha = 0.3F;

				GL11.glRotatef(-yaw, 0F, 1F, 0F);
				GL11.glRotatef(-pitch, 1F, 0F, 0F);
				GL11.glRotatef(roll, 0F, 0F, 1F);
				GL11.glScalef(sizeX, sizeY, sizeZ);

				RenderHelper.drawSphere(0xFFFFFF, 1F);
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				GlStateManager.depthMask(false);
				GlStateManager.scale(1.2F, 1.2F, 1.2F);
				RenderHelper.drawSphere(color, alpha);
				GlStateManager.depthMask(true);
				GlStateManager.disableBlend();
			}
		};
	}
}
