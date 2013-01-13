package techguy.mods.undead.common;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityFlare extends EntityMob
{
	private static final ItemStack defaultHeldItem;

	public ModUndeadEntityFlare(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/flare.png";
		//attackStrength = 2;
		moveSpeed *= 0.4F;
		isImmuneToFire = true;
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, moveSpeed));
		tasks.addTask(4, new ModUndeadEntityAIFlamingArrowAttack(this, moveSpeed, 1, 60));
		tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, false));
	}

	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(13, new Byte((byte)0));
	}

	public int getMaxHealth()
	{
		return 16;
	}

	public int func_82193_c(Entity par1Entity)
	{
		return 2;
	}

	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.flare.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.flare.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.flare.die";
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i)
	{
		return super.attackEntityFrom(damagesource, i);
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


	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}

	protected int getDropItemId()
	{
		return Item.arrow.itemID;
	}

	protected void dropFewItems(boolean flag, int i)
	{
		int j = rand.nextInt(3 + i);
		for (int k = 0; k < j; k++)
		{
			dropItem(Item.arrow.itemID, 1);
		}

		j = rand.nextInt(3 + i);
		for (int l = 0; l < j; l++)
		{
			dropItem(Item.coal.itemID, 1);
		}
	}

	protected void dropRareDrop(int i)
	{
		ItemStack var2 = new ItemStack(Item.bow);
		var2.addEnchantment(Enchantment.flame, 1);
		this.entityDropItem(var2, 0F);
	}


	public ItemStack getHeldItem()
	{
		return defaultHeldItem;
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

	static
	{
		defaultHeldItem = new ItemStack(Item.bow, 1);
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public int func_82202_m()
	{
		return this.dataWatcher.getWatchableObjectByte(13);
	}

}
