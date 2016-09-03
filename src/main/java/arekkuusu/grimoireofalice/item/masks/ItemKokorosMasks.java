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

import arekkuusu.grimoireofalice.client.model.ModelKokorosMasks;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKokorosMasks extends ItemModMask implements ISpecialArmor{

	protected ModelBiped[] models;
	public int type;

	public ItemKokorosMasks(ArmorMaterial material, int dmg) {
		super(material, dmg, EntityEquipmentSlot.HEAD, LibItemName.KOKOROSMASKS);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if(stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
	    stack.getTagCompound().setString("GrimoireOwner", player.getDisplayName().getFormattedText());
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.EPIC;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Tsukumogami of Emotions");
		list.add(TextFormatting.GOLD + "Feel the power of 66 masks");
		list.add(TextFormatting.GOLD + "being worn at the same time");
		if(stack.hasTagCompound()) {
			list.add(TextFormatting.ITALIC + "Property of " + stack.getTagCompound().getString("GrimoireOwner"));
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean p_77663_5_) {
		super.onUpdate(stack, world, entity, slot, p_77663_5_);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
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

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(!armor.hasTagCompound()) {return;}
		if(player.experienceLevel >= 30 && armor.getTagCompound().getString("GrimoireOwner").equals(player.getDisplayName().getFormattedText())) {
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 13
			Potion potion2 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 11
			Potion potion3 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 16
			Potion potion4 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 8
			Potion potion5 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 1
			Potion potion6 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 12
			Potion potion7 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 5
			if(potion1 != null && potion2 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 0, 4));
				player.addPotionEffect(new PotionEffect(potion2, 0, 3));
				player.addPotionEffect(new PotionEffect(potion3, 0, 4));
				player.addPotionEffect(new PotionEffect(potion4, 0, 4));
				player.addPotionEffect(new PotionEffect(potion5, 0, 4));
				player.addPotionEffect(new PotionEffect(potion6, 0, 4));
				player.addPotionEffect(new PotionEffect(potion7, 0, 3));
			}
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
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, net.minecraft.client.model.ModelBiped imodel) {
			ModelBiped model = getArmorModelForSlot(entityLiving, itemStack, Ui.getIndex());
			if(model == null)
				model = provideArmorModelForSlot(itemStack, Ui.getIndex());

			if(model != null)
				return model;

		return super.getArmorModel(entityLiving, itemStack, Ui, model);
	}
	
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModelForSlot(EntityLivingBase entity, ItemStack stack, int slot) {
		if(models == null)
			models = new ModelBiped[4];

		return models[slot];
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
		models[slot] = new ModelKokorosMasks();
		return models[slot];
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return LibMod.MODID + ":textures/models/KokorosMasks_layer_1.png";
    }
	
}
