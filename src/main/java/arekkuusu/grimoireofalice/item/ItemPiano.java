package arekkuusu.grimoireofalice.item;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.entity.EntityNote;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemPiano extends ItemMod {

	public ItemPiano() {
		super(LibItemName.LYRICAPIANO);
		setMaxDamage(500);
		addPropertyOverride(new ResourceLocation("playing"), (stack, world, entity) ->
				entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1F : 0F);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Poltergeists posses this piano");
		list.add(TextFormatting.ITALIC + "Hold right click to use");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if(player instanceof EntityPlayer){
			EntityPlayer playerIn = (EntityPlayer)player;
			if (!player.worldObj.isRemote) {
				EntityNote entityNote = new EntityNote(player.worldObj, playerIn);
				entityNote.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 5.0F);
				player.worldObj.spawnEntityInWorld(entityNote);
			}

			StatBase statBase = StatList.getObjectUseStats(this);
			if(statBase != null) {
				playerIn.addStat(statBase);
			};
		}
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		EntityPlayer playerIn = (EntityPlayer)entityLiving;
		if (!playerIn.capabilities.isCreativeMode) {
			int hurr = (getMaxItemUseDuration(stack) - timeLeft) / 2;
			stack.damageItem(hurr, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, 150);
        }
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 500;
    }
}
