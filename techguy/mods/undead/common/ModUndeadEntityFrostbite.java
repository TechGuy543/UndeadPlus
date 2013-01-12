package techguy.mods.undead.common;

import net.minecraft.block.Block;
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
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityFrostbite extends EntityMob
{

	public ModUndeadEntityFrostbite(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/frostbite.png";
		moveSpeed *= 0.4F;
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
		return 16;
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
					byte0 = 10;
				}
				if(worldObj.difficultySetting == 2)
				{
					byte0 = 11;
				} else
					if(worldObj.difficultySetting == 3)
					{
						byte0 = 12;
					}

				if(byte0 > 0)
				{
					((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, byte0 * 20, 0));
					((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.weakness.id, byte0 * 20, 0));
				}
			}
			return true;
		} else
		{
			return false;
		}
	}
	protected void dropFewItems(boolean flag, int i)
	{
		int j = rand.nextInt(4 + i);
		for (int k = 0; k < j; k++)
		{
			dropItem(Item.snowball.shiftedIndex, 1);
		}
	}

	protected void dropRareDrop(int i)
	{
		dropItem(Block.ice.blockID, 1);
	}

	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.frostbite.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.frostbite.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.frostbite.die";
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
	
	public void onDeath(DamageSource damagesource)
	{

		if (damagesource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer var2 = (EntityPlayer)damagesource.getEntity();
			var2.triggerAchievement(ModUndeadMainRegistry.frostbiteKill);
		}

		super.onDeath(damagesource);
	}

}