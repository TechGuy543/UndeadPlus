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
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityKnight extends EntityMob
{
	public ModUndeadEntityKnight(World world)
	{
		super(world);
		moveSpeed *= 0.4F;
		isImmuneToFire = false;
		texture = "/undeadPlus/mob/knightnew.png";
		//attackStrength = 5;
		experienceValue = 3;
		
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
        return 5;
    }

	public int getDropItemId()
	{
		return Item.ingotIron.shiftedIndex;
	}


	public int getMaxHealth()
	{
		return 36;
	}

	public ItemStack getHeldItem()
	{
		return defaultHeldItem;
	}

	static 
	{
		defaultHeldItem = new ItemStack(Item.swordSteel, 1);
	}
	private static final ItemStack defaultHeldItem;


	protected String getLivingSound()
	{
		return "undeadPlusAudio.mobs.knight.growl";
	}

	protected String getHurtSound()
	{
		return "undeadPlusAudio.mobs.knight.hit";
	}

	protected String getDeathSound()
	{
		return "undeadPlusAudio.mobs.knight.die";
	}

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	
    	getArmorItemForSlot(4, 4);
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
        {
            float var1 = this.getBrightness(1.0F);

            if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean var2 = true;
                ItemStack var3 = this.getCurrentItemOrArmor(4);

                if (var3 != null)
                {
                    if (var3.isItemStackDamageable())
                    {
                        var3.setItemDamage(var3.getItemDamageForDisplay() + this.rand.nextInt(2));

                        if (var3.getItemDamageForDisplay() >= var3.getMaxDamage())
                        {
                            this.renderBrokenItemStack(var3);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    var2 = false;
                }

                if (var2)
                {
                    this.setFire(8);
                }
            }
        }

        super.onLivingUpdate();
    }
    
    public boolean isAIEnabled()
    {
    	return true;
    }
    
    protected void dropRareDrop(int i)
    {
    	this.dropItem(ModUndeadMainRegistry.volatiteIngot.shiftedIndex, 1);
    }

    
    /*@Override
    public boolean attackEntityAsMob(Entity par1Entity)
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

        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
    }*/

}
