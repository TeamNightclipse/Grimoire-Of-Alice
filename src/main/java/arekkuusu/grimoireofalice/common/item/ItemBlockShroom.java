/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.util.helper.IModel;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockShroom extends ItemBlock implements IModel {

	@SuppressWarnings("ConstantConditions")
	public ItemBlockShroom(Block block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).capabilities.isCreativeMode) {
			stack.shrink(1);
		}

		if(stack.getItemDamage() % 3 != 0) {
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2400, 0));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 2400, 0));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 2400, 1));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2400, 1));
			entityLiving.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2400, 0));
		}
		return stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.EAT;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return super.getTranslationKey(stack) + "_" + stack.getMetadata();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, EnumDyeColor.class);
	}
}
