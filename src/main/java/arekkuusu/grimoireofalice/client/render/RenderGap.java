/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ShaderLibrary;
import arekkuusu.grimoireofalice.client.render.model.ModelFlat;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.client.util.helper.BlendHelper;
import arekkuusu.grimoireofalice.common.entity.EntityGap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGap extends Render<EntityGap> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderGap(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityGap entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
		float maxUpAndDown = 0.05F;
		float speed = 2;
		float angle = 0;
		float toDegrees = (float) Math.PI / 180F;
		angle += speed * entity.ticksExisted;
		if(angle > 360) {
			angle -= 360;
		}
		GlStateManager.translate(0, maxUpAndDown * MathHelper.sin(angle * toDegrees), 0);
		GlStateManager.rotate(entity.rotationYaw + 90, 0F, 1F, 0F);
		GlStateManager.rotate(entity.rotationPitch + 90F, 0F, 0F, 1F);
		bindEntityTexture(entity);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		if(entity.getColor() != EnumDyeColor.WHITE) {
			float[] afloat = EntitySheep.getDyeRgb(entity.getColor());
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
		}
		ShaderLibrary.BRIGHT.begin();
		ShaderLibrary.BRIGHT.getUniformJ("brightness").ifPresent(b -> {
			b.set(0F);
			b.upload();
		});
		bindTexture(ResourceLibrary.GAP_COLOR);
		MODEL.render(entity, 0,0,0,0,0, 0.0625F);
		ShaderLibrary.BRIGHT.end();
		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGap entity) {
		return ResourceLibrary.GAP;
	}
}
