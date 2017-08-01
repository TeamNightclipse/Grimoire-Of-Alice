package arekkuusu.grimoireofalice.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public interface Miracle {

	/**
	 * The name of this miracle
	 */
	String getName();

	/**
	 * Test if the miracle can be used. No side effects.
	 */
	boolean canUse(int charge, EntityPlayer player);

	/**
	 * Executes the miracle
	 * @return The amount of charge the miracle requires
	 */
	int execute(Item item, int charge, EntityPlayer player);
}