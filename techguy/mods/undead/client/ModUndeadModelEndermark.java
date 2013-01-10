package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModUndeadModelEndermark extends ModelBase
{
	ModelRenderer Headwear;
	ModelRenderer Body;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;

	public boolean aimedBow;
	
	public ModUndeadModelEndermark()
	{
		textureWidth = 64;
		textureHeight = 32;

		Headwear = new ModelRenderer(this, 0, 16);
		Headwear.addBox(-4F, -8F, -4F, 8, 8, 8);
		Headwear.setRotationPoint(0F, -6F, 0F);
		Headwear.setTextureSize(64, 32);
		Headwear.mirror = true;
		setRotation(Headwear, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 32, 16);
		Body.addBox(-4F, 0F, -2F, 8, 12, 4);
		Body.setRotationPoint(0F, -6F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 56, 0);
		RightArm.addBox(-1F, -2F, -1F, 2, 18, 2);
		RightArm.setRotationPoint(-5F, -4F, 0F);
		RightArm.setTextureSize(64, 32);
		RightArm.mirror = true;
		setRotation(RightArm, 0F, 0F, 0F);
		LeftArm = new ModelRenderer(this, 56, 0);
		LeftArm.addBox(-1F, -2F, -1F, 2, 18, 2);
		LeftArm.setRotationPoint(5F, -4F, 0F);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0F, 0F, 0F);
		LeftArm.mirror = false;
		RightLeg = new ModelRenderer(this, 56, 0);
		RightLeg.addBox(-1F, 0F, -1F, 2, 18, 2);
		RightLeg.setRotationPoint(-2F, 6F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 56, 0);
		LeftLeg.addBox(-1F, 0F, -1F, 2, 18, 2);
		LeftLeg.setRotationPoint(2F, 6F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		LeftLeg.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Headwear.render(f5);
		Body.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		aimedBow = true;
		Headwear.rotateAngleY = f3 / 57.29578F;
		Headwear.rotateAngleX = f4 / 57.29578F;
		RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
		LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		RightArm.rotateAngleZ = 0.0F;
		LeftArm.rotateAngleZ = 0.0F;
		RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
		RightLeg.rotateAngleY = 0.0F;
		LeftLeg.rotateAngleY = 0.0F;
		if (isRiding)
		{
			RightArm.rotateAngleX += -0.6283185F;
			LeftArm.rotateAngleX += -0.6283185F;
			RightLeg.rotateAngleX = -1.256637F;
			LeftLeg.rotateAngleX = -1.256637F;
			RightLeg.rotateAngleY = 0.3141593F;
			LeftLeg.rotateAngleY = -0.3141593F;
		}
		RightArm.rotateAngleY = 0.0F;
		LeftArm.rotateAngleY = 0.0F;
		if (onGround > -9990F)
		{
			float f6 = onGround;
			Body.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
			RightArm.rotationPointZ = MathHelper.sin(Body.rotateAngleY) * 5F;
			RightArm.rotationPointX = -MathHelper.cos(Body.rotateAngleY) * 5F;
			LeftArm.rotationPointZ = -MathHelper.sin(Body.rotateAngleY) * 5F;
			LeftArm.rotationPointX = MathHelper.cos(Body.rotateAngleY) * 5F;
			RightArm.rotateAngleY += Body.rotateAngleY;
			LeftArm.rotateAngleY += Body.rotateAngleY;
			LeftArm.rotateAngleX += Body.rotateAngleY;
			f6 = 1.0F - onGround;
			f6 *= f6;
			f6 *= f6;
			f6 = 1.0F - f6;
			float f8 = MathHelper.sin(f6 * 3.141593F);
			float f10 = MathHelper.sin(onGround * 3.141593F) * -(Headwear.rotateAngleX - 0.7F) * 0.75F;
			RightArm.rotateAngleX -= (double)f8 * 1.2D + (double)f10;
			RightArm.rotateAngleY += Body.rotateAngleY * 2.0F;
			RightArm.rotateAngleZ = MathHelper.sin(onGround * 3.141593F) * -0.4F;
		}


		RightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		LeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		RightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		LeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
        	float var7;
        	float var8;
            var7 = 0.0F;
            var8 = 0.0F;
            this.RightArm.rotateAngleZ = 0.0F;
            this.LeftArm.rotateAngleZ = 0.0F;
            this.RightArm.rotateAngleY = -(0.1F - var7 * 0.6F) + this.Headwear.rotateAngleY;
            this.LeftArm.rotateAngleY = 0.1F - var7 * 0.6F + this.Headwear.rotateAngleY + 0.4F;
            this.RightArm.rotateAngleX = -((float)Math.PI / 2F) + this.Headwear.rotateAngleX;
            this.LeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.Headwear.rotateAngleX;
            this.RightArm.rotateAngleX -= var7 * 1.2F - var8 * 0.4F;
            this.LeftArm.rotateAngleX -= var7 * 1.2F - var8 * 0.4F;
            this.RightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
            this.LeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
            this.RightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
            this.LeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        }

	}

}
