package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNeedle extends ModelBase {
	
    ModelRenderer Body1;
    ModelRenderer Body0;
    ModelRenderer Ring;
  
  public ModelNeedle() {
    textureWidth = 64;
    textureHeight = 32;
    
      Body1 = new ModelRenderer(this, 24, 1);
      Body1.addBox(0F, -0.5F, -10F, 0, 1, 20);
      Body1.setRotationPoint(0.5F, 0F, 0F);
      Body1.setTextureSize(64, 32);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 1.570796F);
      Body0 = new ModelRenderer(this, 0, 0);
      Body0.addBox(0F, -0.5F, -10F, 0, 1, 20);
      Body0.setRotationPoint(0.5F, 0F, 0F);
      Body0.setTextureSize(64, 32);
      Body0.mirror = true;
      setRotation(Body0, 0F, 0F, 0F);
      Ring = new ModelRenderer(this, 29, 23);
      Ring.addBox(0F, -1.5F, -12F, 0, 3, 3);
      Ring.setRotationPoint(0.5F, 0F, 0F);
      Ring.setTextureSize(64, 32);
      Ring.mirror = true;
      setRotation(Ring, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
	super.render(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
	setRotationAngles(limbSwing, limbSwingAmount, age, headYaw, headPitch, scale, entity);
    Body1.render(scale);
    Body0.render(scale);
    Ring.render(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float f, float f1, float f2, float headYaw, float headPitch, float scale, Entity entity) {
    super.setRotationAngles(f, f1, f2, headYaw, headPitch, scale, entity);
  }
  
}

