/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import arekkuusu.grimoireofalice.api.items.IItemData;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import com.google.common.collect.Multimap;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@net.minecraftforge.fml.common.Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemMochiHammer extends ItemModSword implements IItemData, IOwnedBy {

	public ItemMochiHammer(ToolMaterial material) {
		super(material, LibItemName.MOCHI_HAMMER);
		addPropertyOverride(new ResourceLocation("deaths"),
				(stack, world, entity) -> stack.hasTagCompound() ? MathHelper.clamp((float) getData(stack), 0.0F, 90F) : 0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.mochi_hammer_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.mochi_hammer_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.mochi_hammer_description_mid.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.mochi_hammer_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.mochi_hammer_shift.name"));
		}
		list.add(TextFormatting.LIGHT_PURPLE + I18n.format("grimoire.tooltip.mochi_hammer_description.name") + " " + getData(stack));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
		multiplyModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER);
		return modifiers;
	}

	private void multiplyModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id) {
		Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());
		Optional<AttributeModifier> modifierOptional = modifiers.stream()
				.filter(attributeModifier -> attributeModifier.getID().equals(id))
				.findFirst();

		if(modifierOptional.isPresent()) {
			AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier);
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() * 0.2, modifier.getOperation()));
		}
	}

	@Override
	public void setData(ItemStack itemStack, int kills) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setInteger("Lunatic", kills);
		}
		else if(nbt.getInteger("Lunatic") >= 0) {
			nbt.setInteger("Lunatic", kills);
		}
	}

	@Override
	public int getData(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger("Lunatic");
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Item.getItemFromBlock(Blocks.PLANKS);
	}

	@net.minecraftforge.fml.common.Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.SEIRAN;
	}
}