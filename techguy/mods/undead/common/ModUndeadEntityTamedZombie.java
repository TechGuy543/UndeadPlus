package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ModUndeadEntityTamedZombie extends EntityTameable
{

	public ModUndeadEntityTamedZombie(World par1World)
	{
		super(par1World);
		texture = "/undeadPlus/mob/cool.png";
		moveSpeed = 0.35F;
		experienceValue = 0;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
		tasks.addTask(3, new EntityAIFollowOwner(this, moveSpeed, 5F, 200F));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

	}

	public int getMaxHealth()
	{
		return 20;
	}

	public int getTotalArmorValue()
	{
		return 2;
	}

	protected boolean isAIEnabled()
	{
		return true;
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

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) 
	{
		return null;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		Entity entity = par1DamageSource.getEntity();
		aiSit.setSitting(false);

		if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
		{
			par2 = (par2 + 1) / 2;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		byte byte0 = ((byte)(isTamed() ? 4 : 2));

		if (par1Entity != null && !(par1Entity instanceof EntityPlayer))
		{

			return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), byte0);
		}
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), byte0);
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}

	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemStack = par1EntityPlayer.inventory.getCurrentItem();

		if (itemStack != null && itemStack.itemID == Item.rottenFlesh.itemID)
		{
			heal(6);
			itemStack.stackSize--;
			worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0D, 0.0D, 0.0D);

			if (itemStack.stackSize <= 0)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
			}
			return true;
		}


		return super.interact(par1EntityPlayer);
	}

	public EntityAgeable createChild(EntityAgeable var1) 
	{
		return null;
	}

}

