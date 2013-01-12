package techguy.mods.undead.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ModUndeadEntityNecromancerFireball extends EntitySmallFireball
{
    public ModUndeadEntityNecromancerFireball(World world)
    {
        super(world);
        setSize(0.3125F, 0.3125F);
    }

    public ModUndeadEntityNecromancerFireball(World world, EntityLiving entityliving, double d, double d1, double d2)
    {
        super(world, entityliving, d, d1, d2);
        setSize(0.3125F, 0.3125F);
    }

    public ModUndeadEntityNecromancerFireball(World world, double d, double d1, double d2,
            double d3, double d4, double d5)
    {
        super(world, d, d1, d2, d3, d4, d5);
        setSize(0.3125F, 0.3125F);
    }
    
    protected void func_40071_a(MovingObjectPosition movingobjectposition)
    {
        if (!worldObj.isRemote)
        {
            if (movingobjectposition.entityHit != null)
            {
                if (!movingobjectposition.entityHit.isImmuneToFire() && movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, shootingEntity), 2))
                {
                    movingobjectposition.entityHit.setFire(5);
                }
            }
            setDead();
        }
    }

    public boolean canBeCollidedWith()
    {
        return false;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        return false;
    }
}
