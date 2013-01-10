package techguy.mods.undead.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ModUndeadRenderGravestone implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
		/*if (metadata == mod_Undead.gravestone.getRenderType())
		{
			TileEntityRenderer.instance.renderTileEntityAt(new ModUndeadTileEntityGravestone(), 0.0D, 0.0D, 0.0D, 0.0F);
		}*/
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		return true;
	}

	public boolean shouldRender3DInInventory() 
	{
		return false;
	}

	public int getRenderId() 
	{
		return 0;
	}

}
