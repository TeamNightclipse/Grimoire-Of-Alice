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
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;


public class ItemKokorosMasks extends ItemArmor {

	private static final ModelBiped var10 = new ModelKokorosMasks();

	public ItemKokorosMasks(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(stack.getItem() == GOAItem.itemKokorosMasks) {
			return LibMod.MODID + ":textures/models/KokorosMasks_layer_1.png";
		}
		else {
			return null;
		}
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(int id) {
		return var10;
	}

	@SideOnly(Side.CLIENT)
	ModelBiped armorModel = new ModelBiped();

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {

		if(stack != null) {
			if(stack.getItem() instanceof ItemKokorosMasks) {
				int type = ((ItemArmor)stack.getItem()).armorType;

				if(type == 1) {
					armorModel = getArmorModel(1);
				}
				else {
					armorModel = getArmorModel(1);
				}
			}
			if(armorModel != null) {
				armorModel.bipedHead.showModel = armorSlot == 3;
				armorModel.bipedHeadwear.showModel = armorSlot == 3;
				armorModel.bipedBody.showModel = armorSlot == 0;
				armorModel.bipedRightArm.showModel = armorSlot == 3;
				armorModel.bipedLeftArm.showModel = armorSlot == 3;
				armorModel.bipedRightLeg.showModel = armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
				return armorModel;
			}
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Emotions");
		list.add(EnumChatFormatting.GOLD + "Feel the power of 66 masks");
		list.add(EnumChatFormatting.GOLD + "being worn at the same time");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
		EntityPlayer player = (EntityPlayer)entity;
		if(!world.isRemote && entity != null) {
			ItemStack[] inventory = player.inventory.mainInventory;
			for(int i = 0; i < inventory.length; i++) {
				if(inventory[i] != null && inventory[i].getItem() == this && i != p_77663_4_) {
					player.dropPlayerItemWithRandomChoice(inventory[i], true);
					inventory[i] = null;
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel >= 30) {
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 25, 4));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}
}
