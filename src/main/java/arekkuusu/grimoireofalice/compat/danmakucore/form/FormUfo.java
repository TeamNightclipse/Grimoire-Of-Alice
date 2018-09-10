/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.compat.danmakucore.form;

import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibDanmakuName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.teamnightclipse.danmakucore.client.helper.DanCoreRenderHelper;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuState;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.IRenderForm;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.form.RenderingProperty;
import net.katsstuff.teamnightclipse.danmakucore.data.ShotData;
import net.katsstuff.teamnightclipse.danmakucore.impl.form.FormGeneric;
import net.katsstuff.teamnightclipse.mirror.client.shaders.MirrorShaderProgram;
import net.katsstuff.teamnightclipse.mirror.data.Quat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;

public class FormUfo extends FormGeneric {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MOD_ID, "textures/model/entity/barrier.png");

	public FormUfo() {
		super(LibDanmakuName.UFO);
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
				ItemStack stack = new ItemStack(ModItems.UFO); //TODO: Ufo models and colors
				RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
				GlStateManager.rotate((float) (-yaw - 180F), 0F, 1F, 0F);
				GlStateManager.rotate((float) pitch, 1F, 0F, 0F);
				GlStateManager.rotate((float) roll, 0F, 0F, 1F);
				GlStateManager.scale(sizeX, sizeY, sizeZ);
				GlStateManager.rotate(danmaku.ticksExisted() * 32F, 0.0F, 1.0F, 0.0F);
				GlStateManager.color(r, g, b);
				renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
			}

			@Override
			public void renderShaders(DanmakuState danmaku, double x, double y, double z, Quat orientation, float partialTicks, RenderManager manager, MirrorShaderProgram shaderProgram) {
				ShotData shot = danmaku.shot();
				DanCoreRenderHelper.updateDanmakuShaderAttributes(shaderProgram, this, shot);
				renderLegacy(danmaku.copy(
						danmaku.entity(),
						danmaku.extra().copy(
								danmaku.extra().user(),
								danmaku.extra().source(),
								danmaku.extra().shot().copy(
										danmaku.extra().shot().form(),
										danmaku.extra().shot().renderProperties(),
										DanCoreRenderHelper.OverwriteColorEdge(),
										DanCoreRenderHelper.OverwriteColorCore(),
										danmaku.extra().shot().damage(),
										danmaku.extra().shot().sizeX(),
										danmaku.extra().shot().sizeY(),
										danmaku.extra().shot().sizeZ(),
										danmaku.extra().shot().delay(),
										danmaku.extra().shot().end(),
										danmaku.extra().shot().subEntity()
								),
								danmaku.extra().subEntity(),
								danmaku.extra().movement(),
								danmaku.extra().rotation()
						),
						danmaku.tracking()
				), x, y, z, orientation, partialTicks, manager);
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
