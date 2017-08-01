/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
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
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemFakeMiracleMallet extends ItemMod implements IOwnedBy {

	public ItemFakeMiracleMallet() {
		super(LibItemName.FAKE_MIRACLE_MALLET);
		setMaxStackSize(1);
		setMaxDamage(100);
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
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.fake_miracle_mallet_header.name"));
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).getCooldownTracker().hasCooldown(this)) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			Vec3d vec = player.getLookVec();
			if(!player.world.isRemote) {
				List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
						entityLiving.getEntityBoundingBox().offset(vec.x * 2, vec.y * 2, vec.z * 2).grow(3D), entity -> !player.equals(entity));
				if(!list.isEmpty()) {
					list.forEach(entity -> entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), 10F));
					stack.damageItem(1, player);
				}
			}
			player.getCooldownTracker().setCooldown(this, 10);
		}
		entityLiving.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		return super.onEntitySwing(entityLiving, stack);
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.SEIJA_KIJIN;
	}
}
