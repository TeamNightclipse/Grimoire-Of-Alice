package arekkuusu.grimoireofalice.block;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOnbashiraTop extends BlockMod{

	protected static final AxisAlignedBB BOTTOM = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	
	@SuppressWarnings("ConstantConditions")
	BlockOnbashiraTop() {
		super(LibBlockName.ONBASHIRATOP, Material.WOOD);
		setCreativeTab(null);
		setResistance(2000.0F);
		setSoundType(SoundType.WOOD);
		setHardness(2.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Honored pillars");
		list.add(TextFormatting.ITALIC + "Kinda heavy");
	}
	
	@SuppressWarnings("deprecation") //Internal
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return BOTTOM;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		world.setBlockState(pos.up(1), ModBlocks.onbashiraMiddle.getDefaultState());
		world.setBlockState(pos.up(2), ModBlocks.onbashiraMiddle.getDefaultState());
		world.setBlockState(pos.up(3), ModBlocks.onbashiraTop.getDefaultState());
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockToAir(pos);
		worldIn.setBlockState(pos, ModBlocks.onbashira.getDefaultState());
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 2.0F, false);

		for(int i = 1; i < 4; i++) {
			worldIn.setBlockToAir(new BlockPos(pos.getX(), pos.getY() - i, pos.getZ()));
			worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY() - i, pos.getZ() + 0.5, 2.0F, false);
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
}
