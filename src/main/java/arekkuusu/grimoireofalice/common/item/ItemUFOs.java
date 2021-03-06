/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class ItemUFOs extends ItemBase {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemUFOs() {
		super(LibName.UFO);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.playSound(GrimoireSoundEvents.UFO_SPAWN, 0.01F, 1F);
		setActive(stack);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int idk, boolean selected) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if(selected && isActive(stack)) {
				if(player.ticksExisted % 14 == 0) {
					player.playSound(GrimoireSoundEvents.UFO_IDDLE, 0.01F, 1F);
				}
				itemsInRange(world, player);
			}
		}
	}

	private static void itemsInRange(World world, EntityPlayer player) {
		List<EntityItem> aList = world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().grow(10D));

		for(EntityItem item : aList) {
			if(!hasRoomForStack(item.getItem(), player) && player.getDistanceSq(item) < 1.5D * 1.5D) {
				continue;
			}
			item.setPickupDelay(0);
			givePlayerItems(item, player);
		}
	}

	private static void givePlayerItems(EntityItem item, EntityPlayer player) {
		Vec3d look = player.getLookVec();
		double x = player.posX + look.x * 0.2D;
		double y = player.posY + player.height / 2F;
		double z = player.posZ + look.z * 0.2D;
		item.setPosition(x, y, z);
	}

	@SuppressWarnings("ConstantConditions")
	private static boolean hasRoomForStack(ItemStack item, EntityPlayer player) {
		return player.hasCapability(ITEM_HANDLER_CAPABILITY, null)
				&& ItemHandlerHelper.insertItemStacked(player.getCapability(ITEM_HANDLER_CAPABILITY, null), item, true).isEmpty();
	}

	private static void setActive(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
			nbt.setBoolean("Active", true);
		} else {
			nbt.setBoolean("Active", !nbt.getBoolean("Active"));
		}
	}

	private static boolean isActive(ItemStack stack) {
		return stack.getTagCompound() != null && stack.getTagCompound().getBoolean("Active");
	}
}
