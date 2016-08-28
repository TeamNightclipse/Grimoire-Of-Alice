package arekkuusu.grimoireOfAlice.client.render;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntitySpellFX extends EntityFX {

	protected EntitySpellFX(World world, double x, double y, double z, int maxAge) {
		this(world, x, y, z, 1.0F, maxAge);
	}
		
	public EntitySpellFX(World world, double x, double y, double z, float f, int maxAge) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.particleMaxAge = 10;
	}
	
	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.renderParticle(par1Tessellator, par7, par7, par7, par7, par7, par7);
	}

	@Override
	public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.99D;
        this.motionY *= 0.99D;
        this.motionZ *= 0.99D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }
	
}
