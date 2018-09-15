/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import arekkuusu.grimoireofalice.api.AliceAPI;
import arekkuusu.grimoireofalice.common.block.BlockOnbashira;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TileMiniShrine extends TileInventoryBase implements ITickable {

	private final Set<BlockPos> posSet = new HashSet<>();

	public TileMiniShrine() {
		super(1);
	}

	@Override
	public void onLoad() {
		if(!world.isRemote) {
			BlockPos.getAllInBox(pos.add(-5, -5, -5), pos.add(5, 5, 5)).forEach(pos -> {
				TileEntity tile = world.getTileEntity(pos);
				if(tile instanceof TileStoneSphere && posSet.size() < 16 && isValid(pos)) {
					posSet.add(pos);
				}
			});
		}
	}

	private boolean isValid(BlockPos pos) {
		boolean valid = false;
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos).move(EnumFacing.DOWN);
		IBlockState state = world.getBlockState(mutableBlockPos);
		while(state.getBlock() == ModBlocks.ONBASHIRA) {
			if(state.getActualState(world, mutableBlockPos).getValue(BlockOnbashira.PART_LISTED) == BlockOnbashira.OnbashiraPiece.LOWER) {
				valid = Stream.of(EnumFacing.HORIZONTALS).allMatch(f -> world.getBlockState(mutableBlockPos.offset(f)).getBlock() == Blocks.WATER);
				break;
			} else {
				state = world.getBlockState(mutableBlockPos.move(EnumFacing.DOWN));
			}
		}
		return valid;
	}

	public void craft() {
		if(!world.isRemote && !hasItemStack()) {
			List<TileStoneSphere> spheres = posSet.stream()
					.map(p -> world.getTileEntity(p))
					.filter(t -> t instanceof TileStoneSphere)
					.map(t -> ((TileStoneSphere) t))
					.filter(TileStoneSphere::hasItemStack)
					.collect(Collectors.toList());
			List<ItemStack> recipeItems = spheres.stream()
					.map(TileStoneSphere::getItemStack)
					.collect(Collectors.toList());
			AliceAPI.findAltarRecipeMatch(recipeItems, world).ifPresent(recipe -> {
				spheres.forEach(s -> s.setItemStack(ItemStack.EMPTY));
				setItemStack(recipe.getResult());
			});
		}
	}

	@Override
	public void update() {

	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 0 || pass == 1;
	}
}
