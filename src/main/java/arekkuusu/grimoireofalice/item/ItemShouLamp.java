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
import java.util.Random;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemShouLamp extends ItemMod {

	ItemShouLamp() {
		super(LibItemName.SHOULAMP);
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Brings about wealth");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Hold right click to use");
			list.add(TextFormatting.YELLOW + "Long use periods give Luck to everyone around you");
			list.add(TextFormatting.YELLOW + "Short use periods give Luck to only you");
			list.add(TextFormatting.DARK_AQUA + "Hitting entities gives them Unluck");
		} else {
			list.add(TextFormatting.ITALIC + "Shift for details");
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 30, 0));
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			int hurr = getMaxItemUseDuration(stack) - timeLeft;
			float durr = (hurr * 6) / 20F;
			durr = (durr * durr + durr * 2.0F) / 3F;
			durr *= 1.5F;
			if (durr < 10F) {
				player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 125, 5));
			} else {
				//Will add a spell particle
				player.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX, player.posY, player.posZ, 0, 0, 0);
				List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(4.0D));
				for (EntityMob mob : list){
					mob.addPotionEffect(new PotionEffect(MobEffects.LUCK, 125, 5));
				}
			}
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 500, 5));
        return false;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
}
