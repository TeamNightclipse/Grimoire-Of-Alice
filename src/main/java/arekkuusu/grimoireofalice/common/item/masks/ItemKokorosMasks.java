/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.masks;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelKokorosMasks;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
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
		super(material, dmg, LibItemName.KOKOROS_MASKS);
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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + "Tsukumogami of Emotions");
		list.add(TextFormatting.ITALIC + "Feel the power of 66 masks");
		list.add(TextFormatting.ITALIC + "being worn at the same time");
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
		if (!armor.hasTagCompound()) return;

		if (player.getUniqueID().equals(armor.getTagCompound().getUniqueId("GrimoireOwner"))) {
			if (player.moveForward > 0) player.moveRelative(0F, 2F, 0.085F);
			List<PotionEffect> badPotions = player.getActivePotionEffects().stream()
					.filter(potionEffect -> potionEffect.getPotion().isBadEffect()).collect(Collectors.toList());
			if (!badPotions.isEmpty()) {
				badPotions.forEach(potionEffect -> player.removePotionEffect(potionEffect.getPotion()));
			}
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 3));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 3));
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(100, 1000, 1000);
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
		if(!itemStack.hasTagCompound()) return imodel;
		//noinspection ConstantConditions
		if(entityLiving.getUniqueID().equals(itemStack.getTagCompound().getUniqueId("GrimoireOwner"))) {
			if(model == null) model = new ModelKokorosMasks();
			model.setModelAttributes(imodel);
			return model;
		}
		return imodel;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if(!stack.hasTagCompound()) return ResourceLocations.MASK_OF_HOPE.toString();

		//noinspection ConstantConditions
		if(entity.getUniqueID().equals(stack.getTagCompound().getUniqueId("GrimoireOwner"))) {
			return ResourceLocations.KOKOROS_MASKS.toString();
		}
		else return ResourceLocations.MASK_OF_HOPE.toString();
	}
}
