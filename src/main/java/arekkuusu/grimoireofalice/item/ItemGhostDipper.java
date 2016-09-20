/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Queue;

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
        if(GuiScreen.isShiftKeyDown()){
            list.add(TextFormatting.YELLOW + "Right click water to clear");
            list.add(TextFormatting.YELLOW + "Right click on land to place water");
        } else {
            list.add(TextFormatting.ITALIC + "Shift for details");
        }
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, raytraceresult);
        if (ret != null) return ret;

        if (raytraceresult == null)
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStackIn);

        } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();
            if(absorb(worldIn, blockpos)){
                worldIn.playSound(null, blockpos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        IBlockState iblockstate = world.getBlockState(pos.up(1));
        Material material = iblockstate.getMaterial();
        boolean flag1 = iblockstate.getBlock().isReplaceable(world, pos.up(1));

        if (!world.isAirBlock(pos.up(1)) && !flag1) {
            return  EnumActionResult.FAIL;
        } else {
            if (world.provider.doesWaterVaporize()){
                world.playSound(null, pos.up(1), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                for (int k = 0; k < 8; ++k) {
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.up(1).getX()+ Math.random(), pos.up(1).getY() + Math.random(), pos.up(1).getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                }
                return  EnumActionResult.FAIL;
            }
            if (!world.isRemote && flag1 && !material.isLiquid()) {
                world.destroyBlock(pos.up(1), true);
                world.playSound(null, pos.up(1), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos.up(1), Blocks.WATER.getStateFromMeta(0), 11);
            }
        }
        return EnumActionResult.SUCCESS;
    }

    private boolean absorb(World worldIn, BlockPos pos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        List<BlockPos> list = Lists.newArrayList();
        queue.add(new Tuple(pos, 0));
        int i = 0;

        while (!((Queue)queue).isEmpty()) {
            Tuple<BlockPos, Integer> tuple = (Tuple)queue.poll();
            BlockPos blockpos = tuple.getFirst();
            int j = tuple.getSecond();

            for (EnumFacing enumfacing : EnumFacing.values()) {
                BlockPos blockpos1 = blockpos.offset(enumfacing);

                if (worldIn.getBlockState(blockpos1).getMaterial() == Material.WATER) {
                    worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 2);
                    list.add(blockpos1);
                    ++i;

                    if (j < 6) {
                        queue.add(new Tuple(blockpos1, j + 1));
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        for (BlockPos blockpos2 : list) {
            worldIn.notifyNeighborsOfStateChange(blockpos2, Blocks.AIR);
        }

        return i > 0;
    }
}
