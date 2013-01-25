package techguy.mods.undead.common;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
public class ModUndeadBlockHalfSlab extends BlockHalfSlab
{
	/** The list of the types of step blocks. */
	public ModUndeadBlockHalfSlab(int par1, boolean par2)
	{
		super(par1, par2, Material.wood);
		setLightOpacity(0);
		blockIndexInTexture = ModUndeadMainRegistry.dreadPlanks.blockIndexInTexture;
	}
	/**
	 * Returns the block texture based on the side being looked at. Args: side
	 */
	public int getBlockTextureFromSide(int par1)
	{
		return blockIndexInTexture;
	}
	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ModUndeadMainRegistry.dreadPlankSlabs.blockID;
	}
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		if(par1World.getBlockId(par2, par3 - 1, par4) == ModUndeadMainRegistry.dreadPlankSlabs.blockID)
		{
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			par1World.setBlockWithNotify(par2, par3 - 1, par4, ModUndeadMainRegistry.dreadPlanks.blockID);
		}
	}
	/**
	 * Returns an item stack containing a single instance of the current block type. 'par1' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(ModUndeadMainRegistry.dreadPlankSlabs.blockID, 2, par1 & 7);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
	@Override
	public String getFullSlabName(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

}