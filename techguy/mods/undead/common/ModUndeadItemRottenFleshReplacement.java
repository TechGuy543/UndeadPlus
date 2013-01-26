package techguy.mods.undead.common;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class ModUndeadItemRottenFleshReplacement extends ItemFood
{

	
    public ModUndeadItemRottenFleshReplacement(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4);
        this.setCreativeTab(CreativeTabs.tabFood);
    }
    
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        
		ItemStack helmet = par3EntityPlayer.inventory.armorInventory[3];
		ItemStack chestPlate = par3EntityPlayer.inventory.armorInventory[2];
		ItemStack legs = par3EntityPlayer.inventory.armorInventory[1];
		ItemStack boots = par3EntityPlayer.inventory.armorInventory[0];
		if(helmet == null)
		{
			return par1ItemStack;
		}
		if(chestPlate == null)
		{
			return par1ItemStack;
		}
		if(legs == null)
		{
			return par1ItemStack;
		}
		if(boots == null)
		{
			return par1ItemStack;
		}

		if(helmet.itemID == ModUndeadMainRegistry.volatiteHelmet.itemID && chestPlate.itemID == ModUndeadMainRegistry.volatiteChestplate.itemID && legs.itemID == ModUndeadMainRegistry.volatiteLeggings.itemID && boots.itemID == ModUndeadMainRegistry.volatiteBoots.itemID)
		{
			par3EntityPlayer.triggerAchievement(ModUndeadMainRegistry.rottenFlesh);
		}
        
        return par1ItemStack;
    }
}
