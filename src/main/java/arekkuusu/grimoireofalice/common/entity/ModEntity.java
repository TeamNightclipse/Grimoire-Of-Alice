package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntity {

	public static void preInit() {
		int modEntityID = 0;
		EntityRegistry.registerModEntity(EntityNazrinPendulum.class, "Pendulum", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityGrimoireSpell.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMagicCircle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNeedle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDragonJewel.class, "Jewel", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityUnzanFist.class, "Fist", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityEllyScythe.class, "Scythe", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityCursedDecoyDoll.class, "Doll", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCameraSquare.class, "Camera", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		if (GrimoireOfAlice.danmakuCoreInstalled)
			EntityRegistry.registerModEntity(EntityStopWatch.class, "Watch", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityHakureiOrb.class, "Orb", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityBarrier.class, "Barrier", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityMiracleLantern.class, "Lantern", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
	}
}
