package techguy.mods.undead.common;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ModUndeadItemAmourCyrite extends ItemArmor implements IArmorTextureProvider
{
	/** Holds the 'base' maxDamage that each armorType have. */
	private static final int[] maxDamageArray = new int[] {11, 16, 15, 13};

	/**
	 * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
	 */
	public final int armorType;

	/** Holds the amount of damage that the armor reduces at full durability. */
	public final int damageReduceAmount;

	/**
	 * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
	 * iron, 3 is diamond and 4 is gold.
	 */
	public final int renderIndex;

	/** The ModUndeadEnumArmourMaterial used for this ItemArmor */
	private final EnumArmorMaterial material;

	public ModUndeadItemAmourCyrite(int par1, EnumArmorMaterial enumArmorMaterial, int par3, int par4)
	{
		super(par1, enumArmorMaterial, par3, par4);
		this.material = enumArmorMaterial;
		this.armorType = par4;
		this.renderIndex = par3;
		this.damageReduceAmount = enumArmorMaterial.getDamageReductionAmount(par4);
		this.setMaxDamage(enumArmorMaterial.getDurability(par4));
		this.maxStackSize = 1;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
		return this.material.getEnchantability();
	}

	/**
	 * Returns the 'max damage' factor array for the armor, each piece of armor have a durability factor (that gets
	 * multiplied by armor material factor)
	 */
	static int[] getMaxDamageArray()
	{
		return maxDamageArray;
	}

	public String getTextureFile()
	{
		return "/undeadPlus/items.png";
	}

	public String getArmorTextureFile(ItemStack itemstack)
	{
		if(itemstack.itemID == ModUndeadMainRegistry.cyriteHelmet.shiftedIndex || itemstack.itemID == ModUndeadMainRegistry.cyriteChestplate.shiftedIndex || itemstack.itemID == ModUndeadMainRegistry.cyriteBoots.shiftedIndex)
		{
			return "/undeadPlus/misc/cyrite_1.png";
		}
		if(itemstack.itemID == ModUndeadMainRegistry.cyriteLeggings.shiftedIndex)
		{
			return "/undeadPlus/misc/cyrite_2.png";
		}
		return "/undeadPlus/misc/cyrite_1.png";
	}
}
