/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.entity.EntityCameraSquare;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.entity.EntityStopWatch;
import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.Map;
import java.util.Set;

import static net.minecraftforge.common.BiomeDictionary.Type;

public class ModEntity {

    public static void preInit() {
        int modEntityID = 0;
        registerEntity(EntityNazrinPendulum.class, "pendulum", ++modEntityID, 64, 1, true);
        registerEntity(EntityGrimoireSpell.class, "spell", ++modEntityID, 64, 10, true);
        registerEntity(EntityMagicCircle.class, "spell", ++modEntityID, 64, 10, true);
        registerEntity(EntityDragonJewel.class, "jewel", ++modEntityID, 64, 10, false);
        registerEntity(EntityUnzanFist.class, "fist", ++modEntityID, 64, 1, true);
        registerEntity(EntityEllyScythe.class, "scythe", ++modEntityID, 64, 1, true);
        registerEntity(EntityCursedDecoyDoll.class, "doll", ++modEntityID, 64, 10, true);
        registerEntity(EntityHakureiOrb.class, "orb", ++modEntityID, 64, 1, true);
        registerEntity(EntityBarrier.class, "barrier", ++modEntityID, 64, 1, true);
        registerEntity(EntityMiracleLantern.class, "lantern", ++modEntityID, 64, 1, true);
        registerEntity(EntityNetherSoul.class, "soul", ++modEntityID, 64, 1, true);
        registerEntity(EntityFierySword.class, "fiery_sword", ++modEntityID, 64, 1, true);
        registerEntity(EntityIceBlock.class, "ice_block", ++modEntityID, 64, 10, true);
        registerEntity(EntityMiracleCircle.class, "mirale_circle", ++modEntityID, 64, 10, true);
        registerEntity(EntitySpiritualStrikeTalisman.class, "talisman", ++modEntityID, 64, 10, false);
        registerEntity(EntityGap.class, "gap", ++modEntityID, 64, 10, false);
        registerEntity(EntityKinkakuJiCeiling.class, "ceiling", ++modEntityID, 64, 10, true);
        registerEntity(EntityYoukaiBook.class, "youkai", ++modEntityID, 64, 1, true);
        if (GrimoireOfAlice.danmakuCoreInstalled) {
            registerEntity(EntityCameraSquare.class, "camera", ++modEntityID, 64, 10, true);
            registerEntity(EntityStopWatch.class, "watch", ++modEntityID, 64, 1, true);
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
                Set<Biome> biomeSet = BiomeDictionary.getBiomes(entry.getKey());
                EntityRegistry.addSpawn(EntityYoukaiBook.class, entry.getValue(),
                        ConfigHandler.grimoireOfAlice.spawning.minBooks,
                        ConfigHandler.grimoireOfAlice.spawning.maxBooks,
                        EnumCreatureType.AMBIENT,
                        biomeSet.toArray(new Biome[biomeSet.size()]));
            }
        }
    }

    private static void registerEntity(Class<? extends Entity> clazz, String name, int id, int tracingRange, int updateFrequency, boolean sendVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation(LibMod.MODID, name), clazz, name, id, GrimoireOfAlice.instance, tracingRange, updateFrequency, sendVelocityUpdates);
    }
}
