package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

import java.util.List;

public class ItemWallPassingChisel extends ItemMod {

    public ItemWallPassingChisel() {
        super(LibItemName.WALLPASSINGCHISEL);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
        list.add(TextFormatting.GOLD + "Wall passing chisel");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        BlockPos suPos = getPos(world, pos, facing);
        if(suPos != null && world.getBlockState(suPos.up()).getBlock() == Blocks.AIR){ //If BlockPos isn't null and the block above is Air -->
            player.setPosition(suPos.getX() + 0.5, suPos.getY(), suPos.getZ() + 0.5); //Move player to center of block
            stack.damageItem(1, player);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    /**
     * @param world //The world
     * @param pos //Pos of the block right clicked
     * @param facing //Facing of the block right clicked
     * @return //Position for player
     */
    private BlockPos getPos(World world, BlockPos pos, EnumFacing facing){
        int i = 0;
        switch (facing){
            case DOWN:
                while (world.getBlockState(pos.up(i)).getBlock() != Blocks.AIR){ //'++i' until a block of Air is found
                    if(i >= 50){ //If 'i' is 50 or more, return null
                        return null;
                    }
                    ++i;
                }
                return pos.up(i); //Return pos + blocks to Air block
            case UP:
                while (world.getBlockState(pos.down(i)).getBlock() != Blocks.AIR){
                    if(i >= 50){
                        return null;
                    }
                    ++i;
                }
                if (world.getBlockState(pos.down(i + 1)).getBlock() != Blocks.AIR) {
                    return null;
                }
                return pos.down(i + 1);
            case NORTH:
                while (world.getBlockState(pos.south(i)).getBlock() != Blocks.AIR){
                    if(i >= 50){
                        return null;
                    }
                    ++i;
                }
                if (world.getBlockState(pos.down()) != Blocks.AIR) {
                    return pos.south(i);
                }
                return pos.south(i).down();
            case SOUTH:
                while (world.getBlockState(pos.north(i)).getBlock() != Blocks.AIR){
                    if(i >= 50){
                        return null;
                    }
                    ++i;
                }
                if (world.getBlockState(pos.down()) != Blocks.AIR) {
                    return pos.north(i);
                }
                return pos.north(i).down();
            case WEST:
                while (world.getBlockState(pos.east(i)).getBlock() != Blocks.AIR){
                    if(i >= 50){
                        return null;
                    }
                    ++i;
                }
                if (world.getBlockState(pos.down()) != Blocks.AIR) {
                    return pos.east(i);
                }
                return pos.east(i).down();
            case EAST:
                while (world.getBlockState(pos.west(i)).getBlock() != Blocks.AIR){
                    if(i >= 50){
                        break;
                    }
                    ++i;
                }
                if (world.getBlockState(pos.down()) != Blocks.AIR) {
                    return pos.west(i);
                }
                return pos.west(i).down();
            default:
                return null;
        }
    }
}
