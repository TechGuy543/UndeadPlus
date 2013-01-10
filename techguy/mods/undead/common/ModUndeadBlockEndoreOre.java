package techguy.mods.undead.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ModUndeadBlockEndoreOre extends Block
{
	public ModUndeadBlockEndoreOre (int i, int j)
	{
		super(i, j, Material.iron);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
	
	public int idDropped(int i, Random random, int j)
	{
		return Item.enderPearl.shiftedIndex;
	}
}