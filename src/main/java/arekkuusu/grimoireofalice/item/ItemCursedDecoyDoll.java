package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityCursedDecoyDoll;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCursedDecoyDoll extends ItemMod {

	public ItemCursedDecoyDoll() {
		super(LibItemName.DECOY_DOLL);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_PURPLE + "Doll that takes damage for the player");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote) {
			EntityCursedDecoyDoll doll = new EntityCursedDecoyDoll(worldIn, playerIn);
			Vec3d look = playerIn.getLookVec();
			float distance = 2F;
			double dx = playerIn.posX + (look.xCoord * distance);
			double dy = playerIn.posY;
			double dz = playerIn.posZ + (look.zCoord * distance);
			doll.setPosition(dx, dy, dz);
			worldIn.spawnEntityInWorld(doll);
		}
		worldIn.playSound(null, new BlockPos(playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D),
				SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		--itemStackIn.stackSize;
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			EntityCursedDecoyDoll doll = new EntityCursedDecoyDoll(worldIn, playerIn);
			doll.setPosition(pos.getX()+ 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			worldIn.spawnEntityInWorld(doll);
		}
		worldIn.playSound(null, new BlockPos(playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D),
				SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		--stack.stackSize;
		return EnumActionResult.SUCCESS;
	}
}
