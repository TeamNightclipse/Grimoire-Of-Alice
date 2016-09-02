/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.Collection;
import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMapleLeafShield extends ItemModSword {

	public ItemMapleLeafShield(ToolMaterial material) {
		super(material);
		setMaxDamage(250);
		setUnlocalizedName(LibItemName.MAPLELEAFSHIELD);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Momiji goes awoooo~");
		list.add(TextFormatting.GRAY + "Round shield with a red");
		list.add(TextFormatting.GRAY + "maple leaf print on it");
		list.add(TextFormatting.DARK_PURPLE + "Cures potion effects");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		//playerIn.set(itemStackIn, getMaxItemUseDuration(itemStackIn));
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(stack.getItemDamage() == 0) {
			if (!worldIn.isRemote) {
				if(!entityLiving.isPotionActive(Potion.getPotionById(1))){
					entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
					entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 240, 0));
				}
			stack.damageItem(1, entityLiving);
			}
		}
		return stack;
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
		if(event.getEntityLiving() instanceof EntityPlayer && !event.getEntity().worldObj.isRemote) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			ItemStack stack = player.getHeldItem(EnumHand.OFF_HAND);
			if (stack != null && stack.getItem() instanceof ItemMapleLeafShield) {
				if (player.isActiveItemStackBlocking()) {
				damageItemStack(stack, player);
				event.setAmount(0);
				}
			}
		}
	}
}
