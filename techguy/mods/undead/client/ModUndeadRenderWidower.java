package techguy.mods.undead.client;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import techguy.mods.undead.common.ModUndeadEntityWidower;

public class ModUndeadRenderWidower extends RenderLiving
{
    public ModUndeadRenderWidower()
    {
        super(new ModelSpider(), 1.0F);
        setRenderPassModel(new ModelSpider());
    }

    protected float setSpiderDeathMaxRotation(ModUndeadEntityWidower entitywidower)
    {
        return 180F;
    }

    protected int setWidowerEyeBrightness(ModUndeadEntityWidower entitywidower, int i, float f)
    {
        if (i != 0)
        {
            return -1;
        }
        else
        {
            loadTexture("/undeadPlus/mob/widower_eyes.png");
            float f1 = 1.0F;
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
            GL11.glBlendFunc(1, 1);
            int j = 61680;
            int k = j % 0x10000;
            int l = j / 0x10000;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)k / 1.0F, (float)l / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return 1;
        }
    }

    protected void scaleSpider(ModUndeadEntityWidower entitywidower, float f)
    {
        float f1 = entitywidower.spiderScaleAmount();
        GL11.glScalef(f1, f1, f1);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        scaleSpider((ModUndeadEntityWidower)entityliving, f);
    }

    protected float getDeathMaxRotation(EntityLiving entityliving)
    {
        return setSpiderDeathMaxRotation((ModUndeadEntityWidower)entityliving);
    }

    protected int shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return setWidowerEyeBrightness((ModUndeadEntityWidower)entityliving, i, f);
    }
}
