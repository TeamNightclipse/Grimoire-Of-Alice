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

import arekkuusu.grimoireofalice.entity.EntityUnzanFist;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemIchirinRing extends ItemModSword {

	public ItemIchirinRing(ToolMaterial material) {
		super(material, LibItemName.ICHIRIN_RING);
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Nyuudou bender");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Use with Rings in both Hands");
			if(!isHoldingItemsBothHands(player)){
				list.add(TextFormatting.DARK_RED + "Inactive");
			}
			else {
				list.add(TextFormatting.DARK_AQUA + "Active");
			}
		} else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(isHoldingItemsBothHands(playerIn)){
			playerIn.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (!worldIn.isRemote && isWearingUnzan(playerIn)) {
				EntityUnzanFist fist = new EntityUnzanFist(worldIn, playerIn);
				fist.setHeadingFromThrower(playerIn, entityLiving.rotationPitch, entityLiving.rotationYaw, 0.0F, 1.0F, 0);
				Vec3d look = playerIn.getLookVec();
				float distance = 3F;
				double dx = playerIn.posX + (look.xCoord * distance);
				double dy = playerIn.posY + playerIn.getEyeHeight() - 1;
				double dz = playerIn.posZ + (look.zCoord * distance);
				fist.setPosition(dx, dy, dz);
				worldIn.spawnEntityInWorld(fist);
				StatBase statBase = StatList.getObjectUseStats(this);
				if (statBase != null) {
					playerIn.addStat(statBase);
				}
			}
			worldIn.playSound(null, new BlockPos(entityLiving.posX + 0.5D, entityLiving.posY + 0.5D, entityLiving.posZ + 0.5D),
					SoundEvents.ENTITY_IRONGOLEM_HURT, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			playerIn.getCooldownTracker().setCooldown(this, 10);
		}
	}
		
	private boolean isHoldingItemsBothHands(EntityPlayer player) {
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		return main != null && off != null && main.getItem() == off.getItem();
	}

	private boolean isWearingUnzan(EntityPlayer player){
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		return stack != null && stack.getItem() == ModItems.ICHIRIN_AURA;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 100;
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
