package arekkuusu.grimoireofalice.common.core.net;

import arekkuusu.grimoireofalice.common.core.capability.HouraiProvider;
import io.netty.buffer.ByteBuf;
import net.katsstuff.danmakucore.helper.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MessageHouraiUpdate implements IMessage {

	private NBTTagCompound tagCompound;
	private UUID uuid;

	public MessageHouraiUpdate() {
	}

	public MessageHouraiUpdate(NBTTagCompound tag, EntityPlayer player) {
		tagCompound = tag;
		uuid = player.getUniqueID();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		tagCompound = ByteBufUtils.readTag(buf);
		uuid = new UUID(buf.readLong(), buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tagCompound);
		buf.writeLong(uuid.getMostSignificantBits());
		buf.writeLong(uuid.getLeastSignificantBits());
	}

	public static class Handler implements IMessageHandler<MessageHouraiUpdate, IMessage> {

		@Override
		public IMessage onMessage(final MessageHouraiUpdate message, final MessageContext context) {
			IThreadListener mainThread = context.side.isClient() ? Minecraft.getMinecraft() : (WorldServer) context.getServerHandler().playerEntity.worldObj;
			mainThread.addScheduledTask(() -> {
				EntityPlayer entityTarget = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(message.uuid);
				if (entityTarget == null) {
					entityTarget = (EntityPlayer) NBTHelper.getEntityByUUID(message.uuid, context.getServerHandler().playerEntity.worldObj).orElse(null);
				}

				if (entityTarget != null) {
					HouraiProvider.get(entityTarget).loadNBTData(message.tagCompound);
				}
			});

			return null;
		}
	}
}
