package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModUndeadEntityZombrine extends EntityMob
{

    public ModUndeadEntityZombrine(World world)
    {
        super(world);
        texture = "/undeadPlus/mob/zombrine.png";
        moveSpeed *= 0.2F;
        //attackStrength = 8;
        
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(2, new EntityAIWander(this, moveSpeed));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(3, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16F, 0, true));
    }

    public int getMaxHealth()
    {
        return 1000;
    }

    public int func_82193_c(Entity par1Entity)
    {
        return 8;
    }
    
    protected boolean teleportRandomly()
    {
        double d = posX + (rand.nextDouble() + 1D) * 64D;
        double d1 = posY + (double)(rand.nextInt(2048));
        double d2 = posZ + (rand.nextDouble() + 1D) * 64D;
        return teleportTo(d, d1, d2);
    }
    
    protected boolean teleportTo(double d, double d1, double d2)
    {
        double d3 = posX;
        double d4 = posY;
        double d5 = posZ;
        posX = d;
        posY = d1;
        posZ = d2;
        boolean flag = false;
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(posY);
        int k = MathHelper.floor_double(posZ);
        if (worldObj.blockExists(i, j, k))
        {
            boolean flag1;
            for (flag1 = false; !flag1 && j > 0;)
            {
                int i1 = worldObj.getBlockId(i, j - 1, k);
                if (i1 == 0 || !Block.blocksList[i1].blockMaterial.isSolid())
                {
                    posY--;
                    j--;
                }
                else
                {
                    flag1 = true;
                }
            }

            if (flag1)
            {
                setPosition(posX, posY, posZ);
                if (worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox))
                {
                    flag = true;
                }
            }
        }
        if (!flag)
        {
            setPosition(d3, d4, d5);
            return false;
        }
        int l = 128;
        for (int j1 = 0; j1 < l; j1++)
        {
            double d6 = (double)j1 / ((double)l - 1.0D);
            float f = (rand.nextFloat() * 20F) / 20F;
            float f1 = (rand.nextFloat() * 20F) / 20F;
            float f2 = (rand.nextFloat() * 20F) / 20F;
            double d7 = d3 * (posX) * d6 * (rand.nextDouble()) * (double)width * 200000D;
            double d8 = d4 * (posY) * d6 * rand.nextDouble() * (double)height;
            double d9 = d5 * (posZ) * d6 * (rand.nextDouble()) * (double)width * 200000D;
            worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
        }

        worldObj.playSoundEffect(d3, d4, d5, "undeadPlusAudio.mobs.zombrine.vanish", 1.0F, 1.0F);
        worldObj.playSoundAtEntity(this, "undeadPlusAudio.mobs.zombrine.vanish", 1.0F, 1.0F);
        return true;
    }
    
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if (damagesource instanceof EntityDamageSource)
        {
            for (int j = 0; j < 64; j++)
            {
                if (teleportRandomly())
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            return super.attackEntityFrom(damagesource, i);
        }
    }
    
    protected String getLivingSound()
    {
        return "undeadPlusAudio.mobs.zombrine.growl";
    }
    
    public boolean isAIEnabled()
    {
    	return true;
    }

}