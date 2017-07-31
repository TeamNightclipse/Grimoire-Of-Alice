package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.core.capability.IMalletCapability;
import arekkuusu.grimoireofalice.common.core.capability.MalletProvider;
import arekkuusu.grimoireofalice.common.core.net.MalletMessage;
import arekkuusu.grimoireofalice.common.core.net.PacketHandler;
import arekkuusu.grimoireofalice.common.entity.EntityMiracleLantern;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.data.ShotData;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.living.TouhouCharacter;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMiracleMallet extends ItemMod implements IOwnedBy {

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
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.miracle_mallet_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.miracle_mallet_description.name"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@SuppressWarnings("ConstantConditions")
	private void useMallet(EntityPlayer player, EnumHand hand) {
		if(!player.world.isRemote && player.hasCapability(MalletProvider.MALLET_CAPABILITY, null) && !player.getFoodStats().needFood() || player.capabilities.isCreativeMode) {
			IMalletCapability capability = player.getCapability(MalletProvider.MALLET_CAPABILITY, null);
			capability.setSmall(!capability.isSmall());
			capability.markDirty();
			if(player instanceof EntityPlayerMP) {
				PacketHandler.sendTo((EntityPlayerMP) player, new MalletMessage(capability, player.getUniqueID()));
			}
			PacketHandler.sendToNear(player, new MalletMessage(capability, player.getUniqueID()));
		}
		player.getCooldownTracker().setCooldown(this, 50);
		player.swingArm(hand);
		player.setActiveHand(hand);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		useMallet(player, hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			player.world.playSound(player, player.getPosition(), GrimoireSoundEvents.SIMPLE_BELL, SoundCategory.PLAYERS, 1F, 1F);
			if(player.isSneaking() && !player.world.isRemote) {
				Vec3d vec = player.getLookVec();
				List<EntityDanmaku> list = player.world.getEntitiesWithinAABB(EntityDanmaku.class, player.getEntityBoundingBox()
						.offset(vec.x * 10, vec.y * 10, vec.z * 10).grow(10));
				for(EntityDanmaku danmaku : list) {
					ShotData data = danmaku.getShotData();
					if(data.getSizeX() < 5 && data.getSizeY() < 5 && data.getSizeZ() < 5) {
						danmaku.setShotData(data.scaleSize(1.2F));
					}
				}
			}
			else if(!player.world.isRemote && !player.getCooldownTracker().hasCooldown(this)) {
				Vec3d vec = player.getLookVec();
				List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
						entityLiving.getEntityBoundingBox().offset(vec.x * 4, 0, vec.z * 4).grow(3D), entity -> entity != player);
				list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10F + itemRand.nextInt(10)));

				for(int i = 0; i < 4; i++) {
					EntityThrowable lantern = new EntityMiracleLantern(player.world, player);
					player.world.spawnEntity(lantern);
					lantern.setHeadingFromThrower(player, player.rotationPitch - (25 + itemRand.nextInt(20)), player.rotationYaw
							, 0F, 0.2F + 0.1F * itemRand.nextInt(3), 3F);
				}
				player.getCooldownTracker().setCooldown(this, 15);
			}
		}
		return super.onEntitySwing(entityLiving, stack);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if(target instanceof EntityPlayer) {
			useMallet((EntityPlayer) target, hand);
		}
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public TouhouCharacter character(ItemStack stack) {
		return TouhouCharacter.SHINMYOUMARU_SUKUNA;
	}
}
