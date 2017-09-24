package arekkuusu.grimoireofalice.common.core.format;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class AppendItemFlavor implements ItemFlavor {

	private final ItemFlavor first;
	private final ItemFlavor second;
	private final boolean oldEffect;
	private final boolean oldRarity;

	private AppendItemFlavor(ItemFlavor first, ItemFlavor second, boolean oldEffect, boolean oldRarity) {
		this.first = first;
		this.second = second;
		this.oldEffect = oldEffect;
		this.oldRarity = oldRarity;
	}

	public static AppendItemFlavor of(ItemFlavor first, ItemFlavor second, boolean oldEffect, boolean oldRarity) {
		return new AppendItemFlavor(first, second, oldEffect, oldRarity);
	}

	public static AppendItemFlavor of(ItemFlavor first, ItemFlavor second) {
		return of(first, second, true, true);
	}

	@Override
	public List<FormattedString> commonFlavor(ItemStack stack, EntityPlayer player) {
		ImmutableList.Builder<FormattedString> b = ImmutableList.builder();
		b.addAll(first.commonFlavor(stack, player));
		b.addAll(second.commonFlavor(stack, player));
		return b.build();
	}

	@Override
	public List<FormattedString> shiftFlavor(ItemStack stack, EntityPlayer player) {
		ImmutableList.Builder<FormattedString> b = ImmutableList.builder();
		b.addAll(first.shiftFlavor(stack, player));
		b.addAll(second.shiftFlavor(stack, player));
		return b.build();
	}

	@Override
	public List<FormattedString> debugFlavor(ItemStack stack, EntityPlayer player) {
		ImmutableList.Builder<FormattedString> b = ImmutableList.builder();
		b.addAll(first.debugFlavor(stack, player));
		b.addAll(second.debugFlavor(stack, player));
		return b.build();
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return oldEffect ? first.hasEffect(stack) : second.hasEffect(stack);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return oldRarity ? first.getRarity(stack) : second.getRarity(stack);
	}
}
