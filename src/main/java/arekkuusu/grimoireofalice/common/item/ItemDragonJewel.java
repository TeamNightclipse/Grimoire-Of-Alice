/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.entity.EntityDragonJewel;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDragonJewel extends ItemMod {

	public ItemDragonJewel() {
		super(LibItemName.DRAGON_JEWEL);
		setMaxStackSize(1);
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.dragon_jewel_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.dragon_jewel_description_top.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.dragon_jewel_description_bottom.name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if (isSelected && player.getCooldownTracker().hasCooldown(this) && player.ticksExisted % 2 == 0) {
				Vec3d vec = player.getLookVec();
				double x = player.posX;
				double y = player.posY + player.getEyeHeight();
				double z = player.posZ;

				worldIn.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.PLAYERS, 1F, 1F);
				for (int i = 0; i < 8; ++i) {
					double randX = x + itemRand.nextGaussian() / 2.0D;
					double randY = y + itemRand.nextGaussian() / 2.0D;
					double randZ = z + itemRand.nextGaussian() / 2.0D;

					for (int j = 0; j < 6; ++j) {
						worldIn.spawnParticle(EnumParticleTypes.DRAGON_BREATH, randX, randY, randZ, vec.xCoord * 0.08D * j, vec.yCoord * 0.6D,
								vec.zCoord * 0.08D * j);
					}
				}

				if (!worldIn.isRemote) {
					List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
							player.getEntityBoundingBox().offset(vec.xCoord * 3, 0, vec.zCoord * 3).expandXyz(4D), entity -> entity != player);
					list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 6));
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!playerIn.isSneaking()) {
			spawnJewel(itemStackIn, worldIn, playerIn);
		}
		else {
			playerIn.getCooldownTracker().setCooldown(this, 30);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	private void spawnJewel(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			EntityDragonJewel jewel = new EntityDragonJewel(world, player);
			jewel.setPosition(player.posX, player.posY + 2, player.posZ);
			world.spawnEntityInWorld(jewel);
		}
		if(!player.capabilities.isCreativeMode) {
			--stack.stackSize;
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		spawnJewel(stack, worldIn, playerIn);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
