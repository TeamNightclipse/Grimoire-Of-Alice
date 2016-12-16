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

	public EntityItemFireProof(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityItemFireProof(World worldIn, double x, double y, double z, ItemStack stack) {
		super(worldIn, x, y, z, stack);
	}

	public EntityItemFireProof(World worldIn) {
		super(worldIn);
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return source != DamageSource.outOfWorld && !source.isCreativePlayer();
	}
}
