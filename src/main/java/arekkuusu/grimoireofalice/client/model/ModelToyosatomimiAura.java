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
	    
	  Shape1 = new ModelRenderer(this, 0, 0);
	  Shape1.addBox(-254F, -248F, 0F, 511, 404, 1);
	  Shape1.setRotationPoint(0F, -16F, 10F);
	  Shape1.setTextureSize(1024, 512);
	  Shape1.mirror = true;
	  setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(0.05F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
