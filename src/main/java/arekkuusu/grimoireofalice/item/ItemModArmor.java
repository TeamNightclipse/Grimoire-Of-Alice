package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemModArmor extends ItemArmor {

    public ItemModArmor(ArmorMaterial materialIn, int dmg, String id, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, dmg, equipmentSlotIn);
        setRegistryName(id);
        setUnlocalizedName(id);
        GameRegistry.register(this);
        setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }
}
