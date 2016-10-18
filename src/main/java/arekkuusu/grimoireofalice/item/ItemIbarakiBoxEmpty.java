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

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIbarakiBoxEmpty extends ItemMod {

	public ItemIbarakiBoxEmpty() {
		super(LibItemName.IBARAKI_BOX_EMPTY);
		setMaxStackSize(1);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "In exchange for curing illnesses, the personality of the");
		list.add(TextFormatting.GOLD + "one who drinks from will temporarily become like an Oni's");
		list.add(TextFormatting.ITALIC + "Use like bucket");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, raytraceresult);
		if(ret != null) return ret;

		//noinspection ConstantConditions
		if(raytraceresult == null) {
			return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
		}
		else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
		}
		else {
			BlockPos blockpos = raytraceresult.getBlockPos();

			if(!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStackIn)) {
				return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
			}
			else { //No needed to try place content as its always empty
				IBlockState iblockstate = worldIn.getBlockState(blockpos);
				Material material = iblockstate.getMaterial();
				//Modified so it accepts both Water and Lava, as both return the same Item
				if((material == Material.WATER || material == Material.LAVA) && iblockstate.getValue(BlockLiquid.LEVEL) == 0) {
					worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
					StatBase stateBase = StatList.getObjectUseStats(this);
					if(stateBase != null) {
						playerIn.addStat(stateBase);
					}
					playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
					return new ActionResult<>(EnumActionResult.SUCCESS, this.fillBucket(itemStackIn, playerIn, ModItems.IBARAKI_BOX_FILLED));
				}
				else {
					return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
				}
			}
		}
	}

	private ItemStack fillBucket(ItemStack emptyBuckets, EntityPlayer player, Item fullBucket) {
		if(player.capabilities.isCreativeMode) {
			return emptyBuckets;
		}
		else if(--emptyBuckets.stackSize <= 0) {
			return new ItemStack(fullBucket);
		}
		else {
			if(!player.inventory.addItemStackToInventory(new ItemStack(fullBucket))) {
				player.dropItem(new ItemStack(fullBucket), false);
			}

			return emptyBuckets;
		}
	}

}
