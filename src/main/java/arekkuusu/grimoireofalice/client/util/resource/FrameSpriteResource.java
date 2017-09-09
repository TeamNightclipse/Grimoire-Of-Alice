/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.util.resource;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;

public class FrameSpriteResource extends SpriteResource {

	private final int rows, columns;
	private final double frames;
	private final double u;
	private final double v;

	public FrameSpriteResource(ResourceLocation location, int rows, int columns) {
		super(location);

		this.columns = columns;
		this.rows = rows;
		this.u = 1D / columns;
		this.v = 1D / rows;

		this.frames = rows * columns;
	}

	public Tuple<Double, Double> getUVFrame(int age) {
		int frame = (int) (age % frames);
		return new Tuple<>((frame % columns) * u, (frame / columns) * v);
	}

	public double getU() {
		return u;
	}

	public double getV() {
		return v;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public double getFrames() {
		return frames;
	}
}
