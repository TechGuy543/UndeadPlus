package techguy.mods.undead.common;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPickupNotifier;

public class ModUndeadPickupHandler implements IPickupNotifier
{

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) 
	{

		if(item.entityId == ModUndeadMainRegistry.immortiumShard.shiftedIndex)
		{
			player.addStat(ModUndeadMainRegistry.immortiumMine, 1);
		}
	}

}
