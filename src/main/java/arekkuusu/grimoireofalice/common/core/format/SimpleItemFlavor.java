package arekkuusu.grimoireofalice.common.core.format;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class SimpleItemFlavor implements ItemFlavor {

	private final List<FormattedString> common;
	private final List<FormattedString> shift;
	private final List<FormattedString> debug;
	private final boolean effect;
	private final EnumRarity rarity;

	private SimpleItemFlavor(List<FormattedString> common, List<FormattedString> shift, List<FormattedString> debug, boolean effect, EnumRarity
			rarity) {
		this.common = ImmutableList.copyOf(common);
		this.shift = ImmutableList.copyOf(shift);
		this.debug = ImmutableList.copyOf(debug);
		this.effect = effect;
		this.rarity = rarity;
	}

	public static SimpleItemFlavor of(FormattedString... common) {
		return new SimpleItemFlavor(ImmutableList.copyOf(common), ImmutableList.of(), ImmutableList.of(), false, EnumRarity.COMMON);
	}

	public static SimpleItemFlavor of(List<FormattedString> common, boolean hasEffect, EnumRarity rarity) {
		return new SimpleItemFlavor(ImmutableList.copyOf(common), ImmutableList.of(), ImmutableList.of(), hasEffect, rarity);
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public List<FormattedString> commonFlavor(ItemStack stack, EntityPlayer player) {
		return common;
	}

	@Override
	public List<FormattedString> shiftFlavor(ItemStack stack, EntityPlayer player) {
		return shift;
	}

	@Override
	public List<FormattedString> debugFlavor(ItemStack stack, EntityPlayer player) {
		return debug;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return effect;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity;
	}

	public static class Builder {
		private List<FormattedString> common = ImmutableList.of();
		private List<FormattedString> shift = ImmutableList.of();
		private List<FormattedString> debug = ImmutableList.of();
		private boolean effect = false;
		private EnumRarity rarity = EnumRarity.COMMON;

		public Builder common(List<FormattedString> common) {
			this.common = common;
			return this;
		}

		public Builder shift(List<FormattedString> shift) {
			this.shift = shift;
			return this;
		}

		public Builder debug(List<FormattedString> debug) {
			this.debug = debug;
			return this;
		}

		public Builder common(FormattedString... common) {
			this.common = ImmutableList.copyOf(common);
			return this;
		}

		public Builder shift(FormattedString... shift) {
			this.shift = ImmutableList.copyOf(shift);
			return this;
		}

		public Builder debug(FormattedString... debug) {
			this.debug = ImmutableList.copyOf(debug);
			return this;
		}

		public Builder effect(boolean effect) {
			this.effect = effect;
			return this;
		}

		public Builder rarity(EnumRarity rarity) {
			this.rarity = rarity;
			return this;
		}

		public SimpleItemFlavor build() {
			return new SimpleItemFlavor(common, shift, debug, effect, rarity);
		}
	}
}
