/*******************************************************************************
 * Arekkuusu / Solar 2017
 *
 * This project is licensed under the MIT.
 * The source code is available on github: 
 ******************************************************************************/
package arekkuusu.grimoireofalice.client.effect;
import net.katsstuff.teamnightclipse.mirror.client.particles.AbstractMirrorParticle;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleBase extends AbstractMirrorParticle {

	private int layer = 3;

	ParticleBase(World world, Vector3 pos, Vector3 speed) {
		super(world, pos, speed);
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
