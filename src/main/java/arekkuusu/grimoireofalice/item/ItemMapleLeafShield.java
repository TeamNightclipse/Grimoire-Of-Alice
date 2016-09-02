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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//TODO: Redo to make it work more like a shield
public class ItemMapleLeafShield extends ItemModShield {

	public ItemMapleLeafShield() {
		super(LibItemName.MAPLELEAFSHIELD);
		setMaxDamage(250);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Momiji goes awoooo~");
		list.add(TextFormatting.GRAY + "Round shield with a red");
		list.add(TextFormatting.GRAY + "maple leaf print on it");
		list.add(TextFormatting.DARK_PURPLE + "Cures potion effects");
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(stack.getItemDamage() == 0) {
			if (!worldIn.isRemote) {
				Potion potion = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 1
				if(potion != null && !entityLiving.isPotionActive(potion)){
					entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
					entityLiving.addPotionEffect(new PotionEffect(potion, 240, 0));
				}
			stack.damageItem(1, entityLiving);
			}
		}
		return stack;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
        stack.damageItem(10, user);
        return true;
    }
}
