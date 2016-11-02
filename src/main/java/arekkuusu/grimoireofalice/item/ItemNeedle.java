package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.entity.EntityNeedle;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNeedle extends ItemModSword {

	public ItemNeedle(ToolMaterial material) {
		super(material, LibItemName.NEEDLE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.needle_header.name"));
		list.add(TextFormatting.UNDERLINE + I18n.format("grimoire.tooltip.needle_description.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;

			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed < 3) return;
			float convert = timeUsed * 6 / 20F;
			convert = (convert * convert + convert * 2.0F) / 3F;
			if(convert < 0.1F) return;

			boolean isLoli = false;
			if(convert > 1.5F) {
				convert = 1.5F;
				isLoli = true;
			}
			convert *= 1.5F;

			entityplayer.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
			if(!worldIn.isRemote) {
				EntityNeedle entityNeedle = new EntityNeedle(worldIn, entityplayer);
				entityNeedle.setIsCritical(isLoli);
				entityNeedle.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 5.0F, convert, 1.0F);
				if(EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0) {
					entityNeedle.setFire(100);
				}
				worldIn.spawnEntityInWorld(entityNeedle);
			}

			if(!entityplayer.capabilities.isCreativeMode) {
				stack.damageItem(1, entityplayer);
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 100;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
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
