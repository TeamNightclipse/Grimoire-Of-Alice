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

import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Item3rdEye extends ItemPoint {
	
	private static final int TIME = 300;
	
	Item3rdEye() {
		super(EnumRarity.rare, true);
		setMaxStackSize(1);
		setMaxDamage(300);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GOLD + "Satori's 3rdEye");
		list.add(EnumChatFormatting.ITALIC + "Shift right click to activate");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.isSneaking() && stack.getItemDamage() == 0) {
			stack.setItemDamage(300);
		}
		return stack;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.inventory.hasItemStack(stack) && !(stack.getItemDamage() == 0)) {
				player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 10, 0));
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 10, 0));
			}
		}
		if(stack.isItemDamaged()){
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}
	
}
