/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Optional;

@net.minecraftforge.fml.common.Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemGapFoldingUmbrella extends ItemMod implements IOwnedBy {

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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_bottom.name"));
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.folding_umbrella_header_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;

			Optional<BlockPos> posLookedAt = getBlockPosLookedAt(player);
			BlockPos pos;
			if (posLookedAt.isPresent() && !player.isSneaking()) {
				pos = posLookedAt.get();
			} else {
				Vec3d look = player.getLookVec();
				double range = 40.0D;
				double dx = player.posX + look.x * range;
				double dy = player.posY + 1 + look.y * range;
				double dz = player.posZ + look.z * range;
				pos = new BlockPos(dx, dy, dz);
			}

			if (isSafe(world, pos)) {
				if (!world.isRemote && player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				}
				stack.damageItem(1, player);
			}
			world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), GrimoireSoundEvents.WARP,
					SoundCategory.PLAYERS, 0.2F, itemRand.nextFloat() * 0.4F + 0.8F);
			player.getCooldownTracker().setCooldown(this, 30);
		}
	}

	private Optional<BlockPos> getBlockPosLookedAt(EntityPlayer player) {
		double range = 40.0D;
		Vec3d look = player.getLookVec();
		Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		Vec3d vec3d1 = new Vec3d(player.posX + look.x * range, player.posY + player.getEyeHeight() + look.y * range, player.posZ + look.z * range);
		RayTraceResult raytraceresult = player.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
		if (raytraceresult != null) {
			return Optional.of(raytraceresult.getBlockPos().offset(raytraceresult.sideHit));
		}
		else return Optional.empty();
	}

	private boolean isSafe(World world, BlockPos pos) {
		if(pos.getY() < 0) return false;
		IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || !state.isSideSolid(world, pos, EnumFacing.UP);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 2000;
	}

	@net.minecraftforge.fml.common.Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.SEIJA_KIJIN;
	}
}
