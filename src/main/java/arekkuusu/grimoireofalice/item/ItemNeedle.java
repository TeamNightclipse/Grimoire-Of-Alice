package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityNeedle;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemNeedle extends ItemModSword {

	ItemNeedle(ToolMaterial material) {
		super(material, LibItemName.NEEDLE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Shinmyoumaru owned this needle");
		list.add(TextFormatting.UNDERLINE + "Consider yourself lucky");
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
			if (!entityplayer.inventory.hasItemStack(stack)) return;

			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if (timeUsed < 3.0F) return;
			float convert = (timeUsed * 6) / 20F;
			convert = (convert * convert + convert * 2.0F) / 3F;
			if (convert < 0.1F) return;

			boolean isLoli = false;
			if (convert > 1.5F) {
				convert = 1.5F;
				isLoli = true;
			}
			convert *= 1.5F;

			worldIn.playSound(null, new BlockPos(entityplayer.posX + 0.5D, entityplayer.posY + 0.5D, entityplayer.posZ + 0.5D),
					SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			if (!worldIn.isRemote) {
				EntityNeedle entityNeedle = new EntityNeedle(worldIn, entityplayer);
				entityNeedle.setIsCritical(isLoli);
				entityNeedle.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 5.0F, convert, 1.0F);
				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(20), stack) > 0) {
					entityNeedle.setFire(100);
				}
				worldIn.spawnEntityInWorld(entityNeedle);
			}

			if (!entityplayer.capabilities.isCreativeMode) {
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
