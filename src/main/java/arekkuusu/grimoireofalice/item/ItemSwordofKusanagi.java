/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import arekkuusu.grimoireofalice.helper.LogHelper;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordofKusanagi extends ItemSwordOwner {

	ItemSwordofKusanagi(ToolMaterial material) {
		super(material, LibItemName.SWORDOFKUSANAGI);
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
		list.add(TextFormatting.GOLD + "Divine sword forged with Hihi'irokane, a mythic metal");
		list.add(TextFormatting.GRAY + "Discovered within the fourth tail of the eight-headed,");
		list.add(TextFormatting.GRAY + "eight-tailed serpent Yamata-no-Orochi when it was slain ");
		list.add(TextFormatting.GRAY + "by the Shinto god Susanoo-no-Mikoto.");
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(!stack.hasTagCompound()) return;
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(isOwner(stack, player)){
				int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
				float convert = (timeUsed * 6) / 20F;
				convert = (convert * convert + convert * 2.0F) / 3F;
				convert *= 1.5F;
				if (convert > 10F) return;
				for(int t = 0; t < convert; t++){
					for(int u = 0;u < 10; u++){
						Random rand0 = player.getRNG();
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, rand0.nextDouble(), 0, rand0.nextDouble());
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, -rand0.nextDouble(), 0, -rand0.nextDouble());
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, rand0.nextDouble(), 0, -rand0.nextDouble());
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY, player.posZ, -rand0.nextDouble(), 0, rand0.nextDouble());
					}
				}
				List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(4.0D));
				for (EntityMob mob : list){
					mob.attackEntityFrom(DamageSource.magic, convert);
					Vec3d playerPos = player.getPositionVector();
					Vec3d mobPos = mob.getPositionVector();
					double ratio = playerPos.distanceTo(mobPos) / 4;
					double scaling = (1 - ratio);
					Vec3d motion = playerPos.subtract(mobPos).scale(scaling);
					mob.motionX = -motion.xCoord * 2;
					mob.motionY = .3F;
					mob.motionZ = -motion.zCoord * 2;
				}
			}
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
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
