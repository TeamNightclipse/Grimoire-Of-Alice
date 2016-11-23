/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.item.ItemMod;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.plugin.danmakucore.variant.GOADanmakuVariants;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.helper.DanmakuHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLeaf extends ItemMod {

	public ItemLeaf() {
		super(LibItemName.LEAF);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.leaf_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.leaf_description.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	/*
	 * After a player uses the item, it will spawn in the world
	 * an EntityLeaf that will travel and fall slowly.
	*/
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer)entityLiving;
			if(!playerIn.capabilities.isCreativeMode) {
				--stack.stackSize;
			}

			DanmakuHelper.playShotSound(playerIn);

			if(!worldIn.isRemote) {
				DanmakuBuilder.Builder danmaku = DanmakuBuilder.builder().setUser(playerIn).setVariant(GOADanmakuVariants.LEAF);
				float anglePitch = playerIn.isSneaking() ? 45 : -45;
				worldIn.spawnEntityInWorld(danmaku.build().asEntity());
				danmaku.setShot(danmaku.shot.setSize(2));
				danmaku.setAngle(danmaku.angle.rotate(Quat.eulerToQuat(0F, anglePitch, 0F)));
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

}
