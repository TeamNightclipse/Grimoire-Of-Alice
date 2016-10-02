package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

public class ItemSacredToyosatomimi extends ItemSwordOwner {

	ItemSacredToyosatomimi(ToolMaterial material) {
		super(material, LibItemName.SACREDTOYOSATOMIMI);
		setNoRepair();
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

	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Divine sword from Toyosatomimi");
		list.add(TextFormatting.GRAY + "Finds all souls around the player");
		list.add(TextFormatting.GRAY + "and gathers the essence of life");
		list.add(TextFormatting.GRAY + "in the form of an orb");
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		double speed = 1;
		target.motionX = -Math.sin(Math.toRadians(attacker.rotationYaw)) * speed;
		target.motionZ = Math.cos(Math.toRadians(attacker.rotationYaw)) * speed;
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(!worldIn.isRemote){
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) {
				return;
			}
			if(!stack.hasTagCompound()) return;
			if(entityLiving instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)entityLiving;
				if(isOwner(stack, player)) {
					List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(timeUsed));
					if(!list.isEmpty()) {
						player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
						for (EntityMob mob : list) {
							int x = (int)mob.posX;
							int y = (int)mob.posY;
							int z = (int)mob.posZ;
							player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- " + mob.getName() + " : {" + x + ", " + y + ", " + z + "}"));
						}
						player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - - - - - - - - - - - -"));
					} else {
						player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - No souls found - - - - - - -"));
					}
				}
				player.getCooldownTracker().setCooldown(this, timeLeft);
				stack.damageItem(1, player);
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
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
