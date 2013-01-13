package techguy.mods.undead.common;

import net.minecraft.client.Minecraft;
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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ModUndeadEntityScorcher extends EntityMob
{

	public ModUndeadEntityScorcher(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/scorcher.png";
		moveSpeed *= 0.4F;
		isImmuneToFire = true;
		//attackStrength = 1;

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

	public boolean isAIEnabled()
	{
		return true;
	}

	public int func_82193_c(Entity par1Entity)
	{
		return 1;
	}

	public int getMaxHealth()
	{
		return 20;
	}

	public boolean attackEntityAsMob(Entity entity)
	{
		if(entity instanceof EntityPlayer)
		{
			((EntityPlayer)entity).setFire(8);
		}
		return true;
	}

	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.scorcher.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.scorcher.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.scorcher.die";
	}

	public int getDropItemId()
	{
		return Item.fireballCharge.itemID;
	}

	protected void dropRareDrop(int i)
	{
		switch (this.rand.nextInt(4))
		{
		case 0:
			this.dropItem(Item.plateChain.itemID, 1);
			break;
		case 1:
			this.dropItem(Item.helmetChain.itemID, 1);
			break;
		case 2:
			this.dropItem(Item.legsChain.itemID, 1);
			break;
		case 3:
			this.dropItem(Item.bootsChain.itemID, 1);
			break;
		}
	}

	public void onDeath(DamageSource damagesource)
	{
		if (damagesource.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer var2 = (EntityPlayer)damagesource.getEntity();
			var2.triggerAchievement(ModUndeadMainRegistry.scorcherKill);
		}

		super.onDeath(damagesource);
	}

	private Minecraft mc;
}

