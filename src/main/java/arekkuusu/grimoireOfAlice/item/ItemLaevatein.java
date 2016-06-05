/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import arekkuusu.grimoireOfAlice.entity.EntityFireBall2;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

@CleanupDone
public class ItemLaevatein extends ItemGOASword {

	ItemLaevatein(ToolMaterial material) {
		super(material);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "'Damage twig'");
		list.add(EnumChatFormatting.GOLD + "Only true Lolis can wield this sword");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getCurrentEquippedItem() == stack) {
				player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 0));
			}
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.addPotionEffect(new PotionEffect(Potion.weakness.id, 128, 0));
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.experienceLevel > 42) {
			stack.damageItem(10, player);
			if(!world.isRemote) {
				if(player.capabilities.isCreativeMode || player.inventory.hasItem(Items.fire_charge)) {
					world.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "mob.ghast.scream", 1.0F,
							itemRand.nextFloat() * 0.4F + 0.8F);
					Vec3 look = player.getLookVec();
					Vec3 position = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
					Vec3 fireBallPos = Vec3.createVectorHelper(position.xCoord + look.xCoord * 5, position.yCoord + look.yCoord * 5, position.zCoord + look.zCoord * 5);
					EntityFireBall2 fireball2 = new EntityFireBall2(world, player, fireBallPos, look);

					world.spawnEntityInWorld(fireball2);
					player.inventory.consumeInventoryItem(Items.fire_charge);
				}
			}
		}
		else {
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 256, 0));
			world.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "mob.ghast.scream", 1.0F,
					itemRand.nextFloat() * 0.4F + 0.8F);
		}
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
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

		if(!player.canPlayerEdit(x, y, z, side, stack)) {
			return false;
		}
		else {
			boolean success = false;
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(world.isAirBlock(x + i, y, z + j)) {
						world.setBlock(x + i, y, z + j, Blocks.fire);
						success = true;
					}
				}
			}

			if(success) {
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.blaze.death", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
			stack.damageItem(1, player);
			return true;
		}
	}
}
