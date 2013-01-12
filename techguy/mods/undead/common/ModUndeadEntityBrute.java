package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityBrute extends EntityMob
{

    public ModUndeadEntityBrute(World world)
    {
        super(world);
        texture = "/undeadPlus/mob/brute.png";
        moveSpeed *= 0.2F;
       // attackStrength = 8;
        setSize(1F, 2.1F);
        
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
        tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, moveSpeed));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16F, 0, false));
        
    }

    public int getMaxHealth()
    {
        return 60;
    }
    
    public int func_82193_c(Entity par1Entity)
    {
        return 8;
    }
    
    protected String getLivingSound()
    {
        return "undeadPlusAudio.mobs.brute.growl";
    }

    protected String getHurtSound()
    {
        return "undeadPlusAudio.mobs.brute.hit";
    }

    protected String getDeathSound()
    {
        return "undeadPlusAudio.mobs.brute.die";
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
    
    public boolean isAIEnabled()
    {
    	return true;
    }
    
    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3 + i);
        for (int k = 0; k < j; k++)
        {
            dropItem(Item.rottenFlesh.shiftedIndex, 1);
        }

        j = rand.nextInt(3 + i);
        for (int l = 0; l < j; l++)
        {
            dropItem(Item.bone.shiftedIndex, 1);
        }
        
    }
    
    public void dropRareDrop(int i)
    {
    	dropItem(Item.expBottle.shiftedIndex, 1);
    }
    
	public void onDeath(DamageSource damagesource)
	{

		if (damagesource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer var2 = (EntityPlayer)damagesource.getEntity();
			var2.triggerAchievement(ModUndeadMainRegistry.bruteKill);
		}

		super.onDeath(damagesource);
	}

}