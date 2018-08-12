package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.render.model.ModelTenshiHat;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTenshiHat extends ItemBaseArmor  {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemTenshiHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibName.TENSHI_HAT, EntityEquipmentSlot.HEAD);
		setMaxDamage(10_000);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped imodel) {
		if(model == null) {
			model = new ModelTenshiHat();
		}
		model.setModelAttributes(imodel);
		return model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLibrary.TENSHI_HAT.toString();
	}
}
