/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util.resource;

import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class ResourceBuilder {

	public static ResourceLocation[] toArray(int amount, String name, Function<String, ResourceLocation> function) {
		ResourceLocation[] locations = new ResourceLocation[amount];
		for(int i = 0; i < amount; i++) {
			locations[i] = function.apply(name + i);
		}
		return locations;
	}
}
