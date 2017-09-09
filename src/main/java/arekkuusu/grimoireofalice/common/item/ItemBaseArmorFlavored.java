package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.core.format.ItemFlavor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBaseArmorFlavored extends ItemBaseArmor {

	private final ItemFlavor flavor;

	public ItemBaseArmorFlavored(ArmorMaterial materialIn, int dmg, String id, EntityEquipmentSlot equipmentSlotIn, ItemFlavor flavor) {
		super(materialIn, dmg, id, equipmentSlotIn);
		this.flavor = flavor;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return flavor.hasEffect(stack);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return flavor.getRarity(stack);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean debug) {
		super.addInformation(stack, player, tooltip, debug);
		tooltip.addAll(flavor.createDescription(stack, player, debug));
	}
}
