package techguy.mods.undead.common;

import techguy.mods.undead.client.ModUndeadGuiSummoningTable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModUndeadBlockSummoningTable extends BlockContainer
{
	public ModUndeadBlockSummoningTable(int i, int j)
	{
		super(i, j, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {

		mc = ModLoader.getMinecraftInstance();
		mc.displayGuiScreen(new ModUndeadGuiSummoningTable(par1World, par5EntityPlayer, mc));
        return true;
    }
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	public boolean isOpaqueCube()
    {
        return false;
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return getBlockTextureFromSide(i);
    }

    public int getBlockTextureFromSide(int i)
    {
        if (i == 0) 
        {
            return 2;
        }
        if (i == 1)
        {
            return 18;
        }
        else
        {
            return 34;
        }
    }

    public TileEntity createNewTileEntity(World var1)
    {
        return new ModUndeadTileEntitySummoningTable();
    }
    
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }
    
    private void setDefaultDirection(World world, int i, int j, int k)
    {

        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
        {
            byte0 = 3;
        }
        if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
        {
            byte0 = 2;
        }
        if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
        {
            byte0 = 5;
        }
        if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
        {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }
    
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
    
	private Minecraft mc;

	
}
