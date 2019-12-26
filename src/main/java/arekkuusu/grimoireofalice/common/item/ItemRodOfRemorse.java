/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
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

public class ItemRodOfRemorse extends ItemBase  {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemRodOfRemorse() {
		super(LibName.ROD_REMORSE);
		setMaxStackSize(1);
		setMaxDamage(1);
		addPropertyOverride(new ResourceLocation("used"),
				(stack, world, entity) -> getUsed(stack) ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(getUsed(stack)) {
			if(player.capabilities.isCreativeMode) {
				setUsed(stack, false);
				return new ActionResult<>(EnumActionResult.SUCCESS, stack);
			}

			IItemHandler inventory = player.getCapability(ITEM_HANDLER_CAPABILITY, null);

			for(int i = 0; i < inventory.getSlots(); i++) {
				ItemStack invStack = inventory.getStackInSlot(i);
				if(!invStack.isEmpty() && isDyeBlack(invStack)) {
					inventory.extractItem(i, 1, false);
					setUsed(stack, false);
					return new ActionResult<>(EnumActionResult.SUCCESS, stack);
				}
			}
		}

		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	private static boolean isDyeBlack(ItemStack stack) {
		for(int oreId : OreDictionary.getOreIDs(stack)) {
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
		if(!getUsed(stack) && target.getHealth() > 1) {
			float perc = 0.75F;
			target.setHealth(target.getHealth() * perc);
			if(target.getLastDamageSource() != null) {
				target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 4));
			}
			setUsed(stack, true);
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if(!target.world.isRemote) {
			player.sendMessage(new TextComponentString(TextFormatting.YELLOW + "- ")
					.appendSibling(new TextComponentTranslation(getTranslationKey() + ".entityHealthUsage").appendText(": "))
					.appendText(String.valueOf(target.getHealth())));
		}
		return true;
	}

	private static void setUsed(ItemStack itemStack, boolean used) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setBoolean("IsUsed", used);
	}

	private static boolean getUsed(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt != null && nbt.getBoolean("IsUsed");
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
