/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.Collection;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMapleLeafShield extends ItemGOASword {

	public ItemMapleLeafShield(ToolMaterial material) {
		super(material);
		setMaxDamage(250);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GOLD + "Momiji goes awoooo~");
		list.add(EnumChatFormatting.GRAY + "Round shield with a red");
		list.add(EnumChatFormatting.GRAY + "maple leaf print on it");
		list.add(EnumChatFormatting.DARK_PURPLE + "Cures potion effects");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int p_77615_4_) {
		if(stack.getItemDamage() == 0) {
			if (!world.isRemote) {
				if(!player.isPotionActive(Potion.moveSpeed)){
					player.curePotionEffects(new ItemStack(Items.milk_bucket));
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 240, 0));
				}
			stack.damageItem(1, player);
			}
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        p_77644_1_.damageItem(10, p_77644_3_);
        return true;
    }
	
	public void damageItemStack(ItemStack stack, EntityPlayer player){
		stack.damageItem(1, player);
	}
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if(event.entityLiving instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			ItemStack stack = player.getHeldItem();
			if (stack != null && stack.getItem() instanceof ItemMapleLeafShield) {
				if (player.isUsingItem()) {
				damageItemStack(stack, player);
				event.ammount = 0F;
				}
			}
		}
	}
}
