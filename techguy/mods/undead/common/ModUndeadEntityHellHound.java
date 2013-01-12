package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityHellHound extends EntityTameable
{
	/**
	 * This flag is set when the wolf is looking at a player with interest, i.e. with tilted head. This happens when
	 * tamed wolf is wound and player holds porkchop (raw or cooked), or when wild wolf sees bone in player's hands.
	 */
	private boolean looksWithInterest;
	private float field_25048_b;
	private float field_25054_c;

	/** true is the wolf is wet else false */
	private boolean isShaking;
	private boolean field_25052_g;

	/**
	 * This time increases while wolf is shaking and emitting water particles.
	 */
	private float timeHellHoundIsShaking;
	private float prevTimeHellHoundIsShaking;
	int attackStrength = 2;

	public ModUndeadEntityHellHound(World par1World)
	{
		super(par1World);
		looksWithInterest = false;
		texture = "/undeadPlus/mob/hound.png";
		setSize(0.6F, 0.8F);
		moveSpeed = 0.3F;
		isImmuneToFire = true;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10F, 2.0F));
		tasks.addTask(6, new EntityAIMate(this, moveSpeed));
		tasks.addTask(7, new EntityAIWander(this, moveSpeed));
		tasks.addTask(8, new ModUndeadEntityAIHellHoundBeg(this, 8F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Sets the active target the Task system uses for tracking
	 */
	public void setAttackTarget(EntityLiving par1EntityLiving)
	{
		super.setAttackTarget(par1EntityLiving);

		if (par1EntityLiving instanceof EntityPlayer)
		{
			setAngry(true);
		}
	}

	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		dataWatcher.updateObject(18, Integer.valueOf(getHealth()));
	}

	public int getMaxHealth()
	{
		return !isTamed() ? 8 : 20;
	}

	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(18, new Integer(getHealth()));
		getOwner();
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	/**
	 * Returns the texture's file path as a String.
	 */
	public String getTexture()
	{
		if (isTamed())
		{
			return "/undeadPlus/mob/hound_tame.png";
		}

		if (isAngry())
		{
			return "/undeadPlus/mob/hound_angry.png";
		}
		else
		{
			return super.getTexture();
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", isAngry());
		par1NBTTagCompound.setBoolean("Tamed", isTamed());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		setAngry(par1NBTTagCompound.getBoolean("Angry"));
		setTamed(par1NBTTagCompound.getBoolean("Tamed"));
	}


	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		return isAngry();
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (isAngry())
		{
			return "undeadPlusAudio.mobs.hellhound.growl";
		}

		if (rand.nextInt(3) == 0)
		{
			if (isTamed() && dataWatcher.getWatchableObjectInt(18) < 10)
			{
				return "mob.wolf.whine";
			}
			else
			{
				return "mob.wolf.panting";
			}
		}
		else
		{
			return "undeadPlusAudio.mobs.hellhound.bark";
		}
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.hellhound.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.hellhound.die";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId()
	{
		return -1;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!worldObj.isRemote && isShaking && !field_25052_g && !hasPath() && onGround)
		{
			field_25052_g = true;
			timeHellHoundIsShaking = 0.0F;
			prevTimeHellHoundIsShaking = 0.0F;
			worldObj.setEntityState(this, (byte)8);
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		field_25054_c = field_25048_b;

		if (looksWithInterest)
		{
			field_25048_b = field_25048_b + (1.0F - field_25048_b) * 0.4F;
		}
		else
		{
			field_25048_b = field_25048_b + (0.0F - field_25048_b) * 0.4F;
		}

		if (looksWithInterest)
		{
			numTicksToChaseTarget = 10;
		}

		if (isWet())
		{
			isShaking = true;
			field_25052_g = false;
			timeHellHoundIsShaking = 0.0F;
			prevTimeHellHoundIsShaking = 0.0F;
		}
		else if ((isShaking || field_25052_g) && field_25052_g)
		{
			if (timeHellHoundIsShaking == 0.0F)
			{
				worldObj.playSoundAtEntity(this, "mob.wolf.shake", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
			}

			prevTimeHellHoundIsShaking = timeHellHoundIsShaking;
			timeHellHoundIsShaking += 0.05F;

			if (prevTimeHellHoundIsShaking >= 2.0F)
			{
				isShaking = false;
				field_25052_g = false;
				prevTimeHellHoundIsShaking = 0.0F;
				timeHellHoundIsShaking = 0.0F;
			}

			if (timeHellHoundIsShaking > 0.4F)
			{
				float f = (float)boundingBox.minY;
				int i = (int)(MathHelper.sin((timeHellHoundIsShaking - 0.4F) * (float)Math.PI) * 7F);

				for (int j = 0; j < i; j++)
				{
					float f1 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
					float f2 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
					worldObj.spawnParticle("splash", posX + (double)f1, f + 0.8F, posZ + (double)f2, motionX, motionY, motionZ);
				}
			}
		}
	}

	public boolean getHellHoundShaking()
	{
		return isShaking;
	}

	/**
	 * Used when calculating the amount of shading to apply while the wolf is shaking.
	 */
	public float getShadingWhileShaking(float par1)
	{
		return 0.75F + ((prevTimeHellHoundIsShaking + (timeHellHoundIsShaking - prevTimeHellHoundIsShaking) * par1) / 2.0F) * 0.25F;
	}

	public float getShakeAngle(float par1, float par2)
	{
		float f = (prevTimeHellHoundIsShaking + (timeHellHoundIsShaking - prevTimeHellHoundIsShaking) * par1 + par2) / 1.8F;

		if (f < 0.0F)
		{
			f = 0.0F;
		}
		else if (f > 1.0F)
		{
			f = 1.0F;
		}

		return MathHelper.sin(f * (float)Math.PI) * MathHelper.sin(f * (float)Math.PI * 11F) * 0.15F * (float)Math.PI;
	}

	public float getInterestedAngle(float par1)
	{
		return (field_25054_c + (field_25048_b - field_25054_c) * par1) * 0.15F * (float)Math.PI;
	}

	public float getEyeHeight()
	{
		return height * 0.8F;
	}

	/**
	 * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
	 * use in wolves.
	 */
	public int getVerticalFaceSpeed()
	{
		if (isSitting())
		{
			return 20;
		}
		else
		{
			return super.getVerticalFaceSpeed();
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
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
		return attackEntityWithDamageAndFire(par1Entity);
	}

	public boolean attackEntityWithDamageAndFire(Entity par1Entity)
	{	
		int var2 = this.attackStrength;

		if (this.isPotionActive(Potion.damageBoost))
		{
			var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if (this.isPotionActive(Potion.weakness))
		{
			var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		par1Entity.setFire(5);
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
	}
	
    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3 + i);
        for (int k = 0; k < j; k++)
        {
            dropItem(Item.goldNugget.shiftedIndex, 1);
        }

        j = rand.nextInt(3 + i);
        for (int l = 0; l < j; l++)
        {
            dropItem(Item.rottenFlesh.shiftedIndex, 1);
        }

    }
	
	protected void dropRareDrop(int i)
	{
		dropItem(Item.gunpowder.shiftedIndex, 1);
	}
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (!isTamed())
		{
			if (itemstack != null && itemstack.itemID == ModUndeadMainRegistry.rottenBone.shiftedIndex && !isAngry())
			{
				if (!par1EntityPlayer.capabilities.isCreativeMode)
				{
					itemstack.stackSize--;
				}

				if (itemstack.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
				}

				if (!worldObj.isRemote)
				{
					if (rand.nextInt(3) == 0)
					{
						setTamed(true);
						setPathToEntity(null);
						setAttackTarget(null);
						aiSit.setSitting(true);
						setEntityHealth(20);
						setOwner(par1EntityPlayer.username);
						playTameEffect(true);
						worldObj.setEntityState(this, (byte)7);
						par1EntityPlayer.triggerAchievement(ModUndeadMainRegistry.hellHoundTame);
					}
					else
					{
						playTameEffect(false);
						worldObj.setEntityState(this, (byte)6);
					}
				}

				return true;
			}
		}
		else
		{
			if (itemstack != null && (Item.itemsList[itemstack.itemID] instanceof ItemFood))
			{
				ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];

				if (itemfood.isWolfsFavoriteMeat() && dataWatcher.getWatchableObjectInt(18) < 20)
				{
					if (!par1EntityPlayer.capabilities.isCreativeMode)
					{
						itemstack.stackSize--;
					}

					heal(itemfood.getHealAmount());

					if (itemstack.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
					}

					return true;
				}
			}

			if(itemstack != null && itemstack.itemID == ModUndeadMainRegistry.rottenBone.shiftedIndex)
			{
				heal(6);
			}

			if (par1EntityPlayer.username.equalsIgnoreCase(getOwnerName()) && !worldObj.isRemote && !isWheat(itemstack))
			{
				aiSit.setSitting(!isSitting());
				isJumping = false;
				setPathToEntity(null);
			}
		}

		return super.interact(par1EntityPlayer);
	}

	public void handleHealthUpdate(byte par1)
	{
		if (par1 == 8)
		{
			field_25052_g = true;
			timeHellHoundIsShaking = 0.0F;
			prevTimeHellHoundIsShaking = 0.0F;
		}
		else
		{
			super.handleHealthUpdate(par1);
		}
	}

	public float getTailRotation()
	{
		if (isAngry())
		{
			return 1.53938F;
		}

		if (isTamed())
		{
			return (0.55F - (float)(20 - dataWatcher.getWatchableObjectInt(18)) * 0.02F) * (float)Math.PI;
		}
		else
		{
			return ((float)Math.PI / 5F);
		}
	}

	/**
	 * Checks if the parameter is an wheat item.
	 */
	public boolean isWheat(ItemStack par1ItemStack)
	{
		if (par1ItemStack == null)
		{
			return false;
		}

		if (!(Item.itemsList[par1ItemStack.itemID] instanceof ItemFood))
		{
			return false;
		}
		else
		{
			return ((ItemFood)Item.itemsList[par1ItemStack.itemID]).isWolfsFavoriteMeat();
		}
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	public int getMaxSpawnedInChunk()
	{
		return 8;
	}

	/**
	 * gets this wolf's angry state
	 */
	public boolean isAngry()
	{
		return (dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	/**
	 * sets this wolf's angry state to true if the boolean argument is true
	 */
	public void setAngry(boolean par1)
	{
		byte byte0 = dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 2)));
		}
		else
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -3)));
		}
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
	 */
	public EntityAnimal spawnBabyAnimal(EntityAnimal par1EntityAnimal)
	{
		ModUndeadEntityHellHound entityhellhound = new ModUndeadEntityHellHound(worldObj);
		entityhellhound.setOwner(getOwnerName());
		entityhellhound.setTamed(true);
		return entityhellhound;
	}

	public void func_48150_h(boolean par1)
	{
		looksWithInterest = par1;
	}

	public boolean func_48135_b(EntityAnimal par1EntityAnimal)
	{
		if (par1EntityAnimal == this)
		{
			return false;
		}

		if (!isTamed())
		{
			return false;
		}

		if (!(par1EntityAnimal instanceof ModUndeadEntityHellHound))
		{
			return false;
		}

		ModUndeadEntityHellHound entityhellhound = (ModUndeadEntityHellHound)par1EntityAnimal;

		if (!entityhellhound.isTamed())
		{
			return false;
		}

		if (entityhellhound.isSitting())
		{
			return false;
		}
		else
		{
			return isInLove() && entityhellhound.isInLove();
		}
	}

	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		// TODO Auto-generated method stub
		return null;
	}
}