package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNueTrident extends ItemGOASword {

	public ItemNueTrident(ToolMaterial material) {
		super(material);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GOLD + "Trident of sealed beast, Seed of Unknown Form");
		list.add(EnumChatFormatting.OBFUSCATED + "Fear the unknown, weak human!");
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		stack.damageItem(1, user);
		target.addPotionEffect(new PotionEffect(Potion.blindness.id, 2400, 0));
		target.addPotionEffect(new PotionEffect(Potion.confusion.id, 2400, 0));
		return true;
	}
	
}
