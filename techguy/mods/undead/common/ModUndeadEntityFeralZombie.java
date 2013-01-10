package techguy.mods.undead.common;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityFeralZombie extends EntityMob
{
	public ModUndeadEntityFeralZombie(World world)
	{
		super(world);
		moveSpeed *= 0.5F;
		isImmuneToFire = false;
		texture = "/undeadPlus/mob/feral.png";
		//attackStrength = 4;
		experienceValue = 4;
	}

    public int func_82193_c(Entity par1Entity)
    {
        return 4;
    }
	
    protected void attackEntity(Entity par1Entity, float par2)
    {
        float f = getBrightness(1.0F);

        if (f > 0.5F && rand.nextInt(100) == 0)
        {
            entityToAttack = null;
            return;
        }

        if (par2 > 2.0F && par2 < 6F && rand.nextInt(10) == 0)
        {
            if (onGround)
            {
                double d = par1Entity.posX - posX;
                double d1 = par1Entity.posZ - posZ;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f1) * 0.5D * 0.8D + motionX * 0.2D;
                motionZ = (d1 / (double)f1) * 0.5D * 0.8D + motionZ * 0.2D;
                motionY = 0.4D;
            }
        }
        else
        {
            super.attackEntity(par1Entity, par2);
        }
    }

	public int getMaxHealth()
	{
		return 16;
	}

	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte)0));
	}

    public void onLivingUpdate()
    {
        if (worldObj.isDaytime() && !worldObj.isRemote)
        {
            float f = getBrightness(1.0F);
            if (f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                setFire(8);
            }
        }
        super.onLivingUpdate();
    }

	public void onUpdate()
	{
		super.onUpdate();
		if (!worldObj.isRemote)
		{
			func_40148_a(isCollidedHorizontally);
		}
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}

	public boolean isOnLadder()
	{
		return func_40149_l_();
	}

	public boolean func_40149_l_()
	{
		return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void func_40148_a(boolean flag)
	{
		byte byte0 = dataWatcher.getWatchableObjectByte(16);
		if (flag)
		{
			byte0 |= 1;
		}
		else
		{
			byte0 &= 0xfe;
		}
		dataWatcher.updateObject(16, Byte.valueOf(byte0));
	}
	
	/*protected void fall(float f)
    {
    }*/
	
	protected String getLivingSound()
    {
        return "undeadPlusAudio.mobs.feral.growl";
    }

    protected String getHurtSound()
    {
        return "undeadPlusAudio.mobs.feral.hit";
    }

    protected String getDeathSound()
    {
        return "undeadPlusAudio.mobs.feral.die";
    }
    
    protected int getDropItemId()
    {
        return Item.arrow.shiftedIndex;
    }
    
    protected void dropRareDrop(int i)
    {
    	ItemStack var2 = new ItemStack(Item.bootsLeather);
    	var2.addEnchantment(Enchantment.featherFalling, 1);
    	this.entityDropItem(var2, 0F);
    }

}