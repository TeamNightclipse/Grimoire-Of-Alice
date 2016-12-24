package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.lib.LibPotionName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

public class PotionRadiationPoisoning extends Potion {

	public PotionRadiationPoisoning() {
		super(true, 3381504);
		setRegistryName(LibPotionName.RADIATION_POISONING);
		setPotionName("effect." + LibMod.MODID + ".radiation_poisoning");
		registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0D, 0);
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {
		if (entityLivingBaseIn.getHealth() > 1.0F) {
			entityLivingBaseIn.attackEntityFrom(DamageSource.magic, 2.0F);
		}
		if (entityLivingBaseIn instanceof EntityPlayer) {
			((EntityPlayer) entityLivingBaseIn).addExhaustion(0.025F * (float) (p_76394_2_ + 1));
		}
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
		return -4.0D * (double) (amplifier + 1);
	}
}
