package arekkuusu.grimoireofalice.common.core;

import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ISidedProxy {
	void preInit(FMLPreInitializationEvent event);

	void init(FMLInitializationEvent event);

	void displayRecordText(ITextComponent text);

	void spawnNeedleSwing(World world, Vector3 pos, Vector3 speed, int age, float scale);

	void spawnNetherFire(World world, Vector3 pos, Vector3 speed, int age, float scale);

	void spawnRedGas(World world, Vector3 pos, Vector3 speed);

	void spawnRedMist(World world, Entity entity, Vector3 pos, Vector3 speed);

	void spawnShinmyoumaruSpark(World world, Vector3 pos, Vector3 speed);
}
