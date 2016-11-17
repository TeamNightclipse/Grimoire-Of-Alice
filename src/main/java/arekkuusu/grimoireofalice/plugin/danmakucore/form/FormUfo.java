/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.form;

import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibFormName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.impl.form.FormGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FormUfo extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/entities/Barrier.png");

	public FormUfo() {
		super(LibFormName.UFO);
	}

	@Override
	public ResourceLocation getTexture(EntityDanmaku danmaku) {
		return TEXTURE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderForm(EntityDanmaku danmaku, double x, double y, double z, float entityYaw, float partialTicks, RenderManager rendermanager) {
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

		GlStateManager.enableRescaleNormal();

		GlStateManager.rotate(-yaw - 180F, 0F, 1F, 0F);
		GlStateManager.rotate(pitch - 90F, 1F, 0F, 0F);
		GlStateManager.rotate(roll, 0F, 0F, 1F);
		GlStateManager.scale(sizeX, sizeY, sizeZ);

		ItemStack stack = new ItemStack(ModItems.UFOS); //TODO: Ufo models
		Minecraft mc = Minecraft.getMinecraft();
		mc.getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
	}
}
