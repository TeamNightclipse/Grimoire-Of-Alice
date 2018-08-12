/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.render.model.ModelKappaHat;
import arekkuusu.grimoireofalice.client.util.ResourceLibrary;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKappaHat extends ItemBaseArmor {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemKappaHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibName.KAPPA_HAT, EntityEquipmentSlot.HEAD);
		setNoRepair();
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(world.isRaining() && stack.isItemDamaged()) {
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if(player.world.isRemote && !player.capabilities.isFlying && player.moveForward > 0F) {
			if(player.isInWater() || player.isInsideOfMaterial(Material.WATER)) {
				player.moveRelative(0F, 0F, 1F, 0.085F);
			} else if(!player.isWet()) {
				player.motionX *= 0.4;
				player.motionZ *= 0.4;
			}
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLibrary.KAPPA_HAT.toString();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped imodel) {
		if(model == null) {
			model = new ModelKappaHat();
		}
		model.setModelAttributes(imodel);
		return model;
	}
}
