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
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
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
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(3F, 3F, 3F);
		GlStateManager.rotate(rendermanager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rendermanager.playerViewY, 0.0F, 0.0F, 1.0F);
		ItemStack stack = new ItemStack(ModItems.UFOS);
		Minecraft mc = Minecraft.getMinecraft();
		if(stack != null) {
			mc.getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
		}
		GlStateManager.disableRescaleNormal();
	}
}
