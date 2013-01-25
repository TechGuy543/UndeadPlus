package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class ModUndeadBlockDreadWood extends Block
{

	public ModUndeadBlockDreadWood(int i, int j)
	{
		super(i, j, Material.wood);
		setHardness(1.5F);
		setStepSound(Block.soundWoodFootstep);
		setCreativeTab(ModUndeadMainRegistry.undeadCreativeTab);
	}

    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int var10 = par9 & 3;
        byte var11 = 0;

        switch (par5)
        {
            case 0:
            case 1:
                var11 = 0;
                break;
            case 2:
            case 3:
                var11 = 8;
                break;
            case 4:
            case 5:
                var11 = 4;
        }

        return var10 | var11;
    }
    
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? 16 : (var3 == 4 && (par1 == 5 || par1 == 4) ? 16 : (var3 == 8 && (par1 == 2 || par1 == 3) ? 16 : (var4 == 1 ? 116 : (var4 == 2 ? 117 : (var4 == 3 ? 153 : 0)))));
    }
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
    
    public boolean isWood(int x, int y, int z)
    {
    	return true;
    }
	
}

