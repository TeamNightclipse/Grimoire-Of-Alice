/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
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

public class ItemWallPassingChisel extends ItemMod {

	public ItemWallPassingChisel() {
		super(LibItemName.WALL_PASSING_CHISEL);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.wall_passing_chisel_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.wall_passing_chisel_description.name"));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		BlockPos travel = travelBlockPos(world, player, pos, facing);
		if(travel != null && isSafePos(world, travel.up())) { //If BlockPos isn't null and the block above is safe -->
			player.setPosition(travel.getX() + 0.5, travel.getY(), travel.getZ() + 0.5); //Move player to center of block
			player.playSound(SoundEvents.ENTITY_ENDEREYE_LAUNCH, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	/**
	 * @param world The world
	 * @param pos Pos of the block right clicked
	 * @param facing Facing of the block right clicked
	 * @return Position for player
	 */
	@Nullable
	private BlockPos travelBlockPos(World world, EntityPlayer player, BlockPos pos, EnumFacing facing) {
		facing = facing.getOpposite();
		BlockPos triedPos = pos;

		for(int i = 0; i < 50; i++) {
			IBlockState stateBase = world.getBlockState(triedPos);
			if(stateBase.getBlock() != Blocks.BEDROCK && stateBase.getBlock().canEntityDestroy(stateBase, world, pos, player)) {
				if(isSafePos(world, triedPos)) {
					//Logic to prevent player to suffocate or get in awkward positions
					if (facing == EnumFacing.DOWN) {
						if (!isSafePos(world, triedPos.offset(facing))) return null;
						triedPos = triedPos.offset(facing);
					}
					else if (facing != EnumFacing.UP) {
						if (isSafePos(world, triedPos.down())) {
							triedPos = triedPos.down();
						}
					}

					return triedPos;
				}
				else {
					triedPos = triedPos.offset(facing);
				}
			}
		}

		return null;
	}

	private boolean isSafePos(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos);// || state.isSideSolid(world, pos, EnumFacing.UP);
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
