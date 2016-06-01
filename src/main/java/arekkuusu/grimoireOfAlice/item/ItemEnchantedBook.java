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

import arekkuusu.grimoireOfAlice.client.entity.EntityFireBall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemEnchantedBook extends ItemGOABase {

	public ItemEnchantedBook() {
		super();
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
		setMaxDamage(50);
	}

	public static boolean validBookTagContents(NBTTagCompound p_77828_0_) {
		if(!ItemWritableBook.func_150930_a(p_77828_0_)) {
			return false;
		}
		else if(!p_77828_0_.hasKey("title", 8)) {
			return false;
		}
		else {
			String s = p_77828_0_.getString("title");
			return s != null && s.length() <= 16 ? p_77828_0_.hasKey("author", 8) : false;
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_) {
		if(p_77653_1_.hasTagCompound()) {
			NBTTagCompound nbttagcompound = p_77653_1_.getTagCompound();
			String s = nbttagcompound.getString("title");

			if(!StringUtils.isNullOrEmpty(s)) { return s; }
		}

		return super.getItemStackDisplayName(p_77653_1_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.LIGHT_PURPLE + "Are you worthy of this magic?");
		if(p_77624_1_.hasTagCompound()) {
			NBTTagCompound nbttagcompound = p_77624_1_.getTagCompound();
			String s = nbttagcompound.getString("author");

			if(!StringUtils.isNullOrEmpty(s)) {
				p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocalFormatted("book.byAuthor", new Object[] {s}));
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77624_13_, World p_77624_3_, EntityPlayer p_77624_2_) {
		if(!p_77624_3_.isRemote) {
			if(p_77624_2_.capabilities.isCreativeMode || p_77624_2_.inventory.hasItem(Items.fire_charge)) {
				p_77624_3_.playSoundEffect(p_77624_2_.posX + 0.5D, p_77624_2_.posY + 0.5D, p_77624_2_.posZ + 0.5D, "mob.ghast.scream", 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				Vec3 look = p_77624_2_.getLookVec();
				EntityFireBall fireball2 = new EntityFireBall(p_77624_3_, p_77624_2_, 1, 1, 1);
				fireball2.setPosition(p_77624_2_.posX + look.xCoord * 5, p_77624_2_.posY + look.yCoord * 5, p_77624_2_.posZ + look.zCoord * 5);
				fireball2.accelerationX = look.xCoord * 0.1;
				fireball2.accelerationY = look.yCoord * 0.1;
				fireball2.accelerationZ = look.zCoord * 0.1;
				p_77624_3_.spawnEntityInWorld(fireball2);
				p_77624_2_.inventory.consumeInventoryItem(Items.fire_charge);
				p_77624_13_.damageItem(1, p_77624_2_);
			}
			else if(p_77624_2_.experienceLevel > 30 && p_77624_2_.experienceLevel < 60) {
				if(p_77624_2_.capabilities.isCreativeMode || p_77624_2_.inventory.hasItem(Items.coal)) {
					p_77624_2_.inventory.consumeInventoryItem(Items.coal);
					p_77624_2_.addPotionEffect(new PotionEffect(Potion.blindness.id, 25, 0));
					p_77624_2_.addPotionEffect(new PotionEffect(Potion.invisibility.id, 25, 0));
					p_77624_2_.setItemInUse(p_77624_13_, getMaxItemUseDuration(p_77624_13_));
				}
				else if(p_77624_2_.experienceLevel > 60 && p_77624_2_.experienceLevel < 80) {
					if(p_77624_2_.experienceLevel < 70) {
						p_77624_2_.setFire(120);
					}
					else {
						p_77624_2_.addExperienceLevel(-1);
						p_77624_2_.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 4));
					}
					p_77624_2_.setItemInUse(p_77624_13_, getMaxItemUseDuration(p_77624_13_));
					return p_77624_13_;
				}
			}
		}
		return p_77624_13_;
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_,
			int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if(p_77648_2_.experienceLevel <= 30) {
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
				if(p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_)) {
					p_77648_3_.playSoundEffect(p_77648_4_ + 0.5D, p_77648_5_ + 0.5D, p_77648_6_ + 0.5D, "mob.blaze.death", 1.0F,
							itemRand.nextFloat() * 0.4F + 0.8F);
					p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.fire);
					if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_, p_77648_6_ + 1)) {
						p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_, p_77648_6_ + 1, Blocks.fire);
						if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_, p_77648_6_ - 1)) {
							p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_, p_77648_6_ - 1, Blocks.fire);
							if(p_77648_3_.isAirBlock(p_77648_4_ - 1, p_77648_5_, p_77648_6_ + 1)) {
								p_77648_3_.setBlock(p_77648_4_ - 1, p_77648_5_, p_77648_6_ + 1, Blocks.fire);
								if(p_77648_3_.isAirBlock(p_77648_4_ + 1, p_77648_5_, p_77648_6_ - 1)) {
									p_77648_3_.setBlock(p_77648_4_ + 1, p_77648_5_, p_77648_6_ - 1, Blocks.fire);
								}
							}
						}
					}
				}
				return true;
			}
		}
		else if(p_77648_2_.experienceLevel > 30 && p_77648_2_.experienceLevel < 40) {
			p_77648_3_.spawnEntityInWorld(new EntityLightningBolt(p_77648_3_, p_77648_4_ + 0.5, p_77648_5_, p_77648_6_ + 0.5));
			p_77648_1_.damageItem(1, p_77648_2_);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean getShareTag() {
		return true;
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
}
