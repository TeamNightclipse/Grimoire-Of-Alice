/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

@CleanupDone
public class ItemPrimordialShield extends ItemGOASword {

	public ItemPrimordialShield(ToolMaterial material) {
		super(material);
		setMaxDamage(1000);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "Supermassive unidentified object");
		list.add(EnumChatFormatting.GOLD + "After melting the bases of a");
		list.add(EnumChatFormatting.GOLD + "forgoten universe, an unknown");
		list.add(EnumChatFormatting.GOLD + "entity decided to store the");
		list.add(EnumChatFormatting.GOLD + "remains in the form of a shield");
		list.add(EnumChatFormatting.RED + "Only a god would be able to lift it");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(stack.getItemDamage() < stack.getMaxDamage()) {
			stack.setItemDamage(stack.getItemDamage() - 1);
		}

		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getCurrentEquippedItem() == stack && !isWorthy(player)) {
				if(!world.isRemote && p_77663_5_) {
					player.fallDistance = 5.0F;
				}

				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 25, 5));
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 25, 5));
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(isWorthy(player)) {
			if(stack.getItemDamage() == 0) {
				player.capabilities.disableDamage = true;
				player.setItemInUse(stack, getMaxItemUseDuration(stack));
			}
		}
		return stack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int p_77615_4_) {
		if(stack.getItemDamage() == 0) {
			player.capabilities.disableDamage = false;
			stack.damageItem(999, player);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		return true;
	}

	private boolean isWorthy(EntityPlayer player) {
		return player.experienceLevel >= 1000 | player.capabilities.isCreativeMode;
	}
}
