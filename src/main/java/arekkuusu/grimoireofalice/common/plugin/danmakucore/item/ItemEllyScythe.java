/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.common.entity.EntityEllyScythe;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.item.ItemModSword;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.data.AbstractVector3;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.helper.DanmakuHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEllyScythe extends ItemModSword {

	public ItemEllyScythe(ToolMaterial material) {
		super(material, LibItemName.ELLY_SCYTHE);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.elly_scythe_header.name"));
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.attackEntityFrom(DamageSource.causeThornsDamage(user), 5);
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(player.isSneaking()) {
				int duration = getMaxItemUseDuration(stack) - timeLeft;
				float durationSeconds = duration / 20F;
				durationSeconds = (durationSeconds * durationSeconds + durationSeconds * 2.0F) / 3F;
				if(durationSeconds < 0.1F) return;

				boolean critical = false;
				if(durationSeconds > 1.5F) {
					durationSeconds = 1.5F;
					critical = true;
				}
				durationSeconds *= 1.5F;

				DanmakuHelper.playShotSound(player);
				if(!worldIn.isRemote) {
					EntityEllyScythe scythe = new EntityEllyScythe(worldIn, player, stack, durationSeconds);
					scythe.setCritical(critical);
					Vec3d look = player.getLookVec();
					float distance = 1.5F;
					double dx = player.posX + look.xCoord * distance;
					double dz = player.posZ + look.zCoord * distance;
					scythe.setPosition(dx, player.posY + 1.5, dz);
					worldIn.spawnEntityInWorld(scythe);
				}

				if(!player.capabilities.isCreativeMode) {
					player.inventory.mainInventory[player.inventory.currentItem] = null;
				}
			}
			else if(!worldIn.isRemote) {
				for(int i = 0; i < 25; i++) {
					DanmakuHelper.playShotSound(player);
					spawnGroundDanmaku(player);
				}
				EntityMagicCircle circle = new EntityMagicCircle(worldIn, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 50);
				worldIn.spawnEntityInWorld(circle);
				player.getCooldownTracker().setCooldown(this, 50);
			}
		}
	}

	//Taken from DanmakuCore in SpellcardEntityDelusionEnlightenment
	private void spawnGroundDanmaku(EntityPlayer player) {
		AbstractVector3 angle = Vector3.getVecWithoutY(Vector3.randomVector());
		Random random = new Random();
		Vector3 posSource = new Vector3(player).offset(angle, random.nextDouble() * 16);
		Vector3 posReach = posSource.offset(Vector3.Down(), 16);

		RayTraceResult ray = player.worldObj.rayTraceBlocks(posSource.toVec3d(), posReach.toVec3d());

		Vector3 spawnPos = ray != null ? new Vector3(ray.hitVec) : posReach;

		EntityDanmaku danmaku = DanmakuBuilder.builder()
				.setUser(player)
				.setAngle(Vector3.Up())
				.setMovementData(0.2D)
				.setPos(spawnPos)
				.setShot(LibShotData.SHOT_SCALE.setColor(LibColor.COLOR_SATURATED_RED))
				.build().asEntity();
		player.worldObj.spawnEntityInWorld(danmaku);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
