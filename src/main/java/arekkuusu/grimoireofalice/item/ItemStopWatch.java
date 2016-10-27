package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.entity.EntityStopWatch;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStopWatch extends ItemMod {

	public ItemStopWatch() {
		super(LibItemName.STOP_WATCH);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("using"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Sakuya stop watch");
		list.add(TextFormatting.ITALIC + "Right click to activate");
		list.add(TextFormatting.ITALIC + "Shift + left click to dismiss");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(!worldIn.isRemote) {
				EntityStopWatch watch = new EntityStopWatch(worldIn, player);
				Vec3d look = player.getLookVec();
				float distance = 1F;
				double dx = player.posX + look.xCoord;
				double dy = player.posY + player.getEyeHeight() - 1;
				double dz = player.posZ + look.zCoord * distance;
				watch.setPosition(dx, dy, dz);
				worldIn.spawnEntityInWorld(watch);
			}
			if(!player.capabilities.isCreativeMode) {
				--stack.stackSize;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 7000;
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
