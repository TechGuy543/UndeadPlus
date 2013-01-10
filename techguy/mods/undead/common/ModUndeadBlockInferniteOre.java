package techguy.mods.undead.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ModUndeadBlockInferniteOre extends Block
{
	public ModUndeadBlockInferniteOre (int i, int j)
	{
		super(i, j, Material.iron);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
	
	public int idDropped(int i, Random random, int j)
	{
		return Item.blazePowder.shiftedIndex;
	}
	
	public int quantityDropped(Random random)
	{
		return  1 + random.nextInt(1);
	}
}