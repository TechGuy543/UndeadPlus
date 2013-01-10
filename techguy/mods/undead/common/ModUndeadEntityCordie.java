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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityCordie extends EntityMob
{

	public ModUndeadEntityCordie(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/cordie.png";
		moveSpeed *= 0.38F;
		//attackStrength = 6;
		
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityZombie.class, moveSpeed, true));
        tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, moveSpeed));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, 16F, 0, false));
	}
	
    public int func_82193_c(Entity par1Entity)
    {
        return 6;
    }
	
	public boolean interact(EntityPlayer entityplayer)
	{
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		
		if (itemstack != null && itemstack.itemID == Item.shears.shiftedIndex)
		{
			setDead();
			EntityZombie entityZombie = new EntityZombie(worldObj);
			entityZombie.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			entityZombie.setEntityHealth(getHealth());
			entityZombie.renderYawOffset = renderYawOffset;
			worldObj.spawnEntityInWorld(entityZombie);
			worldObj.spawnParticle("largeexplode", posX, posY + (double)(height / 2.0F), posZ, 0.0D, 0.0D, 0.0D);
			for (int i = 0; i < 3; i++)
			{
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY + (double)height, posZ, new ItemStack(ModUndeadMainRegistry.cordycepsFungus)));
			}

			return true;
		}
		else
		{
			return super.interact(entityplayer);
		}
	}


	public int getMaxHealth()
	{
		return 16;
	}

	protected String getLivingSound()
    {
        return "undeadPlusAudio.mobs.cordie.growl";
    }

    protected String getHurtSound()
    {
        return "undeadPlusAudio.mobs.cordie.hit";
    }

    protected String getDeathSound()
    {
        return "undeadPlusAudio.mobs.cordie.die";
    }
    
    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3 + i);
        for (int k = 0; k < j; k++)
        {
            dropItem(Item.rottenFlesh.shiftedIndex, 1);
        }        
    }
    
    protected void dropRareDrop(int i)
    {
        int j1 = rand.nextInt(3 + i);
        for (int l = 0; l < j1; l++)
        {
            dropItem(ModUndeadMainRegistry.cordycepsFungus.shiftedIndex, 1);
        }
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
} 