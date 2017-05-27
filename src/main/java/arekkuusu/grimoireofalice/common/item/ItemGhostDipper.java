/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import com.google.common.collect.Lists;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Queue;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemGhostDipper extends ItemMod implements IOwnedBy {

	public ItemGhostDipper() {
		super(LibItemName.GHOST_DIPPER);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		addPropertyOverride(new ResourceLocation("using"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ghost_dipper_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ghost_dipper_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ghost_dipper_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ghost_dipper_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, true);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, raytraceresult);
		if(ret != null) return ret;

		//noinspection ConstantConditions
		if(raytraceresult == null) return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
		else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
		else {
			if(!playerIn.isSneaking()) {
				BlockPos blockpos = raytraceresult.getBlockPos();

				if (absorb(worldIn, blockpos)) {
					worldIn.playSound(null, blockpos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					if (itemStackIn.isItemDamaged()) {
						itemStackIn.setItemDamage(itemStackIn.getItemDamage() - 1);
					}
					playerIn.setActiveHand(hand);
					return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
				}
			} else {
				BlockPos pos = raytraceresult.getBlockPos();
				boolean replaceable = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
				BlockPos posUp = replaceable && raytraceresult.sideHit == EnumFacing.UP ? pos : pos.offset(raytraceresult.sideHit);

				if(playerIn.canPlayerEdit(posUp, raytraceresult.sideHit, itemStackIn)) {

					IBlockState iblockstate = worldIn.getBlockState(posUp);
					Material material = iblockstate.getMaterial();
					boolean isSolid = material.isSolid();
					boolean canReplace = iblockstate.getBlock().isReplaceable(worldIn, posUp);

					if(worldIn.isAirBlock(posUp) || !isSolid || canReplace) {
						if(worldIn.provider.doesWaterVaporize()) {
							worldIn.playSound(null, posUp, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
									2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

							for(int k = 0; k < 8; ++k) {
								worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posUp.getX() + itemRand.nextDouble(),
										posUp.getY() + itemRand.nextDouble(), posUp.getZ() + itemRand.nextDouble(), 0.0D, 0.0D, 0.0D);
							}
						}
						else {
							if(!worldIn.isRemote && (isSolid || canReplace) && !material.isLiquid()) {
								worldIn.destroyBlock(posUp, true);
							}

							worldIn.playSound(null, posUp, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
							worldIn.setBlockState(posUp, Blocks.FLOWING_WATER.getDefaultState(), 11);
							itemStackIn.damageItem(1, playerIn);
							playerIn.setActiveHand(hand);
							return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
						}
					}
				}
			}
		}

		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}

	private boolean absorb(World worldIn, BlockPos pos) {
		Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
		queue.add(new Tuple<>(pos, 0));
		int blocksChanged = 0;

		while(!queue.isEmpty() && blocksChanged <= 64) {
			Tuple<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockpos = tuple.getFirst();
			int depth = tuple.getSecond();

			for(EnumFacing enumfacing : EnumFacing.values()) {
				BlockPos offset = blockpos.offset(enumfacing);

				if(worldIn.getBlockState(offset).getMaterial() == Material.WATER) {
					++blocksChanged;

					//The client is for the most part concerned with if any blocks were changed. If we reach this far that is true. No need to do more.
					if(!worldIn.isRemote) {
						worldIn.setBlockState(offset, Blocks.AIR.getDefaultState());

						if(depth < 6) {
							queue.add(new Tuple<>(offset, depth + 1));
						}
					}
				}
			}
		}

		return blocksChanged > 0;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 2000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.MINAMITSU_MURASA;
	}
}
