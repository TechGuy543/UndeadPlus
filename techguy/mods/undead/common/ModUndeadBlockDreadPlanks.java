package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockDreadPlanks extends Block
{

	public ModUndeadBlockDreadPlanks(int i, int j)
	{
		super(i, j, Material.wood);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
}
