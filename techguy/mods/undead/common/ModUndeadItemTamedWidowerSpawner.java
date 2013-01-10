package techguy.mods.undead.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ModUndeadItemTamedWidowerSpawner extends Item
{
	public ModUndeadItemTamedWidowerSpawner(int par1)
	{
		super(par1);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{

		EntityBoat var35 = new EntityBoat(par2World, (double)((float) 0.5F), (double)((float)1.0F), (double)((float)0.5F));
		var35.rotationYaw = (float)(((MathHelper.floor_double((double)(par3EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);

		return par1ItemStack;
	}
}


//ModUndeadEntityTamedWidower