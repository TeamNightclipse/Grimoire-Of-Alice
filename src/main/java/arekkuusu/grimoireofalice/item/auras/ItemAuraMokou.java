package arekkuusu.grimoireofalice.item.auras;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.client.model.ModelMokouAura;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;

import javax.annotation.Nullable;

public class ItemAuraMokou extends ItemModAura {
	
	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemAuraMokou(ArmorMaterial material, int dmg) {
		super(material, dmg, LibItemName.AURAMOKOU);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_RED + "Endless pain");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 15, 0));
			player.setFire(1);
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

	@SuppressWarnings("NullableProblems") //Liar
	@Override
	@SideOnly(Side.CLIENT)
	@Nullable
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if(entityLiving.isSneaking()) {
			if (model == null) model = new ModelMokouAura();
			model.setModelAttributes(imodel);
			return model;
		}
		return null;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return LibMod.MODID + ":textures/models/armor/mokou_aura.png";
    }

}
