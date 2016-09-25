/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.lib.LibItemName;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Set;

public class ItemBudahBoul extends ItemTool {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,
			Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE,
			Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH);

	ItemBudahBoul(ToolMaterial materialIn) {
		super(4.0F, -2.8F, materialIn, EFFECTIVE_ON);
		setRegistryName(LibItemName.BUDAHBOUL);
		setUnlocalizedName(LibItemName.BUDAHBOUL);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
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
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Stone begging bowl of the Buddha Gautama Shakyamuni");
		list.add(TextFormatting.DARK_AQUA + "\"An entire life to reach enlightenment");
		list.add(TextFormatting.DARK_AQUA + "and a thousand more to create this boul\"");
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		Block block = blockIn.getBlock();

		if (block == Blocks.OBSIDIAN) {
			return this.toolMaterial.getHarvestLevel() == 3;
		}
		else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE) {
			if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK) {
				if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE) {
					if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE) {
						if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE) {
							if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE) {
								Material material = blockIn.getMaterial();
								return material == Material.ROCK || (material == Material.IRON || material == Material.ANVIL);
							} else {
								return this.toolMaterial.getHarvestLevel() >= 2;
							}
						} else {
							return this.toolMaterial.getHarvestLevel() >= 1;
						}
					} else {
						return this.toolMaterial.getHarvestLevel() >= 1;
					}
				} else {
					return this.toolMaterial.getHarvestLevel() >= 2;
				}
			} else {
				return this.toolMaterial.getHarvestLevel() >= 2;
			}
		} else {
			return this.toolMaterial.getHarvestLevel() >= 2;
		}
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE && material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
