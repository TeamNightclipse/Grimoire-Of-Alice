/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemBudahBoul extends ItemTool implements IOwnedBy {

	private static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.ROCK, Material.IRON, Material.IRON, Material.GLASS,
			Material.PISTON, Material.ANVIL, Material.CIRCUITS, Material.WOOD, Material.GOURD, Material.PLANTS, Material.VINE, Material.GRASS,
			Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY);

	private static final Set<Material> SWORD_MATERIALS = ImmutableSet.of(Material.PLANTS, Material.VINE, Material.CORAL, Material.LEAVES,
			Material.GOURD);

	public ItemBudahBoul(ToolMaterial materialIn) {
		super(4.0F, -2.8F, materialIn, Collections.emptySet());
		setRegistryName(LibItemName.BUDAH_BOUL);
		setUnlocalizedName(LibItemName.BUDAH_BOUL);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);

		setHarvestLevel("pickaxe", toolMaterial.getHarvestLevel());
		setHarvestLevel("axe", toolMaterial.getHarvestLevel());
		setHarvestLevel("shovel", toolMaterial.getHarvestLevel());
		setHarvestLevel("sword", toolMaterial.getHarvestLevel());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.budah_boul_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.budah_boul_description_top.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.budah_boul_description_bottom.name"));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		world.notifyBlockUpdate(pos, state, state, 8);
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return EFFECTIVE_MATERIALS.contains(blockIn.getMaterial()) || blockIn.getBlock() == Blocks.WEB;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		if(state.getBlock() == Blocks.WEB) {
			return 20.0f;
		}

		for(String type : getToolClasses(stack)) {
			if(state.getBlock().isToolEffective(type, state)) {
				return efficiencyOnProperMaterial;
			}
		}

		if(EFFECTIVE_MATERIALS.contains(state.getMaterial())) {
			return efficiencyOnProperMaterial;
		}
		else if(SWORD_MATERIALS.contains(state.getMaterial())) {
			return 1.5f;
		}
		else {
			return 1.0F;
		}
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.KAGUYA_HOURAISAN;
	}
}
