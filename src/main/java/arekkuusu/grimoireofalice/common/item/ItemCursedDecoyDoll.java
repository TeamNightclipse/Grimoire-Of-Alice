/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.FormattedString;
import arekkuusu.grimoireofalice.common.ItemFlavor;
import arekkuusu.grimoireofalice.common.entity.EntityCursedDecoyDoll;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import static net.minecraft.item.EnumRarity.*;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemCursedDecoyDoll extends ItemModFlavored implements IOwnedBy {

	public ItemCursedDecoyDoll() {
		super(LibItemName.DECOY_DOLL, ItemFlavor.simpleBuilder()
				.common(FormattedString.withItalics("grimoire.tooltip.decoy_doll_header.name"))
				.rarity(UNCOMMON)
				.build());
		setMaxStackSize(1);
	}

	private static void spawnDoll(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			EntityCursedDecoyDoll doll = new EntityCursedDecoyDoll(world, player);
			Vec3d look = player.getLookVec();
			float distance = 2F;
			double dx = player.posX + look.x * distance;
			double dy = player.posY;
			double dz = player.posZ + look.z * distance;
			doll.setPosition(dx, dy, dz);
			world.spawnEntity(doll);
		}
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		stack.shrink(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		spawnDoll(stack, world, player);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
									  float hitX, float hitY, float hitZ) {
		spawnDoll(player.getHeldItem(hand), world, player);
		return EnumActionResult.SUCCESS;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.SEIJA_KIJIN;
	}
}
