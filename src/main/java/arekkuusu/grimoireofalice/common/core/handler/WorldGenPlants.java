/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.core.handler;

import java.util.List;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGenPlants {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
		if((event.getResult() == Result.ALLOW || event.getResult() == Result.DEFAULT) && event.getType() == EventType.FLOWERS) {
			int kyo = ConfigHandler.grimoireOfAlice.worldGen.shroomSpawnRate;
			for(int i = 0; i < kyo; i++) {
				int x = event.getPos().getX() + event.getRand().nextInt(16) + 8;
				int z = event.getPos().getZ() + event.getRand().nextInt(16) + 8;
				int y = event.getRand().nextInt(26) + 4;

				BlockPos pos3 = new BlockPos(x, y, z);
				List<IBlockState> states = ModBlocks.shroom.getBlockState().getValidStates();
				int type = event.getRand().nextInt(states.size());
				if(event.getWorld().isAirBlock(pos3) && ModBlocks.shroom.canPlaceBlockAt(event.getWorld(), pos3)) {
					event.getWorld().setBlockState(pos3, states.get(type), 2);
				}
			}
			int mush = ConfigHandler.grimoireOfAlice.worldGen.kyoumarubotanSpawnRate;
			for(int i = 0; i < mush; i++) {
				int x = event.getPos().getX() + event.getRand().nextInt(16) + 8;
				int z = event.getPos().getZ() + event.getRand().nextInt(16) + 8;
				int y = event.getRand().nextInt(26) + 4;

				BlockPos pos3 = new BlockPos(x, y, z);
				if(event.getRand().nextBoolean() && ModBlocks.kyoumarubotan.canPlaceBlockAt(event.getWorld(), pos3)) {
					event.getWorld().setBlockState(pos3, ModBlocks.kyoumarubotan.getDefaultState(), 2);
					break;
				}
			}
		}
	}
}