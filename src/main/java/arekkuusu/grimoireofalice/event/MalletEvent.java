package arekkuusu.grimoireofalice.event;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MalletEvent {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Pre event) {
		EntityLivingBase e = event.getEntity();
		if (e.getEntityData().hasKey("MalletResized")) {
			float size = e.getEntityData().getFloat("MalletResized");
			GlStateManager.pushMatrix();
			if (size > 0 && size != 1) {
				GlStateManager.scale(size, size, size);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderLivingFinished(RenderLivingEvent.Post event) {
		EntityLivingBase e = event.getEntity();
		if (e.getEntityData().hasKey("MalletResized")) {
			GlStateManager.popMatrix();
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.getEntityData().hasKey("MalletResized")) {
			float modifier = player.getEntityData().getFloat("MalletResized");

			AxisAlignedBB axisAlignedBB = player.getEntityBoundingBox(); //Get Bounding Box
			double minX = axisAlignedBB.minX;
			double minY = axisAlignedBB.minY;
			double minZ = axisAlignedBB.minZ;
			axisAlignedBB = new AxisAlignedBB(minX, minY, minZ, modifier == 0.5 ? minX + 0.8 : minX + modifier * 0.8, modifier == 0.5 ? minY + 0.5 : minY + modifier * 2, modifier == 0.5 ? minZ + 0.8 : minZ + modifier * 0.8); //Expand bounding Box
			player.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box
		}
	}
}
