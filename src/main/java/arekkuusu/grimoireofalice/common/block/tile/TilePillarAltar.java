/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block.tile;

import net.katsstuff.teamnightclipse.danmakucore.helper.MathUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TilePillarAltar extends TileBase implements ITickable {

	public int tickCount;

	public TilePillarAltar() {
		super(1);
	}

	public float getRenderHeight() {
		float height = getTileData().getFloat("RenderHeight");
		return MathUtil.fuzzyEqual(height, 0F) ? 1.4F : height;
	}

	public TilePillarAltar setRenderHeight(float renderHeight) {
		NBTTagCompound tag = getTileData();
		tag.setFloat("RenderHeight", renderHeight);
		return this;
	}

	@Override
	public void update() {
		tickCount++;
	}
}
