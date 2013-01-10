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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ModUndeadEntityCoolZombie extends EntityMob
{

    public ModUndeadEntityCoolZombie(World world)
    {
        super(world);
        moveSpeed *= 0.3F;
        //attackStrength = 4;
        texture = "/undeadPlus/mob/cool.png";
        
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
    
    public int func_82193_c(Entity par1Entity)
    {
        return 4;
    }
    
    public boolean attackEntityAsMob(Entity entity)
    {
        int i = func_82193_c(entity);
        if(isPotionActive(Potion.damageBoost))
        {
            i += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if(isPotionActive(Potion.weakness))
        {
            i -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    public int getMaxHealth()
    {
        return 20;
    }   

    protected int getDropItemId()
    {
        return Item.rottenFlesh.shiftedIndex;
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    protected String getLivingSound()
    {
        return "mob.zombie";
    }

    protected String getHurtSound()
    {
        return "mob.zombiehurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombiedeath";
    }
    
    public boolean isAIEnabled()
    {
    	return true;
    }
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    }
    
}
