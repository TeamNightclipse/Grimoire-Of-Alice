/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelIchirinUnzan;
import arekkuusu.grimoireofalice.common.entity.EntityUnzanFist;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemIchirinUnzan extends ItemModArmor implements ISpecialArmor, IOwnedBy {

    @SideOnly(Side.CLIENT)
    private ModelIchirinUnzan model;

    public ItemIchirinUnzan(ArmorMaterial material, int dmg) {
        super(material, dmg, LibItemName.ICHIRIN_UNZAN, EntityEquipmentSlot.CHEST);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
        list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_unzan_header.name"));
        list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.ichirin_unzan_description.name"));
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
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        stack.damageItem(damage, entity);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!world.isRemote && ((player.ticksExisted % 4) == 0) && player.getCooldownTracker().hasCooldown(this)) {
            spawnFist(world, player, 45F);
            spawnFist(world, player, -45F);

            player.motionX = 0;
            player.motionY = 0;
            player.motionZ = 0;
            if(player instanceof EntityPlayerMP) {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;
                playerMP.setPositionAndUpdate(playerMP.prevPosX, playerMP.posY, playerMP.prevPosZ);
            }
        }
    }

    private void spawnFist(World world, EntityLivingBase player, float yaw) {
        Vec3d look = player.getLookVec().rotateYaw(yaw);
        float distance = 5F;
        double x = player.posX + look.x * distance;
        double y = player.posY + 1 + look.y * distance;
        double z = player.posZ + look.z * distance;

        EntityUnzanFist fist = new EntityUnzanFist(world, player);
        fist.setPosition(x, y, z);
        world.spawnEntity(fist);
        fist.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0F, 2F, 50);
    }

    private boolean isHoldingRight(EntityLivingBase player) {
        ItemStack main = player.getHeldItemMainhand();
        return main != null && main.getItem() == ModItems.ICHIRIN_RING;
    }

    private boolean isHoldingLeft(EntityLivingBase player) {
        ItemStack off = player.getHeldItemOffhand();
        return off != null && off.getItem() == ModItems.ICHIRIN_RING;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
        if (model == null) model = new ModelIchirinUnzan(0.05F);
        model.setModelAttributes(imodel);
        model.setRenderRight(isHoldingRight(entityLiving));
        model.setRenderLeft(isHoldingLeft(entityLiving));
        return model;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return ResourceLocations.ICHIRIN_UNZAN.toString();
    }

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.ICHIRIN_KUMOI;
	}
}
