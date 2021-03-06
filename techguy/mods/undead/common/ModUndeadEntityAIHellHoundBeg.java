package techguy.mods.undead.common;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModUndeadEntityAIHellHoundBeg extends EntityAIBase
{
    private ModUndeadEntityHellHound theWolf;
    private EntityPlayer field_48348_b;
    private World field_48349_c;
    private float field_48346_d;
    private int field_48347_e;

    public ModUndeadEntityAIHellHoundBeg(ModUndeadEntityHellHound par1EntityHellHound, float par2)
    {
        theWolf = par1EntityHellHound;
        field_48349_c = par1EntityHellHound.worldObj;
        field_48346_d = par2;
        setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        field_48348_b = field_48349_c.getClosestPlayerToEntity(theWolf, field_48346_d);

        if (field_48348_b == null)
        {
            return false;
        }
        else
        {
            return func_48345_a(field_48348_b);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (!field_48348_b.isEntityAlive())
        {
            return false;
        }

        if (theWolf.getDistanceSqToEntity(field_48348_b) > (double)(field_48346_d * field_48346_d))
        {
            return false;
        }
        else
        {
            return field_48347_e > 0 && func_48345_a(field_48348_b);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        theWolf.func_48150_h(true);
        field_48347_e = 40 + theWolf.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        theWolf.func_48150_h(false);
        field_48348_b = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        theWolf.getLookHelper().setLookPosition(field_48348_b.posX, field_48348_b.posY + (double)field_48348_b.getEyeHeight(), field_48348_b.posZ, 10F, theWolf.getVerticalFaceSpeed());
        field_48347_e--;
    }

    private boolean func_48345_a(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (itemstack == null)
        {
            return false;
        }

        if (!theWolf.isTamed() && itemstack.itemID == Item.bone.itemID)
        {
            return true;
        }
        else
        {
            return theWolf.isWheat(itemstack);
        }
    }
}
