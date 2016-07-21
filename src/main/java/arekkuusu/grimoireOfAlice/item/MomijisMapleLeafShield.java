package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MomijisMapleLeafShield extends ItemGOASword{

	public MomijisMapleLeafShield(ToolMaterial material) {
		super(material);
		setMaxDamage(1000);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "Momiji goes awoooo~");
		list.add(EnumChatFormatting.GOLD + "Round shield with a red");
		list.add(EnumChatFormatting.GOLD + "maple leaf print on it");
		list.add(EnumChatFormatting.RED + "awoowawowowoooaowowo");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(stack.getItemDamage() < stack.getMaxDamage()) {
			stack.setItemDamage(stack.getItemDamage() - 1);
		}

		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getCurrentEquippedItem() == stack) {
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 25, 0));
			}
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
        p_77644_1_.damageItem(10, p_77644_3_);
        return true;
    }
}
