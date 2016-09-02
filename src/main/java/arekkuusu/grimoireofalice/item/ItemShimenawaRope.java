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
		super();
		setMaxStackSize(1);
		setUnlocalizedName(LibItemName.SHIMENAWAROPE);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
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
		int side = facing.getIndex();
		if(side != 1) {
			return EnumActionResult.PASS;
		}
		else if(player.canPlayerEdit(new BlockPos(x, y, z), facing, stack) && world.isAirBlock(new BlockPos(x, y + 1, z))) {
			if(!world.isRaining()) {
				return EnumActionResult.PASS;
			}
			else {
				--stack.stackSize;
				world.createExplosion(null, x + 0.5, y, z + 0.5, 3.0F, true);
				world.addBlockEvent(new BlockPos(x, y + 1, z), ModBlocks.holyKeyStone, 1, 1);
				world.spawnEntityInWorld(new EntityLightningBolt(world, x + 0.5, y + 2, z + 0.5, bFull3D));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x + 0.5, y + 2, z + 0.5, bFull3D));

				return EnumActionResult.SUCCESS;
			}
		}
		else {
			return EnumActionResult.PASS;
		}
	}
}
