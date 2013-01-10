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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ModUndeadEntityGhoul extends EntityMob
{

	public ModUndeadEntityGhoul(World world) 
	{
		super(world);
		//attackStrength = 6;
		moveSpeed *= 0.3F;
		texture = "/undeadPlus/mob/ghoul.png";

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


	public int getMaxHealth() 
	{
		return 40;
	}

	public int getDropItemId()
	{
		return Item.gunpowder.shiftedIndex;
	}

	protected void dropRareDrop(int i)
	{
		dropItem(Item.ghastTear.shiftedIndex, 1);
	}

	public void onLivingUpdate()
	{
		float f = getBrightness(0.2F);

		if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)            
		{
			damageEntity(DamageSource.generic, 6);
		}

		super.onLivingUpdate();
	}

	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.ghoul.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.ghoul.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.ghoul.die";
	}

	public boolean isAIEnabled()
	{
		return true;
	}
	
    public int func_82193_c(Entity par1Entity)
    {
        return 6;
    }

}
