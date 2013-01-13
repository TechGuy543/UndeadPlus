package techguy.mods.undead.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class ModUndeadCraftingHandler implements ICraftingHandler
{

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,	IInventory craftMatrix) 
	{
		if(item.itemID == ModUndeadMainRegistry.crowbar.itemID)
		{
			player.triggerAchievement(ModUndeadMainRegistry.crowbarCraft);
		}
		
		if(item.itemID == ModUndeadMainRegistry.summoningTable.blockID)
		{
			player.triggerAchievement(ModUndeadMainRegistry.summoningCraft);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) 
	{
		
	}

}
