/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemShroomPowder extends ItemBase {

	public ItemShroomPowder() {
		super(LibName.SHROOM_POWDER);
		setHasSubtypes(true);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if(target instanceof EntitySheep) {
			EntitySheep entitysheep = (EntitySheep) target;
			EnumDyeColor dyeColor = EnumDyeColor.byDyeDamage(stack.getMetadata());

			if(!entitysheep.getSheared() && entitysheep.getFleeceColor() != dyeColor) {
				entitysheep.setFleeceColor(dyeColor);
				stack.shrink(1);
			}
			return true;
		}
		return false;
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		int i = stack.getMetadata();
		return super.getTranslationKey() + "." + EnumDyeColor.byDyeDamage(i).getTranslationKey();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if(isInCreativeTab(tab)) {
			for(int i = 0; i < 16; i++) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		ModelHandler.registerModel(this, EnumDyeColor.class);
	}
}
