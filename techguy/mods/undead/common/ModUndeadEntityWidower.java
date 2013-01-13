package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityWidower extends EntityMob
{
    public ModUndeadEntityWidower(World par1World)
    {
        super(par1World);
        texture = "/undeadPlus/mob/widower.png";
        setSize(1.4F, 0.9F);
        moveSpeed = 0.8F;
        //attackStrength = 2;
    }
    
    public int func_82193_c(Entity par1Entity)
    {
        return 2;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte)0));
    }

    public void onLivingUpdate()
    {
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
    

    public int getMaxHealth()
    {
        return 10;
    }

    public double getMountedYOffset()
    {
        return (double)height * 0.75D - 0.5D;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }


    protected Entity findPlayerToAttack()
    {
        float f = getBrightness(1.0F);
        double d = 16D;
        
        if (f < 0.5F)
        {
            
            return worldObj.getClosestVulnerablePlayerToEntity(this, d);
        }
        else
        {
        	return worldObj.getClosestVulnerablePlayerToEntity(this, d);
        }
    }

    protected String getLivingSound()
    {
        return "mob.spider";
    }

    protected String getHurtSound()
    {
        return "mob.spider";
    }

    protected String getDeathSound()
    {
        return "mob.spiderdeath";
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

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }

    protected int getDropItemId()
    {
        return Item.silk.itemID;
    }

    protected void dropRareDrop(int i)
    {
            dropItem(Item.fermentedSpiderEye.itemID, 1);
    }

    public boolean isOnLadder()
    {
        return func_40149_l_();
    }

    public void setInWeb()
    {
    }

    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount()
    {
        return 1.0F;
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
        if (par1PotionEffect.getPotionID() == Potion.poison.id)
        {
            return false;
        }
        else
        {
            return super.isPotionApplicable(par1PotionEffect);
        }
    }

    public boolean func_40149_l_()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_40148_a(boolean par1)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            byte0 |= 1;
        }
        else
        {
            byte0 &= 0xfe;
        }

        dataWatcher.updateObject(16, Byte.valueOf(byte0));
    }


        public boolean attackEntityAsMob(Entity entity)
        {
            if(super.attackEntityAsMob(entity))
            {
                if(entity instanceof EntityLiving)
                {
                    byte byte0 = 0;
                    	if(worldObj.difficultySetting == 1)
                		{
                    		byte0 = 4;
                		}
                        if(worldObj.difficultySetting == 2)
                        {
                            byte0 = 7;
                        } else
                        if(worldObj.difficultySetting == 3)
                        {
                            byte0 = 15;
                        }
                    
                    if(byte0 > 0)
                    {
                        ((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.id, byte0 * 25, 0));
                        ((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.hunger.id, byte0 * 25, 0));
                    }
                }
                return true;
            } else
            {
                return false;
            }
        }
}
