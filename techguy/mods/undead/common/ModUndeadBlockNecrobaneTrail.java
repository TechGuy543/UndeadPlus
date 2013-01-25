package techguy.mods.undead.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModUndeadBlockNecrobaneTrail extends Block
{
	/**
	 * When false, power transmission methods do not look at other redstone wires. Used internally during
	 * updateCurrentStrength.
	 */
	private boolean wiresProvidePower = true;
	private Set blocksNeedingUpdate = new HashSet();

	public ModUndeadBlockNecrobaneTrail(int par1, int par2)
	{
		super(par1, par2, Material.circuits);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public int getBlockTextureFromSideAndMetadata(int par1, int par2)
	{
		return this.blockIndexInTexture;
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 5;
	}

	@SideOnly(Side.CLIENT)

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
	 * when first determining what to render.
	 */
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return 8388608;
	}

	/**
	 * ecks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 **/
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || par1World.getBlockId(par2, par3 - 1, par4) == Block.glowStone.blockID;
	}


	 /**
	  * Returns the ID of the items to drop on destruction.
	  */
	 public int idDropped(int par1, Random par2Random, int par3)
	 {
		 return Item.redstone.itemID;
	 }

	 @SideOnly(Side.CLIENT)

	 /**
	  * A randomly called display update to be able to add particles or other items for display
	  */
	 public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	 {
		 int var6 = par1World.getBlockMetadata(par2, par3, par4);

		 if (var6 > 0)
		 {
			 double var7 = (double)par2 + 0.5D + ((double)par5Random.nextFloat() - 0.5D) * 0.2D;
			 double var9 = (double)((float)par3 + 0.0625F);
			 double var11 = (double)par4 + 0.5D + ((double)par5Random.nextFloat() - 0.5D) * 0.2D;
			 float var13 = (float)var6 / 15.0F;
			 float var14 = var13 * 0.6F + 0.4F;

			 if (var6 == 0)
			 {
				 var14 = 0.0F;
			 }

			 float var15 = var13 * var13 * 0.7F - 0.5F;
			 float var16 = var13 * var13 * 0.6F - 0.7F;

			 if (var15 < 0.0F)
			 {
				 var15 = 0.0F;
			 }

			 if (var16 < 0.0F)
			 {
				 var16 = 0.0F;
			 }

			 par1World.spawnParticle("reddust", var7, var9, var11, (double)var14, (double)var15, (double)var16);
		 }
	 }

	 @SideOnly(Side.CLIENT)

	 /**
	  * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	  */
	 public int idPicked(World par1World, int par2, int par3, int par4)
	 {
		 return ModUndeadMainRegistry.necrobaneAsh.itemID;
	 }
	 
	 public String getTextureFile()
	 {
		 return "/undeadPlus/terrain.png";
	 }
}
