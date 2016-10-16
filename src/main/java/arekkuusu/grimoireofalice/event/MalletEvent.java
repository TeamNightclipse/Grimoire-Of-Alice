package arekkuusu.grimoireofalice.event;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MalletEvent {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Pre event) {
		EntityLivingBase e = event.getEntity();
		if(e.getEntityData().hasKey("MalletResized")) {
			float size = e.getEntityData().getFloat("MalletResized");
			GlStateManager.pushMatrix();
			if(size > 0 && size != 1) {
				GlStateManager.scale(size, size, size);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderLivingFinished(RenderLivingEvent.Post event) {
		EntityLivingBase e = event.getEntity();
		if(e.getEntityData().hasKey("MalletResized")) {
			GlStateManager.popMatrix();
		}
	}
}
