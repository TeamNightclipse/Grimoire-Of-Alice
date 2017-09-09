package arekkuusu.grimoireofalice.common.core;

import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ISidedProxy {
	void preInit(FMLPreInitializationEvent event);

	void init(FMLInitializationEvent event);

	void displayRecordText(ITextComponent text);
}
