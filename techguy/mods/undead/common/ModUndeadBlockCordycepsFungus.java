package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockCordycepsFungus extends Block
{

	protected ModUndeadBlockCordycepsFungus(int i, int j) 
	{
		super(i, j, Material.plants);
	}
	
	public int getRenderType()
	{
		return 1;
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}

}
