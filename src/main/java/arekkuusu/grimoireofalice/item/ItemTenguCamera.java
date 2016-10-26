package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityCameraSquare;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemTenguCamera extends ItemMod {

	public ItemTenguCamera(String id) {
		super(id);
		setMaxDamage(200);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("takingPhoto"), (stack, world, entity) ->
				entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_PURPLE + "Swipe the space in front of the player by taking photos");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			EntityCameraSquare camera = new EntityCameraSquare(worldIn, playerIn);
			Vec3d look = playerIn.getLookVec();
			float distance = 4F;
			double dx = playerIn.posX + (look.xCoord * distance);
			double dy = playerIn.posY + 2 + (look.yCoord * distance);
			double dz = playerIn.posZ + (look.zCoord * distance);
			camera.setPosition(dx, dy, dz);
			worldIn.spawnEntityInWorld(camera);
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		player.motionY = player.motionX = player.motionZ = 0;
		player.setPosition(player.prevPosX, player.prevPosY, player.prevPosZ);
		player.cameraPitch = player.prevCameraPitch;

		if(!player.worldObj.isRemote) {
			getEntities(player).forEach(entity -> {
				entity.motionY = entity.motionX = entity.motionZ = 0;
				entity.rotationYaw = entity.prevRotationYaw;
				entity.rotationPitch = entity.prevRotationPitch;
			});
		}
	}

	public List<EntityLivingBase> getEntities(EntityLivingBase player){
		Vec3d vec = player.getLookVec();
		return player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
				player.getEntityBoundingBox().offset(vec.xCoord * 5, vec.yCoord * 5, vec.zCoord * 5).expandXyz(4),
				entity -> entity != player);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer && !worldIn.isRemote) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			getEntities(player).forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10));
			player.getCooldownTracker().setCooldown(this, 100);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 7000;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}
}
