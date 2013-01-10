package techguy.mods.undead.common;

import java.util.Random;


import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModUndeadBlockReinforcedDoor extends BlockDoor
{
	protected ModUndeadBlockReinforcedDoor(int par1, Material par2Material)
	{
		super(par1, par2Material);
		blockIndexInTexture = 31;

		float var3 = 0.5F;
		float var4 = 1.0F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par5 != 0 && par5 != 1)
		{
			int var6 = this.getFullMetadata(par1IBlockAccess, par2, par3, par4);
			int var7 = this.blockIndexInTexture;

			if ((var6 & 8) != 0)
			{
				var7 -= 16;
			}

			int var8 = var6 & 3;
			boolean var9 = (var6 & 4) != 0;

			if (!var9)
			{
				if (var8 == 0 && par5 == 5)
				{
					var7 = -var7;
				}
				else if (var8 == 1 && par5 == 3)
				{
					var7 = -var7;
				}
				else if (var8 == 2 && par5 == 4)
				{
					var7 = -var7;
				}
				else if (var8 == 3 && par5 == 2)
				{
					var7 = -var7;
				}

				if ((var6 & 16) != 0)
				{
					var7 = -var7;
				}
			}
			else if (var8 == 0 && par5 == 2)
			{
				var7 = -var7;
			}
			else if (var8 == 1 && par5 == 5)
			{
				var7 = -var7;
			}
			else if (var8 == 2 && par5 == 3)
			{
				var7 = -var7;
			}
			else if (var8 == 3 && par5 == 4)
			{
				var7 = -var7;
			}

			return var7;
		}
		else
		{
			return this.blockIndexInTexture;
		}
	}


	/**
	 * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
	 */
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}

	/**
	 * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
	 * block.
	 */
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{


		int var10 = this.getFullMetadata(par1World, par2, par3, par4);
		int var11 = var10 & 7;
		var11 ^= 4;

		if ((var10 & 8) == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var11);
			par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
		}
		else
		{
			par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, var11);
			par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
		}

		par1World.playAuxSFXAtEntity(par5EntityPlayer, 1003, par2, par3, par4, 0);
		return true;

	}



	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ModUndeadMainRegistry.reinforcedDoorItem.shiftedIndex;
	}

	/**
	 * Returns the full metadata value created by combining the metadata of both blocks the door takes up.
	 */
	public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		boolean var6 = (var5 & 8) != 0;
		int var7;
		int var8;

		if (var6)
		{
			var7 = par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4);
			var8 = var5;
		}
		else
		{
			var7 = var5;
			var8 = par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4);
		}

		boolean var9 = (var8 & 1) != 0;
		int var10 = var7 & 7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
		return var10;
	}

	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j)
	{
		return blockIndexInTexture;
	}
}
