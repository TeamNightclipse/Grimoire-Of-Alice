/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.item.ItemMod;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.util.ResourceLocation;
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
	private static final Item[] JEWELS = {
			Items.DIAMOND,
			Items.EMERALD,
			Items.GOLDEN_APPLE,
			Items.GOLD_INGOT,
			Items.GOLD_NUGGET
	};

	private static final int[] COLORS = {
			LibColor.COLOR_SATURATED_GREEN,
			LibColor.COLOR_SATURATED_YELLOW,
			LibColor.COLOR_SATURATED_MAGENTA,
			LibColor.COLOR_SATURATED_RED,
			LibColor.COLOR_SATURATED_BLUE
	};

	public ItemJeweledHourai() {
		super(LibItemName.JEWELED_HOURAI);
		setNoRepair();
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("jewels"),
				(stack, world, entity) -> entity != null && stack.hasTagCompound() ? (float)getJewels(stack) : 0F);
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
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.jeweled_hourai_header.name"));
		list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.jeweled_hourai_description.name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!worldIn.isRemote) {
			short jewels = getJewels(stack);
			if(jewels < 5 && entityIn.ticksExisted % 200 == 0) {
				jewels += 1;
				if(jewels < 0) {
					jewels = 0;
				}
				else if(jewels > 5) {
					jewels = 5;
				}
				setJewels(stack, jewels);
			}
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
			if(!entityLiving.isSneaking()) {
				if(getJewels(stack) >= 1) {
					int timeUsed = stack.getMaxItemUseDuration() - timeLeft;
					if(timeUsed > 30) {
						timeUsed = 30;
					}
					int color = COLORS[itemRand.nextInt(COLORS.length)];

					DanmakuBuilder danmaku = DanmakuBuilder.builder()
							.setUser(entityLiving)
							.setShot(LibShotData.SHOT_CRYSTAL1.setColor(color))
							.build();
					DanmakuCreationHelper.createRandomRingShot(danmaku, timeUsed, 4F, 1D);
					addJewels(stack, (short)-1);
				}
			}
			else {
				if(getJewels(stack) == 5) {
					if(entityLiving.hasCapability(itemHandlerCapability, null)) {
						int pos = itemRand.nextInt(JEWELS.length);
						ItemStack rest = ItemHandlerHelper.insertItemStacked(entityLiving.getCapability(itemHandlerCapability, null),
								new ItemStack(JEWELS[pos]), false);
						if(rest != null) {
							entityLiving.dropItem(rest.getItem(), rest.stackSize);
						}
						addJewels(stack, (short)-5);
					}
				}
			}
		}
		worldIn.playSound(null, new BlockPos(entityLiving.posX + 0.5D, entityLiving.posY + 0.5D, entityLiving.posZ + 0.5D),
				SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
	}

	private void addJewels(ItemStack itemStack, short charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setShort("Jewels", charge);
		}
		else if(nbt.getShort("Jewels") >= 0) {
			nbt.setShort("Jewels", (short)(nbt.getShort("Jewels") + charge));
		}
	}

	private void setJewels(ItemStack itemStack, short charge) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setShort("Jewels", charge);
		}
		else if(nbt.getShort("Jewels") >= 0) {
			nbt.setShort("Jewels", charge);
		}
	}

	private short getJewels(ItemStack itemStack) {
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
