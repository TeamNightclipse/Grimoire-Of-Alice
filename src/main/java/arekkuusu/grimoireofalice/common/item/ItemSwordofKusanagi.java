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
import arekkuusu.grimoireofalice.common.core.format.FormattedString;
import arekkuusu.grimoireofalice.common.core.format.ItemFlavor;
import arekkuusu.grimoireofalice.common.core.helper.MathUtil;
import arekkuusu.grimoireofalice.common.core.helper.MiscHelper;
import arekkuusu.grimoireofalice.common.entity.EntityNetherSoul;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import static net.minecraft.item.EnumRarity.*;
import static net.minecraft.util.text.TextFormatting.*;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemSwordofKusanagi extends ItemSwordOwner implements IOwnedBy {

	public ItemSwordofKusanagi(ToolMaterial material) {
		super(material, LibItemName.SWORD_OF_KUSANAGI, ItemFlavor.simpleBuilder()
				.common(FormattedString.withItalics(WHITE, "grimoire.tooltip.sword_of_kusanagi_header.name"))
				.shift(FormattedString.withItalics("grimoire.tooltip.sword_of_kusanagi_description_body.name"))
				.effect(true)
				.rarity(EPIC)
				.build());
		setNoRepair();
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer && ((EntityPlayer) entityLiving).getCooldownTracker().hasCooldown(this)) {
			return false;
		}
		entityLiving.playSound(GrimoireSoundEvents.ATTTACK_LONG, 0.2F, itemRand.nextFloat() * 0.4F + 0.8F);
		if(!entityLiving.world.isRemote) {
			double range = 50.0D;
			Vec3d look = entityLiving.getLookVec();
			Vec3d vec3d = new Vec3d(entityLiving.posX, entityLiving.posY + entityLiving.getEyeHeight(), entityLiving.posZ);
			Vec3d vec3d1 = new Vec3d(entityLiving.posX + look.x * range, entityLiving.posY + look.y * range, entityLiving.posZ + look.z * range);
			RayTraceResult traceResult = entityLiving.world.rayTraceBlocks(vec3d, vec3d1, false, true, true);
			range = traceResult != null ? traceResult.hitVec.distanceTo(vec3d) : range;

			List<Entity> list = entityLiving.world.getEntitiesInAABBexcluding(entityLiving
					, entityLiving.getEntityBoundingBox().expand(look.x * range, look.y * range, look.z * range).grow(1.0D)
					, Entity::canBeCollidedWith);

			Entity entity = null;
			double d = 0.0D;
			for(Entity entity1 : list) {
				RayTraceResult movingObjectPosition1 = entity1.getEntityBoundingBox().calculateIntercept(vec3d, vec3d1);
				if(movingObjectPosition1 != null) {
					double d1 = vec3d.distanceTo(movingObjectPosition1.hitVec);
					if(d1 < d || MathUtil.fuzzyEqual(d, 0D)) {
						entity = entity1;
						d = d1;
					}
				}
			}

			EntityNetherSoul entityNetherSoul = new EntityNetherSoul(entityLiving.world, entityLiving, entity);
			entityLiving.world.spawnEntity(entityNetherSoul);
			entityNetherSoul.setHeadingFromThrower(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0, 0.1F, 0);
		}
		if(entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 15);
		}
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(isOwner(stack, player)) {
				int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
				float convert = timeUsed * 6 / 20F;
				convert = (convert * convert + convert * 2.0F) / 3F;
				convert *= 1.5F;
				if(convert > 10F) {
					return;
				}
				for(int t = 0; t < convert; t++) {
					for(int u = 0; u < 10; u++) {
						spawnSmoke(player, itemRand.nextDouble(), itemRand.nextDouble());
						spawnSmoke(player, -itemRand.nextDouble(), -itemRand.nextDouble());
						spawnSmoke(player, itemRand.nextDouble(), -itemRand.nextDouble());
						spawnSmoke(player, -itemRand.nextDouble(), itemRand.nextDouble());
					}
				}
				if(!world.isRemote) {
					List<EntityMob> list = world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(4.0D));
					for(EntityMob mob : list) {
						mob.attackEntityFrom(DamageSource.MAGIC, convert);
						MiscHelper.pushEntity(player, mob);
					}
				}
			}
			world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERDRAGON_SHOOT, SoundCategory.PLAYERS, 1F, 1F);
		}
	}

	private static void spawnSmoke(EntityPlayer player, double xVelocity, double zVelocity) {
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
		return 72_000;
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
