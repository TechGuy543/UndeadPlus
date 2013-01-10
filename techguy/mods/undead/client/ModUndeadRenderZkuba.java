package techguy.mods.undead.client;


import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

public class ModUndeadRenderZkuba extends RenderLiving
{
    protected ModUndeadModelZkuba modelZkubaMain;
    protected float field_40296_d;

    public ModUndeadRenderZkuba(ModUndeadModelZkuba par1ModUndeadModelZkuba, float par2)
    {
        this(par1ModUndeadModelZkuba, par2, 1.0F);
        this.modelZkubaMain = par1ModUndeadModelZkuba;
    }

    public ModUndeadRenderZkuba(ModUndeadModelZkuba par1ModUndeadModelZkuba, float par2, float par3)
    {
        super(par1ModUndeadModelZkuba, par2);
        this.modelZkubaMain = par1ModUndeadModelZkuba;
        this.field_40296_d = par3;
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        super.renderEquippedItems(par1EntityLiving, par2);
        ItemStack var3 = par1EntityLiving.getHeldItem();

        if (var3 != null)
        {
            GL11.glPushMatrix();
            this.modelZkubaMain.rightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            float var4;

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(var3, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, var3, BLOCK_3D));

            if (var3.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())))
            {
                var4 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var4 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
            }
            else if (var3.itemID == Item.bow.shiftedIndex)
            {
                var4 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[var3.itemID].isFull3D())
            {
                var4 = 0.625F;
                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                var4 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(var4, var4, var4);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 0);

            if (var3.getItem().requiresMultipleRenderPasses())
            {
                for (int x = 1; x < var3.getItem().getRenderPasses(var3.getItemDamage()); x++)
                {
                    this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, x);
                }
            }

            GL11.glPopMatrix();
        }
    }
}
