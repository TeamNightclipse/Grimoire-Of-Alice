package arekkuusu.grimoireofalice;

import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nullable;

public interface ISidedProxy {
	void preInit(FMLPreInitializationEvent event);
	void init(FMLInitializationEvent event);
	void sparkleFX(ParticleFX particleFX,@Nullable Entity entity, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn);
}
