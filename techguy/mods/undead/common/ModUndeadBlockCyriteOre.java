package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockCyriteOre extends Block 
{
	public ModUndeadBlockCyriteOre (int i, int j)
	{
		super(i, j, Material.rock);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
}