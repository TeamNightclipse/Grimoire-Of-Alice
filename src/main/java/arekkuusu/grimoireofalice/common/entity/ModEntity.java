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
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.Map;

import static net.minecraftforge.common.BiomeDictionary.Type;

public class ModEntity {

    public static void preInit() {
        int modEntityID = 0;
        EntityRegistry.registerModEntity(EntityNazrinPendulum.class, "pendulum", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityGrimoireSpell.class, "spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityMagicCircle.class, "spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityDragonJewel.class, "jewel", ++modEntityID, GrimoireOfAlice.instance, 64, 10, false);
        EntityRegistry.registerModEntity(EntityUnzanFist.class, "fist", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityEllyScythe.class, "scythe", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityCursedDecoyDoll.class, "doll", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityHakureiOrb.class, "orb", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityBarrier.class, "barrier", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityMiracleLantern.class, "lantern", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityNetherSoul.class, "soul", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityFierySword.class, "fiery_sword", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityIceBlock.class, "ice_block", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityMiracleCircle.class, "mirale_circle", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntitySpiritualStrikeTalisman.class, "talisman", ++modEntityID, GrimoireOfAlice.instance, 64, 10, false);
        EntityRegistry.registerModEntity(EntityGap.class, "gap", ++modEntityID, GrimoireOfAlice.instance, 64, 10, false);
        EntityRegistry.registerModEntity(EntityKinkakuJiCeiling.class, "ceiling", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityYoukaiBook.class, "youkai", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        if (GrimoireOfAlice.danmakuCoreInstalled) {
            EntityRegistry.registerModEntity(EntityCameraSquare.class, "camera", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
            EntityRegistry.registerModEntity(EntityStopWatch.class, "watch", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
        }

        //Natural spawns
        if (ConfigHandler.grimoireOfAlice.spawning.bookSpawning) {
            ImmutableMap.Builder<Type, Integer> builder = ImmutableMap.builder();
            builder.put(Type.MAGICAL, 25);
            builder.put(Type.SPOOKY, 20);
            builder.put(Type.MOUNTAIN, 20);
            builder.put(Type.PLAINS, 20);
            builder.put(Type.FOREST, 20);
            builder.put(Type.SNOWY, 20);

            for (Map.Entry<Type, Integer> entry : builder.build().entrySet()) {
                EntityRegistry.addSpawn(EntityYoukaiBook.class, entry.getValue(),
                        ConfigHandler.grimoireOfAlice.spawning.minBooks,
                        ConfigHandler.grimoireOfAlice.spawning.maxBooks,
                        EnumCreatureType.AMBIENT,
                        BiomeDictionary.getBiomesForType(entry.getKey()));
            }
        }
    }
}
