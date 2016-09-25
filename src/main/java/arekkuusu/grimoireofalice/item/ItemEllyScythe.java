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

//import arekkuusu.grimoireofalice.entity.EntityEllyScytheThrowable;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "War ma fé, heman zo eun Anko drouk");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Oberour ar maro known as the grave");
			list.add(TextFormatting.ITALIC + "yard watcher, they said that he");
			list.add(TextFormatting.ITALIC + "protects the graveyard and the souls");
			list.add(TextFormatting.ITALIC + "around it for some unknown reason and");
			list.add(TextFormatting.ITALIC + "collects the lost souls on his land");
		} else {
			list.add(TextFormatting.ITALIC + "Shift for details");
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(user instanceof EntityPlayer) {
			if(target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 64, 0));
			}
			else {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 64, 0));
			}
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 64, 0));
			user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 64, 3));
			stack.damageItem(1, user);
		}
		return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(!player.inventory.hasItemStack(stack)) return;

			int duration = getMaxItemUseDuration(stack) - timeLeft;
			float durationSeconds = duration / 20F;
			//TODO: What does this do?
			durationSeconds = (durationSeconds * durationSeconds + durationSeconds * 2.0F) / 3F;
			if(durationSeconds < 0.1F) return;

			boolean critical = false;
			if(durationSeconds > 1.5F) {
				durationSeconds = 1.5F;
				critical = true;
			}
			durationSeconds *= 1.5F;

			worldIn.playSound(player, new BlockPos(player.posX, player.posY, player.posZ), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS,
					0.6F, 1.0F / (2F * 0.4F + 1.0F));
			if(!worldIn.isRemote) {
				/*EntityEllyScytheThrowable throwable = new EntityEllyScytheThrowable(worldIn, player, stack, durationSeconds);
				throwable.setIsCritical(critical);
				throwable.setKnockbackStrength(EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack));
				if(EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0) {
					throwable.setFire(100);
				}
				worldIn.spawnEntityInWorld(throwable);*/
			}

			if(!player.capabilities.isCreativeMode) {
				ItemStack reducedStack = stack.copy();
				if(--reducedStack.stackSize == 0) {
					reducedStack = null;
				}
				player.inventory.mainInventory[player.inventory.currentItem] = reducedStack;
			}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
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
