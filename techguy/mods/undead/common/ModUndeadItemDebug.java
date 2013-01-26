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
		return 9;
	}

	public String getTextureFile()
	{
		return "/undeadPlus/items.png";
	}
}
