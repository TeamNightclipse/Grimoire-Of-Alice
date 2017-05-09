package arekkuusu.grimoireofalice.common.core.net;

import arekkuusu.grimoireofalice.common.core.capability.CapabilityMallet;
import arekkuusu.grimoireofalice.common.core.capability.IMalletCapability;
import arekkuusu.grimoireofalice.common.core.capability.MalletProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MalletMessage implements IMessage {

	private IMalletCapability capability;
	private UUID target;

	public MalletMessage() {
		capability = new CapabilityMallet.DefaultMalletCapability();
	}

	public MalletMessage(IMalletCapability capability, UUID target) {
		this.capability = capability;
		this.target = target;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		capability.setSmall(buf.readBoolean());
		capability.setScaled(buf.readInt());
		capability.doAnimation(buf.readBoolean());

		target = new UUID(buf.readLong(), buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(capability.isSmall());
		buf.writeInt(capability.getScaled());
		buf.writeBoolean(capability.doAnimation());

		buf.writeLong(target.getMostSignificantBits());
		buf.writeLong(target.getLeastSignificantBits());
	}

	public static class MalletMessageHandler implements IMessageHandler<MalletMessage, IMessage> {

		@SuppressWarnings ("ConstantConditions")
		@Override
		public IMessage onMessage(MalletMessage message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				final EntityPlayer entityTarget = Minecraft.getMinecraft().world.getPlayerEntityByUUID(message.target);

				if (entityTarget != null && entityTarget.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
					IMalletCapability capability = entityTarget.getCapability(MalletProvider.MALLET_CAPABILITY, null);
					capability.setSmall(message.capability.isSmall());
					capability.setScaled(message.capability.getScaled());
					capability.doAnimation(message.capability.doAnimation());
				}
			});
			return null;
		}
	}
}
