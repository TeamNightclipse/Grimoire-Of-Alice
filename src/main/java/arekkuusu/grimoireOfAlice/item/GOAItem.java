/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.lib.LibItemName;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class GOAItem {

	public static Item itemEnchantedBook;
	public static Item itemGrimoireBook;
	public static Item itemVolatileString;
	public static Item itemShimenawaRope;
	public static Item itemThrowingExplosiveDoll;
	public static Item itemThrowingNeedleDoll;
	public static Item itemAlicesDoll;

	public static void preInit() {
		//@formatter:off
		itemEnchantedBook = new ItemEnchantedBook().setUnlocalizedName("EnchantedBook").setTextureName(LibMod.MODID + ":EnchantedBook");
		itemGrimoireBook = new ItemGrimoireBook().setUnlocalizedName("GrimoireBook").setTextureName(LibMod.MODID + ":GrimoireBook");
		itemVolatileString = new ItemVolatileString().setUnlocalizedName("VolatileString").setTextureName(LibMod.MODID + ":VolatileString");
		itemShimenawaRope = new ItemShimenawaRope().setUnlocalizedName("ShimenawaRope").setTextureName(LibMod.MODID + ":ShimenawaRope");
		itemThrowingExplosiveDoll = new ItemThrowingExplosiveDoll().setUnlocalizedName("ThrowingExplosiveDoll").setTextureName(LibMod.MODID + ":ThrowingExplosiveDoll");
		itemThrowingNeedleDoll = new ItemThrowingNeedleDoll().setUnlocalizedName("ThrowingNeedleDoll").setTextureName(LibMod.MODID + ":ThrowingNeedleDoll");
		itemAlicesDoll = new ItemAlicesDoll().setUnlocalizedName("AlicesDoll").setTextureName(LibMod.MODID + ":AlicesDoll");
		//@formatter:on

		GameRegistry.registerItem(itemEnchantedBook, LibItemName.ENCHANTEDBOOK);
		GameRegistry.registerItem(itemGrimoireBook, LibItemName.GRIMOIREBOOK);
		GameRegistry.registerItem(itemVolatileString, LibItemName.VOLATILESTRING);
		GameRegistry.registerItem(itemShimenawaRope, LibItemName.SHIMENAWAROPE);
		GameRegistry.registerItem(itemThrowingExplosiveDoll, LibItemName.EXPLOSIVEDOLL);
		GameRegistry.registerItem(itemThrowingNeedleDoll, LibItemName.NEEDLEDOLL);
		GameRegistry.registerItem(itemAlicesDoll, LibItemName.ALICESDOLL);
	}
}
