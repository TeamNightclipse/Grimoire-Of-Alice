/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemWatermelonSword extends ItemBaseSword  {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemWatermelonSword(ToolMaterial material) {
		super(material, LibName.WATERMELON_SWORD);
		setMaxDamage(4);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);

		if(stack.getItemDamage() <= 0 && attacker.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			ItemStack rest = ItemHandlerHelper.insertItemStacked(attacker.getCapability(ITEM_HANDLER_CAPABILITY, null),
					new ItemStack(ModItems.POPSICLE_STICK), false);
			if(!rest.isEmpty()) {
				attacker.dropItem(rest.getItem(), rest.getCount());
			}
		}

		if(target.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			ItemStack rest = ItemHandlerHelper.insertItemStacked(target.getCapability(ITEM_HANDLER_CAPABILITY, null),
					new ItemStack(Items.MELON, itemRand.nextInt(3)), false);
			if(!rest.isEmpty()) {
				attacker.dropItem(rest.getItem(), rest.getCount());
			}
		}
		else {
			attacker.dropItem(Items.MELON, itemRand.nextInt(3));
		}

		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
