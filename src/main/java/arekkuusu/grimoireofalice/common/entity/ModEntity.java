/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.lib.LibMod;
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

	private static int id;

	public static void preInit() {
		register(EntityNazrinPendulum.class, "pendulum");
		register(EntityMagicCircle.class, "spell");
		register(EntityDragonJewel.class, "jewel");
		register(EntityUnzanFist.class, "fist");
		register(EntityEllyScythe.class, "scythe");
		register(EntityCursedDecoyDoll.class, "doll");
		register(EntityHakureiOrb.class, "orb");
		register(EntityBarrier.class, "barrier");
		register(EntityMiracleLantern.class, "lantern");
		register(EntityNetherSoul.class, "soul");
		register(EntityFierySword.class, "fiery_sword");
		register(EntityIceBlock.class, "ice_block");
		register(EntityMiracleCircle.class, "mirale_circle");
		register(EntitySpiritualStrikeTalisman.class, "talisman");
		register(EntityGap.class, "gap");
		register(EntityKinkakuJiCeiling.class, "ceiling");
		register(EntityYoukaiBook.class, "youkai");
		register(EntityCameraSquare.class, "camera");
		register(EntityStopWatch.class, "watch");

		//Natural spawns
		if(ConfigHandler.grimoireOfAlice.spawning.bookSpawning) {
			ImmutableMap.Builder<Type, Integer> builder = ImmutableMap.builder();
			builder.put(Type.MAGICAL, 25);
			builder.put(Type.SPOOKY, 20);
			builder.put(Type.MOUNTAIN, 20);
			builder.put(Type.PLAINS, 20);
			builder.put(Type.FOREST, 20);
			builder.put(Type.SNOWY, 20);

			for(Map.Entry<Type, Integer> entry : builder.build().entrySet()) {
				Set<Biome> biomeSet = BiomeDictionary.getBiomes(entry.getKey());
				EntityRegistry.addSpawn(EntityYoukaiBook.class, entry.getValue(),
						ConfigHandler.grimoireOfAlice.spawning.minBooks,
						ConfigHandler.grimoireOfAlice.spawning.maxBooks,
						EnumCreatureType.AMBIENT,
						biomeSet.toArray(new Biome[biomeSet.size()]));
			}
		}
	}

	private static void register(Class<? extends Entity> clazz, String name) {
		ResourceLocation resource = new ResourceLocation(LibMod.MOD_ID, name);
		EntityRegistry.registerModEntity(resource, clazz, name, ++id, Alice.instance, 64, 1, true);
	}
}
