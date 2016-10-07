/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.entity.EntityFireBalloon;
import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.handler.EnumTextures;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLaevatein extends ItemModSword {

	ItemLaevatein(ToolMaterial material) {
		super(material, LibItemName.LAEVATEIN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Magic staff gambantein, Lævateinn of fire and chaos");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer)entity;
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0));
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 128, 0));
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.capabilities.isCreativeMode || playerIn.experienceLevel > 30) {
			worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 1.0F,
					itemRand.nextFloat() * 0.1F + 0.8F);
			itemStackIn.damageItem(10, playerIn);
			if(!worldIn.isRemote) {
				if(playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItemStack(new ItemStack(Items.FIRE_CHARGE))) {
					Vec3d look = playerIn.getLookVec();
					Vec3d position = new Vec3d(playerIn.posX, playerIn.posY, playerIn.posZ);
					Vec3d fireBallPos = new Vec3d(position.xCoord + look.xCoord * 5, position.yCoord + look.yCoord * 5, position.zCoord + look.zCoord * 5);
					EntityFireBalloon fireball2 = new EntityFireBalloon(worldIn, playerIn, fireBallPos, look);

					worldIn.spawnEntityInWorld(fireball2);
					playerIn.inventory.deleteStack(new ItemStack(Items.FIRE_CHARGE));
					EntityMagicCircle circle = new EntityMagicCircle(worldIn, playerIn, EnumTextures.RED_NORMAL, 15);
					worldIn.spawnEntityInWorld(circle);
					playerIn.getCooldownTracker().setCooldown(this, 10);
				}
			}
		}
		else {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 256, 0));
			worldIn.playSound(playerIn, playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D, SoundEvents.ENTITY_GHAST_SCREAM,
					SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.1F + 0.8F);
			playerIn.getCooldownTracker().setCooldown(this, 500);
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		BlockPos block = pos.offset(facing);

		if(!player.canPlayerEdit(block, facing, stack)) {
			return EnumActionResult.PASS;
		}
		else {
			boolean success = false;
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					BlockPos newPos = block.add(i, 0, j);
					if(world.isAirBlock(newPos)) {
						world.setBlockState(newPos, Blocks.FIRE.getDefaultState());
						success = true;
					}
				}
			}

			if(success) {
				world.playSound(player, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, SoundEvents.ENTITY_BLAZE_DEATH,
						SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
			stack.damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.BLAZE_ROD;
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
