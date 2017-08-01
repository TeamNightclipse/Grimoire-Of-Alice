package arekkuusu.grimoireofalice.common.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public final class ModMaterials {

	//Armor Materials
	public static final ItemArmor.ArmorMaterial WEAK_PAPER = EnumHelper.addArmorMaterial("WEAK_PAPER", "No", 33, new int[]{3, 4, 3, 2}, 30,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ItemArmor.ArmorMaterial STRONG_LEATHER = EnumHelper.addArmorMaterial("STRONG_LEATHER", "No", 47, new int[]{3, 4, 3, 2}, 30,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ItemArmor.ArmorMaterial MASK = EnumHelper.addArmorMaterial("MASK", "No", 141, new int[]{4, 3, 3, 4}, 0,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2);
	public static final ItemArmor.ArmorMaterial STRONG_HIHIIROKANE = EnumHelper.addArmorMaterial("STRONG_HIHIIROKANE", "No", 453, new int[]{25, 30, 26, 23}, 0,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 25);
	public static final ItemArmor.ArmorMaterial ENSHRINED_SHIMENAWA = EnumHelper.addArmorMaterial("ENSHRINED_SHIMENAWA", "No", 435, new int[]{10, 18, 14, 11}, 50,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 15);
	public static final ItemArmor.ArmorMaterial FIRE_LEATHER = EnumHelper.addArmorMaterial("FIRE_LEATHER", "No", 141, new int[]{4, 8, 6, 3}, 50,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 25);

	//Tool Materials
	public static final Item.ToolMaterial WEAK_MATERIAL = EnumHelper.addToolMaterial("WEAK_MATERIAL", 3, 10, 15.0F, -2F, 30);
	public static final Item.ToolMaterial STRONG_GOLD = EnumHelper.addToolMaterial("STRONG_GOLD", 3, 1561, 15.0F, 3F, 30);
	public static final Item.ToolMaterial STRONG_IRON = EnumHelper.addToolMaterial("STRONG_IRON", 2, 450, 6.0F, 2.0F, 14);
	public static final Item.ToolMaterial STRONG_STONE = EnumHelper.addToolMaterial("STRONG_STONE", 2, 250, 3.0F, 1.5F, 14);
	public static final Item.ToolMaterial NOODLE = EnumHelper.addToolMaterial("NOODLE", 3, 251, 15.0F, 0F, 30);
	public static final Item.ToolMaterial BUDAH_BOUL = EnumHelper.addToolMaterial("BUDAH_BOUL", 4, 1561, 80.0F, 3.0F, 100);

	private ModMaterials() {
	}
}
