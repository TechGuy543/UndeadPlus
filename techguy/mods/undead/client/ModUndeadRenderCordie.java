package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import techguy.mods.undead.common.ModUndeadEntityCordie;
import techguy.mods.undead.common.ModUndeadMainRegistry;

public class ModUndeadRenderCordie extends RenderLiving
{
    public ModUndeadRenderCordie(ModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void func_40273_a(ModUndeadEntityCordie entitycordie, double d, double d1, double d2,
            float f, float f1)
    {
        super.doRenderLiving(entitycordie, d, d1, d2, f, f1);
    }

    protected void func_40272_a(ModUndeadEntityCordie entitycordie, float f)
    {
        super.renderEquippedItems(entitycordie, f);    
        {
        	loadTexture("/undeadPlus/terrain.png");
        	GL11.glEnable(2884 /*GL_CULL_FACE*/);
        	GL11.glPushMatrix();
        	GL11.glScalef(1.0F, -1F, 1.0F);
        	GL11.glTranslatef(0.0F, 0.0F, 0.5F);
        	GL11.glRotatef(42F, 18F, 0.0F, 0.0F);
        	renderBlocks.renderBlockAsItem(ModUndeadMainRegistry.cordycepsFungusBlock, 0, 1.0F);
       	
        	
        	GL11.glTranslatef(0.0F, -0.3F, 0.5F);
        	GL11.glRotatef(42F, 18F, 0.0F, 0.0F);
        	renderBlocks.renderBlockAsItem(ModUndeadMainRegistry.cordycepsFungusBlock, 0, 1.0F);
        	
        	/*((ModelBiped)mainModel).bipedBody.postRender(0.0625F);
        	GL11.glTranslatef(0.1F, 0.0F, -0.6F);
        	GL11.glRotatef(42F, 0.0F, 1.0F, 0.0F);
        	renderBlocks.renderBlockAsItem(mod_Undead.CordycepsFungusBlock, 0, 1.0F);*/
        	GL11.glPopMatrix();
        	GL11.glPushMatrix();
        	
        	/*((ModelBiped)mainModel).bipedHead.postRender(0.0625F);
        	GL11.glScalef(1.0F, -1F, 1.0F);
        	GL11.glTranslatef(0.0F, 1F, 0.F);
        	GL11.glRotatef(12F, 0.0F, 1.0F, 0.0F);
        	renderBlocks.renderBlockAsItem(mod_Undead.cordycepsFungusBlock, 0, 1.0F);*/
        	GL11.glPopMatrix();
        	GL11.glDisable(2884 /*GL_CULL_FACE*/);
        	return;
        }
    }

    protected void renderEquippedItems(EntityLiving entityliving, float f)
    {
        func_40272_a((ModUndeadEntityCordie)entityliving, f);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        func_40273_a((ModUndeadEntityCordie)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        func_40273_a((ModUndeadEntityCordie)entity, d, d1, d2, f, f1);
    }
}

