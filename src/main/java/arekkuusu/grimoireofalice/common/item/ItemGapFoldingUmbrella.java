package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
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

public class ItemGapFoldingUmbrella extends ItemMod {

	public ItemGapFoldingUmbrella() {
		super(LibItemName.FOLDING_UMBRELLA);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
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
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.folding_umbrella_header_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_bottom.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		Vec3d look = playerIn.getLookVec();
		float distance = 40F;
		double dx = playerIn.posX + look.xCoord * distance;
		double dy = playerIn.posY + 1 + look.yCoord * distance;
		double dz = playerIn.posZ + look.zCoord * distance;
		if(isSafe(worldIn, dx, dy, dz)) {
			playerIn.setPosition(dx, dy, dz);
		}
		itemStackIn.damageItem(1, playerIn);
		playerIn.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		playerIn.getCooldownTracker().setCooldown(this, 10);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	private boolean isSafe(World world, double x, double y, double z) {
		if(y < 0) return false;
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || !state.isSideSolid(world, pos, EnumFacing.UP);
	}
}
