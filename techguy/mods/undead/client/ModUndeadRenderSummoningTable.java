package techguy.mods.undead.client;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import techguy.mods.undead.common.ModUndeadTileEntitySummoningTable;

public class ModUndeadRenderSummoningTable extends TileEntitySpecialRenderer
{
    private ModelBook field_40450_a;

    public ModUndeadRenderSummoningTable()
    {
        field_40450_a = new ModelBook();
    }

    public void func_40449_a(ModUndeadTileEntitySummoningTable par1TileEntitySummoningTable, double par2, double par4, double par6, float par8)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.75F, (float)par6 + 0.5F);
        float f = (float)par1TileEntitySummoningTable.tickCount + par8;
        GL11.glTranslatef(0.0F, 0.1F + MathHelper.sin(f * 0.1F) * 0.01F, 0.0F);
        float f1;

        for (f1 = par1TileEntitySummoningTable.bookRotation2 - par1TileEntitySummoningTable.bookRotationPrev; f1 >= (float)Math.PI; f1 -= ((float)Math.PI * 2F)) { }

        for (; f1 < -(float)Math.PI; f1 += ((float)Math.PI * 2F)) { }

        float f2 = par1TileEntitySummoningTable.bookRotationPrev + f1 * par8;
        GL11.glRotatef((-f2 * 180F) / (float)Math.PI, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(80F, 0.0F, 0.0F, 1.0F);
        bindTextureByName("/undeadPlus/misc/summoningbook.png");
        float f3 = par1TileEntitySummoningTable.pageFlipPrev + (par1TileEntitySummoningTable.pageFlip - par1TileEntitySummoningTable.pageFlipPrev) * par8 + 0.25F;
        float f4 = par1TileEntitySummoningTable.pageFlipPrev + (par1TileEntitySummoningTable.pageFlip - par1TileEntitySummoningTable.pageFlipPrev) * par8 + 0.75F;
        f3 = (f3 - (float)MathHelper.truncateDoubleToInt(f3)) * 1.6F - 0.3F;
        f4 = (f4 - (float)MathHelper.truncateDoubleToInt(f4)) * 1.6F - 0.3F;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f4 < 0.0F)
        {
            f4 = 0.0F;
        }

        if (f3 > 1.0F)
        {
            f3 = 1.0F;
        }

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        float f5 = par1TileEntitySummoningTable.bookSpreadPrev + (par1TileEntitySummoningTable.bookSpread - par1TileEntitySummoningTable.bookSpreadPrev) * par8;
        field_40450_a.render(null, f, f3, f4, f5, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        func_40449_a((ModUndeadTileEntitySummoningTable)par1TileEntity, par2, par4, par6, par8);
    }
}
