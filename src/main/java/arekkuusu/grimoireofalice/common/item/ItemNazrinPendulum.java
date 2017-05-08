/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import arekkuusu.grimoireofalice.common.entity.EntityNazrinPendulum;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@net.minecraftforge.fml.common.Optional.Interface(iface = "IOwnedBy", modid = "danmakucore", striprefs = true)
public class ItemNazrinPendulum extends ItemMod implements IOwnedBy {

	public ItemNazrinPendulum() {
		super(LibItemName.NAZRIN_PENDULUM);
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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_pendulum_header.name"));
		list.add(TextFormatting.AQUA + I18n.format("grimoire.tooltip.nazrin_pendulum_ore.name") + " " + getOre(stack));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_pendulum_description.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_pendulum_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (timeLeft < 40) {
				if (playerIn.getHeldItemOffhand() != null) {
					ItemStack itemStack = playerIn.getHeldItemOffhand();
					Optional<String> ore = Arrays.stream(OreDictionary.getOreIDs(itemStack))
							.mapToObj(OreDictionary::getOreName).filter(s -> s.startsWith("ore")).findFirst();

					ore.ifPresent(s -> setOre(stack, s));
				}
				else {
					setOre(stack, "");
				}
				return;
			}

			if (!worldIn.isRemote) {
				EntityNazrinPendulum pendulum = new EntityNazrinPendulum(worldIn, playerIn, stack, getOre(stack), !playerIn.isSneaking());
				Vec3d look = playerIn.getLookVec();
				float distance = 2F;
				double dx = playerIn.posX + look.xCoord * distance;
				double dy = playerIn.posY + playerIn.getEyeHeight() - 0.5;
				double dz = playerIn.posZ + look.zCoord * distance;
				pendulum.setPosition(dx, dy, dz);
				worldIn.spawnEntityInWorld(pendulum);
			}
			playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
			if (!playerIn.capabilities.isCreativeMode)
				--stack.stackSize;
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			EntityNazrinPendulum pendulum = new EntityNazrinPendulum(worldIn, playerIn, stack, getOre(stack), false);
			pendulum.setPosition(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);
			worldIn.spawnEntityInWorld(pendulum);
		}
		playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		--stack.stackSize;
		return EnumActionResult.SUCCESS;
	}

	private void setOre(ItemStack itemStack, String ore) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setString("oreBlock", ore);
	}

	private String getOre(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? "" : nbt.getString("oreBlock");
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 80;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@net.minecraftforge.fml.common.Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.NAZRIN;
	}
}
