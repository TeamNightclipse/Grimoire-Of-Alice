/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.fx;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NetherFire extends Particle {

	public NetherFire(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		particleScale = 4;
		particleMaxAge = 10;

		TextureAtlasSprite atlasSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ResourceLocations.NETHER_FIRE.toString());

		setParticleTexture(atlasSprite);
	}

	@Override
	public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ,
			float rotationXY, float rotationXZ) {
		particleScale *= 0.9;
		super.renderParticle(worldRendererIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public boolean shouldDisableDepth() {
		return true;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge) {
			setExpired();
		}
	}
}
