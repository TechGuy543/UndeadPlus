package techguy.mods.undead.common;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModUndeadEntityBuccaneer extends EntityMob
{
	public ModUndeadEntityBuccaneer(World world)
	{
		super(world);
		texture = "/undeadPlus/mob/buccaneer.png";
		moveSpeed *= 0.5F;
		//attackStrength = 6;

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
		return 25;
	}
	
    public int func_82193_c(Entity par1Entity)
    {
        return 6;
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
			dropItem(Item.fishRaw.shiftedIndex, 1);
		}

	}

	protected void dropRareDrop(int i)
	{
		ItemStack var2 = new ItemStack(Item.swordGold);
		var2.addEnchantment(Enchantment.smite, 2);
		entityDropItem(var2, 0F);
	}

	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

	public ItemStack getHeldItem()
	{
		return defaultHeldItem;
	}   

	static 
	{
		defaultHeldItem = new ItemStack(Item.swordGold, 1);
	}

	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.buccaneer.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.buccaneer.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.buccaneer.die";
	}

	public void onLivingUpdate()
	{
		if (isWet())
		{
			moveSpeed *= 0.7F;
		}
		super.onLivingUpdate();
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	private static final ItemStack defaultHeldItem;
}