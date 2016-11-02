/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.katsstuff.danmakucore.helper.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOnbashira extends BlockMod implements ITileEntityProvider {

	public static final PropertyEnum<Part> PART = PropertyEnum.create("part", Part.class);
	private static final AxisAlignedBB BB_TOP = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockOnbashira() {
		super(LibBlockName.ONBASHIRA, Material.WOOD);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("axe", 1);
		setResistance(2000.0F);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PART);
	}

	@Override
	protected IBlockState defaultState() {
		return super.defaultState().withProperty(PART, Part.LOWER);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PART, Part.fromIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PART).getIndex();
	}

	@SuppressWarnings("deprecation")
	@Override
	public float getBlockHardness(IBlockState state, World worldIn, BlockPos pos) {
		switch(state.getValue(PART)) {
			case MIDDLE:
				return 2000F;
			default:
				return super.getBlockHardness(state, worldIn, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch(state.getValue(PART)) {
			case TOP:
				return BB_TOP;
			default:
				return super.getBoundingBox(state, source, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		switch(state.getValue(PART)) {
			case MIDDLE:
				return EnumBlockRenderType.INVISIBLE;
			default:
				return super.getRenderType(state);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Material getMaterial(IBlockState state) {
		switch(state.getValue(PART)) {
			case MIDDLE:
				return Material.AIR;
			default:
				return super.getMaterial(state);
		}
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 125, 5));
		}
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getValue(PART) == Part.TOP;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		switch(Part.fromIndex(meta)) {
			case TOP:
				return new TilePillarAltar();
			default:
				return null;
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		switch(state.getValue(PART)) {
			case TOP:
				TilePillarAltar tile = (TilePillarAltar)worldIn.getTileEntity(pos);
				boolean ok = false;
				if(tile != null) {
					if(playerIn.isSneaking()) {
						ok = tile.removeItem(playerIn);
					}
					else if(heldItem != null) {
						ok = tile.addItem(playerIn, heldItem);
					}
				}
				return ok;
			default:
				return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.onbashira_block_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.onbashira_heavy.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.onbashira_altar_tier.name"));
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		world.setBlockState(pos.up(1), getDefaultState().withProperty(PART, Part.MIDDLE));
		world.setBlockState(pos.up(2), getDefaultState().withProperty(PART, Part.MIDDLE));
		world.setBlockState(pos.up(3), getDefaultState().withProperty(PART, Part.TOP));
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		switch(state.getValue(PART)) {
			case LOWER:
				for(int i = 0; i < 4; i++) {
					worldIn.setBlockToAir(pos.up(i));
					worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5 + i, pos.getZ() + 0.5, 2.0F, false);
				}
				break;
			case TOP:
				for(int i = 0; i < 4; i++) {
					worldIn.setBlockToAir(pos.down(i));
					worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5 - i, pos.getZ() + 0.5, 2.0F, false);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public enum Part implements IStringSerializable {
		LOWER("lower", 0),
		MIDDLE("middle", 1),
		TOP("top", 2);

		private final String name;
		private final int index;

		Part(String name, int index) {
			this.name = name;
			this.index = index;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return name;
		}

		public int getIndex() {
			return index;
		}

		public static Part fromIndex(int index) {
			switch(index) {
				case 2:
					return TOP;
				case 1:
					return MIDDLE;
				default:
					return LOWER;
			}
		}
	}
}
