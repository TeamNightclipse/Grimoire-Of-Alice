package arekkuusu.grimoireofalice.common.core.net;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class PacketHandler {

	private static final SimpleNetworkWrapper HANDLER = new SimpleNetworkWrapper(LibMod.MODID);

	public static void init(){
		int id = 0;
		HANDLER.registerMessage(MessageHouraiUpdate.Handler.class, MessageHouraiUpdate.class, id++, Side.CLIENT);
	}

	public static void sendToServer(IMessage message) {
		HANDLER.sendToServer(message);
	}

	public static void sendTo(EntityPlayerMP player, IMessage message){
		HANDLER.sendTo(message, player);
	}
}
