package arekkuusu.grimoireofalice.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBlockShroom extends ItemBlock {

	public ItemBlockShroom(Block block){
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode) {
			--stack.stackSize;
		}
		entityLiving.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2400, 0));
		entityLiving.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 2400, 0));
		entityLiving.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 2400, 1));

		if(stack.getItemDamage() % 3 == 0) {
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2400, 1));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2400, 0));
		}
		return stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.EAT;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 32;
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + stack.getMetadata();
	}
}
