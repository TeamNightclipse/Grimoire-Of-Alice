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

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Item3rdEye extends ItemMod {
	
	public Item3rdEye() {
		super(LibItemName.EYE);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Satori's 3rdEye");
		list.add(TextFormatting.ITALIC + "Shift right click to activate");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if(player.isSneaking()) {
			player.getCooldownTracker().setCooldown(this, 300);
		}
		return new ActionResult<>(EnumActionResult.PASS, stack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean isSelected) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getCooldownTracker().hasCooldown(this) && stack.getItem() == this) {
				player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 10, 0));
				player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 10, 0));
			} else if(isSelected){
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expandXyz(20.0D));
				if(!list.isEmpty()) {
					list.stream().filter(mob -> mob instanceof EntityMob).map(mob -> (EntityMob)mob).forEach(mob -> {
						mob.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 30, 0));
					});
				}
			}
		}
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
