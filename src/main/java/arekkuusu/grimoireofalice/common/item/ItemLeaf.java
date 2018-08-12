/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import arekkuusu.grimoireofalice.compat.danmakucore.LibGOAShotData;
import com.google.common.collect.Lists;
import net.katsstuff.teamnightclipse.danmakucore.DanmakuCore;
import net.katsstuff.teamnightclipse.danmakucore.danmaku.DanmakuTemplate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.JavaConversions;

public class ItemLeaf extends ItemBase {

	public ItemLeaf() {
		super(LibName.LEAF);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}

		if(player.isSneaking()) {
			for(int j = 0; j < 8; ++j) {
				world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
			}
		} else {
			if(!world.isRemote) {
				DanmakuTemplate.Builder builder = DanmakuTemplate.builder().setUser(player).setShot(LibGOAShotData.LEAF.setSize(2));
				DanmakuCore.proxy().spawnDanmaku(JavaConversions.asScalaBuffer(Lists.newArrayList(builder.build().asEntity())));
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72_000;
	}
}
