package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFireRobe;
import arekkuusu.grimoireofalice.common.entity.EntityItemFireProof;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFireRobe extends ItemModArmor implements ISpecialArmor {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemFireRobe(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.FIRE_ROBE, EntityEquipmentSlot.CHEST);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.fire_robe_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.fire_robe_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.fire_robe_description_mid.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.fire_robe_description_shift.name"));
		}
	}

	private void extinguishEffect(EntityLivingBase target, World world) {
		target.extinguish();
		for(int k = 0; k < 8; ++k) {
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX - 0.5 + itemRand.nextDouble(), target.posY - 1 + itemRand.nextDouble() * 2,
					target.posZ - 0.5 + itemRand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		boolean isActive = player.getActivePotionEffects().contains(new PotionEffect(MobEffects.GLOWING));
		if(player.isBurning() && !isActive) {
			extinguishEffect(player, world);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(target instanceof EntityPlayer) {
			extinguishEffect(target, target.worldObj);
		}
		else {
			target.setFire(10);
		}
		return true;
	}

	private boolean isActive(EntityLivingBase target) {
		return target.isPotionActive(MobEffects.GLOWING);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(isActive(entity)) {
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(isActive(player)) return new ArmorProperties(4, 0, 0);
		return new ArmorProperties(4, 100, 100);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 5;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (model == null) model = new ModelFireRobe();
		model.setModelAttributes(_default);
		return model;
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItemFireProof item = new EntityItemFireProof(world, location.posX, location.posY, location.posZ, itemstack);
		item.setDefaultPickupDelay();
		return item;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLocations.FIRE_ROBE.toString();
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
