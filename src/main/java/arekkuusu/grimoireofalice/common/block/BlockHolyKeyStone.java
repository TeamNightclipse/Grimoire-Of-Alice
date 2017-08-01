/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHolyKeyStone extends BlockMod {

	public BlockHolyKeyStone() {
		super(LibBlockName.HOLY_KEY, Material.ROCK);
		setHardness(2.0F);
		setHarvestLevel("pickaxe", 1);
		setSoundType(SoundType.STONE);
		setResistance(15.0F);
		setLightLevel(0.5F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.holy_key_stone_header.name"));
	}

	@Override
	public int tickRate(World world) {
		return world.isRaining() ? 40 : 100;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleUpdate(pos, this, tickRate(world));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		Optional<EntityPlayer> optPlayer = getPlayerInRange(world, pos);
		if(optPlayer.isPresent()) {
			EntityPlayer player = optPlayer.get();
			addPlayerEffect(player);
			spawnParticles(world, pos, rand);
			world.scheduleUpdate(pos, this, 10); //Update more frequently if a player is around
		}
		else {
			world.scheduleUpdate(pos, this, tickRate(world));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World world, BlockPos pos, Random rand) {
		Optional<EntityPlayer> optPlayer = getPlayerInRange(world, pos);
		optPlayer.ifPresent(ignored -> spawnParticles(world, pos, rand));
	}

	private static void addPlayerEffect(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 3));
	}

	private void spawnParticles(World world, BlockPos pos, Random rand) {
		for(EnumFacing facing : EnumFacing.values()) {
			float x = pos.getX() + rand.nextFloat();
			float y = pos.getY() + rand.nextFloat();
			float z = pos.getZ() + rand.nextFloat();
			float d0 = facing.getAxisDirection().getOffset() * 0.0625F;

			if(world.getBlockLightOpacity(pos.offset(facing)) != 0) {
				switch(facing.getAxis()) {
					case X:
						x = pos.getX() + facing.getFrontOffsetX() + d0;
						break;
					case Y:
						y = pos.getY() + facing.getFrontOffsetY() + d0;
						break;
					case Z:
						z = pos.getZ() + facing.getFrontOffsetZ() + d0;
						break;
				}
			}

			if(x < pos.getX() || x > pos.getX() + 1 || y < 0.0D || y > pos.getY() + 1 || z < pos.getZ() || z > pos.getZ() + 1) {
				world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, x, y, z, 0.0D, 0.0D, 0.0D, 0);
			}
		}
	}

	private static Optional<EntityPlayer> getPlayerInRange(World world, BlockPos pos) {
		if(world.isRaining()) {
			return Optional.ofNullable(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 3, false));
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
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

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 75, 2));
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50, 2));
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 2));
		}
	}
}