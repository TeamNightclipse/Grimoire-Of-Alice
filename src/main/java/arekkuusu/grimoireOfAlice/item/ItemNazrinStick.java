/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemNazrinStick extends ItemSword {

	public ItemNazrinStick(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "Rare treasure form an old era");
		list.add(EnumChatFormatting.GOLD + "Used by mice to find cheese");
		list.add(EnumChatFormatting.GOLD + "some meters away form their");
		list.add(EnumChatFormatting.GOLD + "homes...mmmmm");
		list.add(EnumChatFormatting.GOLD + "O-Only works for mice? Oh well");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player) {
		if(player.capabilities.isCreativeMode || player.inventory.hasItem(Items.coal)) {
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 25, 0));
			player.setItemInUse(p_77659_1_, getMaxItemUseDuration(p_77659_1_));
		}
		return p_77659_1_;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int p_77615_4_) {
		if(player.experienceLevel > 40) {
			player.inventory.consumeInventoryItem(Items.coal);
			//Vec3 look = player.getLookVec();
			world.createExplosion(null, player.posX - 5.0, player.posY, player.posZ - 5.0, 2.5F, false);
			world.createExplosion(null, player.posX - 5.0, player.posY, player.posZ, 2.5F, false);
			world.createExplosion(null, player.posX - 5.0, player.posY, player.posZ + 5.0, 2.5F, false);
			world.createExplosion(null, player.posX, player.posY, player.posZ + 5.0, 2.5F, false);
			world.createExplosion(null, player.posX, player.posY, player.posZ - 5.0, 2.5F, false);
			world.createExplosion(null, player.posX + 5.0, player.posY, player.posZ - 5.0, 2.5F, false);
			world.createExplosion(null, player.posX + 5.0, player.posY, player.posZ, 2.5F, false);
			world.createExplosion(null, player.posX + 5.0, player.posY, player.posZ + 5.0, 2.5F, false);
		}
		else {
			player.inventory.consumeInventoryItem(Items.coal);
			world.createExplosion(null, player.posX, player.posY, player.posZ, 1.0F, false);
			stack.damageItem(1, player);
		}
	}

}