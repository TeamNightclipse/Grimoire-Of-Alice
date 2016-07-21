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

import arekkuusu.grimoireOfAlice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

@CleanupDone
public class ItemEllyScythe extends ItemGOASword {

	ItemEllyScythe(ToolMaterial material) {
		super(material);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.WHITE + "War ma fé, heman zo eun Anko drouk");
		p_77624_3_.add(EnumChatFormatting.GOLD + "Oberour ar maro known as the grave");
		p_77624_3_.add(EnumChatFormatting.GOLD + "yard watcher, they said that he");
		p_77624_3_.add(EnumChatFormatting.GOLD + "protects the graveyard and the souls");
		p_77624_3_.add(EnumChatFormatting.GOLD + "around it for some unknown reason and");
		p_77624_3_.add(EnumChatFormatting.GOLD + "collects the lost souls on his land");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)user;
			if(player.experienceLevel > 40) {
				target.addPotionEffect(new PotionEffect(Potion.harm.id, 128, 0));
				target.addPotionEffect(new PotionEffect(Potion.blindness.id, 128, 0));
				user.addPotionEffect(new PotionEffect(Potion.heal.id, 128, 0));
				stack.damageItem(1, user);
			}
			else {
				target.addPotionEffect(new PotionEffect(Potion.heal.id, 128, 0));
				user.addPotionEffect(new PotionEffect(Potion.harm.id, 128, 0));
				user.addPotionEffect(new PotionEffect(Potion.blindness.id, 128, 0));
				stack.damageItem(1, user);
			}
		}

		return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
		return itemstack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i){
		if (!entityplayer.inventory.hasItemStack(itemstack)) return;
		
		int hurr = getMaxItemUseDuration(itemstack) - i;
		float durr = hurr / 20F;
		durr = (durr * durr + durr * 2.0F) / 3F;
		if (durr < 0.1F) return;
		
		boolean isLoli = false;
		if (durr > 1.5F) {
			durr = 1.5F;
			isLoli = true;
		}
		durr *= 1.5F;
		
		world.playSoundAtEntity(entityplayer, "random.bow", 0.6F, 1.0F / (2F * 0.4F + 1.0F));
		if (!world.isRemote) {
			EntityEllyScytheThrowable entityboomerang = new EntityEllyScytheThrowable(world, entityplayer, itemstack, durr);
			entityboomerang.setIsCritical(isLoli);
			entityboomerang.setKnockbackStrength(EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, itemstack));
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, itemstack) > 0) {
				entityboomerang.setFire(100);
			}
			world.spawnEntityInWorld(entityboomerang);
		}
		
		if (!entityplayer.capabilities.isCreativeMode) {
			ItemStack itemstack2 = itemstack.copy();
			if (--itemstack2.stackSize == 0) {
				itemstack2 = null;
			}
			entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = itemstack2;
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.block;
	}
}
