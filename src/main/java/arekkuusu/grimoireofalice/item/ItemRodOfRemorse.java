package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ItemRodOfRemorse extends ItemMod {

	public ItemRodOfRemorse() {
		super(LibItemName.RODREMORSE);
		setMaxDamage(1);
	}

	/*@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof EntityLiving){
			EntityLiving affected = (EntityLiving)entity;
			affected.setHealth(affected.getMaxHealth() * 0.75F);
		}
		return false;
	}*/

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (target.getMaxHealth() * 0.75f < target.getHealth() && !stack.isItemDamaged()) {
			target.setHealth(target.getMaxHealth() * 0.75F);
			stack.setItemDamage(1);
			return true;
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(!target.worldObj.isRemote) {
			playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW + "- Health: " + target.getHealth()));
		}
		return true;
	}
}
