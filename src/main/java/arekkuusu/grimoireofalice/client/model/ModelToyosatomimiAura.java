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

public class ModelToyosatomimiAura extends ModelBiped {

	//fields
	private final ModelRenderer aura;

	public ModelToyosatomimiAura() {
		textureWidth = 1024;
		textureHeight = 512;

		aura = new ModelRenderer(this, 1024, 512);
		aura.setRotationPoint(0F, -16F, 10F);
		aura.addBox(-254F, -248F, 0F, 511, 404, 1);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.aura.render(0.05F);
	}

	public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}
