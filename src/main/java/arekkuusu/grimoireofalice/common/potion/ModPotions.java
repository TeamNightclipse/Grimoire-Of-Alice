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
