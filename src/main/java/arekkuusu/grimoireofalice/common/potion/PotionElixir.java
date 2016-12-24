package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.lib.LibPotionName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class PotionElixir extends Potion {

	public PotionElixir() {
		super(false, 3381504);
		setRegistryName(LibPotionName.ELIXIRPOTION);
		setPotionName("effect." + LibMod.MODID + ".elixir");
		setBeneficial();
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {}
}
