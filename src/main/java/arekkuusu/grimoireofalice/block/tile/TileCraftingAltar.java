package arekkuusu.grimoireofalice.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import java.util.Random;

public class TileCraftingAltar extends TileItemHandler {

	public int tickCount;
	public float pageFlip;
	public float pageFlipPrev;
	public float flipT;
	public float flipA;
	public float bookSpread;
	public float bookSpreadPrev;
	public float bookRotation;
	public float bookRotationPrev;
	public float tRot;
	private static final Random rand = new Random();

	private static final BlockPos[] PILLAR_LOCATIONS = {
			new BlockPos(-4, 0, 0),
			new BlockPos(-3, 0, 3),
			new BlockPos(0, 0, 4),
			new BlockPos(3, 0, 3),
			new BlockPos(4, 0, 0),
			new BlockPos(3, 0, -3),
			new BlockPos(0, 0, -4),
			new BlockPos(-3, 0, -3)};

	public boolean addItem(ItemStack stack) {
		boolean did = false;
		if (itemHandler.getStackInSlot(1) == null) {
			did = true;
			ItemStack stackToAdd = stack.copy();
			stackToAdd.stackSize = 1;
			itemHandler.setStackInSlot(1, stackToAdd);
		}
		if (did) {
			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}

		return true;
	}

	public boolean removeItem(EntityPlayer player) {
		boolean did = false;
		if (itemHandler.getStackInSlot(0) != null) {
			did = true;
			ItemStack stackToTake = itemHandler.getStackInSlot(0);
			itemHandler.setStackInSlot(0, null);

			if (player != null && !player.capabilities.isCreativeMode)
				if (!player.inventory.addItemStackToInventory(new ItemStack(stackToTake.getItem(), 1))) {
					player.dropItem(stackToTake.getItem(), 1);
				}
		}
		if (did) {
			IBlockState state = getWorld().getBlockState(getPos());
			getWorld().notifyBlockUpdate(getPos(), state, state, 8);
		}

		return true;
	}

	@Override
	public void writePacketNBT(NBTTagCompound tagCompound) {
		super.writePacketNBT(tagCompound);
	}

	@Override
	public void readPacketNBT(NBTTagCompound tagCompound) {
		super.readPacketNBT(tagCompound);
	}

	@Override
	public void update() {
		bookPart();
	}

	private void bookPart(){
		this.bookSpreadPrev = this.bookSpread;
		this.bookRotationPrev = this.bookRotation;
		EntityPlayer entityplayer = this.worldObj.getClosestPlayer((double) ((float) this.pos.getX() + 0.5F), (double) ((float) this.pos.getY() + 0.5F), (double) ((float) this.pos.getZ() + 0.5F), 3.0D, false);

		if (entityplayer != null) {
			double d0 = entityplayer.posX - (double) ((float) this.pos.getX() + 0.5F);
			double d1 = entityplayer.posZ - (double) ((float) this.pos.getZ() + 0.5F);
			this.tRot = (float) MathHelper.atan2(d1, d0);
			this.bookSpread += 0.1F;

			if (this.bookSpread < 0.5F || rand.nextInt(40) == 0) {
				float f1 = this.flipT;

				while (true) {
					this.flipT += (float) (rand.nextInt(4) - rand.nextInt(4));

					if (f1 != this.flipT) {
						break;
					}
				}
			}
		} else {
			this.tRot += 0.02F;
			this.bookSpread -= 0.1F;
		}

		while (this.bookRotation >= (float) Math.PI) {
			this.bookRotation -= ((float) Math.PI * 2F);
		}

		while (this.bookRotation < -(float) Math.PI) {
			this.bookRotation += ((float) Math.PI * 2F);
		}

		while (this.tRot >= (float) Math.PI) {
			this.tRot -= ((float) Math.PI * 2F);
		}

		while (this.tRot < -(float) Math.PI) {
			this.tRot += ((float) Math.PI * 2F);
		}

		float f2;

		for (f2 = this.tRot - this.bookRotation; f2 >= (float) Math.PI; f2 -= ((float) Math.PI * 2F)) {
			;
		}

		while (f2 < -(float) Math.PI) {
			f2 += ((float) Math.PI * 2F);
		}

		this.bookRotation += f2 * 0.4F;
		this.bookSpread = MathHelper.clamp_float(this.bookSpread, 0.0F, 1.0F);
		++this.tickCount;
		this.pageFlipPrev = this.pageFlip;
		float f = (this.flipT - this.pageFlip) * 0.4F;
		float f3 = 0.2F;
		f = MathHelper.clamp_float(f, -0.2F, 0.2F);
		this.flipA += (f - this.flipA) * 0.9F;
		this.pageFlip += this.flipA;
	}
}
