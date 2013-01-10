package techguy.mods.undead.common;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import techguy.mods.undead.client.ModUndeadModelGravestone;

public class ModUndeadTileEntityGravestoneRenderer extends TileEntitySpecialRenderer 
{

	public ModUndeadTileEntityGravestoneRenderer() 
	{
		model = new ModUndeadModelGravestone();
	}

	public void renderAModelAt(ModUndeadTileEntityGravestone tile, double d, double d1, double d2, float f) 
	{
		int i = 0;
		int j = 0;

		if (tile.worldObj != null)
		{
			i = tile.getBlockMetadata();
		}
		
		if (i == 3)
		{
			j = 0;
		}

		if (i == 5)
		{
			j = 90;
		}

		if (i == 2)
		{
			j = 180;
		}

		if (i == 4)
		{
			j = 270;
		}

		bindTextureByName("/undeadPlus/misc/gravestone.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		model.renderModel(0.0625F);
		GL11.glPopMatrix();

	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) 
	{
		renderAModelAt((ModUndeadTileEntityGravestone) tileentity, d, d1, d2, f);
	}

	private ModUndeadModelGravestone model;
}