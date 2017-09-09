package arekkuusu.grimoireofalice.common.core.format;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Provides stuff which is mostly for flavor to items.
 * Currently that includes item description, item effect, and rarity.
 */
public interface ItemFlavor {

	static ItemFlavor of(FormattedString... common) {
		return SimpleItemFlavor.of(common);
	}

	static ItemFlavor of(List<FormattedString> common, boolean hasEffect, EnumRarity rarity) {
		return SimpleItemFlavor.of(common, hasEffect, rarity);
	}

	static SimpleItemFlavor.Builder simpleBuilder() {
		return SimpleItemFlavor.builder();
	}

	static ComplexItemFlavor.Builder complexBuilder() {
		return ComplexItemFlavor.builder();
	}

	/**
	 * Append the specified flavor after this one.
	 * The effect and rarity is taken from this flavor.
	 * @param second The flavor to append
	 */
	default ItemFlavor append(ItemFlavor second) {
		return append(second, true, true);
	}

	/**
	 * Append the specified flavor after this one.
	 * @param second The flavor to append
	 * @param oldEffect If true, the effect is taken from this flavor,
	 * if false it's taken from {@code second}.
	 * @param oldRarity If true, the rarity is taken from this flavor,
	 * if false it's taken from {@code second}.
	 */
	default ItemFlavor append(ItemFlavor second, boolean oldEffect, boolean oldRarity) {
		return AppendItemFlavor.of(this, second, oldEffect, oldRarity);
	}

	/**
	 * Prepend the specified flavor before this one.
	 * The effect and rarity is taken from the specified flavor.
	 * @param second The flavor to prepend
	 */
	default ItemFlavor prepend(ItemFlavor second) {
		return prepend(second, true, true);
	}

	/**
	 * Prepend the specified flavor before this one.
	 * The effect and rarity is taken from the specified flavor.
	 * @param second The flavor to prepend
	 * @param oldEffect If true, the effect is taken from this flavor,
	 * if false it's taken from {@code second}.
	 * @param oldRarity If true, the rarity is taken from this flavor,
	 * if false it's taken from {@code second}.
	 */
	default ItemFlavor prepend(ItemFlavor second, boolean oldEffect, boolean oldRarity) {
		return AppendItemFlavor.of(second, this, !oldEffect, !oldRarity);
	}

	/**
	 * The description that is always shows in hover.
	 */
	List<FormattedString> commonFlavor(ItemStack stack, EntityPlayer player);

	/**
	 * The description that is shows when shift is held down.
	 */
	List<FormattedString> shiftFlavor(ItemStack stack, EntityPlayer player);

	/**
	 * The description that is shows if debug is turned on (F3+H)
	 */
	List<FormattedString> debugFlavor(ItemStack stack, EntityPlayer player);

	/**
	 * Creates the description to show for the item.
	 * @param stack The current stack
	 * @param player The player hovering over the item
	 * @param debug If debug is turned on (F3+H)
	 * @return A list of strings representing the lines to add to the description.
	 */
	@SideOnly(Side.CLIENT)
	default List<String> createDescription(ItemStack stack, EntityPlayer player, boolean debug) {
		ImmutableList.Builder<String> builder = ImmutableList.builder();
		builder.addAll(commonFlavor(stack, player).stream().map(FormattedString::createFormattedString).collect(Collectors.toList()));
		List<FormattedString> unformattedShift = shiftFlavor(stack, player);
		if(GuiScreen.isShiftKeyDown()) {
			builder.addAll(unformattedShift.stream().map(FormattedString::createFormattedString).collect(Collectors.toList()));
		}
		else if(unformattedShift.isEmpty()) {
			builder.add(I18n.format(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "grimoire.tooltip.shift_description"));
		}

		if(debug) {
			builder.addAll(debugFlavor(stack, player).stream().map(FormattedString::createFormattedString).collect(Collectors.toList()));
		}

		return builder.build();
	}

	/**
	 * If this item has a glistering effect.
	 */
	boolean hasEffect(ItemStack stack);

	/**
	 * The rarity of the item. Affects the color of the item name.
	 */
	EnumRarity getRarity(ItemStack stack);
}
