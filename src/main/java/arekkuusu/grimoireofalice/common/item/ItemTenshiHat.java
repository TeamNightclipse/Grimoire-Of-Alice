package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelTenshiHat;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemTenshiHat extends ItemModArmor implements IOwnedBy {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemTenshiHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.TENSHI_HAT, EntityEquipmentSlot.HEAD);
		setMaxDamage(10000);
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.tenshi_hat_header.name"));
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if(model == null) {
			model = new ModelTenshiHat();
		}
		model.setModelAttributes(imodel);
		return model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLocations.TENSHI_HAT.toString();
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.TENSHI_HINANAWI;
	}
}
