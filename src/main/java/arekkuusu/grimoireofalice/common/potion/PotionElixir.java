package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.common.lib.LibPotionName;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionElixir extends PotionMod {

	public PotionElixir() {
		super(LibPotionName.ELIXIRPOTION, false, 0x0078E4, 1);
		setBeneficial();
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {}
}
