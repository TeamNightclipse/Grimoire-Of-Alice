/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.food;

import arekkuusu.grimoireofalice.client.util.helper.IModel;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemModFood extends ItemFood implements IModel {

	public ItemModFood(int healAmount, float potionEffectProbability, boolean isWolfFood, String id) {
		super(healAmount, potionEffectProbability, isWolfFood);
		ModItems.setRegistry(this, id);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
