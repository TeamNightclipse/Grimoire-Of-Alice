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

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShinmyoumaruNeedle extends ItemModSword {

	public ItemShinmyoumaruNeedle(ToolMaterial material) {
		super(material, LibItemName.SHINMYOUMARU_NEEDLE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.needle_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			if (isSelected && player.getCooldownTracker().hasCooldown(this) && !wasShifting(stack)) {
				if (player.ticksExisted % 2 == 0) {
					EnumHand hand = player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
					player.swingArm(hand);

					Vec3d vec = player.getLookVec();
					float distance = 4F + itemRand.nextInt(3);
					double dx = player.posX + vec.xCoord * distance;
					double dy = player.posY + 2.5 + vec.yCoord * distance;
					double dz = player.posZ + vec.zCoord * distance;
					GrimoireOfAlice.proxy.sparkleFX(ParticleFX.NEEDLE_SWING, null, dx, dy, dz, itemRand.nextFloat(), 0F, 0F);
					worldIn.playSound(player, player.getPosition(), GrimoireSoundEvents.NEEDLE_SWEEP, SoundCategory.PLAYERS, 1F, 1F);

					if (!worldIn.isRemote) {
						List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
								player.getEntityBoundingBox().offset(vec.xCoord * 2, 0, vec.zCoord * 2).expandXyz(4D), entity -> entity != player);
						list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 6));
					}
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityLiving);
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) timeUsed = 50;
			setShifting(stack, player.isSneaking());
			player.getCooldownTracker().setCooldown(this, timeUsed);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	private void setShifting(ItemStack itemStack, boolean does) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setBoolean("NeedleMode", does);
	}

	private boolean wasShifting(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt != null && nbt.getBoolean("NeedleMode");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
