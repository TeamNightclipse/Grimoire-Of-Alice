package arekkuusu.grimoireofalice.block.tile;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.item.crafting.RecipeAltar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class TileCraftingAltar extends TileItemHandler implements ITickable {

	//Yes, these are used by the Renderer to render the book
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

	public boolean addItem(ItemStack stack) {
		boolean added = false;
		if (getItemStack() == null) {
			added = true;
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			itemHandler.setStackInSlot(0, stackToAdd);

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}
		return added;
	}

	public boolean removeItem(@Nullable EntityPlayer player) {
		boolean removed = false;
		if (getItemStack() != null) {
			removed = true;
			ItemStack stackToTake = getItemStack();
			itemHandler.setStackInSlot(0, null);

			if(player != null && !player.capabilities.isCreativeMode) {
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(stackToTake.getItem(), 1));
			}

			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}
		return removed;
	}

	public boolean doCrafting() {
		if (getItemStack() == null) {
			ArrayList<TilePillarAltar> arrayAltar = new ArrayList<>();
			ArrayList<ItemStack> arrayItem = new ArrayList<>();
			for (BlockPos pos : PILLAR_LOCATIONS) {
				pos = pos.add(getPos());
				System.out.println(pos.getX() + " " + pos.getZ());
				if ((getWorld().getBlockState(pos).getBlock() == ModBlocks.PILLAR_ALTAR)) {
					arrayAltar.add((TilePillarAltar) getWorld().getTileEntity(pos));
				}
			}
			if (!arrayAltar.isEmpty() && arrayAltar.size() > 2) {
				for (TilePillarAltar altar : arrayAltar) {
					if (!altar.hasItem()) continue;
					arrayItem.add(altar.getItemStack());
				}
				if (!arrayItem.isEmpty()) {
					RecipeAltar.recipes.stream().filter(recipe -> recipe.checkRecipe(arrayItem)).forEach(recipe -> {
						for (TilePillarAltar altar : arrayAltar) {
							altar.removeItem(null);
						}
						addItem(recipe.getResult());
					});
				}
			}
		}
		return true;
	}

	public ItemStack getItemStack(){
		return itemHandler.getStackInSlot(0);
	}

	@Override
	public void update() {
		bookPart();
	}

	private void bookPart(){
		bookSpreadPrev = bookSpread;
		bookRotationPrev = bookRotation;
		EntityPlayer entityplayer = worldObj.getClosestPlayer((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), 3.0D, false);

		if (entityplayer != null) {
			double d0 = entityplayer.posX - (pos.getX() + 0.5F);
			double d1 = entityplayer.posZ - (pos.getZ() + 0.5F);
			tRot = (float) MathHelper.atan2(d1, d0);
			bookSpread += 0.1F;

			if (bookSpread < 0.5F || rand.nextInt(40) == 0) {
				float f1 = flipT;

				while (true) {
					flipT += rand.nextInt(4) - rand.nextInt(4);

					if (f1 != flipT) {
						break;
					}
				}
			}
		} else {
			tRot += 0.02F;
			bookSpread -= 0.1F;
		}

		while (bookRotation >= Math.PI) {
			bookRotation -= Math.PI * 2F;
		}

		while (bookRotation < -Math.PI) {
			bookRotation += Math.PI * 2F;
		}

		while (tRot >= Math.PI) {
			tRot -= Math.PI * 2F;
		}

		while (tRot < -Math.PI) {
			tRot += Math.PI * 2F;
		}

		float f2 = tRot - bookRotation;
		while(f2 >= Math.PI) {
			f2 -= Math.PI * 2F;
		}

		while (f2 < -Math.PI) {
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
