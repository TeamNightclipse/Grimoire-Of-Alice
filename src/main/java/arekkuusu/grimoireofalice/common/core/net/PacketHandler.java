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

	private PacketHandler() {
	}

	public static void init() {
		HANDLER.registerMessage(MalletMessage.MalletMessageHandler.class, MalletMessage.class, 0, Side.CLIENT);
	}

	public static void sendToServer(IMessage message) {
		HANDLER.sendToServer(message);
	}

	public static void sendTo(EntityPlayerMP player, IMessage message) {
		HANDLER.sendTo(message, player);
	}

	public static void sendToNear(Entity entity, IMessage message) {
		sendToNear(entity.getEntityWorld(), new BlockPos(entity), message);
	}

	public static void sendToNear(World world, BlockPos pos, IMessage message) {
		if(world instanceof WorldServer) {
			final WorldServer ws = (WorldServer) world;

			for(EntityPlayer player : ws.playerEntities) {
				final EntityPlayerMP playerMP = (EntityPlayerMP) player;

				if(playerMP.getDistanceSq(pos) < 64 * 64
						&& ws.getPlayerChunkMap().isPlayerWatchingChunk(playerMP, pos.getX() >> 4, pos.getZ() >> 4)) {
					HANDLER.sendTo(message, playerMP);
				}
			}
		}
	}
}
