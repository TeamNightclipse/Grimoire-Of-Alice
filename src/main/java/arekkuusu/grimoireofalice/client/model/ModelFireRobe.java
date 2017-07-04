/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */

@SideOnly(Side.CLIENT)
public class ModelFireRobe extends ModelBiped {

	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer chest;

	public ModelFireRobe() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leftArm = new ModelRenderer(this, 24, 0);
		this.leftArm.setRotationPoint(5.0F, 2.0F, -0.0F);
		this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 14, 4, 0.25F);
		this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.10000736613927509F);
		this.rightArm = new ModelRenderer(this, 24, 0);
		this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 14, 4, 0.25F);
		this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736613927509F);
		this.chest = new ModelRenderer(this, 0, 0);
		this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chest.addBox(-4.0F, 0.0F, -2.0F, 8, 14, 4, 0.25F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bipedRightArm = rightArm;
		bipedBody = chest;
		bipedLeftArm = leftArm;
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
