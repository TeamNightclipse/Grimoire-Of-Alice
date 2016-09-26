package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if(itemStackIn.isItemDamaged()) {
			if(player.capabilities.isCreativeMode){
				itemStackIn.setItemDamage(0);
				return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
			}
			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				ItemStack item = player.inventory.mainInventory[i];
				if (item != null && item.getItem() == Items.DYE && item.getItemDamage() == 0) {
					if (item.stackSize > 0) {
						--player.inventory.mainInventory[i].stackSize;
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
			target.setHealth(target.getMaxHealth() * 0.75F);
			stack.setItemDamage(1);
			return true;
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(!target.worldObj.isRemote) {
			playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW + "- ")
					.appendSibling(new TextComponentTranslation(getUnlocalizedName() + ".entityHealthUsage").appendText(": ")));
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
