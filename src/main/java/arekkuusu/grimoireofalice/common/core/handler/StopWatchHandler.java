package arekkuusu.grimoireofalice.common.core.handler;

import java.util.List;

import arekkuusu.grimoireofalice.common.entity.EntityStopWatch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//TODO: Optimize
public class StopWatchHandler {

	@SubscribeEvent
	public void onTick(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if(!living.worldObj.isRemote) {
			List<EntityStopWatch> clocks = living.worldObj.getEntitiesWithinAABB(EntityStopWatch.class,
					living.getEntityBoundingBox().expandXyz(EntityStopWatch.RANGE));

			if(!clocks.isEmpty()) {
				if(living instanceof EntityPlayer && clocks.stream().flatMap(e -> e.getPlayers().stream()).anyMatch(
						uuid -> uuid.equals(living.getUniqueID()))) {
					return;
				}

				event.setCanceled(true);
			}
		}
	}
}
