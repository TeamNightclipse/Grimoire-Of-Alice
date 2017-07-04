/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelMarisaHat;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibGuiID;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemMarisaHat extends ItemModArmor implements ISpecialArmor, IOwnedBy {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemMarisaHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.MARISA_HAT, EntityEquipmentSlot.HEAD);
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.marisa_hat_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.marisa_hat_description.name"));
	}

	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound oldCapNbt) {
		return new InvProvider();
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, 1, 5);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 3;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		stack.damageItem(damage, entity);
		if(stack.getItemDamage() == 0) {
			IItemHandler handler = stack.getCapability(ITEM_HANDLER_CAPABILITY, null);
			for(int i = 0; i < handler.getSlots(); i++) {
				if(!entity.world.isRemote) {
					EntityItem item = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, handler.extractItem(i, 64, false));
					entity.world.spawnEntity(item);
				}
			}
			entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
		}
	}

	private static class InvProvider implements ICapabilitySerializable<NBTBase> {

		@CapabilityInject(IItemHandler.class)
		private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

		private final IItemHandler inv = new ItemStackHandler(3) {

			@Override
			public ItemStack insertItem(int slot, ItemStack toInsert, boolean simulate) {
				return toInsert.hasCapability(ITEM_HANDLER_CAPABILITY, null) ? toInsert : super.insertItem(slot, toInsert, simulate);
			}
		};

		@Override
		public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
		}

		@Nullable
		@Override
		public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
			if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
			}
			else {
				return null;
			}
		}

		@Override
		public NBTBase serializeNBT() {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
		}

		@Override
		public void deserializeNBT(NBTBase nbt) {
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!player.isSneaking()) {
			EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(stack);
			ItemStack slotStack = player.getItemStackFromSlot(entityequipmentslot);

			if(slotStack.isEmpty()) {
				player.setItemStackToSlot(entityequipmentslot, stack.copy());
				stack.setCount(0);
			}
		}
		else {
			player.openGui(GrimoireOfAlice.instance, LibGuiID.HAT, world, hand.ordinal(), -1, -1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(!player.isSneaking()) {
			world.getEntitiesWithinAABB(EntityItem.class, player.getEntityBoundingBox().grow(5)).forEach(i -> i.onCollideWithPlayer(player));
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Item.getItemFromBlock(Blocks.WOOL);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if(model == null) {
			model = new ModelMarisaHat();
		}
		model.setModelAttributes(imodel);
		return model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLocations.MARISA_HAT.toString();
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.MARISA_KIRISAME;
	}
}
