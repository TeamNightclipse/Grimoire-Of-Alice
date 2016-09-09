package arekkuusu.grimoireofalice.item;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSyringe extends ItemModSword {

	ItemSyringe(ToolMaterial material) {
		super(material, LibItemName.SYRINGE);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Eirin's Syringe");
		list.add(TextFormatting.ITALIC + "\"Dont worry, it just hurts a lot\"");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote) { // Are potions supposed to be Remote or not?
			playerIn.addPotionEffect(new PotionEffect(getRandomPotion(), 1200, 0));
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		stack.damageItem(1, user);
		target.addPotionEffect(new PotionEffect(getRandomPotion(), 1200, 0));
		return true;
	}
	
	public Potion getRandomPotion(){
		Random rand = new Random();
		Potion potion = Potion.getPotionById(rand.nextInt(27));
		return potion;
	}
	
}
