package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityInferno extends EntityFlying implements IMob
{
	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity;

	/** Cooldown time between target loss and new target aquirement. */
	private int aggroCooldown;
	public int prevAttackCounter;
	public int attackCounter;

	public ModUndeadEntityInferno(World par1World)
	{
		super(par1World);
		courseChangeCooldown = 0;
		targetedEntity = null;
		aggroCooldown = 0;
		prevAttackCounter = 0;
		attackCounter = 0;
		texture = "/undeadPlus/mob/inferno.png";
		setSize(4F, 4F);
		isImmuneToFire = true;
		experienceValue = 6;
		//ModLoader.getMinecraftInstance().effectRenderer.addEffect(new ModUndeadEntityBlueFlamesFX(ModLoader.getMinecraftInstance().theWorld, 0, 0, 255, 0.09, 0.09, 0.09));
	}

	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	public int getMaxHealth()
	{
		return 16;
	}

	protected void updateEntityActionState()
	{
		if (!worldObj.isRemote && worldObj.difficultySetting == 0)
		{
			setDead();
		}

		despawnEntity();
		prevAttackCounter = attackCounter;
		double d = waypointX - posX;
		double d1 = waypointY - posY;
		double d2 = waypointZ - posZ;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);

		if (d3 < 1.0D || d3 > 60D)
		{
			if(!ModLoader.getMinecraftInstance().thePlayer.capabilities.isCreativeMode)
			{
				waypointX = ModLoader.getMinecraftInstance().thePlayer.posX;
				waypointY = ModLoader.getMinecraftInstance().thePlayer.posY;
				waypointZ = ModLoader.getMinecraftInstance().thePlayer.posZ;
			}

			if(ModLoader.getMinecraftInstance().thePlayer.capabilities.isCreativeMode)
			{
				waypointX = posX + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16F);
				waypointY = posY + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16F);
				waypointZ = posZ + (double)((rand.nextFloat() * 2.0F - 1.0F) * 16F);
			}

		}

		if (courseChangeCooldown-- <= 0)
		{
			courseChangeCooldown += rand.nextInt(5) + 2;

			if (isCourseTraversable(waypointX, waypointY, waypointZ, d3))
			{
				motionX += (d / d3) * 0.10000000000000001D;
				motionY += (d1 / d3) * 0.10000000000000001D;
				motionZ += (d2 / d3) * 0.10000000000000001D;
			}
			else
			{
				waypointX = posX;
				waypointY = posY;
				waypointZ = posZ;
			}
		}

		if (targetedEntity != null && targetedEntity.isDead)
		{
			targetedEntity = null;
		}

		if (targetedEntity == null || aggroCooldown-- <= 0)
		{
			targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100D);

			if (targetedEntity != null)
			{
				aggroCooldown = 20;
			}
		}

		double d4 = 64D;

		if (targetedEntity != null && targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
		{
			double d5 = targetedEntity.posX - posX;
			double d6 = (targetedEntity.boundingBox.minY + (double)(targetedEntity.height / 2.0F)) - (posY + (double)(height / 2.0F));
			double d7 = targetedEntity.posZ - posZ;
			renderYawOffset = rotationYaw = (-(float)Math.atan2(d5, d7) * 180F) / (float)Math.PI;

			if (canEntityBeSeen(targetedEntity))
			{
				if (attackCounter == 10)
				{
					worldObj.playAuxSFXAtEntity(null, 1007, (int)posX, (int)posY, (int)posZ, 0);
				}

				attackCounter++;

				if (attackCounter == 20)
				{
					worldObj.createExplosion(this, posX, posY, posZ, 4F, true);
					attackCounter = -40;
				}
			}
			else if (attackCounter > 0)
			{
				attackCounter--;
			}
		}
		else
		{
			renderYawOffset = rotationYaw = (-(float)Math.atan2(motionX, motionZ) * 180F) / (float)Math.PI;

			if (attackCounter > 0)
			{
				attackCounter--;
			}
		}

		if (!worldObj.isRemote)
		{
			byte byte0 = dataWatcher.getWatchableObjectByte(16);
			byte byte1 = (byte)(attackCounter <= 10 ? 0 : 1);

			if (byte0 != byte1)
			{
				dataWatcher.updateObject(16, Byte.valueOf(byte1));
			}
		}
	}

	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if(!ModLoader.getMinecraftInstance().thePlayer.capabilities.isCreativeMode)
		{
			worldObj.createExplosion(this, posX, posY, posZ, 4F, true);
			setDead();
		}

	}

	/**
	 * True if the ghast has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
	{
		double d = (waypointX - posX) / par7;
		double d1 = (waypointY - posY) / par7;
		double d2 = (waypointZ - posZ) / par7;
		AxisAlignedBB axisalignedbb = boundingBox.copy();

		for (int i = 1; (double)i < par7; i++)
		{
			axisalignedbb.offset(d, d1, d2);

			if (worldObj.getCollidingBoundingBoxes(this, axisalignedbb).size() > 0)
			{
				return false;
			}
		}

		return true;
	}


	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.inferno.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.inferno.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.inferno.die";
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */

	protected int getDropItemId()
	{
		return Item.blazePowder.itemID;
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int i = rand.nextInt(2) + rand.nextInt(1 + par2);

		for (int j = 0; j < i; j++)
		{
			dropItem(Item.fireballCharge.itemID, 1);
		}

		i = rand.nextInt(3) + rand.nextInt(1 + par2);

		for (int k = 0; k < i; k++)
		{
			dropItem(Item.blazePowder.itemID, 1);
		}
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 10F;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return rand.nextInt(20) == 0 && super.getCanSpawnHere() && worldObj.difficultySetting > 0;
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}
}
