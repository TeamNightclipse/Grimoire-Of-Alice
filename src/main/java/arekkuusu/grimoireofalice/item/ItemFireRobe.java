package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.gui.GuiScreen;
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

import java.util.List;

public class ItemFireRobe extends ItemModArmor implements ISpecialArmor {

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
		list.add(TextFormatting.GOLD + "Made from the fur of a mystical rat that could live in fire");
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.DARK_AQUA + "Completely fire proof, the user is protected from hell itself");
			list.add(TextFormatting.DARK_AQUA + "Stronger than any man-made armor, demonic by nature");
			list.add(TextFormatting.DARK_PURPLE + "Can be nullified by purification barriers");
		} else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
	}

	private void extinguishEffect(EntityLivingBase target, World world) {
		target.extinguish();
		for(int k = 0; k < 8; ++k) {
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX - 0.5 + itemRand.nextDouble(),
					target.posY - 1 + itemRand.nextDouble() * 2, target.posZ - 0.5 + itemRand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		boolean isActive = player.getActivePotionEffects().contains(new PotionEffect(MobEffects.GLOWING));
		if(player.isBurning() && !isActive){
			extinguishEffect(player, world);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(target instanceof EntityPlayer) {
			extinguishEffect(target, target.worldObj);
		} else {
			target.setFire(10);
		}
		return false; //TODO: Why false here?
	}

	private boolean isActive(EntityLivingBase target) {
		return target.isPotionActive(MobEffects.GLOWING);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(isActive(entity)){
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(isActive(player)){
			return new ArmorProperties(4, 0, 0);
		}
		return new ArmorProperties(4, 100, 100);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 5;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return LibMod.MODID + ":textures/models/armor/firerobe.png";
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
