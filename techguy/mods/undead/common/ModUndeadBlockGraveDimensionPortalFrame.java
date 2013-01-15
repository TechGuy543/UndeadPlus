package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModUndeadBlockGraveDimensionPortalFrame extends Block
{

	public ModUndeadBlockGraveDimensionPortalFrame(int i, int j)
	{
		super(i, j, Material.rock);
		setCreativeTab(ModUndeadMainRegistry.undeadCreativeTab);
		setHardness(1.5F);
		setResistance(10F);
	}

	public String getTextureFile()
	{
		return "/undeadPlus/terrain.png";
	}
}
