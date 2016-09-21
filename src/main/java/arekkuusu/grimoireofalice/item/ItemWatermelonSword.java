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
	private static Capability<IItemHandler> itemHandlerCapability;

	public ItemWatermelonSword(ToolMaterial material) {
		super(material, LibItemName.WATERMELONSWORD);
		setMaxDamage(1);
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

		if(attacker.hasCapability(itemHandlerCapability, null)) {
			ItemStack rest = ItemHandlerHelper.insertItemStacked(attacker.getCapability(itemHandlerCapability, null),
					new ItemStack(ModItems.popsicleStick), false);
			if(rest != null) {
				attacker.dropItem(rest.getItem(), rest.stackSize);
			}
		}

		if(target.hasCapability(itemHandlerCapability, null)) {
			ItemStack rest = ItemHandlerHelper.insertItemStacked(target.getCapability(itemHandlerCapability, null),
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

}
