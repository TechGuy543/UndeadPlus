package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModUndeadModelGravestone extends ModelBase
{
	ModelRenderer Shape1;

	public ModUndeadModelGravestone()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 12, 14, 2);
		Shape1.setRotationPoint(-6F, 10F, 4F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
	}


	public void renderModel(float f5)
	{
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
