/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.event;

import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.core.capability.IMalletCapability;
import arekkuusu.grimoireofalice.common.core.capability.MalletProvider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

import static arekkuusu.grimoireofalice.common.core.handler.ConfigHandler.grimoireOfAlice;

@SideOnly(Side.CLIENT)
@SuppressWarnings("ConstantConditions")
public class MalletClientEvent {

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(grimoireOfAlice.features.shrinkAnimation && entity.world.isRemote && entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability capability = entity.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			if(capability.doAnimation()) {
				int scale = capability.getScaled();

				for(int i = 0; i < 10; ++i) {
					Random rand = entity.world.rand;
					Alice.proxy.spawnShinmyoumaruSpark(entity.world,
							entity.posX + (rand.nextDouble() - 0.5D) * entity.width,
							entity.posY + rand.nextDouble() * (entity.height * ((double) scale / 10)),
							entity.posZ + (rand.nextDouble() - 0.5D) * entity.width,
							(rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
				}

				if(capability.isSmall() && scale > 0) {
					capability.setScaled(--scale);
				}
				else if(!capability.isSmall() && scale < 10) {
					capability.setScaled(++scale);
				}

				if((capability.isSmall() && scale <= 0) || scale >= 10) {
					capability.doAnimation(false);
				}
			}
		}
	}

	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Pre event) {
		EntityLivingBase entity = event.getEntity();
		if(grimoireOfAlice.features.shrinkAnimation && entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			IMalletCapability capability = entity.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			GlStateManager.pushMatrix();
			if(capability.doAnimation()) {
				double scale = 0.1D + ((double) capability.getScaled() / 10);
				GlStateManager.scale(scale, scale, scale);
			}
			else if(capability.isSmall()) {
				GlStateManager.scale(0.1D, 0.1D, 0.1D);
			}
		}
	}

	@SubscribeEvent
	public void onRenderLivingFinished(RenderLivingEvent.Post event) {
		EntityLivingBase entity = event.getEntity();
		if(grimoireOfAlice.features.shrinkAnimation && entity.hasCapability(MalletProvider.MALLET_CAPABILITY, null)) {
			GlStateManager.popMatrix();
		}
	}
}
