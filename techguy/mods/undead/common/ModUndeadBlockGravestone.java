package techguy.mods.undead.common;
import java.util.Random;


import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
public class ModUndeadBlockGravestone extends BlockContainer implements ISimpleBlockRenderingHandler
{

	public ModUndeadBlockGravestone(int i, int j, Class class1) 
	{
		super(i, j, Material.cloth);
		gravestoneClass = class1;
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Block.cobblestone.blockID;
	}

	public int quantityDropped(Random random) 
	{
		return random.nextInt(2);
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	public int getRenderType() 
	{
		return ModUndeadMainRegistry.gravestoneRenderID;
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		int i = MathHelper.floor_double((double)((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		if (i == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
		}

		if (i == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
		}

		if (i == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
		}

		if (i == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
		}
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l)
	{
		EntityPlayer par5EntityPlayer = ModLoader.getMinecraftInstance().thePlayer;
		int p = MathHelper.floor_double((double)((par5EntityPlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3; //this is a smart equation

		byte byte0 = 3;


		if (p == 0)
		{
			byte0 = 4;
		}
		if (p == 1)
		{
			byte0 = 3;
		}
		if (p == 2)
		{
			byte0 = 2;
		}
		if (p == 3)
		{
			byte0 = 1;
		}

		world.setBlockMetadataWithNotify(i, j, k, byte0);

	}

	public boolean canPlaceBlockAt(World par1World, int i, int j, int k)
	{
		int l = par1World.getBlockId(i, j - 1, k);
		if(l != 0)
		{
			return true;
		}
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		float f = 0.125F;

		if (i == 2)
		{
			setBlockBounds(0.0F, 0.0F, 1.0F - f, 0.8F, 0.8F, 0.8F);
		}

		if (i == 3)
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		}

		if (i == 4)
		{
			setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (i == 5)
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		}

		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		float f = 0.125F;

		if (i == 2)
		{
			setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		}

		if (i == 3)
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		}

		if (i == 4)
		{
			setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (i == 5)
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		}

		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	private Class gravestoneClass;

	public TileEntity createNewTileEntity(World var1) 
	{
		return new ModUndeadTileEntityGravestone();
	}

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
			TileEntityRenderer.instance.renderTileEntityAt(new ModUndeadTileEntityGravestone(), 0.0D, 0.0D, 0.0D, 0.0F);
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		return false;
	}

	public boolean shouldRender3DInInventory() 
	{
		return true;
	}
	
	public int getRenderId() 
	{
		return 0;
	}

}