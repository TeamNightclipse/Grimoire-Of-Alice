/*******************************************************************************
 * Arekkuusu / Solar 2017
 *
 * This project is licensed under the MIT.
 * The source code is available on github: 
 ******************************************************************************/
package arekkuusu.grimoireofalice.client.effect;

import arekkuusu.grimoireofalice.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleUtil {

	public static void spawnNeedleSwing(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale) {
		if(doParticle()) {
			NeedleSwing particle = new NeedleSwing(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, age, scale);
			ClientProxy.PARTICLE_RENDERER.add(particle);
		}
	}

	public static void spawnNetherFire(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale) {
		if(doParticle()) {
			NetherFire particle = new NetherFire(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, age, scale);
			ClientProxy.PARTICLE_RENDERER.add(particle);
		}
	}

	public static void spawnRedGas(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		if(doParticle()) {
			RedGas particle = new RedGas(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
			ClientProxy.PARTICLE_RENDERER.add(particle);
		}
	}

	public static void spawnRedMist(World world, Entity entity, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		if(doParticle()) {
			RedMist particle = new RedMist(world, entity, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
			ClientProxy.PARTICLE_RENDERER.add(particle);
		}
	}

	public static void spawnShinmyoumaruSpark(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		if(doParticle()) {
			ShinmyoumaruSpark particle = new ShinmyoumaruSpark(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
			ClientProxy.PARTICLE_RENDERER.add(particle);
		}
	}

	@SideOnly(Side.CLIENT)
	private static boolean doParticle() {
		float chance = 1F;
		if(Minecraft.getMinecraft().gameSettings.particleSetting == 1)
			chance = 0.6F;
		else if(Minecraft.getMinecraft().gameSettings.particleSetting == 2)
			chance = 0.2F;

		return chance == 1F || Math.random() < chance;
	}
}
