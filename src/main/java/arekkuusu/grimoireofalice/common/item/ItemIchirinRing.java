/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.entity.EntityUnzanFist;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemIchirinRing extends ItemModSword implements IOwnedBy {

	public ItemIchirinRing(ToolMaterial material) {
		super(material, LibItemName.ICHIRIN_RING);
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_ring_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_use.name"));
			if(!isWearingUnzan(player)) {
				list.add(TextFormatting.DARK_RED + I18n.format("grimoire.tooltip.ichirin_inactive.name"));
			}
			else {
				list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.ichirin_active.name"));
			}
		}
		else {
			list.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_shift.name"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(isHoldingRing(hand, player)) {
			player.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<>(EnumActionResult.FAIL, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(isWearingUnzan(player)) {
				if(isStand(player) && player.isSneaking()) {
					player.getCooldownTracker().setCooldown(ModItems.ICHIRIN_UNZAN, 125);
					player.getCooldownTracker().setCooldown(this, 125);
					player.playSound(GrimoireSoundEvents.ORA, 1F, 1F);
				}
				else {
					if(!world.isRemote) {
						Vec3d look = player.getLookVec();
						float distance = 5F;
						double dx = player.posX + look.x * distance;
						double dy = player.posY + 1 + look.y * distance;
						double dz = player.posZ + look.z * distance;

						EntityUnzanFist fist = new EntityUnzanFist(world, player);
						fist.setPosition(dx, dy, dz);
						world.spawnEntity(fist);
						fist.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0F, 2F, 0F);
					}
					player.getCooldownTracker().setCooldown(this, 10);
					player.playSound(SoundEvents.ENTITY_IRONGOLEM_HURT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
				}
			}
		}
	}

	private boolean isStand(EntityPlayer player) {
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		return !stack.isEmpty() && stack.getDisplayName().equalsIgnoreCase("star platinum");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(attacker instanceof EntityPlayer && !isWearingUnzan((EntityPlayer) attacker)) {
			stack.damageItem(1, attacker);
		}
		else {
			target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 5);
			attacker.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		}
		target.motionX = -MathHelper.sin((float) Math.toRadians(attacker.rotationYaw));
		target.motionZ = MathHelper.cos((float) Math.toRadians(attacker.rotationYaw));
		return true;
	}

	private boolean isHoldingRing(EnumHand hand, EntityPlayer player) {
		ItemStack main = player.getHeldItem(hand);
		return !main.isEmpty() && main.getItem() == ModItems.ICHIRIN_RING;
	}

	private boolean isWearingUnzan(EntityPlayer player) {
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		return !stack.isEmpty() && stack.getItem() == ModItems.ICHIRIN_UNZAN;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
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

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.ICHIRIN_KUMOI;
	}
}
