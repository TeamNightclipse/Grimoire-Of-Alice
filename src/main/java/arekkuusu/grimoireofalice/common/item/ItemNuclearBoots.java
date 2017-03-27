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
import arekkuusu.grimoireofalice.client.model.ModelNuclearBoots;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemNuclearBoots extends ItemModArmor {

	@SideOnly(Side.CLIENT)
	private ModelBiped model;

	public ItemNuclearBoots(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.NUCLEAR_BOOTS, EntityEquipmentSlot.FEET);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nuclear_boots_header.name"));
		tooltip.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nuclear_boots_description_top.name"));
		tooltip.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nuclear_boots_description_bottom.name"));
	}

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!world.isRemote && player.ticksExisted % 20 == 0 && isHoldingRod(player)) {
            BlockPos posI = new BlockPos(player.posX - 4, player.posY - 4, player.posZ - 4);
            BlockPos posF = new BlockPos(player.posX + 4, player.posY + 4, player.posZ + 4);

            BlockPos.getAllInBox(posI, posF).forEach(pos -> {
                Material material = world.getBlockState(pos).getMaterial();
                if (material == Material.WATER && itemRand.nextInt(10) == 0) {
                    ((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(),
                            pos.getY(), pos.getZ(), 8, 0D, 0D, 0D, 0D);
                }
                else if (material == Material.ICE && itemRand.nextInt(5) == 0) {
                    setBlock(world, Blocks.FLOWING_WATER, pos);
                }
                else if (material == Material.SNOW && itemRand.nextBoolean()) {
                    world.setBlockToAir(pos);
                    setBlock(world, Blocks.AIR, pos);
                }
            });
        }
    }

    private boolean isHoldingRod(EntityPlayer player) {
        boolean hasRod = GrimoireOfAlice.danmakuCoreInstalled;
        if (hasRod) {
            ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
            if (stack == null) {
                stack = player.getHeldItem(EnumHand.OFF_HAND);
            }
            hasRod = stack != null;
        }
        return hasRod;
    }

    private void setBlock(World world, Block block, BlockPos pos) {
        world.setBlockState(pos, block.getDefaultState(), 3);
        ((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX(),
                pos.getY(), pos.getZ(), 8, 0D, 0D, 0D, 0D);
    }

    @Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot Ui, ModelBiped imodel) {
		if (model == null) model = new ModelNuclearBoots();
		model.setModelAttributes(imodel);
		return model;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ResourceLocations.NUCLEAR_BOOTS.toString();
	}
}
