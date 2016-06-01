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

	public ItemAmenonuhoko(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setMaxDamage(200);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE + "Heavenly jeweled spear");
		p_77624_3_.add(EnumChatFormatting.GOLD + "Created by Elder Gods,");
		p_77624_3_.add(EnumChatFormatting.GOLD + "once used to raise the");
		p_77624_3_.add(EnumChatFormatting.GOLD + "primordial land-mass,");
		p_77624_3_.add(EnumChatFormatting.GOLD + "Onogoro-shima, from the sea");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par1ItemStack.getItemDamage() < par1ItemStack.getMaxDamage()) {
			par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
		}

		if(par5 && par3Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer)par3Entity;
			if(!par2World.isRemote && !entityplayer.capabilities.isCreativeMode) {
				if(entityplayer.getCurrentEquippedItem().getItem() == GOAItem.itemAmenonuhoko) {
					if(par1ItemStack.getItemDamage() > 0) {
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
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_,
			int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if(p_77648_1_.getItemDamage() == 0) {
			if(p_77648_7_ == 0) {
				--p_77648_5_;
			}

			if(p_77648_7_ == 1) {
				++p_77648_5_;
			}

			if(p_77648_7_ == 2) {
				--p_77648_6_;
			}

			if(p_77648_7_ == 3) {
				++p_77648_6_;
			}

			if(p_77648_7_ == 4) {
				--p_77648_4_;
			}

			if(p_77648_7_ == 5) {
				++p_77648_4_;
			}

			if(!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_)) {
				return false;
			}
			else {
				p_77648_3_.playSoundEffect(p_77648_4_ + 0.5D, p_77648_5_ + 0.5D, p_77648_6_ + 0.5D, "mob.endermen.portal", 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_)) {
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, GOABlock.blockCompactStone);
				}
				//Layer1
				if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_, p_77648_6_)) {
					p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_, p_77648_6_, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_ + 1)) {
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_ + 1, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_, p_77648_6_)) {
					p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_, p_77648_6_, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_ - 1)) {
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_ - 1, GOABlock.blockCompactStone);
				}
				//Layer2/3/4
				for(int i = 1; i <= 3; i++) {
					if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_)) {
						p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_ + i, p_77648_6_)) {
						p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_ + i, p_77648_6_, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ + 1)) {
						p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ + 1, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_ + i, p_77648_6_)) {
						p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_ + i, p_77648_6_, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ - 1)) {
						p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ - 1, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_ + i, p_77648_6_ + 1)) {
						p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_ + i, p_77648_6_ + 1, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_ + i, p_77648_6_ - 1)) {
						p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_ + i, p_77648_6_ - 1, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_ + i, p_77648_6_ + 1)) {
						p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_ + i, p_77648_6_ + 1, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_ + i, p_77648_6_ - 1)) {
						p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_ + i, p_77648_6_ - 1, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ + 2, p_77648_5_ + i, p_77648_6_)) {
						p_77648_3_.setBlock(p_77648_4_ + 2, p_77648_5_ + i, p_77648_6_, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ + 2)) {
						p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ + 2, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_ - 2, p_77648_5_ + i, p_77648_6_)) {
						p_77648_3_.setBlock(p_77648_4_ - 2, p_77648_5_ + i, p_77648_6_, GOABlock.blockCompactStone);
					}
					if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ - 2)) {
						p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + i, p_77648_6_ - 2, GOABlock.blockCompactStone);
					}
				}
				//Layer3 Corner
				if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_ + 2, p_77648_6_ + 2)) {
					p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_ + 2, p_77648_6_ + 2, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_ + 2, p_77648_6_ - 2)) {
					p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_ + 2, p_77648_6_ - 2, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_ + 2, p_77648_6_ + 2)) {
					p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_ + 2, p_77648_6_ + 2, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_ + 2, p_77648_6_ - 2)) {
					p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_ + 2, p_77648_6_ - 2, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ + 2, p_77648_5_ + 2, p_77648_6_ + 1)) {
					p_77648_3_.setBlock(p_77648_4_ + 2, p_77648_5_ + 2, p_77648_6_ + 1, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ - 2, p_77648_5_ + 2, p_77648_6_ + 1)) {
					p_77648_3_.setBlock(p_77648_4_ - 2, p_77648_5_ + 2, p_77648_6_ + 1, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ + 2, p_77648_5_ + 2, p_77648_6_ - 1)) {
					p_77648_3_.setBlock(p_77648_4_ + 2, p_77648_5_ + 2, p_77648_6_ - 1, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ - 2, p_77648_5_ + 2, p_77648_6_ - 1)) {
					p_77648_3_.setBlock(p_77648_4_ - 2, p_77648_5_ + 2, p_77648_6_ - 1, GOABlock.blockCompactStone);
				}
				//Layer5
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + 4, p_77648_6_)) {
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 4, p_77648_6_, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_ + 4, p_77648_6_)) {
					p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_ + 4, p_77648_6_, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + 4, p_77648_6_ + 1)) {
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 4, p_77648_6_ + 1, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_ + 4, p_77648_6_)) {
					p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_ + 4, p_77648_6_, GOABlock.blockCompactStone);
				}
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + 4, p_77648_6_ - 1)) {
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 4, p_77648_6_ - 1, GOABlock.blockCompactStone);
				}
				p_77648_1_.damageItem(199, p_77648_2_);
			}
			return true;
		}
		else {
			return false;
		}
	}

}
