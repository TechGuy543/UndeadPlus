package techguy.mods.undead.client;

import techguy.mods.undead.common.ModUndeadEntityMaggot;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class ModUndeadRenderMaggot extends RenderLiving
{
    public ModUndeadRenderMaggot()
    {
        super(new ModelSilverfish(), 0.3F);
    }

    protected float getMaggotDeathRotation(ModUndeadEntityMaggot entitysilverfish)
    {
        return 180F;
    }

    public void renderMaggot(ModUndeadEntityMaggot entitysilverfish, double d, double d1, double d2,
            float f, float f1)
    {
        super.doRenderLiving(entitysilverfish, d, d1, d2, f, f1);
    }

    protected int shouldMaggotRenderPass(ModUndeadEntityMaggot entitysilverfish, int i, float f)
    {
        return -1;
    }

    protected float getDeathMaxRotation(EntityLiving entityliving)
    {
        return getMaggotDeathRotation((ModUndeadEntityMaggot)entityliving);
    }

    protected int shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return shouldMaggotRenderPass((ModUndeadEntityMaggot)entityliving, i, f);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        renderMaggot((ModUndeadEntityMaggot)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        renderMaggot((ModUndeadEntityMaggot)entity, d, d1, d2, f, f1);
    }
}
