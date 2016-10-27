/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemWatermelonSword extends ItemModSword {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemWatermelonSword(ToolMaterial material) {
		super(material, LibItemName.WATERMELON_SWORD);
		setMaxDamage(1);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Eat cold with salt, lots");
		list.add(TextFormatting.ITALIC + "as salty as life...");
	}

	@SuppressWarnings("ConstantConditions") //Liar
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		--stack.stackSize;

		if(attacker.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			ItemStack rest = ItemHandlerHelper.insertItemStacked(attacker.getCapability(ITEM_HANDLER_CAPABILITY, null),
					new ItemStack(ModItems.POPSICLE_STICK), false);
			if(rest != null) {
				attacker.dropItem(rest.getItem(), rest.stackSize);
			}
		}

		if(target.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			ItemStack rest = ItemHandlerHelper.insertItemStacked(target.getCapability(ITEM_HANDLER_CAPABILITY, null),
					new ItemStack(Items.MELON, itemRand.nextInt(3)), false);
			if(rest != null) {
				attacker.dropItem(rest.getItem(), rest.stackSize);
			}
		}
		else {
			attacker.dropItem(Items.MELON, itemRand.nextInt(3));
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
