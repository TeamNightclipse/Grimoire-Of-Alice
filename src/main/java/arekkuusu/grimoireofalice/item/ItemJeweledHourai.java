/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemJeweledHourai extends ItemMod {

	@CapabilityInject(IItemHandler.class)
	private static Capability<IItemHandler> itemHandlerCapability;
	private static final Item[] JEWELS =
			{Items.DIAMOND, ModItems.hihiirokane, Items.EMERALD, Items.GOLDEN_APPLE, Items.GOLD_INGOT, Items.GOLD_NUGGET};

	ItemJeweledHourai() {
		super(LibItemName.JEWELEDHOURAI);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "A jeweled branch from the mythical island of Hōrai");
		list.add(TextFormatting.DARK_AQUA + "\"Mirage—the Vision of the Intangible\"");
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		int jewels = getJewels(stack);
		if(jewels > 0 && entityIn.ticksExisted % 50 == 0) {
			jewels -= 50;
			if(jewels < 0) {
				jewels = 0;
			}
			setJewels(stack, jewels);
			System.out.println(jewels);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@SuppressWarnings("ConstantConditions") //Liar
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(!worldIn.isRemote) {
			if(entityLiving.isSneaking()) {
				if(getJewels(stack) <= 200) {
					addJewels(stack, 50);
				}
			}
			else {
				if(getJewels(stack) <= 200) {
					if(entityLiving.hasCapability(itemHandlerCapability, null)) {
						int pos = itemRand.nextInt(5);
						ItemStack rest = ItemHandlerHelper.insertItemStacked(entityLiving.getCapability(itemHandlerCapability, null),
								new ItemStack(JEWELS[pos]), false);
						if(rest != null) {
							entityLiving.dropItem(rest.getItem(), rest.stackSize);
						}
						addJewels(stack, 400);
					}
				}
			}
		}
		worldIn.playSound(null, new BlockPos(entityLiving.posX + 0.5D, entityLiving.posY + 0.5D, entityLiving.posZ + 0.5D),
				SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
	}

	private void addJewels(ItemStack itemStack, int charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setShort("Jewels", (short)charge);
		}
		else if(nbt.getShort("Jewels") >= 0) {
			nbt.setShort("Jewels", (short)(nbt.getShort("Jewels") + charge));
		}
	}

	private void setJewels(ItemStack itemStack, int charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setShort("Jewels", (short)charge);
		}
		else if(nbt.getShort("Jewels") >= 0) {
			nbt.setShort("Jewels", (short)(charge));
		}
	}

	private int getJewels(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getShort("Jewels");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 500;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}