/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShimenawaRope extends ItemMod {

	ItemShimenawaRope() {
		super(LibItemName.SHIMENAWAROPE);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Tenshi's little gift");
		list.add(TextFormatting.GRAY + "A little piece of Heaven,");
		list.add(TextFormatting.RED + "Might cause an incident");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		if (facing != EnumFacing.UP) {
            return EnumActionResult.PASS;
        }
		else if(player.canPlayerEdit(pos.up(1), facing, stack) && world.isAirBlock(pos.up(1))) {
			if(!world.isRaining()) {
				return EnumActionResult.PASS;
			}
			else {
				world.createExplosion(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 3.0F, false);
				world.setBlockState(pos.up(1), ModBlocks.holyKeyStone.getDefaultState());
				world.spawnEntityInWorld(new EntityLightningBolt(world, pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5, false));
				world.spawnEntityInWorld(new EntityLightningBolt(world, pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5, false));
				--stack.stackSize;
				
				return EnumActionResult.SUCCESS;
			}
		}
		else {
			return EnumActionResult.PASS;
		}
	}
}
