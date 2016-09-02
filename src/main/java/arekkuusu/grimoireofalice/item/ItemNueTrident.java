package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNueTrident extends ItemModSword {

	public ItemNueTrident(ToolMaterial material) {
		super(material);
		setUnlocalizedName(LibItemName.NUETRIDENT);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Trident of sealed beast, Seed of Unknown Form");
		list.add(TextFormatting.OBFUSCATED + "Fear the unknown, weak human!");
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		stack.damageItem(1, user);
		target.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 2400, 0));
		target.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 2400, 0));
		return true;
	}
	
}
