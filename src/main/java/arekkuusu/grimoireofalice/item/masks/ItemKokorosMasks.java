/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.masks;

import java.util.List;
import java.util.UUID;

import arekkuusu.grimoireofalice.client.model.ModelKokorosMasks;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKokorosMasks extends ItemModMask {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemKokorosMasks(ArmorMaterial material, int dmg) {
		super(material, dmg, LibItemName.KOKOROSMASKS);
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setUniqueId("GrimoireOwner", player.getUniqueID());
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.EPIC;
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Tsukumogami of Emotions");
		list.add(TextFormatting.GOLD + "Feel the power of 66 masks");
		list.add(TextFormatting.GOLD + "being worn at the same time");
		if(stack.hasTagCompound()) {
			UUID ownerUuid = stack.getTagCompound().getUniqueId("GrimoireOwner");
			if(ownerUuid != null) {
				if(UsernameCache.containsUUID(ownerUuid)) {
					list.add(TextFormatting.ITALIC + "Property of " + UsernameCache.getLastKnownUsername(ownerUuid));
				}
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		super.onUpdate(stack, world, entity, slot, selected);
		
		if(selected && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;

			if(!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			NBTTagCompound compound = stack.getTagCompound();
			//noinspection ConstantConditions
			if(!compound.hasKey("GrimoireOwner")) {
				compound.setUniqueId("GrimoireOwner", player.getUniqueID());
			}
		
			if(!world.isRemote) {
				ItemStack[] inventory = player.inventory.mainInventory;
				for(int i = 0; i < inventory.length; i++) {
					if(inventory[i] != null && inventory[i].getItem() == this && i != slot) {
						player.dropItem(inventory[i], true);
						inventory[i] = null;
					}
				}
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(!armor.hasTagCompound()) return;
			if(player.getUniqueID().equals(armor.getTagCompound().getUniqueId("GrimoireOwner"))){
			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 3));
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 3));
		}
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if(model == null) model = new ModelKokorosMasks();
		model.setModelAttributes(imodel);
		return model;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return LibMod.MODID + ":textures/models/armor/kokorosmasks_layer_1.png";
    }
	
}
