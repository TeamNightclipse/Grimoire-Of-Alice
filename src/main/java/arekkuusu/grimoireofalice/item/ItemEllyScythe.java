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

import arekkuusu.grimoireofalice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEllyScythe extends ItemModSword {

	ItemEllyScythe(ToolMaterial material) {
		super(material, LibItemName.ELLYSCYTHE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.GOLD + "War ma fé, heman zo eun Anko drouk");
		p_77624_3_.add(TextFormatting.ITALIC + "Oberour ar maro known as the grave");
		p_77624_3_.add(TextFormatting.ITALIC + "yard watcher, they said that he");
		p_77624_3_.add(TextFormatting.ITALIC + "protects the graveyard and the souls");
		p_77624_3_.add(TextFormatting.ITALIC + "around it for some unknown reason and");
		p_77624_3_.add(TextFormatting.ITALIC + "collects the lost souls on his land");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)user;
			if(target instanceof EntityZombie || target instanceof EntitySkeleton) {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 128, 0));
			}
			else {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 128, 0));
			}
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 128, 0));
			user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 128, 3));
			stack.damageItem(1, user);
		}
		return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entityLiving;
		if (!player.inventory.hasItemStack(stack)) return stack;;
		
		int hurr = getMaxItemUseDuration(stack);
		float durr = hurr / 20F;
		durr = (durr * durr + durr * 2.0F) / 3F;
		if (durr < 0.1F) return stack;;
		
		boolean isLoli = false;
		if (durr > 1.5F) {
			durr = 1.5F;
			isLoli = true;
		}
		durr *= 1.5F;
		
		worldIn.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.6F, 1.0F / (2F * 0.4F + 1.0F));
		if (!worldIn.isRemote) {
			EntityEllyScytheThrowable entityboomerang = new EntityEllyScytheThrowable(worldIn, player, stack, durr);
			entityboomerang.setIsCritical(isLoli);
			entityboomerang.setKnockbackStrength(EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack));
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0) {
				entityboomerang.setFire(100);
			}
			worldIn.spawnEntityInWorld(entityboomerang);
		}
		
		if (!player.capabilities.isCreativeMode) {
			ItemStack itemstack2 = stack.copy();
			if (--itemstack2.stackSize == 0) {
				itemstack2 = null;
			}
			player.inventory.mainInventory[player.inventory.currentItem] = itemstack2;
			}
		}
		return stack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
	}
}
