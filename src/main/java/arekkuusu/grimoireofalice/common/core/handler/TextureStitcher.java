package arekkuusu.grimoireofalice.common.core.handler;

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
		event.getMap().registerSprite(ResourceLocations.RED_GAS);
		event.getMap().registerSprite(ResourceLocations.NETHER_FIRE);
	}
}
