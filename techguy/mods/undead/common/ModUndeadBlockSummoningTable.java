package techguy.mods.undead.common;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import techguy.mods.undead.client.ModUndeadGuiSummoningTable;

public class ModUndeadBlockSummoningTable extends BlockContainer
{
	public ModUndeadBlockSummoningTable(int i, int j)
	{
		super(i, j, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		setCreativeTab(ModUndeadMainRegistry.undeadCreativeTab);
		setHardness(4.2F);
		setResistance(10F);
	}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {

		mc = Minecraft.getMinecraft();
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
    
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
    
	private Minecraft mc;

	
}
