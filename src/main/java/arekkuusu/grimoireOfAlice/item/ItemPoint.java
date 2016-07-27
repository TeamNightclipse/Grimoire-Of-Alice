package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPoint extends Item{

	private final EnumRarity rarity;
	private final boolean effect;

	public ItemPoint() {
		this(EnumRarity.uncommon);
	}

	public ItemPoint(EnumRarity rarity) {
		this(rarity, false);
	}

	public ItemPoint(EnumRarity rarity, boolean hasEffect) {
		effect = hasEffect;
		this.rarity = rarity;
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return rarity;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return effect || super.hasEffect(par1ItemStack, pass);
	}
	
}
