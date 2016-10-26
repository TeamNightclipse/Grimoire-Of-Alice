/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityNazrinPendulum;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
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

public class ItemNazrinPendulum extends ItemMod {

	public ItemNazrinPendulum() {
		super(LibItemName.NAZRIN_PENDULUM);
        setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Magical pendulum that searches for ores underground");
        if(GuiScreen.isShiftKeyDown()){
            list.add(TextFormatting.YELLOW + "Right click to activate, Shift to dismiss");
        } else {
            list.add(TextFormatting.ITALIC + "SHIFT for details");
        }
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote) {
			EntityNazrinPendulum pendulum = new EntityNazrinPendulum(worldIn, playerIn, !playerIn.isSneaking());
			Vec3d look = playerIn.getLookVec();
			float distance = 2F;
			double dx = playerIn.posX + (look.xCoord * distance);
			double dy = playerIn.posY + playerIn.getEyeHeight() - 0.5;
			double dz = playerIn.posZ + (look.zCoord * distance);
			pendulum.setPosition(dx, dy, dz);
			worldIn.spawnEntityInWorld(pendulum);
		}
		--itemStackIn.stackSize;
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			EntityNazrinPendulum pendulum = new EntityNazrinPendulum(worldIn, playerIn, false);
			pendulum.setPosition(pos.getX()+ 0.5, pos.getY() + 2, pos.getZ() + 0.5);
			worldIn.spawnEntityInWorld(pendulum);
		}
		playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		--stack.stackSize;
		return EnumActionResult.SUCCESS;
	}
}
