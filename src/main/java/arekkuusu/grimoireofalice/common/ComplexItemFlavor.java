package arekkuusu.grimoireofalice.common;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ComplexItemFlavor implements ItemFlavor {

	private final BiFunction<ItemStack, EntityPlayer, List<FormattedString>> common;
	private final BiFunction<ItemStack, EntityPlayer, List<FormattedString>> shift;
	private final BiFunction<ItemStack, EntityPlayer, List<FormattedString>> debug;
	private final Function<ItemStack, Boolean> effect;
	private final Function<ItemStack, EnumRarity> rarity;

	private ComplexItemFlavor(BiFunction<ItemStack, EntityPlayer, List<FormattedString>> common,
			BiFunction<ItemStack, EntityPlayer, List<FormattedString>> shift, BiFunction<ItemStack, EntityPlayer, List<FormattedString>> debug,
			Function<ItemStack, Boolean> effect, Function<ItemStack, EnumRarity> rarity) {
		this.common = common;
		this.shift = shift;
		this.debug = debug;
		this.effect = effect;
		this.rarity = rarity;
	}

	public static ComplexItemFlavor of(BiFunction<ItemStack, EntityPlayer, List<FormattedString>> common) {
		return new ComplexItemFlavor(common, (s, p) -> ImmutableList.of(), (s, p) -> ImmutableList.of(), s -> false, s -> EnumRarity.COMMON);
	}

	public static ComplexItemFlavor ofS(BiFunction<ItemStack, EntityPlayer, FormattedString> common) {
		return new ComplexItemFlavor(common.andThen(ImmutableList::of), (s, p) -> ImmutableList.of(), (s, p) -> ImmutableList.of(), s -> false,
				s -> EnumRarity.COMMON);
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public List<FormattedString> commonFlavor(ItemStack stack, EntityPlayer player) {
		return common.apply(stack, player);
	}

	@Override
	public List<FormattedString> shiftFlavor(ItemStack stack, EntityPlayer player) {
		return shift.apply(stack, player);
	}

	@Override
	public List<FormattedString> debugFlavor(ItemStack stack, EntityPlayer player) {
		return debug.apply(stack, player);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return effect.apply(stack);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return rarity.apply(stack);
	}

	public static class Builder {

		private BiFunction<ItemStack, EntityPlayer, List<FormattedString>> common = (s, p) -> ImmutableList.of();
		private BiFunction<ItemStack, EntityPlayer, List<FormattedString>> shift = (s, p) -> ImmutableList.of();
		private BiFunction<ItemStack, EntityPlayer, List<FormattedString>> debug = (s, p) -> ImmutableList.of();
		private Function<ItemStack, Boolean> effect = s -> false;
		private Function<ItemStack, EnumRarity> rarity = s -> EnumRarity.COMMON;

		public Builder common(BiFunction<ItemStack, EntityPlayer, List<FormattedString>> common) {
			this.common = common;
			return this;
		}

		public Builder shift(BiFunction<ItemStack, EntityPlayer, List<FormattedString>> shift) {
			this.shift = shift;
			return this;
		}

		public Builder debug(BiFunction<ItemStack, EntityPlayer, List<FormattedString>> debug) {
			this.debug = debug;
			return this;
		}

		public Builder commonS(BiFunction<ItemStack, EntityPlayer, FormattedString> common) {
			this.common = common.andThen(ImmutableList::of);
			return this;
		}

		public Builder shiftS(BiFunction<ItemStack, EntityPlayer, FormattedString> shift) {
			this.shift = shift.andThen(ImmutableList::of);
			return this;
		}

		public Builder debugS(BiFunction<ItemStack, EntityPlayer, FormattedString> debug) {
			this.debug = debug.andThen(ImmutableList::of);
			return this;
		}

		public Builder effect(Function<ItemStack, Boolean> effect) {
			this.effect = effect;
			return this;
		}

		public Builder rarity(Function<ItemStack, EnumRarity> rarity) {
			this.rarity = rarity;
			return this;
		}

		public ComplexItemFlavor build() {
			return new ComplexItemFlavor(common, shift, debug, effect, rarity);
		}
	}
}
