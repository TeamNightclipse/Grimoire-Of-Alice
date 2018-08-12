/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.client.util.helper.IModel;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import arekkuusu.grimoireofalice.common.lib.LibName;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.Set;

public class ItemBudahBoul extends ItemTool implements IModel {

	private static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.ROCK, Material.IRON, Material.IRON, Material.GLASS,
			Material.PISTON, Material.ANVIL, Material.CIRCUITS, Material.WOOD, Material.GOURD, Material.PLANTS, Material.VINE, Material.GRASS,
			Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY);

	private static final Set<Material> SWORD_MATERIALS = ImmutableSet.of(Material.PLANTS, Material.VINE, Material.CORAL, Material.LEAVES,
			Material.GOURD);

	public ItemBudahBoul() {
		super(4.0F, -2.8F, ModMaterials.BUDAH_BOUL, Collections.emptySet());
		ModItems.setRegistry(this, LibName.BUDAH_BOUL);
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
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		world.notifyBlockUpdate(pos, state, state, 8);
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return EFFECTIVE_MATERIALS.contains(blockIn.getMaterial()) || blockIn.getBlock() == Blocks.WEB;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if(state.getBlock() == Blocks.WEB) {
			return 20.0f;
		}

		for(String type : getToolClasses(stack)) {
			if(state.getBlock().isToolEffective(type, state)) {
				return efficiency;
			}
		}

		if(EFFECTIVE_MATERIALS.contains(state.getMaterial())) {
			return efficiency;
		} else if(SWORD_MATERIALS.contains(state.getMaterial())) {
			return 1.5f;
		} else {
			return 1.0F;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
