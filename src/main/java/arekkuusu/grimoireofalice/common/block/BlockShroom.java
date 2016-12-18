/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockShroom extends BlockModBush {

	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	public BlockShroom() {
		super(LibBlockName.SHROOM, Material.PLANTS);
		setTickRandomly(true);
		setLightLevel(0.5F);
		setHardness(0.0F);
		setSoundType(SoundType.PLANT);
		setDefaultState(blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
	}

	@Override
	protected IBlockState defaultState() {
		return super.defaultState().withProperty(COLOR, EnumDyeColor.WHITE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shroom_block_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.shroom_block_tasty.name"));
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Cave;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		canBlockStay(worldIn, pos, state);
	}

	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		return canPlaceBlockAt(world, pos);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		Block block = soil.getBlock();
		return super.canPlaceBlockAt(worldIn, pos)
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
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> stacks) {
		for(int i = 0; i < 16; i++) {
			stacks.add(new ItemStack(item, 1, i));
		}
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

	@SuppressWarnings("deprecation") //Internal
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta >= EnumDyeColor.values().length) {
			meta = 0;
		}
		return getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}
}
