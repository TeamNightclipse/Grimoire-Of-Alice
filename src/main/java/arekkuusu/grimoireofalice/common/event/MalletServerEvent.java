/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.common.core.capability.IMalletCapability;
import arekkuusu.grimoireofalice.common.core.capability.MalletProvider;
import arekkuusu.grimoireofalice.common.core.net.MalletMessage;
import arekkuusu.grimoireofalice.common.core.net.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MalletServerEvent {

	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onLivingUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer entity = event.player;
		if (entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability capability = entity.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			if (capability.doAnimation()) {
				float original = entity.getDefaultEyeHeight();
				float mod = entity.eyeHeight;
				if (capability.isSmall() && entity.eyeHeight > 0) {
					entity.eyeHeight -= 0.064F;
				} else
				if (!capability.isSmall() && entity.eyeHeight < 1.68) {
					entity.eyeHeight += 0.080F;
					mod += 0.080F;
					if(entity.eyeHeight > original) {
						entity.eyeHeight = original;
					}
				}

				if((capability.isSmall() && entity.eyeHeight <= 0.32) || mod >= 1.68F) {
					float offset = capability.isSmall() ? 0.005F : -0.05F;
					capability.doAnimation(false);
					entity.setPositionAndUpdate(entity.posX + offset, entity.posY + 0.1, entity.posZ + offset);
				}
			} else
			if(capability.isSmall()) {
				entity.eyeHeight = 0.2F;
				setSize(entity, 0.3F, 0.3F);
			}
		}
	}

	protected void setSize(EntityLivingBase entity, float width, float height) {
		if (width != entity.width || height != entity.height) {
			float f = entity.width;
			entity.width = width;
			entity.height = height;
			AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
			entity.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) entity.width, axisalignedbb.minY + (double) entity.height, axisalignedbb.minZ + (double) entity.width));

			if (entity.width > f && entity.ticksExisted > 0 && !entity.world.isRemote) {
				entity.moveEntity((double) (f - entity.width), 0, (double) (f - entity.width));
			}
		}
	}
	
	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
		EntityPlayer oldPlayer = event.getOriginal();
		EntityPlayer newPlayer = event.getEntityPlayer();

		if (event.isWasDeath() && oldPlayer.hasCapability(MalletProvider.MALLET_CAPABILITY, null) && newPlayer.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability oldCap = oldPlayer.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			IMalletCapability newCap = oldPlayer.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			newCap.doAnimation(oldCap.doAnimation());
			newCap.setScaled(oldCap.getScaled());
			newCap.setSmall(oldCap.isSmall());
		}
	}

	@SuppressWarnings("ConstantConditions")
	@SubscribeEvent
	public void onWorldJoin(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			if (player.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
				IMalletCapability capability = player.getCapability(MalletProvider.MALLET_CAPABILITY, null);
				capability.markDirty();
				PacketHandler.sendToNear(player, new MalletMessage(capability, player.getUniqueID()));
			}
		}
	}
}
