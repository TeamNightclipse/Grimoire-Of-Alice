/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.common.lib.LibPotionName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class PotionRadiationPoisoning extends PotionMod {

	public PotionRadiationPoisoning() {
		super(LibPotionName.RADIATION_POISONING, true, 0xB934E4, 0);
		registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "22652B89-116E-49DC-9B6B-9971189B5CE5", 0.0D, 0);
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
		if(entityLivingBaseIn.getHealth() > 1.0F) {
			entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 2.0F);
		}
		if(entityLivingBaseIn instanceof EntityPlayer) {
			((EntityPlayer) entityLivingBaseIn).addExhaustion(0.025F * (float) (amplifier + 1));
		}
		entityLivingBaseIn.setFire(100);
	}

	@Override
	public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		entityLivingBaseIn.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 500));
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration % 4 == 0;
	}

	@Override
	public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
		return -4.0D * (double) (amplifier + 1);
	}
}
