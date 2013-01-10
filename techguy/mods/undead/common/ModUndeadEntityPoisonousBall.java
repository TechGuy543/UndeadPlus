package techguy.mods.undead.common;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ModUndeadEntityPoisonousBall extends Entity
{
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private boolean inGround;
    public EntityLiving shootingEntity;
    private int ticksAlive;
    private int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

    public ModUndeadEntityPoisonousBall(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        ticksInAir = 0;
        setSize(1.0F, 1.0F);
    }

    protected void entityInit()
    {
    }

    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength() * 4D;
        d1 *= 64D;
        return d < d1 * d1;
    }

    public ModUndeadEntityPoisonousBall(World world, double d, double d1, double d2,
            double d3, double d4, double d5)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        ticksInAir = 0;
        setSize(1.0F, 1.0F);
        setLocationAndAngles(d, d1, d2, rotationYaw, rotationPitch);
        setPosition(d, d1, d2);
        double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
        accelerationX = (d3 / d6) * 0.10000000000000001D;
        accelerationY = (d4 / d6) * 0.10000000000000001D;
        accelerationZ = (d5 / d6) * 0.10000000000000001D;
    }

    public ModUndeadEntityPoisonousBall(World world, EntityLiving entityliving, double d, double d1, double d2)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inGround = false;
        ticksInAir = 0;
        shootingEntity = entityliving;
        setSize(1.0F, 1.0F);
        setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = motionY = motionZ = 0.0D;
        d += rand.nextGaussian() * 0.40000000000000002D;
        d1 += rand.nextGaussian() * 0.40000000000000002D;
        d2 += rand.nextGaussian() * 0.40000000000000002D;
        double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        accelerationX = (d / d3) * 0.10000000000000001D;
        accelerationY = (d1 / d3) * 0.10000000000000001D;
        accelerationZ = (d2 / d3) * 0.10000000000000001D;
    }

    public void onUpdate()
    {
        if (!this.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
        {
            this.setDead();
        }
        else
        {
            super.onUpdate();

            if (this.inGround)
            {
                int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

                if (var1 == this.inTile)
                {
                    ++this.ticksAlive;

                    if (this.ticksAlive == 600)
                    {
                        this.setDead();
                    }

                    return;
                }

                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3 var15 = worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 var2 = worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
            var15 = worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            var2 = worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (var3 != null)
            {
                var2 = worldObj.getWorldVec3Pool().getVecFromPool(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
            }

            Entity var4 = null;
            List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;
            Iterator var8 = var5.iterator();

            while (var8.hasNext())
            {
                Entity var9 = (Entity)var8.next();

                if (var9.canBeCollidedWith() && (!var9.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25))
                {
                    float var10 = 0.3F;
                    AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
                    MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);

                    if (var12 != null)
                    {
                        double var13 = var15.distanceTo(var12.hitVec);

                        if (var13 < var6 || var6 == 0.0D)
                        {
                            var4 = var9;
                            var6 = var13;
                        }
                    }
                }
            }

            if (var4 != null)
            {
                var3 = new MovingObjectPosition(var4);
            }


            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var16) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float var17 = 0.95F;

            if (this.isInWater())
            {
                for (int var19 = 0; var19 < 4; ++var19)
                {
                    float var18 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var18, this.posY - this.motionY * (double)var18, this.posZ - this.motionZ * (double)var18, this.motionX, this.motionY, this.motionZ);
                }

                var17 = 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= (double)var17;
            this.motionY *= (double)var17;
            this.motionZ *= (double)var17;
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    
    protected void onImpact(MovingObjectPosition movingobjectposition)
    {
        if (!worldObj.isRemote)
        {
            if (movingobjectposition.entityHit != null)
            {
                if(movingobjectposition.entityHit instanceof EntityPlayer)
                {
                	((EntityLiving)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 3 * 20, 0));
                }
            }
            
            setDead();
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        setBeenAttacked();
        if (damagesource.getEntity() != null)
        {
            Vec3 vec3d = damagesource.getEntity().getLookVec();
            if (vec3d != null)
            {
                motionX = vec3d.xCoord;
                motionY = vec3d.yCoord;
                motionZ = vec3d.zCoord;
                accelerationX = motionX * 0.10000000000000001D;
                accelerationY = motionY * 0.10000000000000001D;
                accelerationZ = motionZ * 0.10000000000000001D;
            }
            if (damagesource.getEntity() instanceof EntityLiving)
            {
                shootingEntity = (EntityLiving)damagesource.getEntity();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

}
         