/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.api.GrimoireOfAliceAPI;
import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.api.tile.ITileItemHolder;
import arekkuusu.grimoireofalice.common.block.BlockOnbashira;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileCraftingAltar extends TileItemHandler implements ITileItemHolder, ITickable {

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
	private static final Random rand = new Random();

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

	@Override
	public boolean addItem(@Nullable EntityPlayer player, ItemStack stack) {
		boolean added = false;
		if(!hasItem()) {
			added = true;
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			itemHandler.insertItem(0, stackToAdd, false);

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}
		return added;
	}

	@Override
	public boolean removeItem(@Nullable EntityPlayer player) {
		boolean removed = false;
		ItemStack stackToTake = itemHandler.extractItem(0, 1, false);
		if(stackToTake != null) {
			worldObj.playSound(null, getPos(), SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1F, 0.5F);
			removed = true;

			if(player != null && !player.capabilities.isCreativeMode) {
				ItemHandlerHelper.giveItemToPlayer(player, stackToTake);
			}

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}
		return removed;
	}

	@Override
	public void destroy() {
		if (!worldObj.isRemote) {
			ItemStack output = itemHandler.extractItem(0, 1, false);
			if (output != null) {
				EntityItem outputItem = new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, output);
				worldObj.spawnEntityInWorld(outputItem);
			}
		}
	}

	public boolean doCrafting() {
		if(!hasItem()) {
			List<TilePillarAltar> altars = new ArrayList<>();
			for(BlockPos pos : PILLAR_LOCATIONS) {
				pos = pos.add(getPos());
				if(getWorld().getBlockState(pos).getBlock() == ModBlocks.PILLAR_ALTAR) {
					altars.add((TilePillarAltar)getWorld().getTileEntity(pos));
				}
			}
			for(BlockPos pos : SECOND_PILLAR_LOCATIONS) {
				pos = pos.add(getPos());
				if(getWorld().getBlockState(pos).getBlock() == ModBlocks.ONBASHIRA
						&& getWorld().getBlockState(pos).getValue(BlockOnbashira.PART) == BlockOnbashira.Part.TOP) {
					altars.add((TilePillarAltar)getWorld().getTileEntity(pos));
				}
			}
			if(!altars.isEmpty() && altars.size() >= 2) {
				List<ItemStack> recipeItems = altars.stream()
						.filter(TilePillarAltar::hasItem)
						.map(TilePillarAltar::getItemStack)
						.collect(Collectors.toList());

				GrimoireOfAliceAPI.getRecipes().stream().filter(recipe -> recipe.checkRecipe(recipeItems, worldObj)).forEach(recipe -> {
					for(TilePillarAltar altar : altars) {
						altar.removeItem(null);
					}
					addItem(null, recipe.getResult());
					doEffect();
				});
			}
		}
		return hasItem();
	}

	private void doEffect() {
		worldObj.playSound(null, getPos(), GrimoireSoundEvents.CRAFTING_SPELL, SoundCategory.BLOCKS, 1F, 0.5F);
		for (int i = 0; i < 9; i++) {
			double d0 = pos.getX() + rand.nextFloat();
			double d1 = pos.getY() + 1 + rand.nextFloat();
			double d2 = pos.getZ() + rand.nextFloat();

			worldObj.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0, d1, d2, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
		}
	}

	public ItemStack getItemStack() {
		return itemHandler.getStackInSlot(0);
	}

	public boolean hasItem() {
		return getItemStack() != null;
	}

	@Override
	public void update() {
		if (tickCount % 2 == 0) {
			double p0 = pos.getX() - 4.5D + rand.nextInt(10);
			double p1 = pos.getY() + 0.5D + rand.nextInt(5);
			double p2 = pos.getZ() - 4.5D + rand.nextInt(10);
			double p3 = (0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);

			worldObj.spawnParticle(EnumParticleTypes.END_ROD, p0 + p3, p1 + p3, p2 + p3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
		}

		bookSpreadPrev = bookSpread;
		bookRotationPrev = bookRotation;
		EntityPlayer entityplayer = worldObj.getClosestPlayer(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, 3.0D, false);

		if(entityplayer != null) {
			double d0 = entityplayer.posX - (pos.getX() + 0.5F);
			double d1 = entityplayer.posZ - (pos.getZ() + 0.5F);
			tRot = (float)MathHelper.atan2(d1, d0);
			bookSpread += 0.1F;

			if(bookSpread < 0.5F || rand.nextInt(40) == 0) {
				float f1 = flipT;

				while(true) {
					flipT += rand.nextInt(4) - rand.nextInt(4);

					if(f1 != flipT) {
						break;
					}
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
		bookSpread = MathHelper.clamp_float(bookSpread, 0.0F, 1.0F);
		++tickCount;
		pageFlipPrev = pageFlip;
		float f = (flipT - pageFlip) * 0.4F;
		f = MathHelper.clamp_float(f, -0.2F, 0.2F);
		flipA += (f - flipA) * 0.9F;
		pageFlip += flipA;
	}
}
