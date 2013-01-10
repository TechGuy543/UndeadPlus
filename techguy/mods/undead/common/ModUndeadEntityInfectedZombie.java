package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityInfectedZombie extends EntityMob
{

	public ModUndeadEntityInfectedZombie(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/infected.png";
		moveSpeed = 0.25F;
		//attackStrength = 4;

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
	
	public boolean isAIEnabled()
	{
		return true;
	}

	public int getMaxHealth()
	{
		return 20;
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
				} 
				else
					if(worldObj.difficultySetting == 3)
					{
						byte0 = 15;
					}

				if(byte0 > 0)
				{
					((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.poison.id, byte0 * 20, 0));
				}
			}
			return true;
		} else
		{
			return false;
		}
	}

	public void onLivingUpdate()
	{
		if (worldObj.isDaytime() && !worldObj.isRemote)
		{
			float f = getEntityBrightness(1.0F);
			if (f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
			{
				setFire(8);
			}
		}
		super.onLivingUpdate();
	}

	public int getEntityBrightnessForRender(float f)
	{
		return super.getBrightnessForRender(f);
	}

	public float getEntityBrightness(float f)
	{
		return super.getBrightness(f);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.zombie";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.zombiehurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.zombiedeath";
	}

	/**
	 * Will get destroyed next tick.
	 */
	public void onDeath(DamageSource par1)
	{
		int i = rand.nextInt(19);

		if (!worldObj.isRemote && i == 3)
		{

				ModUndeadEntityInfectedZombie entityinfectedzombie = new ModUndeadEntityInfectedZombie(worldObj);
				ModUndeadEntityCrawler entitycrawler = new ModUndeadEntityCrawler(worldObj);
				entitycrawler.setLocationAndAngles(posX, posY + 0.5D, posZ, rand.nextFloat() * 360F, 0.0F);
				worldObj.spawnEntityInWorld(entitycrawler);
		}

		super.onDeath(par1);
	}
	
	protected void dropRareDrop(int i)
	{
		this.dropItem(Potion.poison.id, 1);
	}
}
