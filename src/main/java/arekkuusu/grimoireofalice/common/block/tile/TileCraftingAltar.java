/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.api.recipes.IAltarRecipe;
import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.core.helper.MathUtil;
import arekkuusu.grimoireofalice.common.event.AchievementEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TileCraftingAltar extends TileBase implements ITickable {

	public int tickCount;
	public float pageFlip;
	public float pageFlipPrev;
	public float bookSpread;
	public float bookSpreadPrev;
	public float bookRotation;
	public float bookRotationPrev;
	private float flipT;
	private float flipA;
	private float tRot;

	private static final BlockPos[] PILLAR_LOCATIONS = {
			new BlockPos(0, 0, -4),
			new BlockPos(3, 0, -3),
			new BlockPos(4, 0, 0),
			new BlockPos(3, 0, 3),
			new BlockPos(0, 0, 4),
			new BlockPos(-3, 0, 3),
			new BlockPos(-4, 0, 0),
			new BlockPos(-3, 0, -3)
	};

	private static final BlockPos[] SECOND_PILLAR_LOCATIONS = {
			new BlockPos(2, 3, 5),
			new BlockPos(-2, 3, 5),
			new BlockPos(5, 3, 2),
			new BlockPos(5, 3, -2),
			new BlockPos(2, 3, -5),
			new BlockPos(-2, 3, -5),
			new BlockPos(-5, 3, 2),
			new BlockPos(-5, 3, -2)
	};

	public TileCraftingAltar() {
		super(1);
	}

	public void doCrafting(@Nullable EntityPlayer player) {
		if(!world.isRemote && !hasItemStack()) {
			List<TilePillarAltar> altars = new ArrayList<>();
			for(BlockPos pos : PILLAR_LOCATIONS) {
				getPillarPos(pos.add(getPos())).ifPresent(altars::add);
			}
			for(BlockPos pos : SECOND_PILLAR_LOCATIONS) {
				getPillarPos(pos.add(getPos())).ifPresent(altars::add);
			}
			List<ItemStack> stacks = altars.stream().filter(TileBase::hasItemStack).map(TilePillarAltar::getItemStack).collect(Collectors.toList());
			Optional<IAltarRecipe> optional = AliceAPI.findAltarRecipeMatch(stacks, world);
			optional.ifPresent(recipe -> {
				AchievementEvents.onAltarCraft(player, recipe.getResult());
				altars.forEach(tile -> tile.setItemStack(ItemStack.EMPTY));
				setItemStack(recipe.getResult());
				craftEffect();
			});
		}
	}

	private Optional<TilePillarAltar> getPillarPos(BlockPos pos) {
		return Optional.ofNullable((TilePillarAltar) world.getTileEntity(pos));
	}

	private void craftEffect() {
		world.playSound(null, getPos(), GrimoireSoundEvents.CRAFTING_SPELL, SoundCategory.BLOCKS, 1F, 0.5F);
		for(int i = 0; i < 9; i++) {
			double d0 = pos.getX() + world.rand.nextFloat();
			double d1 = pos.getY() + 1 + world.rand.nextFloat();
			double d2 = pos.getZ() + world.rand.nextFloat();

			((WorldServer) world).spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0, d1, d2, 9, world.rand.nextGaussian() * 0.005D, world.rand.nextGaussian() * 0.005D, world.rand.nextGaussian() * 0.005D, 0.1D);
		}
	}

	@Override
	public void update() {
		if(++tickCount % 2 == 0) {
			double p0 = pos.getX() - 4.5D + world.rand.nextInt(10);
			double p1 = pos.getY() + 0.5D + world.rand.nextInt(5);
			double p2 = pos.getZ() - 4.5D + world.rand.nextInt(10);
			double p3 = (0.4F - (world.rand.nextFloat() + world.rand.nextFloat()) * 0.4F);

			world.spawnParticle(EnumParticleTypes.END_ROD, p0 + p3, p1 + p3, p2 + p3, world.rand.nextGaussian() * 0.005D, world.rand.nextGaussian() * 0.005D, world.rand.nextGaussian() * 0.005D);
		}

		//-----------------BLAME MINECRAFT-----------------//
		EntityPlayer entityplayer = world.getClosestPlayer(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, 5D, false);
		bookSpreadPrev = bookSpread;
		bookRotationPrev = bookRotation;

		if(entityplayer != null) {
			double d0 = entityplayer.posX - (pos.getX() + 0.5F);
			double d1 = entityplayer.posZ - (pos.getZ() + 0.5F);
			tRot = (float) MathHelper.atan2(d1, d0);
			bookSpread += 0.1F;

			if(bookSpread < 0.5F || world.rand.nextInt(40) == 0) {
				float f1 = flipT;

				while(MathUtil.fuzzyEqual(f1, flipT)) {
					flipT += world.rand.nextInt(4) - world.rand.nextInt(4);
				}
			}
		}
		else {
			tRot += 0.02F;
			bookSpread -= 0.1F;
		}

		while(bookRotation >= Math.PI) {
			bookRotation -= Math.PI * 2F;
		}

		while(bookRotation < -Math.PI) {
			bookRotation += Math.PI * 2F;
		}

		while(tRot >= Math.PI) {
			tRot -= Math.PI * 2F;
		}

		while(tRot < -Math.PI) {
			tRot += Math.PI * 2F;
		}

		float f2 = tRot - bookRotation;
		while(f2 >= Math.PI) {
			f2 -= Math.PI * 2F;
		}

		while(f2 < -Math.PI) {
			f2 += Math.PI * 2F;
		}

		bookRotation += f2 * 0.4F;
		bookSpread = MathHelper.clamp(bookSpread, 0F, 1F);
		pageFlipPrev = pageFlip;
		float f = (flipT - pageFlip) * 0.4F;
		f = MathHelper.clamp(f, -0.2F, 0.2F);
		flipA += (f - flipA) * 0.9F;
		pageFlip += flipA;
		//-----------------BLAME MINECRAFT-----------------//
	}
}
