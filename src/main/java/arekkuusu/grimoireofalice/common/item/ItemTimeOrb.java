/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTimeOrb extends ItemMod {

	public ItemTimeOrb() {
		super(LibItemName.TIME_ORB);
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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.time_orb_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.time_orb_description_top.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.time_orb_description_bottom.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!world.isDaytime()) {
			if(!player.capabilities.isCreativeMode) {
				stack.shrink(1);
			}

			player.playSound(SoundEvents.ENTITY_FIREWORK_TWINKLE, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
			if(!world.isRemote) {
				world.setWorldTime(world.getWorldTime() - 500);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}
}
