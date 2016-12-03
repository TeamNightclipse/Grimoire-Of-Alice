/*
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.form;

import arekkuusu.grimoireofalice.common.lib.LibFormName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.katsstuff.danmakucore.entity.danmaku.form.Form;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

public class GOAForms {

	private static final IForgeRegistry<Form> REGISTRY = GameRegistry.findRegistry(Form.class);

	public static final Form WIND = REGISTRY.getValue(resource(LibFormName.WIND));
	public static final Form NOTE = REGISTRY.getValue(resource(LibFormName.NOTE));
	public static final Form LEAF = REGISTRY.getValue(resource(LibFormName.LEAF));
	public static final Form UFO = REGISTRY.getValue(resource(LibFormName.UFO));

	private static ResourceLocation resource(String name) {
		return new ResourceLocation(LibMod.MODID, name);
	}
}
