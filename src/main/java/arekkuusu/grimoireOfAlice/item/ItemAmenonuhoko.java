/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import com.google.common.collect.Range;

import arekkuusu.grimoireOfAlice.block.GOABlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemAmenonuhoko extends ItemSword {

	ItemAmenonuhoko(ToolMaterial material) {
		super(material);
		setMaxDamage(200);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "Heavenly jeweled spear");
		list.add(EnumChatFormatting.GOLD + "Created by Elder Gods,");
		list.add(EnumChatFormatting.GOLD + "once used to raise the");
		list.add(EnumChatFormatting.GOLD + "primordial land-mass,");
		list.add(EnumChatFormatting.GOLD + "Onogoro-shima, from the sea");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if(stack.getItemDamage() < stack.getMaxDamage()) {
			stack.setItemDamage(stack.getItemDamage() - 1);
		}

		if(par5 && entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer)entity;
			if(!world.isRemote && !entityplayer.capabilities.isCreativeMode) {
				if(entityplayer.getCurrentEquippedItem().getItem() == GOAItem.itemAmenonuhoko) {
					if(stack.getItemDamage() > 0) {
						entityplayer.fallDistance = 0.0F;
					}
				}
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		if(stack.getItemDamage() == 0) {
			stack.damageItem(199, player);
		}
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		if(stack.getItemDamage() == 0) {
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
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.endermen.portal", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

				//Layer1
				replaceAirComact(world, x - 1, y, z);
				replaceAirComact(world, x, y, z - 1);
				replaceAirComact(world, x, y, z);
				replaceAirComact(world, x, y, z + 1);
				replaceAirComact(world, x + 1, y, z);

				//Layer2/3/4
				for(int i = 1; i <= 3; i++) {

					for(int j = -1; j <= 1; j++) {
						for(int k = -1; k <= 1; k++) {
							replaceAirComact(world, x + j, y + i, z + k);
						}
					}

					replaceAirComact(world, x - 2, y + i, z);
					replaceAirComact(world, x, y + i, z - 2);
					replaceAirComact(world, x, y + i, z + 2);
					replaceAirComact(world, x + 2, y + i, z);
				}

				//Layer3 Corner
				replaceAirComact(world, x - 2, y + 2, z - 1);
				replaceAirComact(world, x - 2, y + 2, z + 1);

				replaceAirComact(world, x - 1, y + 2, z - 2);
				replaceAirComact(world, x - 1, y + 2, z + 2);

				replaceAirComact(world, x + 1, y + 2, z - 2);
				replaceAirComact(world, x + 1, y + 2, z + 2);

				replaceAirComact(world, x + 2, y + 2, z + 1);
				replaceAirComact(world, x + 2, y + 2, z - 1);

				//Layer5
				replaceAirComact(world, x - 1, y + 4, z);
				replaceAirComact(world, x, y + 4, z - 1);
				replaceAirComact(world, x, y + 4, z);
				replaceAirComact(world, x, y + 4, z + 1);
				replaceAirComact(world, x + 1, y + 4, z);

				stack.damageItem(199, player);
			}
			return true;
		}
		else {
			return false;
		}
	}

	private void replaceAirComact(World world, int x, int y, int z) {
		if(world.isAirBlock(x, y, z)) {
			world.setBlock(x, y, z, GOABlock.blockCompactStone);
		}
	}
}
