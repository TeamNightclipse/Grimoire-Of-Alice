package arekkuusu.grimoireofalice.potion;

import arekkuusu.grimoireofalice.lib.LibPotionName;
import net.katsstuff.danmakucore.lib.LibMod;
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
