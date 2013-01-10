package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityThinker extends EntityMob
{

    public ModUndeadEntityThinker(World par1world)
    {
        super(par1world);
        texture = "/undeadPlus/mob/thinker.png";
        moveSpeed *= 0.4F;
        //attackStrength = 6;
        experienceValue = 5;

        getNavigator().setBreakDoors(true);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
        tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, moveSpeed));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIOpenDoor(this, true));
        tasks.addTask(7, new EntityAIRestrictOpenDoor(this));
        tasks.addTask(8, new EntityAIWander(this, moveSpeed));
        tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(10, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16F, 0, false));
    }
    
    public int func_82193_c(Entity par1Entity)
    {
        return 6;
    }

    public int getMaxHealth()
    {
    	return 20;
    }

    protected void dropFewItems(boolean b, int i)
    {
        super.dropFewItems(b, i);

        int j = rand.nextInt(3 + i);
        for (int k = 0; k < j; k++)
        {
            dropItem(Item.paper.shiftedIndex, 1);
        }
    }
    
    protected void dropRareDrop(int i)
    {
    	dropItem(Item.book.shiftedIndex, 1);
    }

    protected String getLivingSound()
    {
    	return "undeadPlusAudio.mobs.thinker.growl";
    }

    protected String getHurtSound()
    {
    	return "undeadPlusAudio.mobs.thinker.hit";
    }

    protected String getDeathSound()
    {
    	return "undeadPlusAudio.mobs.thinker.die";
    }

    protected boolean isAIEnabled()
    {
    	return true;
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
    	return EnumCreatureAttribute.UNDEAD;
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
    
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 64D);

        if (entityplayer != null && canEntityBeSeen(entityplayer))
        {
            return entityplayer;
        }
        else
        {
            return null;
        }
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte)0));
    }
    
    public boolean isOnLadder()
    {
        return func_40149_l_();
    }
    
    public boolean func_40149_l_()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

}