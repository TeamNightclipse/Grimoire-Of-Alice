/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.render.model.ModelFireRobe;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.entity.EntityItemFireProof;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFireRobe extends ItemBaseArmor implements ISpecialArmor {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemFireRobe(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibName.FIRE_ROBE, EntityEquipmentSlot.CHEST);
		setMaxDamage(10);
		setNoRepair();
	}

	private static void extinguishEffect(EntityLivingBase target, World world) {
		if(!world.isRemote) {
			target.extinguish();
		}
		for(int k = 0; k < 8; ++k) {
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, target.posX - 0.5 + itemRand.nextDouble(), target.posY - 1 + itemRand.nextDouble() * 2,
					target.posZ - 0.5 + itemRand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(player.isBurning() && !isActive(player)) {
			extinguishEffect(player, world);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(target instanceof EntityPlayer) {
			extinguishEffect(target, target.world);
		} else {
			target.setFire(10);
		}
		return true;
	}

	private static boolean isActive(EntityLivingBase target) {
		return target.isPotionActive(MobEffects.GLOWING);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		if(isActive(entity)) {
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(isActive(player)) {
			return new ArmorProperties(4, 0, 0);
		}
		return new ArmorProperties(4, 25, 10);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped modelBiped) {
		if(model == null) {
			model = new ModelFireRobe();
		}
		model.setModelAttributes(modelBiped);
		return model;
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItemFireProof item = new EntityItemFireProof(world, location.posX, location.posY, location.posZ, itemstack);
		item.setDefaultPickupDelay();
		return item;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLibrary.FIRE_ROBE.toString();
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
