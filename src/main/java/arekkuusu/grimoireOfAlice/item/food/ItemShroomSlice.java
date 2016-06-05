/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.food;

import java.util.List;

import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

@CleanupDone
public class ItemShroomSlice extends ItemFood {

	public ItemShroomSlice() {
		super(4, 1.2F, false);
		setHasSubtypes(true);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass) {
		return stack.getItemDamage() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GREEN + "Psilocybin mushroom");
		list.add(EnumChatFormatting.DARK_PURPLE + "WaNna HaVE a TrIP bRo??!?");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 2400, 0));
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 0));
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 2400, 1));
		}

		if(stack.getItemDamage() > 0) {
			if(!world.isRemote) {
				player.addPotionEffect(new PotionEffect(Potion.hunger.id, 600, 0));
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2400, 1));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 2400, 0));
			}
		}
		else {
			super.onFoodEaten(stack, world, player);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
}
