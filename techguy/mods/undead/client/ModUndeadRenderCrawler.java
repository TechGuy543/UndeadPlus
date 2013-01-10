package techguy.mods.undead.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class ModUndeadRenderCrawler extends RenderLiving
{
    protected ModUndeadModelCrawler modelCrawlerMain;
    protected float field_40296_d;

    public ModUndeadRenderCrawler(ModUndeadModelCrawler modelcrawler, float f)
    {
        this(modelcrawler, f, 1.0F);
        modelCrawlerMain = modelcrawler;
    }

    public ModUndeadRenderCrawler(ModUndeadModelCrawler modelcrawler, float f, float f1)
    {
        super(modelcrawler, f);
        modelCrawlerMain = modelcrawler;
        field_40296_d = f1;
    }

    protected void renderEquippedItems(EntityLiving entityliving, float f)
    {
        super.renderEquippedItems(entityliving, f);
        ItemStack itemstack = entityliving.getHeldItem();
        if (itemstack != null)
        {
            GL11.glPushMatrix();
            modelCrawlerMain.rightarm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            if (itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
            {
                float f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f1 *= 0.75F;
                GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
            }
            else if (itemstack.itemID == Item.bow.shiftedIndex)
            {
                float f2 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack.itemID].isFull3D())
            {
                float f3 = 0.625F;
                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f3, -f3, f3);
                GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                float f4 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f4, f4, f4);
                GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
            }
            renderManager.itemRenderer.renderItem(entityliving, itemstack, 0);
            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                renderManager.itemRenderer.renderItem(entityliving, itemstack, 1);
            }
            GL11.glPopMatrix();
        }
    }
}


