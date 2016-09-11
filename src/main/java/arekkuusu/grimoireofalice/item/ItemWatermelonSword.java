package arekkuusu.grimoireofalice.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemWatermelonSword extends ItemModSword {

	public ItemWatermelonSword(ToolMaterial material) {
		super(material, LibItemName.WATERMELONSWORD);
		setMaxDamage(1);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Eat cold with salt, lots");
		list.add(TextFormatting.ITALIC + "as salty as life...");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        --stack.stackSize;
        if(attacker instanceof EntityPlayer){
        	((EntityPlayer)attacker).inventory.addItemStackToInventory(stack.stackSize <= 0 ? new ItemStack(ModItems.popsicleStick) : stack);
        }
        if(target instanceof EntityPlayer){
        	Random rand = new Random();
        	((EntityPlayer)target).inventory.addItemStackToInventory(new ItemStack(Items.MELON, rand.nextInt(3)));
        }
        return true;
    }

}
