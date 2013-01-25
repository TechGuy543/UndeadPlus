package techguy.mods.undead.common;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ModUndeadBlockNecrobane extends Block
{
	public ModUndeadBlockNecrobane (int i, int j)
	{
		super(i, j, Material.plants);
		float var4 = 0.22F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}

	public int getRenderType()
	{
		return 1;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canThisPlantGrowOnThisBlockID(par1World.getBlockId(par2, par3 - 1, par4));
	}

	protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return par1 == ModUndeadMainRegistry.dimGrass.blockID || par1 == ModUndeadMainRegistry.dimDirt.blockID;
	}
	
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	if(!par1World.isAirBlock(par2, par3 - 1, par4) && canPlaceBlockAt(par1World, par2, par3, par4))
    	{
            return false;
    	}
		return true;
    }
    
    protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
}
