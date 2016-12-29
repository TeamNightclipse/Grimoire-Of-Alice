package arekkuusu.grimoireofalice.common.core;

import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import com.sun.istack.internal.NotNull;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public interface ISidedProxy {
	void preInit(FMLPreInitializationEvent event);
	void init(FMLInitializationEvent event);
	void sparkleFX(ParticleFX particleFX, @Nullable Entity entity, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn);
	@SideOnly(Side.CLIENT)
	void displayRecordText(@NotNull String i18Format, TextFormatting... color);
}
