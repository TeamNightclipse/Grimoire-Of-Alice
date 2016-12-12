package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;
import java.util.Optional;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.entity.EntityMiracleLantern;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMiracleMallet extends ItemMod {

	public ItemMiracleMallet() {
		super(LibItemName.MIRACLE_MALLET);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.miracle_mallet_header.name"));
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.miracle_mallet_description.name"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	private void useMallet(EntityPlayer player, EnumHand hand) {
		if (!player.getFoodStats().needFood() || player.capabilities.isCreativeMode) {
			if (!player.getEntityData().hasKey("MalletResized")) {
				float size = player.isSneaking() ? 0.5F : 1.5F;

				player.eyeHeight = player.eyeHeight + (player.isSneaking() ? -1.00F : 1.00F);
				player.getEntityData().setFloat("MalletResized", size);
			} else {
				float size = player.getEntityData().getFloat("MalletResized");
				float eyeHeight = player.eyeHeight;

				if (player.isSneaking()) {
					size -= 0.5;
					if (eyeHeight > 1.00F) {
						eyeHeight -= 1.00F;
					}
				} else {
					size += 0.5;
					if (eyeHeight < 3.00F) {
						eyeHeight += 1.00F;
					}
				}

				if (size <= 0) {
					size = 0.5F;
				}
				if (size > 2) {
					size = 2.0F;
				}

				player.eyeHeight = eyeHeight;
				player.getEntityData().setFloat("MalletResized", size);
			}
		}
		player.getCooldownTracker().setCooldown(this, 50);
		player.swingArm(hand);
		player.setActiveHand(hand);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		useMallet(playerIn, hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			player.worldObj.playSound(player, player.getPosition(), GrimoireSoundEvents.SIMPLE_BELL, SoundCategory.PLAYERS, 1F, 1F);
			if (player.isSneaking()) {
				Optional<Entity> lookedAt = Vector3.getEntityLookedAt(player, entity -> entity != player && entity instanceof EntityDanmaku, 35);
				if (lookedAt.isPresent()) { //FIXME: Never present
					EntityDanmaku danmaku = (EntityDanmaku) lookedAt.get();
					ShotData data = danmaku.getShotData();
					danmaku.setShotData(data.scaleSize(1.2F));
				}
			}
			else if (!player.worldObj.isRemote && !player.getCooldownTracker().hasCooldown(this)) {
				Vec3d vec = player.getLookVec();
				List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
						entityLiving.getEntityBoundingBox().offset(vec.xCoord * 4, 0, vec.zCoord * 4).expandXyz(3D), entity -> entity != player);
				list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player).setDamageBypassesArmor(), 10F + itemRand.nextInt(10)));

				for (int i = 0; i < 4; i++) {
					EntityThrowable lantern = new EntityMiracleLantern(player.worldObj, player);
					player.worldObj.spawnEntityInWorld(lantern);
					lantern.setHeadingFromThrower(player, player.rotationPitch - (25 + itemRand.nextInt(20)), player.rotationYaw
							, 0F, 0.2F + 0.1F * itemRand.nextInt(3), 3F);
				}
				player.getCooldownTracker().setCooldown(this, 25);
			}
		}
		return super.onEntitySwing(entityLiving, stack);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		useMallet(playerIn, hand);
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
