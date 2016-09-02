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
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLaevatein extends ItemModSword {

	ItemLaevatein(ToolMaterial material) {
		super(material);
		setUnlocalizedName(LibItemName.LAEVATEIN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Magic staff gambantein, Lævateinn of fire and chaos");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getHeldItemMainhand() == stack) {
				player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 0, 0));
			}
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 128, 0));
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.experienceLevel > 30) {
			itemStackIn.damageItem(10, playerIn);
			if(!worldIn.isRemote) {
				if(playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItemStack(new ItemStack(Items.FIRE_CHARGE))) {
					worldIn.playSound(playerIn, new BlockPos(playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D), new SoundEvent( new ResourceLocation("mob.ghast.scream")), SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
					Vec3d look = playerIn.getLookVec();
					Vec3d position = new Vec3d(playerIn.posX, playerIn.posY, playerIn.posZ);
					Vec3d fireBallPos = new Vec3d(position.xCoord + look.xCoord * 5, position.yCoord + look.yCoord * 5, position.zCoord + look.zCoord * 5);
					EntityFireBalloon fireball2 = new EntityFireBalloon(worldIn, playerIn, fireBallPos, look);

					worldIn.spawnEntityInWorld(fireball2);
					playerIn.inventory.deleteStack(new ItemStack(Items.FIRE_CHARGE));
				}
			}
		}
		else {
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 256, 0));
			worldIn.playSound(playerIn, new BlockPos(playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D), new SoundEvent( new ResourceLocation("mob.ghast.scream")), SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		int side = facing.getIndex();
		if(side == 0) {
			--y;
		}

		if(side == 1) {
			++y;
		}

		if(side == 2) {
			--z;
		}

		if(side == 3) {
			++z;
		}

		if(side == 4) {
			--x;
		}

		if(side == 5) {
			++x;
		}

		if(!player.canPlayerEdit(new BlockPos(x, y, z), facing, stack)) {
			return EnumActionResult.PASS;
		}
		else {
			boolean success = false;
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(world.isAirBlock(new BlockPos(x + i, y, z + j))) {
						world.addBlockEvent(new BlockPos(x + i, y, z + j), Blocks.FIRE, 1, 1);
						success = true;
					}
				}
			}

			if(success) {
				world.playSound(player, new BlockPos(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D), new SoundEvent( new ResourceLocation("mob.blaze.death")), SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
			stack.damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}
	}
}
