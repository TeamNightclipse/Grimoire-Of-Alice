/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.handler;

import arekkuusu.grimoireOfAlice.block.GOABlock;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

@CleanupDone
public class WorldGenPlants {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
		if((event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT) && event.type == EventType.FLOWERS) {
			for(int i = 0; i < 20; i++) {
				int x = event.chunkX + event.rand.nextInt(16) + 8;
				int z = event.chunkZ + event.rand.nextInt(16) + 8;
				int y = event.rand.nextInt(26) + 4;

				int lolis = event.rand.nextInt(16);
				if(event.world.isAirBlock(x, y, z) && GOABlock.shroom.canBlockStay(event.world, x, y, z))
					event.world.setBlock(x, y, z, GOABlock.shroom, lolis, 2);
			}
			for(int i = 0; i < 20; i++) {
				int x = event.chunkX + event.rand.nextInt(16) + 8;
				int z = event.chunkZ + event.rand.nextInt(16) + 8;
				int y = event.rand.nextInt(26) + 4;

				int lolis = event.rand.nextInt(16);
				if(event.world.isAirBlock(x, y, z) && GOABlock.kyoumarubotan.canBlockStay(event.world, x, y, z))
					event.world.setBlock(x, y, z, GOABlock.kyoumarubotan, lolis, 2);
			}
		}
	}
}
