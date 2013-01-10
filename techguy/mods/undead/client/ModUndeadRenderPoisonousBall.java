package techguy.mods.undead.client;

import java.awt.Dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import techguy.mods.undead.common.ModUndeadEntityPoisonousBall;
import techguy.mods.undead.common.ModUndeadEntityVent;

import cpw.mods.fml.client.ITextureFX;
import cpw.mods.fml.common.registry.IThrowableEntity;

public class ModUndeadRenderPoisonousBall extends Render implements ITextureFX
{
    private float field_40269_a;

    public ModUndeadRenderPoisonousBall(float f)
    {
        field_40269_a = f;
    }

    public void doRenderPoionousBall(ModUndeadEntityPoisonousBall entitypoisonousball, double d, double d1, double d2,
            float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        float f2 = field_40269_a;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        int ball = 9;
        loadTexture("/undeadPlus/items.png");
        Tessellator tessellator = Tessellator.instance;
        float f3 = (float)((ball % 16) * 16 + 0) / 256F;
        float f4 = (float)((ball % 16) * 16 + 16) / 256F;
        float f5 = (float)((ball / 16) * 16 + 0) / 256F;
        float f6 = (float)((ball / 16) * 16 + 16) / 256F;
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
        tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
        tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
        tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
        tessellator.draw();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        doRenderPoionousBall((ModUndeadEntityPoisonousBall)entity, d, d1, d2, f, f1);
    }

	@Override
	public void onTexturePackChanged(RenderEngine engine, ITexturePack texturepack, Dimension dimensions) 
	{
		
	}

	@Override
	public void onTextureDimensionsUpdate(int width, int height) 
	{
		
	}

	@Override
	public void setErrored(boolean errored) 
	{
		
	}

	@Override
	public boolean getErrored() 
	{
		return false;
	}


}
