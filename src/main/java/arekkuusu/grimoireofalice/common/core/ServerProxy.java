/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.core;

import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements ISidedProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void displayRecordText(ITextComponent text) {
	}

	@Override
	public void spawnNeedleSwing(World world, Vector3 pos, Vector3 speed, int age, float scale) {
	}

	@Override
	public void spawnNetherFire(World world, Vector3 pos, Vector3 speed, int age, float scale) {
	}

	@Override
	public void spawnRedGas(World world, Vector3 pos, Vector3 speed) {
	}

	@Override
	public void spawnRedMist(World world, Entity entity, Vector3 pos, Vector3 speed) {
	}

	@Override
	public void spawnShinmyoumaruSpark(World world, Vector3 pos, Vector3 speed) {
	}
}
