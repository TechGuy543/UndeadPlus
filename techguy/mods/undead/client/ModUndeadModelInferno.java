package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModUndeadModelInferno extends ModelBase
{
  //fields
    ModelRenderer Shape1;
  
  public ModUndeadModelInferno()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-11.5F, -11.5F, -11.5F, 23, 23, 23);
      Shape1.setRotationPoint(0F, 12.5F, 0F);
      Shape1.setTextureSize(128, 128);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par6)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, par6);
  }

}