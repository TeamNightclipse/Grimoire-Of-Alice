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
import java.util.Queue;

import com.google.common.collect.Lists;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGhostDipper extends ItemMod {

	public ItemGhostDipper() {
		super(LibItemName.GHOSTDIPPER);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Fearsome ladle known to sink monstrous ships");
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.YELLOW + "Right click water to clear");
			list.add(TextFormatting.YELLOW + "Right click on land to place water");
		}
		else {
			list.add(TextFormatting.ITALIC + "Shift for details");
		}
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, raytraceresult);
		if(ret != null) return ret;

		if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
		}
		else {
			BlockPos blockpos = raytraceresult.getBlockPos();
			if(absorb(worldIn, blockpos)) {
				worldIn.playSound(null, blockpos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		BlockPos posUp = pos.up();
		IBlockState iblockstate = world.getBlockState(posUp);
		Material material = iblockstate.getMaterial();
		boolean canReplace = iblockstate.getBlock().isReplaceable(world, posUp);

		if(!world.isAirBlock(posUp) && !canReplace) {
			return EnumActionResult.FAIL;
		}
		else {
			if(world.provider.doesWaterVaporize()) {
				world.playSound(null, posUp, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
						2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
				for(int k = 0; k < 8; ++k) {
					world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posUp.getX() + itemRand.nextDouble(), posUp.getY() + itemRand.nextDouble(),
							posUp.getZ() + itemRand.nextDouble(), 0.0D, 0.0D, 0.0D);
				}
				return EnumActionResult.FAIL;
			}
			else if(!world.isRemote && canReplace && !material.isLiquid()) {
				world.destroyBlock(posUp, true);
				world.playSound(null, posUp, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.setBlockState(posUp, Blocks.WATER.getDefaultState(), 11);
			}
		}
		return EnumActionResult.SUCCESS;
	}

	private boolean absorb(World worldIn, BlockPos pos) {
		Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
		queue.add(new Tuple<>(pos, 0));
		int blocksChanged = 0;

		while(!queue.isEmpty()) {
			Tuple<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockpos = tuple.getFirst();
			int j = tuple.getSecond();

			for(EnumFacing enumfacing : EnumFacing.values()) {
				BlockPos offset = blockpos.offset(enumfacing);

				if(worldIn.getBlockState(offset).getMaterial() == Material.WATER) {
					worldIn.setBlockState(offset, Blocks.AIR.getDefaultState());
					++blocksChanged;

					if(j < 6) {
						queue.add(new Tuple<>(offset, j + 1));
					}
				}
			}

			if(blocksChanged > 64) {
				break;
			}
		}

		return blocksChanged > 0;
	}
}
