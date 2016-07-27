package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import arekkuusu.grimoireOfAlice.entity.EntityNeedle;
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
import net.minecraft.world.World;

public class ItemNeedle extends ItemGOASword{

	ItemNeedle(ToolMaterial material) {
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
		list.add(EnumChatFormatting.WHITE + "Only pain will remain...");
		list.add(EnumChatFormatting.GOLD + "Deadly weapon used by Miko");
		list.add(EnumChatFormatting.GOLD + "to painfully kill youkai");
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
		if (hurr < 3.0F) return;
		float durr = (hurr*6) / 20F;
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
			EntityNeedle EntityNeedle = new EntityNeedle(world, entityplayer, itemstack, durr);
			EntityNeedle.setIsCritical(isLoli);
			EntityNeedle.setKnockbackStrength(EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, itemstack));
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, itemstack) > 0) {
				EntityNeedle.setFire(100);
			}
			world.spawnEntityInWorld(EntityNeedle);
		}
		
		if (!entityplayer.capabilities.isCreativeMode) {
			/*ItemStack itemstack2 = itemstack.copy();
			if (--itemstack2.stackSize == 0) {
				itemstack2 = null;
			}
			entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = itemstack2;*/
			itemstack.damageItem(1, entityplayer);
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
