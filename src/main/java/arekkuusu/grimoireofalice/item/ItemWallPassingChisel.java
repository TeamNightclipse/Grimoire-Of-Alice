package arekkuusu.grimoireofalice.item;

import java.util.List;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWallPassingChisel extends ItemMod {

	ItemWallPassingChisel() {
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
		list.add(TextFormatting.ITALIC + "It works as a fancy hair pin");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		BlockPos suPos = getPos(world, pos, facing);
		if(suPos != null && world.getBlockState(suPos.up()).getBlock() == Blocks.AIR) { //If BlockPos isn't null and the block above is Air -->
			player.setPosition(suPos.getX() + 0.5, suPos.getY(), suPos.getZ() + 0.5); //Move player to center of block
			world.playSound(null, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D),
					SoundEvents.ENTITY_ENDEREYE_LAUNCH, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
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
	private BlockPos getPos(World world, BlockPos pos, EnumFacing facing) {
		facing = facing.getOpposite();
		BlockPos triedPos = pos;

		for(int i = 0; i < 50; i++) {
			if(world.getBlockState(triedPos).getBlock() != Blocks.BEDROCK) {
				if(world.getBlockState(triedPos).getBlock() == Blocks.AIR) {
					//Logic to prevent player to suffocate or get in awkward positions
					if(facing == EnumFacing.DOWN) {
						if(world.getBlockState(triedPos.offset(facing)).getBlock() != Blocks.AIR) {
							return null;
						}
						triedPos = triedPos.offset(facing);
					}
					else if(facing != EnumFacing.UP) {
						if(world.getBlockState(triedPos.down()).getBlock() == Blocks.AIR) {
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

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
