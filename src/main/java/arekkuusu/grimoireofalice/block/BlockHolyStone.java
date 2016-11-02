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
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHolyStone extends BlockMod {

	private static final AxisAlignedBB SMALL = new AxisAlignedBB(0.1875F, 0.1875F, 0.1875F, 1F - 0.1875F, 1F - 0.1875F, 1F - 0.1875F);
	private final Map<Item, Consumer<EntityPlayer>> effects = effectsMap();

	public BlockHolyStone() {
		super(LibBlockName.HOLY_STONE, Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);
	}

	private ImmutableMap<Item, Consumer<EntityPlayer>> effectsMap() {
		ImmutableMap.Builder<Item, Consumer<EntityPlayer>> builder = ImmutableMap.builder();
		builder.put(Items.GOLD_NUGGET, player -> player.addExperience(5));
		builder.put(Items.GOLD_INGOT, player -> player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2490, 0)));
		builder.put(Items.IRON_INGOT, player -> player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2490, 0)));
		builder.put(Items.BLAZE_POWDER, player -> player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2490, 0)));
		builder.put(Items.SPECKLED_MELON, player -> player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2490, 0)));
		builder.put(Items.DIAMOND, player -> player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2490, 4)));
		return builder.build();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.holy_stone_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.holy_stone_gifts.name"));
	}

	@Override
	public int tickRate(World worldIn) {
		return worldIn.isRaining() ? 40 : 100;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		Optional<EntityPlayer> optPlayer = getPlayerInRange(world, pos);
		if(optPlayer.isPresent()) {
			EntityPlayer player = optPlayer.get();
			addPlayerEffect(player);
			ifNear(world, pos);
			world.scheduleUpdate(pos, this, 10); //Update more frequently if a player is around
		}
		else {
			world.scheduleUpdate(pos, this, tickRate(world));
		}
	}

	protected void addPlayerEffect(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, 1));
	}

	protected void ifNear(World world, BlockPos pos) {
		if(world.isRemote) {
			float randX = pos.getX() + world.rand.nextFloat();
			float randY = pos.getY() + world.rand.nextFloat();
			float randZ = pos.getZ() + world.rand.nextFloat();
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, randX, randY, randZ, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, randX, randY, randZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private Optional<EntityPlayer> getPlayerInRange(World world, BlockPos pos) {
		if(world.isRaining()) return Optional.ofNullable(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false));
		else return Optional.empty();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote && heldItem != null) {
			Optional<Consumer<EntityPlayer>> effect = Optional.ofNullable(effects.get(heldItem.getItem()));
			if(effect.isPresent()) {
				--heldItem.stackSize;
				effect.get().accept(player);
				player.worldObj.playSound(player, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 0.1F, 1.0F);
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("deprecation") //Internal, not deprecated
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation") //Internal, not deprecated
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@SuppressWarnings("deprecation") //Internal, not deprecated
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SMALL;
	}
}
