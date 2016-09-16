package arekkuusu.grimoireofalice.item.auras;

import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.client.model.ModelKanakoAura;

import java.util.List;

public class ItemAuraKanako extends ItemModAura {

    @SideOnly(Side.CLIENT)
    private ModelBiped model;

    public ItemAuraKanako(ArmorMaterial material, int dmg) {
        super(material, dmg, LibItemName.AURAKANAKO);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
        list.add(TextFormatting.GOLD + "Its true power is unknown, the legend says");
        list.add(TextFormatting.GOLD + "it possesses a power unimaginable to humans");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        if(world.isRaining()){
            player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 15, 0));
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
        if (model == null) model = new ModelKanakoAura();
        model.setModelAttributes(imodel);
        return model;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return LibMod.MODID + ":textures/models/armor/kanako_aura.png";
    }
}
