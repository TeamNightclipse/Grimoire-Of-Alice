/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemUFOs extends ItemMod {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemUFOs() {
		super(LibItemName.UFOS);
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
		list.add(TextFormatting.GRAY + "Gathers items around the player");
		list.add(TextFormatting.ITALIC + "Does not work with point items");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int idk, boolean selected) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(selected) {
				itemsInRange(world, player, 10);
			}
		}
	}

	private void itemsInRange(World world, EntityPlayer player, double range) {
		List<EntityItem> aList = world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().expandXyz(range));

		for(EntityItem item : aList) {
			if(!stackHasRoom(item.getEntityItem(), player)) {
				continue;
			}
			item.setPickupDelay(0);
			if(player.getDistanceToEntity(item) < 1.5D) {
				continue;
			}
			givePlayerItems(item, player);
		}
	}

	private void givePlayerItems(EntityItem item, EntityPlayer player) {
		player.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, item.posX + itemRand.nextGaussian() / 8, item.posY + 0.2D,
				item.posZ + itemRand.nextGaussian() / 8, 0D, 0.9D, 0.0D);
		Vec3d look = player.getLookVec();
		double x = player.posX + look.xCoord * 0.2D;
		double y = player.posY - player.height / 2F;
		double z = player.posZ + look.zCoord * 0.2D;
		item.setPosition(x, y, z);
		player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.1F, 0.5F * ((itemRand.nextFloat() - itemRand.nextFloat()) * 0.7F + 1.8F));
	}

	@SuppressWarnings("ConstantConditions")
	private boolean stackHasRoom(ItemStack item, EntityPlayer player) {
		return player.hasCapability(ITEM_HANDLER_CAPABILITY, null)
				&& ItemHandlerHelper.insertItemStacked(player.getCapability(ITEM_HANDLER_CAPABILITY, null), item, true) == null;

	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}
}
