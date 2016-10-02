/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.auras;

import arekkuusu.grimoireofalice.client.model.ModelAuraIchirin;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAuraIchirin extends ItemModAura {

    @SideOnly(Side.CLIENT)
    private ModelBiped model;

    public ItemAuraIchirin(ArmorMaterial material, int dmg) {
        super(material, dmg, LibItemName.AURAICHIRIN, EntityEquipmentSlot.CHEST);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
        list.add(TextFormatting.DARK_RED + "Cloud Spirit Nyuudou");
		list.add(TextFormatting.ITALIC + "Grant the user a powerful Nyuudou");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
            if (!player.isSneaking()) {
                player.capabilities.allowFlying = true;
                player.capabilities.isFlying = true;
            }
            else {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
            }
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return damageReduceAmount;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
        if (model == null) model = new ModelAuraIchirin(0.05F);
        model.setModelAttributes(imodel);
        return model;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return LibMod.MODID + ":textures/models/armor/ichirin_aura.png";
    }
}
