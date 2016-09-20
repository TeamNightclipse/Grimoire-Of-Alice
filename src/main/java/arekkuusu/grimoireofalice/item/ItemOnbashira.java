/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.item.auras.ItemAuraKanako;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemOnbashira extends ItemModSword {

	public ItemOnbashira(ToolMaterial material) {
		super(material, LibItemName.KANAKOONBASHIRA);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Leg of an Ent sealed with magic.");
		list.add(TextFormatting.DARK_AQUA + "Its massive density make it almost unwieldable");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(isSelected) {
				ItemStack armorItemInSlot = player.inventory.armorItemInSlot(2);
				if(armorItemInSlot != null && armorItemInSlot.getItem() instanceof ItemAuraKanako) {
					player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 0));
				} else {
					player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0, 5));
				}
			}
		}
	}
	
}
