package arekkuusu.grimoireofalice.common.core;

import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ISidedProxy {
	void preInit(FMLPreInitializationEvent event);

	void init(FMLInitializationEvent event);

	void displayRecordText(ITextComponent text);

	void spawnNeedleSwing(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale);



	void spawnNetherFire(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale);


	void spawnRedGas(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed);

	void spawnRedMist(World world, Entity entity, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed);

	void spawnShinmyoumaruSpark(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed);
}
