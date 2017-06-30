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

import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemIbarakiBoxEmpty extends ItemMod implements IOwnedBy {

	public ItemIbarakiBoxEmpty() {
		super(LibItemName.IBARAKI_BOX_EMPTY);
		setMaxStackSize(1);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ibaraki_box_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ibaraki_box_description.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		RayTraceResult raytraceresult = rayTrace(world, player, true);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, world, stack, raytraceresult);
		if(ret != null) return ret;

		//noinspection ConstantConditions
		if(raytraceresult == null) return new ActionResult<>(EnumActionResult.PASS, stack);
		else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) return new ActionResult<>(EnumActionResult.PASS, stack);
		else {
			BlockPos blockpos = raytraceresult.getBlockPos();

			if(world.isBlockModifiable(player, blockpos)
					&& player.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, stack)) {
				IBlockState iblockstate = world.getBlockState(blockpos);
				Material material = iblockstate.getMaterial();
				//Modified so it accepts both Water and Lava, as both return the same Item
				if((material == Material.WATER || material == Material.LAVA) && iblockstate.getValue(BlockLiquid.LEVEL) == 0) {
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
					player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
					return new ActionResult<>(EnumActionResult.SUCCESS, fillBucket(stack, player, ModItems.IBARAKI_BOX_FILLED));
				}
			}
			return new ActionResult<>(EnumActionResult.FAIL, stack);
		}
	}

	private ItemStack fillBucket(ItemStack emptyBuckets, EntityPlayer player, Item fullBucket) {
		if(player.capabilities.isCreativeMode) return emptyBuckets;
		else {
			emptyBuckets.shrink(1);
			if(emptyBuckets.isEmpty()) return new ItemStack(fullBucket);
			else {
				if(!player.inventory.addItemStackToInventory(new ItemStack(fullBucket))) {
					player.dropItem(new ItemStack(fullBucket), false);
				}

				return emptyBuckets;
			}
		}
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.KASEN_IBARAKI;
	}
}
