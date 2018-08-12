package arekkuusu.grimoireofalice.common.item.food;

import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemIbukiGourd extends ItemModFood  {

	public ItemIbukiGourd() {
		super(0, 2F, false, LibName.IBUKI_GOURD);
		setMaxStackSize(1);
		setAlwaysEdible();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 4800, 0));
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 4800, 1));
		player.getCooldownTracker().setCooldown(this, 50);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer) {
			entityLiving.playSound(SoundEvents.ENTITY_PLAYER_BURP, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			onFoodEaten(stack, world, (EntityPlayer) entityLiving);
		}
		return stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
}
