package techguy.mods.undead.common;

import net.minecraft.item.Item;

public class ModUndeadItemDebug extends Item
{

	public ModUndeadItemDebug(int i)
	{
		super(i);
	}

	public int getIconFromDamage(int par1)
	{

		if (par1 == 1)
		{
			return 9;
		}
		if(par1 == 2)
		{
			return 4;
		}
		return 10;
	}

	public String getTextureFile()
	{
		return "/undeadPlus/items.png";
	}
}
