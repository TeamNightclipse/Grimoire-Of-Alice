/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.render.model.ModelKanakoShimenawa;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKanakoShimenawa extends ItemBaseArmor implements ISpecialArmor {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemKanakoShimenawa(ArmorMaterial material, int dmg) {
		super(material, dmg, LibName.KANAKO_SHIMENAWA, EntityEquipmentSlot.CHEST);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.isWet() && player.ticksExisted % 4 == 0) {
			world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX + (itemRand.nextDouble() - 0.5D) * player.width,
					player.posY + itemRand.nextDouble() * player.height - 0.25D, player.posZ + (itemRand.nextDouble() - 0.5D) * player.width,
					(itemRand.nextFloat() - 0.5D) * 0.2, -itemRand.nextFloat(), (itemRand.nextFloat() - 0.5D) * 0.2);
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(5, 15, 6);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped imodel) {
		if(model == null) {
			model = new ModelKanakoShimenawa();
		}
		model.setModelAttributes(imodel);
		return model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLibrary.KANAKO_SHIMENAWA.toString();
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
