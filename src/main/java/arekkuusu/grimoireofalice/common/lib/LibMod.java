/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.lib;

public class LibMod {

	public static final String MODID = "grimoireofalice";
	//ID for MOD
	public static final String MODNAME = "GrimoireofAlice";
	//Name of MOD
	public static final String MODVER = "@VERSION@";
	//Version of MOD
	public static final String DEPENDENCIES = "required-after:Forge@[12.18.2.2105,];after:danmakucore";//@[0.2.0,]
	//Dependency
	public static final String PROXYCLIENT = "arekkuusu.grimoireofalice.client.ClientProxy";
	//Client Required
	public static final String PROXYCOMMON = "arekkuusu.grimoireofalice.common.core.ServerProxy";

}
