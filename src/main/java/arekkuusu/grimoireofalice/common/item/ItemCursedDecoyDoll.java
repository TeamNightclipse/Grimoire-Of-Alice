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

import arekkuusu.grimoireofalice.common.entity.EntityCursedDecoyDoll;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.decoy_doll_header.name"));
	}

	private void spawnDoll(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			EntityCursedDecoyDoll doll = new EntityCursedDecoyDoll(world, player);
			Vec3d look = player.getLookVec();
			float distance = 2F;
			double dx = player.posX + look.xCoord * distance;
			double dy = player.posY;
			double dz = player.posZ + look.zCoord * distance;
			doll.setPosition(dx, dy, dz);
			world.spawnEntityInWorld(doll);
		}
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		--stack.stackSize;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		spawnDoll(itemStackIn, worldIn, playerIn);
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		spawnDoll(stack, worldIn, playerIn);
		return EnumActionResult.SUCCESS;
	}
}
