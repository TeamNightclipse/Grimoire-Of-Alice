package arekkuusu.grimoireofalice.common.core.network;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(LibMod.MOD_ID);
	private static int id = 0;

	public static void init() {
		register(MalletMessage.MalletMessageHandler.class, MalletMessage.class, Side.CLIENT);
	}

	private static <H extends IMessageHandler<M, IMessage>, M extends IMessage> void register(Class<H> handler, Class<M> message, Side side) {
		INSTANCE.registerMessage(handler, message, id++, side);
	}

	public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint target) {
		INSTANCE.sendToAllAround(message, target);
	}

	public static void sendTo(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}

	public static NetworkRegistry.TargetPoint fromWorldPos(World world, BlockPos pos, int range) {
		return new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range);
	}

	public static NetworkRegistry.TargetPoint fromTileEntity(TileEntity te, int range) {
		return new NetworkRegistry.TargetPoint(te.getWorld().provider.getDimension(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), range);
	}

	public static NetworkRegistry.TargetPoint fromEntity(Entity entity, int range) {
		return new NetworkRegistry.TargetPoint(entity.world.provider.getDimension(), entity.posX, entity.posY, entity.posZ, range);
	}
}
