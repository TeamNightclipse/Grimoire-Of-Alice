package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFakeMiracleMallet extends ItemMod {

	public ItemFakeMiracleMallet() {
		super(LibItemName.FAKE_MIRACLE_MALLET);
		setMaxStackSize(1);
		setNoRepair();
		addPropertyOverride(new ResourceLocation("swinging"), (stack, world, entity) -> entity != null && entity.isSwingInProgress ? 1F : 0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_PURPLE + I18n.format("grimoire.tooltip.fake_miracle_mallet_header.name"));
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			Vec3d vec = player.getLookVec();
			List<EntityLivingBase> list = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
					entityLiving.getEntityBoundingBox().offset(vec.xCoord * 2, 0, vec.zCoord * 2).expandXyz(3D), entity -> entity != player);
			list.forEach(entity -> entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), 10F));
		}
		entityLiving.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		return super.onEntitySwing(entityLiving, stack);
	}
}
