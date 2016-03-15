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
import java.util.Random;

import arekkuusu.grimoireOfAlice.entity.projectile.EntityThrowingExplosiveDoll;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemThrowingExplosiveDoll extends ItemGOABase {

	public ItemThrowingExplosiveDoll() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_){
		
		p_77624_3_.add(EnumChatFormatting.DARK_AQUA
				+ "Some say Dolls lifes matter too");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ " -#Dollslifesmatter!");
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		Random random = world.rand;
		double d0 = 0.0625D;
		for(int l = 0; l < 6; ++l) {
			float x = 0;
			float y = 0;
			float z = 0;
			double d1 = x + random.nextFloat();
			double d2 = y + random.nextFloat();
			double d3 = z + random.nextFloat();
			if(l == 0) d2 = y + 1 + d0;
			if(l == 1) d2 = y + 0 - d0;
			if(l == 2) d3 = z + 1 + d0;
			if(l == 3) d3 = z + 0 - d0;
			if(l == 4) d1 = x + 1 + d0;
			if(l == 5) d1 = x + 0 - d0;
			if(d1 < x || d1 > x + 1 || d2 < 0.0D || d2 > y + 1 || d3 < z || d3 > z + 1) {
				world.spawnParticle("flame", player.posX - d1, player.posY - d2, player.posZ - d3, 0.0D, 0.0D, 0.0D); //spawns a particle (flame) in the world (world) on the position of the player who's using the item.
			}
		}

		if(!player.capabilities.isCreativeMode) {
			--stack.stackSize;
		}

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if(!world.isRemote) {
			world.spawnEntityInWorld(new EntityThrowingExplosiveDoll(world, player));
		}

		return stack;
	}
}
