package arekkuusu.grimoireofalice.client.handler;

import arekkuusu.grimoireofalice.common.core.capability.IMalletCapability;
import arekkuusu.grimoireofalice.common.core.capability.MalletProvider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static arekkuusu.grimoireofalice.common.core.handler.ConfigHandler.grimoireOfAlice;

@SideOnly(Side.CLIENT)
@SuppressWarnings("ConstantConditions")
public class MalletClientEvent {

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (grimoireOfAlice.features.shrinkAnimation && entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability capability = entity.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			if (capability.doAnimation()) {
				int scale = capability.getScaled();
				if (capability.isSmall() && scale > 0) {
					capability.setScaled(--scale);
				} else
				if (!capability.isSmall() && scale < 25) {
					capability.setScaled(++scale);
				}
				else {
					capability.doAnimation(false);
				}
			}
		}
	}

	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Pre event) {
		EntityLivingBase entity = event.getEntity();
		if (grimoireOfAlice.features.shrinkAnimation && entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability capability = entity.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			GlStateManager.pushMatrix();
			if (capability.doAnimation()) {
				double scale = 0.1D + ((double) capability.getScaled() / 25D);
				GlStateManager.scale(scale, scale, scale);
			} else
			if(capability.isSmall()) {
				GlStateManager.scale(0.1D, 0.1D, 0.1D);
			}
		}
	}

	@SubscribeEvent
	public void onRenderLivingFinished(RenderLivingEvent.Post event) {
		EntityLivingBase entity = event.getEntity();
		if (grimoireOfAlice.features.shrinkAnimation && entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			GlStateManager.popMatrix();
		}
	}
}
