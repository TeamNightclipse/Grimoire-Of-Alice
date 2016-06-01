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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemShimenawaRope extends ItemGOABase {

	public ItemShimenawaRope() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE
				+ "Tenshi's little gift");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "A little piece of Heaven,");
		p_77624_3_.add(EnumChatFormatting.RED
				+ "Might cause an incident");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(par7 != 1) {
			return false;
		}
		else if(player.canPlayerEdit(x, y, z, par7, stack) && world.isAirBlock(x, y+1, z)) {
			if(!world.isRaining()){
				return false;
			} else {
		    --stack.stackSize;
			world.createExplosion(null, x+0.5, y, z+0.5, 3.0F, true);
			world.setBlock(x, y+1, z, GOABlock.blockHolyKeyStone);
			world.spawnEntityInWorld(new EntityLightningBolt(world, x+0.5, y+2, z+0.5));
			world.spawnEntityInWorld(new EntityLightningBolt(world, x+0.5, y+2, z+0.5));

			return true;
			}
		}
		else {
			return false;
		}
	}
}
