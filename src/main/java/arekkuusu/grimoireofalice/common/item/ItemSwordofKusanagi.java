/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.entity.EntityNetherSoul;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemSwordofKusanagi extends ItemSwordOwner implements IOwnedBy {

	public ItemSwordofKusanagi(ToolMaterial material) {
		super(material, LibItemName.SWORD_OF_KUSANAGI);
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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sword_of_kusanagi_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sword_of_kusanagi_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sword_of_kusanagi_description.mid.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sword_of_kusanagi_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sword_of_kusanagi_shift.name"));
		}
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if (entityLiving instanceof EntityPlayer && ((EntityPlayer) entityLiving).getCooldownTracker().hasCooldown(this)) {
			return false;
		}
		entityLiving.playSound(GrimoireSoundEvents.ATTTACK_LONG, 0.2F, itemRand.nextFloat() * 0.4F + 0.8F);
		if (!entityLiving.world.isRemote) {
			double range = 50.0D;
			Vec3d look = entityLiving.getLookVec();
			Vec3d vec3d = new Vec3d(entityLiving.posX, entityLiving.posY + entityLiving.getEyeHeight(), entityLiving.posZ);
			Vec3d vec3d1 = new Vec3d(entityLiving.posX + look.xCoord * range, entityLiving.posY + look.yCoord * range, entityLiving.posZ + look.zCoord * range);
			RayTraceResult traceResult = entityLiving.world.rayTraceBlocks(vec3d, vec3d1, false, true, true);
			range = traceResult != null ? traceResult.hitVec.distanceTo(vec3d) : range;

			List<Entity> list = entityLiving.world.getEntitiesInAABBexcluding(entityLiving
					, entityLiving.getEntityBoundingBox().addCoord(look.xCoord * range, look.yCoord * range, look.zCoord * range).expandXyz(1.0D)
					, Entity::canBeCollidedWith);

			Entity entity = null;
			double d = 0.0D;
			for (Entity entity1 : list) {
				RayTraceResult movingObjectPosition1 = entity1.getEntityBoundingBox().calculateIntercept(vec3d, vec3d1);
				if (movingObjectPosition1 != null) {
					double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
					if (d1 < d || d == 0.0D) {
						entity = entity1;
						d = d1;
					}
				}
			}

			EntityNetherSoul entityNetherSoul = new EntityNetherSoul(entityLiving.world, entityLiving, entity);
			entityLiving.world.spawnEntityInWorld(entityNetherSoul);
			entityNetherSoul.setHeadingFromThrower(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0, 0.1F, 0);
		}
		if (entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 15);
		}
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
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(isOwner(stack, player)) {
				int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
				float convert = timeUsed * 6 / 20F;
				convert = (convert * convert + convert * 2.0F) / 3F;
				convert *= 1.5F;
				if(convert > 10F) return;
				for(int t = 0; t < convert; t++) {
					for(int u = 0; u < 10; u++) {
						spawnSmoke(player, itemRand.nextDouble(), itemRand.nextDouble());
						spawnSmoke(player, -itemRand.nextDouble(), -itemRand.nextDouble());
						spawnSmoke(player, itemRand.nextDouble(), -itemRand.nextDouble());
						spawnSmoke(player, -itemRand.nextDouble(), itemRand.nextDouble());
					}
				}
				if(!worldIn.isRemote) {
					List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(4.0D));
					for(EntityMob mob : list) {
						mob.attackEntityFrom(DamageSource.magic, convert);
						Vec3d playerPos = player.getPositionVector();
						Vec3d mobPos = mob.getPositionVector();
						double ratio = playerPos.distanceTo(mobPos) / 4;
						double scaling = 1 - ratio;
						Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
						mob.motionX = -motion.xCoord * 2;
						mob.motionY = .3F;
						mob.motionZ = -motion.zCoord * 2;
					}
				}
			}
			worldIn.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERDRAGON_SHOOT, SoundCategory.PLAYERS, 1F, 1F);
		}
	}

	private void spawnSmoke(EntityPlayer player, double xVelocity, double zVelocity) {
		player.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, xVelocity, 0, zVelocity);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.RINNOSUKE_MORICHIKA;
	}
}
