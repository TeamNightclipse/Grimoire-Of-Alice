/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntity {

	public static void preInit() {
		int modEntityID = 0;
		EntityRegistry.registerModEntity(EntityNazrinPendulum.class, "Pendulum", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityGrimoireSpell.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMagicCircle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDragonJewel.class, "Jewel", ++modEntityID, GrimoireOfAlice.instance, 64, 10, false);
		EntityRegistry.registerModEntity(EntityUnzanFist.class, "Fist", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityEllyScythe.class, "Scythe", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityCursedDecoyDoll.class, "Doll", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityHakureiOrb.class, "Orb", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityBarrier.class, "Barrier", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityMiracleLantern.class, "Lantern", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNetherSoul.class, "Soul", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityFierySword.class, "FierySword", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityIceBlock.class, "IceBlock", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMiracleCircle.class, "MiraleCircle", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntitySpiritualStrikeTalisman.class, "Talisman", ++modEntityID, GrimoireOfAlice.instance, 64, 10, false);
		EntityRegistry.registerModEntity(EntityGap.class, "Gap", ++modEntityID, GrimoireOfAlice.instance, 64, 10, false);
		EntityRegistry.registerModEntity(EntityKinkakuJiCeiling.class, "Ceiling", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			EntityRegistry.registerModEntity(EntityCameraSquare.class, "Camera", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
			EntityRegistry.registerModEntity(EntityStopWatch.class, "Watch", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		}
	}
}
