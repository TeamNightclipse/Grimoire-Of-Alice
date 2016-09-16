package arekkuusu.grimoireofalice.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemOnbashira extends ItemModSword {

	public ItemOnbashira(ToolMaterial material) {
		super(material, LibItemName.KANAKOONBASHIRA);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Leg of an Ent sealed with magic.");
		list.add(TextFormatting.DARK_AQUA + "Its massive density make it almost unwieldable");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(isSelected) {
				player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 0, 3));
			}
		}
	}
	
}
