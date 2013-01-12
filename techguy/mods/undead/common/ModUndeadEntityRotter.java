package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityRotter extends EntityMob
{

	public ModUndeadEntityRotter(World world)
    {
        super(world);
        texture = "/undeadPlus/mob/rotter.png";
        //attackStrength = 6;
        moveSpeed *= 0.5F;
        
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(2, new EntityAIWander(this, moveSpeed));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(3, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
    }
 
    public int getMaxHealth()
    {
        return 24;
    }
    
    public int func_82193_c(Entity par1Entity)
    {
        return 6;
    }

    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3 + i);
        for (int l = 0; l < j; l++)
        {
            dropItem(Item.bone.shiftedIndex, 1);
        }

        j = rand.nextInt(3 + i);
        for (int k = 0; k < j; k++)
        {
            dropItem(Item.rottenFlesh.shiftedIndex, 1);
        }  
    }
    
    protected void dropRareDrop(int i)
    {
    	dropItem(ModUndeadMainRegistry.rottenBone.shiftedIndex, 1);
    }
    
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }
        
        static 
    {
        defaultHeldItem = new ItemStack(Item.bone, 1);
    }

    private static final ItemStack defaultHeldItem;

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
    
    protected String getLivingSound()
    {
        return "undeadPlusAudio.mobs.rotter.growl";
    }

    protected String getHurtSound()
    {
        return "undeadPlusAudio.mobs.rotter.hit";
    }

    protected String getDeathSound()
    {
        return "undeadPlusAudio.mobs.rotter.die";
    }
    
	public void onDeath(DamageSource par1)
	{
		int i = rand.nextInt(4);

		if (!worldObj.isRemote && i == 3)
		{

				ModUndeadEntityMaggot entitymaggot = new ModUndeadEntityMaggot(worldObj);
				entitymaggot.setLocationAndAngles(posX, posY + 0.5D, posZ, rand.nextFloat() * 360F, 0.0F);
				worldObj.spawnEntityInWorld(entitymaggot);
		}
		
			if (par1.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer var2 = (EntityPlayer)par1.getEntity();
				var2.triggerAchievement(ModUndeadMainRegistry.rotterKill);
			}


		super.onDeath(par1);
	}
    
    public boolean isAIEnabled()
    {
    	return true;
    }
}