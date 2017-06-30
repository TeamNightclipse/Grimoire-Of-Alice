/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemFireProof extends EntityItem {

	public EntityItemFireProof(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityItemFireProof(World world, double x, double y, double z, ItemStack stack) {
		super(world, x, y, z, stack);
	}

	public EntityItemFireProof(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		extinguish();
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer();
	}
}
