package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockVolatiteOre extends Block
{
	public ModUndeadBlockVolatiteOre (int i, int j)
	{
		super(i, j, Material.iron);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
}