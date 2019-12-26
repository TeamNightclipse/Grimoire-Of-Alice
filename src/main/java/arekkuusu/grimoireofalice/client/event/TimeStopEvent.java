package arekkuusu.grimoireofalice.client.event;

import arekkuusu.grimoireofalice.client.util.ShaderLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityStopWatch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TimeStopEvent {

	private boolean isTimeStop = false;

	@SubscribeEvent
	public void onPostRender(RenderWorldLastEvent event) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		boolean isStopwatch = !player.world.getEntitiesWithinAABB(
				EntityStopWatch.class, player.getEntityBoundingBox().grow(EntityStopWatch.RANGE)
		).isEmpty();
		if(isStopwatch) {
			startTimeStop();
		} else {
			stopTimeStop();
		}
	}

	private void startTimeStop() {
		if (!isTimeStop || !isShaderActive()) {
			Minecraft.getMinecraft().entityRenderer.loadShader(ShaderLibrary.INVERT);
			isTimeStop = true;
		}
	}

	private boolean isShaderActive() {
		ShaderGroup shaderGroup = Minecraft.getMinecraft().entityRenderer.getShaderGroup();
		//noinspection ConstantConditions
		return shaderGroup != null && shaderGroup.getShaderGroupName().equals(ShaderLibrary.INVERT.toString());
	}

	private void stopTimeStop() {
		if (isTimeStop) {
			Minecraft.getMinecraft().entityRenderer.stopUseShader();
			isTimeStop = false;
		}
	}
}
