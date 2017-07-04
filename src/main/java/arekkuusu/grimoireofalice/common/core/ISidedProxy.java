package arekkuusu.grimoireofalice.common.core;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ISidedProxy {
	void preInit(FMLPreInitializationEvent event);

	void init(FMLInitializationEvent event);

	void sparkleFX(ParticleFX particleFX, @Nullable Entity entity, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn);

	void displayRecordText(ITextComponent text);
}
