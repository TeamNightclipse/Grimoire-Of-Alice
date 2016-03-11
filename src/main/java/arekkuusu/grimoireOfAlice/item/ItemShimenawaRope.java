/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.block.GOABlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemShimenawaRope extends ItemGOABase {

	public ItemShimenawaRope() {
		super();
		setMaxStackSize(16);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(par7 != 1) {
			return false;
		}
		else if(player.canPlayerEdit(x, y, z, par7, stack)) {

			world.setBlock(x, y, z, GOABlock.blockHolyKeyStone);
			--stack.stackSize;

			return true;
		}
		else {
			return false;
		}
	}
}
