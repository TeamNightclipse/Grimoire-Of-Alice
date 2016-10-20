package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityBarrier;
import arekkuusu.grimoireofalice.entity.EntityHakureiOrb;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemHakureiGohei extends ItemMod {

	private static final String[] MODES = {
			"§fPassive",
			"§bAura manipulation",
			"§dHakurei Yin-Yang Orbs",
			"§eExploding Barrier",
			"§6Motion Barrier",
			"§4Offensive"
	};

	public ItemHakureiGohei() {
		super(LibItemName.HAKUREI_GOHEI);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Gohei from a powerful Shrine Maiden");
		list.add("§7Gohei Mode: " + MODES[getMode(stack)]);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entityIn;
			if(!player.capabilities.isCreativeMode){
				if((isSelected || getMode(stack) == 0)) {
					player.capabilities.allowFlying = true;
					player.capabilities.isFlying = true;
				} else {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
				}
			}
		}
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);

		int mode = getMode(itemStackIn);
		if(!playerIn.isSneaking())
		if (mode == 2) {
			worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.HOSTILE, 1.0F,
					itemRand.nextFloat() * 0.1F + 0.8F);
			EntityHakureiOrb orb = new EntityHakureiOrb(worldIn, playerIn);
			if (!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(orb);
			}
		} else if(mode == 3 || mode == 4){
			EntityBarrier barrier = new EntityBarrier(worldIn, playerIn, (byte)mode);
			if (!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(barrier);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack itemStackIn, EntityLivingBase playerIn, int ticks) {
		if (getMode(itemStackIn) == 1) {
			if (playerIn instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)playerIn;
				double range = 5.0D;
				Vec3d look = player.getLookVec();
				Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
				Vec3d vec3d1 = new Vec3d(player.posX + look.xCoord * range, player.posY + look.yCoord * range, player.posZ + look.zCoord * range);
				RayTraceResult movingObjectPosition = player.worldObj.rayTraceBlocks(vec3d, vec3d1, false, true, true);
				if (movingObjectPosition != null) {
					vec3d1 = new Vec3d(movingObjectPosition.hitVec.xCoord, movingObjectPosition.hitVec.yCoord, movingObjectPosition.hitVec.zCoord);
				}
				EntityLivingBase entity = null;
				List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
						player.getEntityBoundingBox().addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expandXyz(1.0D),
						entityFound -> entityFound != playerIn);
				double d = 0.0D;
				for (EntityLivingBase entity1 : list) {
					float f2 = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f2, f2, f2);
					RayTraceResult movingObjectPosition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
					if (movingObjectPosition1 != null) {
						double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
						if (d1 < d || d == 0.0D) {
							entity = entity1;
							d = d1;
						}
					}
				}
				if (entity != null && !player.worldObj.isRemote) {
					float distance = 5F;
					double dx = player.posX + (look.xCoord * distance);
					double dy = player.posY + 2 + (look.yCoord * distance);
					double dz = player.posZ + (look.zCoord * distance);
					if(isSafe(player.worldObj, dx, dy, dz)) {
						entity.motionY = 0D;
						entity.fallDistance = 0;
						entity.setPosition(dx, dy, dz);
					}
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			if (playerIn.isSneaking()) {
				int mode = getMode(stack);
				mode += 1;
				if (mode < 0) {
					mode = 0;
				} else if (mode > 5) {
					mode = 0;
				}
				setMode(stack, mode);
			} else {
				int mode = getMode(stack);
				if (mode == 0) {
					Vec3d look = playerIn.getLookVec();
					float distance = 5F;
					double dx = playerIn.posX + (look.xCoord * distance);
					double dy = playerIn.posY + 1 + (look.yCoord * distance);
					double dz = playerIn.posZ + (look.zCoord * distance);
					if (isSafe(worldIn, dx, dy, dz)) {
						playerIn.setPosition(dx, dy, dz);
					}
				}
				playerIn.swingArm(EnumHand.MAIN_HAND);
				playerIn.getCooldownTracker().setCooldown(this, 10);
			}
			stack.damageItem(1, playerIn);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(getMode(stack) == 5)
		if(target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD){
			attacker.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
			target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 20);
		} else if(target.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD){
			attacker.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
			target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 10);
		} else if(target instanceof EntityPlayer){
			attacker.worldObj.spawnParticle(EnumParticleTypes.HEART, target.posX, target.posY + 1, target.posZ, 0.0D, 0.0D, 0.0D);
			target.attackEntityFrom(DamageSource.magic, 5);
		} else {
			target.attackEntityFrom(DamageSource.causeThornsDamage(attacker), 3);
		}
		return true;
	}

	private void setMode(ItemStack itemStack, int mode) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setByte("GoheiMode", (byte)mode);
		} else if(nbt.getShort("GoheiMode") >= 0) {
			nbt.setByte("GoheiMode", (byte)(mode));
		}
	}

	private int getMode(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? 0 : nbt.getByte("GoheiMode");
	}

	private boolean isSafe(World world, double x, double y, double z){
		BlockPos pos = new BlockPos(x, y, z);
		Block block = world.getBlockState(pos).getBlock();
		return block == Blocks.AIR || block == Blocks.DOUBLE_PLANT || block == Blocks.TALLGRASS;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
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
