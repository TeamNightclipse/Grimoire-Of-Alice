/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.lib.LibPotionName;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public class ModPotions {

	@ObjectHolder(LibPotionName.ELIXIRPOTION)
	public static final Potion ELIXIR = new PotionElixir();
	@ObjectHolder(LibPotionName.RADIATION_POISONING)
	public static final Potion RADIATION_POISONING = new PotionRadiationPoisoning();
}
