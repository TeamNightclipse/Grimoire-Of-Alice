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

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPrimordialShield extends ItemModShield {

	ItemPrimordialShield() {
		super(LibItemName.PRIMORDIALSHIELD);
		setMaxDamage(1000);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Supermassive unidentified object");
		list.add(TextFormatting.GRAY + "After melting the bases of a");
		list.add(TextFormatting.GRAY + "forgoten universe, an unknown");
		list.add(TextFormatting.GRAY + "entity decided to store the");
		list.add(TextFormatting.GRAY + "remains in the form of a shield");
		list.add(TextFormatting.ITALIC + "Said to protect the injured in");
		list.add(TextFormatting.ITALIC + "times of desperation");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(stack.isItemDamaged()){
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getHeldItemMainhand() == stack && !isWorthy(player)) {
				if(!world.isRemote && p_77663_5_) {
					player.fallDistance = 2.0F;
				}

				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 25, 5));
				player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 25, 5));
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(isWorthy(playerIn)) {
			if(itemStackIn.getItemDamage() == 0) {
				playerIn.capabilities.disableDamage = true;
				//playerIn.setItemInUse(itemStackIn, getMaxItemUseDuration(itemStackIn));
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(stack.getItemDamage() == 0 && !player.capabilities.isCreativeMode) {
				player.capabilities.disableDamage = false;
				stack.damageItem(999, player);
			}
		}
		return stack;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		return true;
	}

	private boolean isWorthy(EntityPlayer player) {
		return player.getHealth() <= 4 || player.capabilities.isCreativeMode;
	}
}
