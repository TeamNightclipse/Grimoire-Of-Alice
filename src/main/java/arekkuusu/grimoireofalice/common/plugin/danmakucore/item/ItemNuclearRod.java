/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.item.ItemMod;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.LibGOAShotData;
import arekkuusu.grimoireofalice.common.potion.ModPotions;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.katsstuff.danmakucore.lib.LibColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemNuclearRod extends ItemMod implements IOwnedBy {

	public ItemNuclearRod() {
		super(LibItemName.NUCLEAR_ROD);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("using"),
				(stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nuclear_rod_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase livingBase, int count) {
		if(livingBase instanceof  EntityPlayer && hasFullSet((EntityPlayer) livingBase)) {
			List<EntityLivingBase> list = livingBase.world.getEntitiesWithinAABB(EntityLivingBase.class, livingBase.getEntityBoundingBox().expandXyz(5), entity -> entity != livingBase);
			for (EntityLivingBase living : list) {
				living.addPotionEffect(new PotionEffect(ModPotions.RADIATION_POISONING, 100));
			}
		}
		if (count % 50 == 0) {
			livingBase.playSound(GrimoireSoundEvents.CAUTION, 0.2F, 0F);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 15);
		}
		int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
		if (timeUsed < 20) return;

		entityLiving.playSound(GrimoireSoundEvents.WAVE, 0.2F, 1F);
		if (!worldIn.isRemote) {
			if (entityLiving.isSneaking()) {
				DanmakuTemplate danmaku = DanmakuTemplate.builder()
						.setUser(entityLiving)
						.setMovementData(0.5F)
						.setShot(LibGOAShotData.SUN.setColor(LibColor.COLOR_SATURATED_RED).setSize(5))
						.build();

				if(entityLiving instanceof  EntityPlayer && hasFullSet((EntityPlayer) entityLiving)) {
					DanmakuCreationHelper.createSphereShot(Quat.orientationOf(entityLiving), danmaku, 4, 5, entityLiving.rotationPitch, 1D);
				}
				else {
					DanmakuCreationHelper.createCircleShot(Quat.orientationOf(entityLiving), danmaku, 5, entityLiving.rotationPitch, 1D);
				}
			}
			else {
				EntityDanmaku danmaku = DanmakuTemplate.builder()
						.setUser(entityLiving)
						.setShot(LibGOAShotData.SUN.setColor(LibColor.COLOR_SATURATED_RED).setSize(5))
						.setMovementData(1F)
						.build().asEntity();
				worldIn.spawnEntityInWorld(danmaku);
                Vec3d vec3 = entityLiving.getLookVec();
                entityLiving.motionX -= vec3.xCoord;
                entityLiving.motionY -= vec3.yCoord;
                entityLiving.motionZ -= vec3.zCoord;
			}
		}
	}

	private boolean hasFullSet(EntityPlayer player) {
		return player.inventory.armorInventory[0] != null && player.inventory.armorInventory[2] != null
				&& player.inventory.armorInventory[0].getItem() == ModItems.NUCLEAR_BOOTS
				&& player.inventory.armorInventory[2].getItem() == ModItems.UTSUHO_WINGS;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}

	@Override
	public EnumTouhouCharacters character(ItemStack stack) {
		return EnumTouhouCharacters.UTSUHO_REIUJI_OKUU;
	}
}
