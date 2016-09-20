/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.food;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShroomSlice extends ItemModFood {

	public ItemShroomSlice() {
		super(4, 1.2F, false, LibItemName.SHROOMSLICE);
		setHasSubtypes(true);
		setAlwaysEdible();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() > 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Psilocybin mushroom");
		list.add(TextFormatting.ITALIC + "Shrooms~ze!");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2400, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 2400, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 2400, 1));
		}

		if(stack.getItemDamage() > 0) {
			if(!world.isRemote) {
				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0));
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2400, 1));
				player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2400, 0));
			}
		}
		else {
			super.onFoodEaten(stack, world, player);
		}
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
}
