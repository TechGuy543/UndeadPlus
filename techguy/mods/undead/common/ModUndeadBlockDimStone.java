package techguy.mods.undead.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class ModUndeadBlockDimStone extends Block
{
	public ModUndeadBlockDimStone (int i, int j)
	{
		super(i, j, Material.rock);
	}

	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}

	public int idDropped(int i, Random random, int j)
	{
		return Block.cobblestone.blockID;
	}

	protected boolean canSilkHarvest()
	{
		return true;
	}
	
    public boolean isGenMineableReplaceable(World world, int x, int y, int z)
    {
        return true;
    }
}