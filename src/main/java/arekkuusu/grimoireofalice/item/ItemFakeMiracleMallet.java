package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFakeMiracleMallet extends ItemMod {

	ItemFakeMiracleMallet() {
		super(LibItemName.FAKEMIRACLEMALLET);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("swinging"), (stack, world, entity) ->
				entity != null && entity.isSwingInProgress ? 1F : 0F);
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
		list.add(TextFormatting.DARK_PURPLE + "Swipe the space in front of you, dealing damage");
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof  EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			Vec3d vec = player.getLookVec();
			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, entityLiving.getEntityBoundingBox().offset(vec.xCoord * 2, 0, vec.zCoord * 2).expandXyz(3));
			if (!list.isEmpty()) {
				list.stream().filter(entity -> entity instanceof EntityLivingBase).forEach(entity -> {
					entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), 10F);
				});
			}
		}
		return super.onEntitySwing(entityLiving, stack);
	}
}
