package arekkuusu.grimoireofalice.common.core.handler;

import java.util.ArrayList;
import java.util.List;

import arekkuusu.grimoireofalice.common.entity.EntityStopWatch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StopWatchHandler {

	private static final List<EntityStopWatch> clocks = new ArrayList<>();

	public static void addClock(EntityStopWatch pos) {
		clocks.add(pos);
	}

	public static void removeClock(EntityStopWatch pos) {
		clocks.remove(pos);
	}

	@SubscribeEvent
	public void onTick(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if(!living.worldObj.isRemote && !clocks.isEmpty()) {
			AxisAlignedBB livingAABB = living.getEntityBoundingBox().expandXyz(EntityStopWatch.RANGE);

			for(EntityStopWatch clock : clocks) {
				if(livingAABB.isVecInside(clock.getPositionVector())) {
					if(living instanceof EntityPlayer && clock.getPlayers().stream().anyMatch(uuid -> uuid.equals(living.getUniqueID()))) {
						return;
					}

					event.setCanceled(true);
					return;
				}
			}
		}
	}
}
