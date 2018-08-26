/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import arekkuusu.grimoireofalice.client.util.helper.IModel;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockShroom extends BlockModBush implements IModel {

	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	public BlockShroom() {
		super(LibName.SHROOM, Material.PLANTS);
		setDefaultState(blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		setTickRandomly(true);
		setLightLevel(0.5F);
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Cave;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		canBlockStay(world, pos, state);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		return canPlaceBlockAt(world, pos);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState soil = world.getBlockState(pos.down());
		Block block = soil.getBlock();
		return super.canPlaceBlockAt(world, pos)
				|| block == Blocks.HARDENED_CLAY
				|| block == Blocks.STAINED_HARDENED_CLAY
				|| block == Blocks.STONE
				|| block == ModBlocks.COMPACT_STONE;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if(rand.nextDouble() < 10 * 0.25F) {
			world.spawnParticle(EnumParticleTypes.SPELL, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0D, 1.0D, 0.0D);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> stacks) {
		for(int i = 0; i < 16; i++) {
			stacks.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, COLOR);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLOR).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta >= EnumDyeColor.values().length) {
			meta = 0;
		}
		return getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, EnumDyeColor.class);
	}
}
