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
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;


@SideOnly(Side.CLIENT)
public class ModelDoll extends ModelBase{

	  //fields
    ModelRenderer body;
    ModelRenderer neck;
    ModelRenderer leftArm;
    ModelRenderer rightArm;
    ModelRenderer head;
    ModelRenderer skirtTop;
    ModelRenderer skirtMiddleTop;
    ModelRenderer skirtMiddle;
    ModelRenderer skirtBottom;
    ModelRenderer leftLeg;
    ModelRenderer rightLeg;
    ModelRenderer hairBack;
    ModelRenderer leftHair;
    ModelRenderer rightHair;
    ModelRenderer ribbon;
    ModelRenderer ribbonLeft;
    ModelRenderer ribbonRight;
  
  public ModelDoll()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(0F, -1.5F, -1F, 1, 3, 2);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 0, 0);
      neck.addBox(0F, -2.5F, -1F, 1, 1, 2);
      neck.setRotationPoint(0F, 0F, 0F);
      neck.setTextureSize(64, 32);
      neck.mirror = true;
      setRotation(neck, 0F, 0F, 0F);
      leftArm = new ModelRenderer(this, 0, 0);
      leftArm.addBox(-2F, -1.5F, 1F, 4, 1, 1);
      leftArm.setRotationPoint(0F, 0F, 0F);
      leftArm.setTextureSize(64, 32);
      leftArm.mirror = true;
      setRotation(leftArm, 0F, 0.5934119F, 0.6283185F);
      rightArm = new ModelRenderer(this, 0, 0);
      rightArm.addBox(-2F, -1.5F, -2F, 4, 1, 1);
      rightArm.setRotationPoint(0F, 0F, 0F);
      rightArm.setTextureSize(64, 32);
      rightArm.mirror = true;
      setRotation(rightArm, 0F, -0.5934119F, 0.6283185F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-1F, -5.5F, -1.5F, 3, 3, 3);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      skirtTop = new ModelRenderer(this, 0, 0);
      skirtTop.addBox(-1F, 0F, -1.5F, 3, 2, 3);
      skirtTop.setRotationPoint(0F, 0F, 0F);
      skirtTop.setTextureSize(64, 32);
      skirtTop.mirror = true;
      setRotation(skirtTop, 0F, 0F, 0F);
      skirtMiddleTop = new ModelRenderer(this, 0, 0);
      skirtMiddleTop.addBox(-1.5F, 0.75F, -2F, 4, 2, 4);
      skirtMiddleTop.setRotationPoint(0F, 0F, 0F);
      skirtMiddleTop.setTextureSize(64, 32);
      skirtMiddleTop.mirror = true;
      setRotation(skirtMiddleTop, 0F, 0F, 0F);
      skirtMiddle = new ModelRenderer(this, 0, 0);
      skirtMiddle.addBox(-2F, 1.6F, -2.5F, 5, 1, 5);
      skirtMiddle.setRotationPoint(0F, 0F, 0F);
      skirtMiddle.setTextureSize(64, 32);
      skirtMiddle.mirror = true;
      setRotation(skirtMiddle, 0F, 0F, 0F);
      skirtBottom = new ModelRenderer(this, 0, 0);
      skirtBottom.addBox(-2.5F, 2.4F, -3F, 6, 1, 6);
      skirtBottom.setRotationPoint(0F, 0F, 0F);
      skirtBottom.setTextureSize(64, 32);
      skirtBottom.mirror = true;
      setRotation(skirtBottom, 0F, 0F, 0F);
      leftLeg = new ModelRenderer(this, 0, 0);
      leftLeg.addBox(0F, 2F, 0F, 1, 3, 1);
      leftLeg.setRotationPoint(0F, 0F, 0F);
      leftLeg.setTextureSize(64, 32);
      leftLeg.mirror = true;
      setRotation(leftLeg, 0.1396263F, 0F, 0.0698132F);
      rightLeg = new ModelRenderer(this, 0, 0);
      rightLeg.addBox(0F, 1F, -1F, 1, 4, 1);
      rightLeg.setRotationPoint(0F, 0F, 0F);
      rightLeg.setTextureSize(64, 32);
      rightLeg.mirror = true;
      setRotation(rightLeg, -0.1396263F, 0F, 0.0698132F);
      hairBack = new ModelRenderer(this, 0, 0);
      hairBack.addBox(0.3F, -1.62F, -1.5F, 5, 0, 3);
      hairBack.setRotationPoint(0F, 0F, 0F);
      hairBack.setTextureSize(64, 32);
      hairBack.mirror = true;
      setRotation(hairBack, 0F, 0F, -1.466077F);
      leftHair = new ModelRenderer(this, 0, 0);
      leftHair.addBox(1F, -2.1F, -5.3F, 1, 0, 4);
      leftHair.setRotationPoint(0F, 0F, 0F);
      leftHair.setTextureSize(64, 32);
      leftHair.mirror = true;
      setRotation(leftHair, -1.466077F, 0F, 0F);
      rightHair = new ModelRenderer(this, 0, 0);
      rightHair.addBox(1F, -2.1F, 1.3F, 1, 0, 4);
      rightHair.setRotationPoint(0F, 0F, 0F);
      rightHair.setTextureSize(64, 32);
      rightHair.mirror = true;
      setRotation(rightHair, 1.466077F, 0F, 0F);
      ribbon = new ModelRenderer(this, 0, 0);
      ribbon.addBox(0F, -5.8F, -1.5F, 1, 1, 3);
      ribbon.setRotationPoint(0F, 0F, 0F);
      ribbon.setTextureSize(64, 32);
      ribbon.mirror = true;
      setRotation(ribbon, 0F, 0F, 0F);
      ribbonLeft = new ModelRenderer(this, 0, 0);
      ribbonLeft.addBox(0.5F, -8.5F, -0.7F, 0, 3, 1);
      ribbonLeft.setRotationPoint(0F, 0F, 0F);
      ribbonLeft.setTextureSize(64, 32);
      ribbonLeft.mirror = true;
      setRotation(ribbonLeft, -0.1047198F, 0F, 0F);
      ribbonRight = new ModelRenderer(this, 0, 0);
      ribbonRight.addBox(0.5F, -8.5F, 1F, 0, 3, 1);
      ribbonRight.setRotationPoint(0F, 0F, 0F);
      ribbonRight.setTextureSize(64, 32);
      ribbonRight.mirror = true;
      setRotation(ribbonRight, 0.296706F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    body.render(f5);
    neck.render(f5);
    leftArm.render(f5);
    rightArm.render(f5);
    head.render(f5);
    skirtTop.render(f5);
    skirtMiddleTop.render(f5);
    skirtMiddle.render(f5);
    skirtBottom.render(f5);
    leftLeg.render(f5);
    rightLeg.render(f5);
    hairBack.render(f5);
    leftHair.render(f5);
    rightHair.render(f5);
    ribbon.render(f5);
    ribbonLeft.render(f5);
    ribbonRight.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
	
}