package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockDreadWood extends Block
{

	public ModUndeadBlockDreadWood(int i, int j)
	{
		super(i, j, Material.wood);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j)
	{
		return getBlockTextureFromSide(i);
	}

	public int getBlockTextureFromSide(int i)
	{
		if (i == 0)
		{
			return 16;
		}
		if (i == 1)
		{
			return 16;
		}
		else
		{
			return 0;
		}
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
	
}

