/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.masks;

import java.util.List;

import arekkuusu.grimoireOfAlice.client.model.ModelKokorosMasks;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

@CleanupDone
public class ItemKokorosMasks extends ItemMask implements ISpecialArmor{

	protected ModelBiped[] models;
	public int type;

	public ItemKokorosMasks(ArmorMaterial material, int p_i45325_2_) {
		super(material, p_i45325_2_, LibMod.MODID + ":textures/models/KokorosMasks_layer_1.png");
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if( stack.stackTagCompound == null )
	    	stack.setTagCompound( new NBTTagCompound( ) );
	    stack.stackTagCompound.setString("GrimoireOwner", player.getDisplayName());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Emotions");
		list.add(EnumChatFormatting.GOLD + "Feel the power of 66 masks");
		list.add(EnumChatFormatting.GOLD + "being worn at the same time");
		if(stack.hasTagCompound()) {
			list.add(EnumChatFormatting.ITALIC + "Property of " + stack.stackTagCompound.getString("GrimoireOwner"));
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
						player.dropPlayerItemWithRandomChoice(inventory[i], true);
						inventory[i] = null;
					}
				}
			}
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(!armor.hasTagCompound()) {return;}
		if(player.experienceLevel >= 30 && armor.stackTagCompound.getString("GrimoireOwner").equals(player.getDisplayName())) {
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 0, 3));
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 0, 3));
		}
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
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
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
			ModelBiped model = getArmorModelForSlot(entityLiving, itemStack, armorSlot);
			if(model == null)
				model = provideArmorModelForSlot(itemStack, armorSlot);

			if(model != null)
				return model;

		return super.getArmorModel(entityLiving, itemStack, armorSlot);
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
}
