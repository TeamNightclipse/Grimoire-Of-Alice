/*******************************************************************************
 * Arekkuusu / Solar 2017
 *
 * This project is licensed under the MIT.
 * The source code is available on github: 
 ******************************************************************************/
package arekkuusu.grimoireofalice.client.effect;

import net.katsstuff.danmakucore.client.particle.AbstractParticleGlow;
import net.katsstuff.danmakucore.client.particle.IGlowParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleBase extends AbstractParticleGlow implements IGlowParticle {

	private int layer = 3;

	ParticleBase(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
	}

	public void setAtlas(ResourceLocation location) {
		TextureAtlasSprite atlasSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
		layer = 1;
		setParticleTexture(atlasSprite);
	}

	@Override
	public int getFXLayer() {
		return layer;
	}

	@Override
	public boolean alive() {
		return isAlive();
	}

	@Override
	public boolean isAdditive() {
		return true;
	}

	@Override
	public boolean ignoreDepth() {
		return false;
	}
}
