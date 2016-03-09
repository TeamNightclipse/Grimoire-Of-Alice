/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireofalice.client.entity.proyectile.EntityThrowingExplosiveDoll;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.libMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
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
		
		itemEnchantedBook = new ItemEnchantedBook().setUnlocalizedName("EnchantedBook").setTextureName(libMod.MODID + ":EnchantedBook");
		itemGrimoireBook = new ItemGrimoireBook().setUnlocalizedName("GrimoireBook").setTextureName(libMod.MODID + ":GrimoireBook");
		itemVolatileString = new ItemVolatileString().setUnlocalizedName("VolatileString").setTextureName(libMod.MODID + ":VolatileString");
		itemShimenawaRope = new ItemShimenawaRope().setUnlocalizedName("ShimenawaRope").setTextureName(libMod.MODID + ":ShimenawaRope");
		itemThrowingExplosiveDoll = new ItemThrowingExplosiveDoll().setUnlocalizedName("ThrowingExplosiveDoll").setTextureName(libMod.MODID + ":ThrowingExplosiveDoll");
		itemThrowingNeedleDoll = new ItemThrowingNeedleDoll().setUnlocalizedName("ThrowingNeedleDoll").setTextureName(libMod.MODID + ":ThrowingNeedleDoll");
		itemAlicesDoll = new ItemAlicesDoll().setUnlocalizedName("AlicesDoll").setTextureName(libMod.MODID + ":AlicesDoll");
		
		GameRegistry.registerItem(itemEnchantedBook, LibItemName.ENCHANTEDBOOK);
		GameRegistry.registerItem(itemGrimoireBook, LibItemName.GRIMOIREBOOK);
		GameRegistry.registerItem(itemVolatileString, LibItemName.VOLATILESTRING);
		GameRegistry.registerItem(itemShimenawaRope, LibItemName.SHIMENAWAROPE);
		GameRegistry.registerItem(itemThrowingExplosiveDoll, LibItemName.EXPLOSIVEDOLL);
		GameRegistry.registerItem(itemThrowingNeedleDoll, LibItemName.NEEDLEDOLL);
		GameRegistry.registerItem(itemAlicesDoll, LibItemName.ALICESDOLL);

	}

}
