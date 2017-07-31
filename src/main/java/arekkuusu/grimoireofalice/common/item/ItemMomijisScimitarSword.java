/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import com.google.common.collect.Multimap;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@net.minecraftforge.fml.common.Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemMomijisScimitarSword extends ItemModSword implements IOwnedBy {

	public ItemMomijisScimitarSword(ToolMaterial material) {
		super(material, LibItemName.MOMIJIS_SCIMITAR_SWORD);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.momijis_scimitar_sword_header.name"));
		list.add(TextFormatting.GRAY + I18n.format("grimoire.tooltip.momijis_scimitar_sword_description.name"));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
		multiplyModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, 1);
		return modifiers;
	}

	private void multiplyModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double multiplier) {
		Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());
		Optional<AttributeModifier> modifierOptional = modifiers.stream()
				.filter(attributeModifier -> attributeModifier.getID().equals(id))
				.findFirst();

		if(modifierOptional.isPresent()) {
			AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier);
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() * multiplier, modifier.getOperation()));
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}

	@net.minecraftforge.fml.common.Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.MOMIJI_INUBASHIRI;
	}
}
