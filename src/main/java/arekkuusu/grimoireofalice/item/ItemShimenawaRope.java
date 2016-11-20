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
import net.minecraft.client.resources.I18n;
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

	public ItemShimenawaRope() {
		super(LibItemName.SHIMENAWA_ROPE);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.shimenawa_rope_header.name"));
		list.add(TextFormatting.GRAY + I18n.format("grimoire.tooltip.shimenawa_rope_description_top.name"));
		list.add(TextFormatting.RED + I18n.format("grimoire.tooltip.shimenawa_rope_description_bottom.name"));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		if(facing != EnumFacing.UP) return EnumActionResult.PASS;
		else if(player.canPlayerEdit(pos.up(1), facing, stack) && world.isAirBlock(pos.up(1))) {
			if(!world.isRaining()) return EnumActionResult.PASS;
			else {
				world.createExplosion(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 3.0F, false);
				world.setBlockState(pos.up(1), ModBlocks.HOLY_KEY_STONE.getDefaultState());
				world.spawnEntityInWorld(new EntityLightningBolt(world, pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5, false));
				world.spawnEntityInWorld(new EntityLightningBolt(world, pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5, false));
				--stack.stackSize;

				return EnumActionResult.SUCCESS;
			}
		}
		else return EnumActionResult.PASS;
	}
}
