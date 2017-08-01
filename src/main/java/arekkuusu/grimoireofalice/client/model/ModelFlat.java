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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFlat extends ModelBase {

	private final ModelRenderer flat;

	public ModelFlat() {
		textureWidth = 32;
		textureHeight = 32;

		this.textureWidth = 32;
		this.textureHeight = 32;
		this.flat = new ModelRenderer(this, -32, 0);
		this.flat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.flat.addBox(-16.0F, 0.0F, -16.0F, 32, 0, 32, 0.0F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.flat.render(f5);
	}
}
