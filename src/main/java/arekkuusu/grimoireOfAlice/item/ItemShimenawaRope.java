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

import arekkuusu.grimoireOfAlice.block.GOABlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemShimenawaRope extends ItemGOABase {

	public ItemShimenawaRope() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "Tenshi's little gift");
		list.add(EnumChatFormatting.GOLD + "A little piece of Heaven,");
		list.add(EnumChatFormatting.RED + "Might cause an incident");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
		if(side != 1) {
			return false;
		}
		else if(player.canPlayerEdit(x, y, z, side, stack) && world.isAirBlock(x, y + 1, z)) {
			if(!world.isRaining()) {
				return false;
			}
			else {
				--stack.stackSize;
				world.createExplosion(null, x + 0.5, y, z + 0.5, 3.0F, true);
				world.setBlock(x, y + 1, z, GOABlock.blockHolyKeyStone);
				world.spawnEntityInWorld(new EntityLightningBolt(world, x + 0.5, y + 2, z + 0.5));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x + 0.5, y + 2, z + 0.5));

				return true;
			}
		}
		else {
			return false;
		}
	}
}
