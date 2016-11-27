package arekkuusu.grimoireofalice.common.handler;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TextureStitcher {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) {
		event.getMap().registerSprite(ResourceLocations.SHINMYOUMARU_SPARKLE);
		event.getMap().registerSprite(ResourceLocations.RED_MIST);
	}
}
