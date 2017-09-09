package arekkuusu.grimoireofalice.common.danmakucore.form;

import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibFormName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.form.IRenderForm;
import net.katsstuff.danmakucore.impl.form.FormGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FormLeaf extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MOD_ID, "textures/models/entities/leaf.png");

	public FormLeaf() {
		super(LibFormName.LEAF);
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
				float sizeX = shotData.getSizeX();
				float sizeY = shotData.getSizeY();
				float sizeZ = shotData.getSizeZ();
				int color = shotData.getColor();
				float r = (color >> 16 & 255) / 255.0F;
				float g = (color >> 8 & 255) / 255.0F;
				float b = (color & 255) / 255.0F;

				ItemStack stack = new ItemStack(ModItems.leaf_item);
				RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

				GlStateManager.enableRescaleNormal();
				GlStateManager.rotate(-yaw - 180F, 0F, 1F, 0F);
				GlStateManager.rotate(pitch, 1F, 0F, 0F);
				GlStateManager.rotate(roll, 0F, 0F, 1F);
				GlStateManager.scale(sizeX, sizeY, sizeZ);
				GlStateManager.rotate(danmaku.ticksExisted * 32F, 0.0F, 1.0F, 0.0F);

				//TODO: color
				renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);

				GlStateManager.disableRescaleNormal();
			}
		};
	}
}