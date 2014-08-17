package democretes.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityWindBlast extends Entity {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block block;
    private boolean inGround;
    public EntityLivingBase shootingEntity;
    private int ticksAlive;
    private int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

    public EntityWindBlast(World world) {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected void entityInit() {}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double range = this.boundingBox.getAverageEdgeLength() * 4.0D;
        range *= 64.0D;
        return distance < range * range;
    }

    public EntityWindBlast(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d6 = (double)MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        this.accelerationX = motionX / d6 * 0.1D;
        this.accelerationY = motionY / d6 * 0.1D;
        this.accelerationZ = motionZ / d6 * 0.1D;
    }

    public EntityWindBlast(World world, EntityLivingBase entity, double motionX, double motionY, double motionZ) {
        super(world);
        this.shootingEntity = entity;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(entity.posX + entity.getLookVec().xCoord*2, entity.posY + 0.5D + entity.getLookVec().yCoord*2, entity.posZ + entity.getLookVec().zCoord*2, entity.rotationYaw, entity.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        motionX += this.rand.nextGaussian() * 0.4D;
        motionY += this.rand.nextGaussian() * 0.4D;
        motionZ += this.rand.nextGaussian() * 0.4D;
        double d3 = (double)MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        this.accelerationX = motionX / d3 * 0.1D;
        this.accelerationY = motionY / d3 * 0.1D;
        this.accelerationZ = motionZ / d3 * 0.1D;
    }

    @Override
    public void onUpdate() {
        if(!this.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ))) {
            this.setDead();
        }else{
            super.onUpdate();
            this.setFire(1);
            if(this.inGround) {
                if (this.worldObj.getBlock(this.xTile, this.yTile, this.zTile) == this.block) {
                    ++this.ticksAlive;
                    if (this.ticksAlive == 600) {
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
            }else{
                ++this.ticksInAir;
            }
            
            Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition mop = this.worldObj.rayTraceBlocks(vec3, vec31);
            vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (mop != null) {
                vec31 = Vec3.createVectorHelper(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
            }

            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);

                if (entity1.canBeCollidedWith() && (!entity1.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
                    MovingObjectPosition mop2 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (mop2 != null) {
                        double distance = vec3.distanceTo(mop2.hitVec);
                        if (distance < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = distance;
                        }
                    }
                }
            }
            if (entity != null) {
                mop = new MovingObjectPosition(entity);
            }

            if (mop != null) {
                this.onImpact(mop);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

            for (this.rotationPitch = (float)(Math.atan2((double)f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F){
                ;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }
            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f2 = this.getMotionFactor();

            if (this.isInWater()) {
                for (int j = 0; j < 4; ++j) {
                    float f3 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f3, this.posY - this.motionY * (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ);
                }
                f2 = 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= (double)f2;
            this.motionY *= (double)f2;
            this.motionZ *= (double)f2;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    protected float getMotionFactor() {
        return 0.95F;
    }

    protected void onImpact(MovingObjectPosition mop) {
    	
	}

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setShort("xTile", (short)this.xTile);
        nbt.setShort("yTile", (short)this.yTile);
        nbt.setShort("zTile", (short)this.zTile);
        nbt.setByte("inTile", (byte)Block.getIdFromBlock(this.block));
        nbt.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        nbt.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        this.xTile = nbt.getShort("xTile");
        this.yTile = nbt.getShort("yTile");
        this.zTile = nbt.getShort("zTile");
        this.block = Block.getBlockById(nbt.getByte("inTile") & 255);
        this.inGround = nbt.getByte("inGround") == 1;

        if (nbt.hasKey("direction", 9))
        {
            NBTTagList nbttaglist = nbt.getTagList("direction", 6);
            this.motionX = nbttaglist.func_150309_d(0);
            this.motionY = nbttaglist.func_150309_d(1);
            this.motionZ = nbttaglist.func_150309_d(2);
        }
        else
        {
            this.setDead();
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public float getCollisionBorderSize() {
        return 1.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float f) {
        if (this.isEntityInvulnerable()) {
            return false;
        }else{
            this.setBeenAttacked();
            if (source.getEntity() != null) {
                Vec3 vec3 = source.getEntity().getLookVec();
                if (vec3 != null) {
                    this.motionX = vec3.xCoord;
                    this.motionY = vec3.yCoord;
                    this.motionZ = vec3.zCoord;
                    this.accelerationX = this.motionX * 0.1D;
                    this.accelerationY = this.motionY * 0.1D;
                    this.accelerationZ = this.motionZ * 0.1D;
                }
                if (source.getEntity() instanceof EntityLivingBase) {
                    this.shootingEntity = (EntityLivingBase)source.getEntity();
                }

                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0F;
    }

    @Override
    public float getBrightness(float p_70013_1_) {
        return 1.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_) {
        return 15728880;
    }
}
