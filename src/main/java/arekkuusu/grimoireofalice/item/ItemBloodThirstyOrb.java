package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBloodThirstyOrb extends ItemMod {

	ItemBloodThirstyOrb() {
		super(LibItemName.BLOODTHIRSTYORB);
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Feed it blood in exchange for certain death");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) { //Recycled code...
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if (player.isSneaking()) {
				moveToClosestPlayer(worldIn, player);
			} else {
				moveToMob(player);
			}
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			player.getCooldownTracker().setCooldown(this, timeUsed);
			player.attackEntityFrom(DamageSource.generic, 1);
		}
	}

	private void moveToClosestPlayer(World worldIn, EntityPlayer player){
		List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.getPosition()).expandXyz(30));
		if(!entities.isEmpty()) {
			for (Entity entity : entities){
				if(entity instanceof EntityPlayer) {
					int x = (int) (entity.posX + 0.5);
					int y = (int) entity.posY;
					int z = (int) (entity.posZ + 0.5);
					player.setPosition(x, y, z);
					break;
				}
			}
		}
	}

	private void moveToMob(EntityPlayer player){
		double range = 32.0D;
		Vec3d look = player.getLookVec();
		Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		Vec3d vec3d1 = new Vec3d(player.posX + look.xCoord * range, player.posY + look.yCoord * range, player.posZ + look.zCoord * range);
		RayTraceResult movingObjectPosition = player.worldObj.rayTraceBlocks(vec3d, vec3d1, false, true, true);
		if (movingObjectPosition != null) {
			vec3d1 = new Vec3d(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
		}
		EntityLivingBase entity = null;
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expandXyz(1.0D));
		double d = 0.0D;
		for (Entity entity1 : list) {
			if (entity1 instanceof EntityLivingBase) {
				float f2 = 0.3F;
				AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f2, f2, f2);
				RayTraceResult movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
				if (movingObjectPosition1 != null) {
					double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
					if (d1 < d || d == 0.0D) {
						entity = (EntityLivingBase) entity1;
						d = d1;
					}
				}
			}
		}
		if (entity != null) {
			double x = entity.posX + 0.5;
			double y = entity.posY -  look.yCoord;
			double z = entity.posZ + 0.5;
			player.setPosition(x, y, z);
		}
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
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
