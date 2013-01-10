package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModUndeadModelFeralZombie extends ModelBase
{
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
  
  public ModUndeadModelFeralZombie()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 6, 6, 6);
      head.setRotationPoint(1F, 3F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 13, 17);
      body.addBox(-4F, 0F, -2F, 6, 12, 3);
      body.setRotationPoint(1F, 1F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 32, 18);
      rightarm.addBox(-1F, -2F, -2F, 3, 11, 3); //HERE 1st int - -3F
      rightarm.setRotationPoint(-3F, 3F, 0F);
      rightarm.setTextureSize(64, 32);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 32, 18);
      leftarm.addBox(-2F, -2F, -2F, 3, 11, 3);
      leftarm.setRotationPoint(4F, 3F, 0F);
      leftarm.setTextureSize(64, 32);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 18);
      rightleg.addBox(-2F, 0F, -2F, 3, 11, 3);
      rightleg.setRotationPoint(-1F, 13F, 0F);
      rightleg.setTextureSize(64, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 18);
      leftleg.addBox(-2F, 0F, -2F, 3, 11, 3);
      leftleg.setRotationPoint(2F, 13F, 0F);
      leftleg.setTextureSize(64, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
	  head.rotateAngleY = f3 / 57.29578F;
      head.rotateAngleX = f4 / 57.29578F;
      float f6 = MathHelper.sin(onGround * 3.141593F);
      float f7 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * 3.141593F);
      rightarm.rotateAngleZ = 0.0F;
      leftarm.rotateAngleZ = 0.0F;
      rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
      leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
      rightarm.rotateAngleX = -1.570796F;
      leftarm.rotateAngleX = -1.570796F;
      rightarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
      leftarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
      rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
      leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
      rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
      rightleg.rotateAngleY = 0.0F;
      leftleg.rotateAngleY = 0.0F;
      if (isRiding)
      {
          rightarm.rotateAngleX += -0.6283185F;
          leftarm.rotateAngleX += -0.6283185F;
          rightleg.rotateAngleX = -1.256637F;
          leftleg.rotateAngleX = -1.256637F;
          rightleg.rotateAngleY = 0.3141593F;
          leftleg.rotateAngleY = -0.3141593F;
      }
      rightarm.rotateAngleY = 0.0F;
      leftarm.rotateAngleY = 0.0F;
      if (onGround > -9990F)
      {
          
          body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
          rightarm.rotationPointZ = MathHelper.sin(body.rotateAngleY) * 5F;
          rightarm.rotationPointX = -MathHelper.cos(body.rotateAngleY) * 5F;
          leftarm.rotationPointZ = -MathHelper.sin(body.rotateAngleY) * 5F;
          leftarm.rotationPointX = MathHelper.cos(body.rotateAngleY) * 5F;
          rightarm.rotateAngleY += body.rotateAngleY;
          leftarm.rotateAngleY += body.rotateAngleY;
          leftarm.rotateAngleX += body.rotateAngleY;
          f6 = 1.0F - onGround;
          f6 *= f6;
          f6 *= f6;
          f6 = 1.0F - f6;
          float f8 = MathHelper.sin(f6 * 3.141593F);
          float f10 = MathHelper.sin(onGround * 3.141593F) * -(head.rotateAngleX - 0.7F) * 0.75F;
          rightarm.rotateAngleX -= (double)f8 * 1.2D + (double)f10;
          rightarm.rotateAngleY += body.rotateAngleY * 2.0F;
          rightarm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
      }
      
      
      rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
      rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
      leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
      

}
}
