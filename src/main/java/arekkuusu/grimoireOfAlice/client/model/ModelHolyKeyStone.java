/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHolyKeyStone extends ModelBase
{
  //fields
    ModelRenderer Stone1;
    ModelRenderer Stone2;
    ModelRenderer Stone3;
    ModelRenderer Stone4;
    ModelRenderer Stone5;
    ModelRenderer Stone6;
    ModelRenderer String1;
    ModelRenderer String2;
    ModelRenderer String3;
    ModelRenderer String4;
    ModelRenderer String5;
    ModelRenderer String6;
    ModelRenderer String7;
    ModelRenderer String8;
    ModelRenderer String9;
    ModelRenderer String10;
    ModelRenderer String11;
    ModelRenderer String12;
  
  public ModelHolyKeyStone()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Stone1 = new ModelRenderer(this, 16, 17);
      Stone1.addBox(-6F, 2F, -6F, 12, 3, 12);
      Stone1.setRotationPoint(0F, 10F, 0F);
      Stone1.setTextureSize(64, 32);
      Stone1.mirror = true;
      setRotation(Stone1, 0F, 0F, 0F);
      Stone2 = new ModelRenderer(this, 0, 6);
      Stone2.addBox(-5F, 5F, -5F, 10, 1, 10);
      Stone2.setRotationPoint(0F, 10F, 0F);
      Stone2.setTextureSize(64, 32);
      Stone2.mirror = true;
      setRotation(Stone2, 0F, 0F, 0F);
      Stone3 = new ModelRenderer(this, 32, 7);
      Stone3.addBox(-4F, 6F, -4F, 8, 1, 8);
      Stone3.setRotationPoint(0F, 10F, 0F);
      Stone3.setTextureSize(64, 32);
      Stone3.mirror = true;
      setRotation(Stone3, 0F, 0F, 0F);
      Stone4 = new ModelRenderer(this, 40, 0);
      Stone4.addBox(-3F, 7F, -3F, 6, 1, 6);
      Stone4.setRotationPoint(0F, 10F, 0F);
      Stone4.setTextureSize(64, 32);
      Stone4.mirror = true;
      setRotation(Stone4, 0F, 0F, 0F);
      Stone5 = new ModelRenderer(this, 0, 21);
      Stone5.addBox(-2.5F, 8F, -2.5F, 5, 1, 5);
      Stone5.setRotationPoint(0F, 10F, 0F);
      Stone5.setTextureSize(64, 32);
      Stone5.mirror = true;
      setRotation(Stone5, 0F, 0F, 0F);
      Stone6 = new ModelRenderer(this, 0, 27);
      Stone6.addBox(-2F, 9F, -2F, 4, 1, 4);
      Stone6.setRotationPoint(0F, 10F, 0F);
      Stone6.setTextureSize(64, 32);
      Stone6.mirror = true;
      setRotation(Stone6, 0F, 0F, 0F);
      String1 = new ModelRenderer(this, 28, 0);
      String1.addBox(3F, 3F, -5.8F, 2, 6, 0);
      String1.setRotationPoint(0F, 10F, 0F);
      String1.setTextureSize(64, 32);
      String1.mirror = true;
      setRotation(String1, -0.1047198F, 0F, 0F);
      String2 = new ModelRenderer(this, 16, 0);
      String2.addBox(-1F, 3F, -5.8F, 2, 6, 0);
      String2.setRotationPoint(0F, 10F, 0F);
      String2.setTextureSize(64, 32);
      String2.mirror = true;
      setRotation(String2, -0.1047198F, 0F, 0F);
      String3 = new ModelRenderer(this, 4, 0);
      String3.addBox(-5F, 3F, -5.8F, 2, 6, 0);
      String3.setRotationPoint(0F, 10F, 0F);
      String3.setTextureSize(64, 32);
      String3.mirror = true;
      setRotation(String3, -0.1047198F, 0F, 0F);
      String4 = new ModelRenderer(this, 4, 0);
      String4.addBox(3F, 3F, -5.8F, 2, 6, 0);
      String4.setRotationPoint(0F, 10F, 0F);
      String4.setTextureSize(64, 32);
      String4.mirror = true;
      setRotation(String4, -0.1047198F, 1.570796F, 0F);
      String5 = new ModelRenderer(this, 16, 0);
      String5.addBox(-1F, 3F, -5.8F, 2, 6, 0);
      String5.setRotationPoint(0F, 10F, 0F);
      String5.setTextureSize(64, 32);
      String5.mirror = true;
      setRotation(String5, -0.1047198F, 1.570796F, 0F);
      String6 = new ModelRenderer(this, 28, 0);
      String6.addBox(-5F, 3F, -5.8F, 2, 6, 0);
      String6.setRotationPoint(0F, 10F, 0F);
      String6.setTextureSize(64, 32);
      String6.mirror = true;
      setRotation(String6, -0.1047198F, 1.570796F, 0F);
      String7 = new ModelRenderer(this, 16, 0);
      String7.addBox(-5F, 3F, 5.8F, 2, 6, 0);
      String7.setRotationPoint(0F, 10F, 0F);
      String7.setTextureSize(64, 32);
      String7.mirror = true;
      setRotation(String7, 0.1047198F, 0F, 0F);
      String8 = new ModelRenderer(this, 28, 0);
      String8.addBox(-1F, 3F, 5.8F, 2, 6, 0);
      String8.setRotationPoint(0F, 10F, 0F);
      String8.setTextureSize(64, 32);
      String8.mirror = true;
      setRotation(String8, 0.1047198F, 0F, 0F);
      String9 = new ModelRenderer(this, 4, 0);
      String9.addBox(3F, 3F, 5.8F, 2, 6, 0);
      String9.setRotationPoint(0F, 10F, 0F);
      String9.setTextureSize(64, 32);
      String9.mirror = true;
      setRotation(String9, 0.1047198F, 0F, 0F);
      String10 = new ModelRenderer(this, 16, 0);
      String10.addBox(-5F, 3F, 5.8F, 2, 6, 0);
      String10.setRotationPoint(0F, 10F, 0F);
      String10.setTextureSize(64, 32);
      String10.mirror = true;
      setRotation(String10, 0.1047198F, 1.570796F, 0F);
      String11 = new ModelRenderer(this, 4, 0);
      String11.addBox(-1F, 3F, 5.8F, 2, 6, 0);
      String11.setRotationPoint(0F, 10F, 0F);
      String11.setTextureSize(64, 32);
      String11.mirror = true;
      setRotation(String11, 0.1047198F, 1.570796F, 0F);
      String12 = new ModelRenderer(this, 28, 0);
      String12.addBox(3F, 3F, 5.8F, 2, 6, 0);
      String12.setRotationPoint(0F, 10F, 0F);
      String12.setTextureSize(64, 32);
      String12.mirror = true;
      setRotation(String12, 0.1047198F, 1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Stone1.render(f5);
    Stone2.render(f5);
    Stone3.render(f5);
    Stone4.render(f5);
    Stone5.render(f5);
    Stone6.render(f5);
    String1.render(f5);
    String2.render(f5);
    String3.render(f5);
    String4.render(f5);
    String5.render(f5);
    String6.render(f5);
    String7.render(f5);
    String8.render(f5);
    String9.render(f5);
    String10.render(f5);
    String11.render(f5);
    String12.render(f5);
  }
  
  public void renderModel(float f5){
	  
	    Stone1.render(f5);
	    Stone2.render(f5);
	    Stone3.render(f5);
	    Stone4.render(f5);
	    Stone5.render(f5);
	    Stone6.render(f5);
	    String1.render(f5);
	    String2.render(f5);
	    String3.render(f5);
	    String4.render(f5);
	    String5.render(f5);
	    String6.render(f5);
	    String7.render(f5);
	    String8.render(f5);
	    String9.render(f5);
	    String10.render(f5);
	    String11.render(f5);
	    String12.render(f5);
	  
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
