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

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRodOfRemorse extends ItemMod {

	public ItemRodOfRemorse() {
		super(LibItemName.ROD_REMORSE);
		setMaxStackSize(1);
		setMaxDamage(1);
		addPropertyOverride(new ResourceLocation("used"),
				(stack, world, entity) -> getUsed(stack) ? 1F : 0F);
	}

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.rod_of_remorse_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.rod_of_remorse_description.name"));
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if(getUsed(itemStackIn)) {
			if(player.capabilities.isCreativeMode) {
				setUsed(itemStackIn, false);
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			}

			if(player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
				IItemHandler inventory = player.getCapability(ITEM_HANDLER_CAPABILITY, null);

				for(int i = 0; i < inventory.getSlots(); i++) {
					ItemStack stack = inventory.getStackInSlot(i);
					if(stack != null && isDyeBlack(stack)) {
						inventory.extractItem(i, 1, false);
						setUsed(itemStackIn, false);
						return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
					}
				}
			}
		}

		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}

	private boolean isDyeBlack(ItemStack stack) {
		for (int oreId : OreDictionary.getOreIDs(stack)) {
			if(OreDictionary.getOreName(oreId).equals("dyeBlack")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!getUsed(stack) && target.getHealth() > 1) {
			float perc = 0.75F;
			target.setHealth(target.getHealth() * perc);
			setUsed(stack, true);
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if(!target.worldObj.isRemote) {
			playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW + "- ")
					.appendSibling(new TextComponentTranslation(getUnlocalizedName() + ".entityHealthUsage").appendText(": "))
					.appendText(String.valueOf(target.getHealth())));
		}
		return true;
	}

	private void setUsed(ItemStack itemStack, boolean used) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setBoolean("IsUsed", used);
	}

	private boolean getUsed(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt != null && nbt.getBoolean("IsUsed");
	}


	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
