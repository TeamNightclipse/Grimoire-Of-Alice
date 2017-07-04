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
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordOwner extends ItemModSword {

	public ItemSwordOwner(ToolMaterial material, String id) {
		super(material, id);
	}

	public static final String OWNER_TAG = "GrimoireOwner";

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setUniqueId(OWNER_TAG, player.getUniqueID());
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		if(stack.hasTagCompound()) {
			UUID ownerUuid = getOwnerUUID(stack);
			if(ownerUuid != null && UsernameCache.containsUUID(ownerUuid)) {
				list.add(TextFormatting.ITALIC + "Property of " + UsernameCache.getLastKnownUsername(ownerUuid));
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(selected && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;

			if(!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			NBTTagCompound compound = stack.getTagCompound();
			//noinspection ConstantConditions
			if(!compound.hasUniqueId(OWNER_TAG)) {
				compound.setUniqueId(OWNER_TAG, player.getUniqueID());
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Nullable
	public UUID getOwnerUUID(ItemStack stack) {
		return stack.hasTagCompound() ? stack.getTagCompound().getUniqueId(OWNER_TAG) : null;
	}

	@SuppressWarnings("ConstantConditions")
	public boolean isOwner(ItemStack stack, EntityPlayer player) {
		return stack.hasTagCompound() && player.getUniqueID().equals(getOwnerUUID(stack));
	}
}
