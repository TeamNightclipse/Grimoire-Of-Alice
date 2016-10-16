package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.client.model.ModelMarisaHat;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMarisaHat extends ItemModArmor  {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemMarisaHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.MARISA_HAT, EntityEquipmentSlot.HEAD);
		setMaxDamage(200);
		setNoRepair();
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "\"daze~!\"");
		list.add(TextFormatting.ITALIC + "Pickup items 5 blocks away");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(!player.isSneaking()) {
			List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().expandXyz(5));
			for (EntityItem item : list) {
				item.onCollideWithPlayer(player);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if (model == null) model = new ModelMarisaHat();
		model.setModelAttributes(imodel);
		return model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return LibMod.MODID + ":textures/models/armor/marisahat.png";
	}
}
