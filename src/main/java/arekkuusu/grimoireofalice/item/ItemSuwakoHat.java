package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.client.model.ModelSuwakoHat;
import arekkuusu.grimoireofalice.item.ItemModArmor;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSuwakoHat extends ItemModArmor {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	ItemSuwakoHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.SUWAKOHAT, EntityEquipmentSlot.HEAD);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(!stack.isItemEnchanted()) {
			stack.addEnchantment(Enchantments.THORNS, 10);
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 15, 1));
		if(player.isWet()) {
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 30, 0));
		}
		else {
			player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 30, 0));
		}
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if(model == null) model = new ModelSuwakoHat();
		model.setModelAttributes(imodel);
		return model;
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.SLIME_BALL;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return LibMod.MODID + ":textures/models/armor/suwakohat.png";
	}
}
