package techguy.mods.undead.common;

import java.util.Random;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockDimDirt extends Block
{
	public ModUndeadBlockDimDirt (int i, int j)
	{
		super(i, j, Material.ground);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
	
	public int idDropped(int i, Random random, int j)
	{
		return ModUndeadMainRegistry.dimDirt.blockID;
	}
}