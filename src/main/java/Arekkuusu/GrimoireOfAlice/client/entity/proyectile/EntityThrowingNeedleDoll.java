package arekkuusu.grimoireOfAlice.client.entity.proyectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityThrowingNeedleDoll extends EntityThrowable{
	
	public EntityThrowingNeedleDoll(World world){
	
		super(world);

	}
	
	public EntityThrowingNeedleDoll(World world, EntityLivingBase entity) {
		
		super(world, entity);
		
		}
	
	public EntityThrowingNeedleDoll(World world, double x, double y, double z) {
			
		super(world, x, y, z);
		
		}

	protected float getGravityVelocity(){
			
			return 0.07F;
			
		}
	
	@SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }
	
	private int explosionRadius= 1;
	
		@SideOnly(Side.CLIENT)
		@Override
	protected void onImpact(MovingObjectPosition mop) {
			
		if (mop.entityHit != null) {

		float throwDamage = 5;

		mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), throwDamage);
        if (mop.entityHit instanceof EntityLivingBase)
        {
            byte b0 = 0;

            if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
            {
                b0 = 10;
            }
            else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
            {
                b0 = 40;
            }

            if (b0 > 0)
            {
                ((EntityLivingBase)mop.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 1));
            }
		}
		
        for (int l = 0; l < 4; ++l) {
        	
        	this.worldObj.spawnParticle("magicCrit", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        	
        	}

	if (!worldObj.isRemote) {
		
		setDead();
		
			}
		}
	}		
}
