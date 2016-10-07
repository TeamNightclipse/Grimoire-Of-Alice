/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFlatTexture extends ModelBase {

	private final ModelRenderer texture;

	public ModelFlatTexture() {
		textureWidth = 32;
		textureHeight = 32;

		texture = new ModelRenderer(this, -32, 0);
		texture.addBox(-16F, 0F, -16F, 32, 1, 32);
		texture.setRotationPoint(0F, 0F, 0F);
		texture.setTextureSize(32, 32);
		texture.mirror = true;
		setRotation(texture, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		texture.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
