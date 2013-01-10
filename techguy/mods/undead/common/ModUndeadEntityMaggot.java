package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class ModUndeadEntityMaggot extends EntityMob
{

	/**
	 * A cooldown before this entity will search for another Silverfish to join them in battle.
	 */
	private int allySummonCooldown;

	public ModUndeadEntityMaggot(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/maggot.png";
		setSize(0.3F, 0.7F);
		moveSpeed = 0.6F;
		//attackStrength = 2;
		experienceValue = 2;
	}

	public int getMaxHealth()
	{
		return 8;
	}
	
    public int func_82193_c(Entity par1Entity)
    {
        return 2;
    }

	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected Entity findPlayerToAttack()
	{
		double d = 8D;
		return worldObj.getClosestVulnerablePlayerToEntity(this, d);
	}

	protected String getLivingSound()
	{
		return "mob.silverfish.say";
	}

	protected String getHurtSound()
	{
		return "mob.silverfish.hit";
	}

	protected String getDeathSound()
	{
		return "mob.silverfish.kill";
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i)
	{
		if(allySummonCooldown <= 0 && (damagesource instanceof EntityDamageSource))
		{
			allySummonCooldown = 20;
		}
		return super.attackEntityFrom(damagesource, i);
	}

	protected void attackEntity(Entity entity, float f)
	{
		if(attackTime <= 0 && f < 1.2F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
		{
			attackTime = 20;
			entity.attackEntityFrom(DamageSource.causeMobDamage(this), func_82193_c(entity));
		}
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int i, int j, int k, int l)
	{
		worldObj.playSoundAtEntity(this, "mob.silverfish.step", 1.0F, 1.0F);
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}

	public void onUpdate()
	{
		renderYawOffset = rotationYaw;
		super.onUpdate();
	}


	/**
	 * Checks to make sure the light is not too bright where the mob is spawning
	 */
	protected boolean isValidLightLevel()
	{
		return true;
	}


	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEFINED;
	}

	public boolean getCanSpawnHere()
	{
		if (super.getCanSpawnHere())
		{
			EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 1D);
			return entityplayer == null;
		}
		else
		{
			return false;
		}
	}

	public int getDropItemId()
	{
		return ModUndeadMainRegistry.maggotMeatRaw.shiftedIndex;
	}

	/*protected void dropRareDrop(int i)
	{
		dropItem(Item.silk.shiftedIndex, 1);
	}*/
}
