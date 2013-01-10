package techguy.mods.undead.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ModUndeadItemKrelickBerry extends ItemFood
{
	public ModUndeadItemKrelickBerry(int i, int j, boolean b)
	{
		super(i, j, b);
	}
	
	public String getTextureFile()
	{
		return "/undeadPlus/items.png";
	}
	
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	--par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
    	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 3 * 20, 0));
        return par1ItemStack;
    }
    

}
