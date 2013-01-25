package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModUndeadModelBloodshot extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer TailTop;
    ModelRenderer TailMiddle;
    ModelRenderer TailBottom;
    ModelRenderer ArmLeft;
    ModelRenderer ArmRight;
  
  public ModUndeadModelBloodshot()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Head = new ModelRenderer(this, 0, 1);
      Head.addBox(-2.5F, -3F, -1.5F, 5, 5, 3);
      Head.setRotationPoint(0F, 15F, 0F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      TailTop = new ModelRenderer(this, 16, 9);
      TailTop.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
      TailTop.setRotationPoint(0F, 16F, 0F);
      TailTop.setTextureSize(64, 32);
      TailTop.mirror = true;
      setRotation(TailTop, -0.1858931F, 0F, 0F);
      TailMiddle = new ModelRenderer(this, 16, 9);
      TailMiddle.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
      TailMiddle.setRotationPoint(0F, 18.5F, -0.5F);
      TailMiddle.setTextureSize(64, 32);
      TailMiddle.mirror = true;
      setRotation(TailMiddle, 0.2230717F, 0F, 0F);
      TailBottom = new ModelRenderer(this, 16, 9);
      TailBottom.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
      TailBottom.setRotationPoint(0F, 21F, 0F);
      TailBottom.setTextureSize(64, 32);
      TailBottom.mirror = true;
      setRotation(TailBottom, 0.5205006F, 0F, 0F);
      ArmLeft = new ModelRenderer(this, 16, 0);
      ArmLeft.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
      ArmLeft.setRotationPoint(-1F, 17F, 0F);
      ArmLeft.setTextureSize(64, 32);
      ArmLeft.mirror = true;
      setRotation(ArmLeft, 0F, 0F, 0.2268928F);
      ArmRight.mirror = true;
      ArmRight = new ModelRenderer(this, 16, 0);
      ArmRight.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
      ArmRight.setRotationPoint(1F, 17F, 0F);
      ArmRight.setTextureSize(64, 32);
      ArmRight.mirror = true;
      setRotation(ArmRight, 0F, 0F, -0.2268928F);
      ArmRight.mirror = false;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    TailTop.render(f5);
    TailMiddle.render(f5);
    TailBottom.render(f5);
    ArmLeft.render(f5);
    ArmRight.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
  }

}
