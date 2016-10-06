package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemRodOfRemorse extends ItemMod {

	ItemRodOfRemorse() {
		super(LibItemName.RODREMORSE);
		setMaxDamage(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "The worse the sin, the heavier the rod becomes");
		list.add(TextFormatting.ITALIC + "The person in question will be beaten up until they repent");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if(itemStackIn.isItemDamaged()) {
			if(player.capabilities.isCreativeMode){
				itemStackIn.setItemDamage(0);
				return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
			}
			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				ItemStack item = player.inventory.mainInventory[i];
				if (item != null && item.getItem() == Items.DYE && item.getItemDamage() == 0) {
					if (item.stackSize > 1) {
						player.inventory.mainInventory[i].stackSize--;
					} else {
						player.inventory.mainInventory[i] = null;
					}
					itemStackIn.setItemDamage(0);
					break;
				}
			}
		}
		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!stack.isItemDamaged()) {
			float perc = 0.75F;
			if(target.getLastAttackerTime() % 50 == 0){
				perc -= 0.10;
			}
			if(target.getAge() % 50 == 0){
				perc -= 0.10;
			}
			if(target.getRevengeTimer() % 50 == 0){
				perc -= 0.10;
			}
			target.setHealth(target.getMaxHealth() * perc);
			stack.setItemDamage(1);
			return true;
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(!target.worldObj.isRemote) {
			playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW + "- ")
					.appendSibling(new TextComponentTranslation(getUnlocalizedName() + ".entityHealthUsage")
							.appendText(": "))
					.appendText(String.valueOf((int)target.getHealth())));
		}
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
