package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToyosatomimiAura extends ModelBiped {
  //fields
    ModelRenderer Shape1;

  public ModelToyosatomimiAura() {
	  textureWidth = 1024;
	  textureHeight = 512;

	  Shape1 = new ModelRenderer(this, 1024, 512);
      Shape1.setRotationPoint(0F, -16F, 10F);
	  Shape1.addBox(-254F, -248F, 0F, 511, 404, 1);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.Shape1.render(0.05F);
  }

  public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
      modelRenderer.rotateAngleX = x;
      modelRenderer.rotateAngleY = y;
      modelRenderer.rotateAngleZ = z;
  }

}
