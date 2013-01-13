package techguy.mods.undead.common;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPickupNotifier;

public class ModUndeadPickupHandler implements IPickupNotifier
{

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) 
	{

		if(item.func_92014_d().itemID == ModUndeadMainRegistry.immortiumShard.itemID)
		{		
			player.addStat(ModUndeadMainRegistry.immortiumMine, 1);
		}
	}

}
