/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AliceCreativeTab extends CreativeTabs {

	private NonNullList<ItemStack> list;

	AliceCreativeTab() {
		super(LibMod.MOD_ID);
		setBackgroundImageName("items.png");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.MIRACLE_MALLET);
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		this.list = list;
		//Blocks
		addBlock(ModBlocks.COMPACT_STONE);
		addBlock(ModBlocks.HIHIIROKANE_ORE);
		addBlock(ModBlocks.HIHIIROKANE_BLOCK);
		addBlock(ModBlocks.ONBASHIRA);
		addBlock(ModBlocks.CRAFTING_ALTAR);
		addBlock(ModBlocks.STONE_SPHERE);
		addBlock(ModBlocks.SHIMENAWA);
		addBlock(ModBlocks.SHIDE);
		addBlock(ModBlocks.KYOUMARUBOTAN);
		addBlock(ModBlocks.SHROOM);
		addItem(ModItems.SHROOM_POWDER);
		addItem(ModItems.MORTAR_AND_PESTLE);

		//Materials
		addItem(ModItems.HIHIIROKANE);
		addItem(ModItems.IMPURE_HIHIIROKANE);
		addItem(ModItems.TAMAHAGANE_STEEL);
		addItem(ModItems.SOLIDIFIED_PAPER);
		addItem(ModItems.PASTE);
		addItem(ModItems.BLACK_FEATHER);
		addItem(ModItems.COWRIE_SHELL);
		addItem(ModItems.SWALLOW_EGG);
		addItem(ModItems.DRAGON_SCALE);
		addBlock(ModBlocks.DRAGON_STONE);
		addItem(ModItems.NETHER_SHARD);
		addItem(ModItems.HARDENED_LEATHER);
		addItem(ModItems.MASK);
		addItem(ModItems.UFO_BLUE);
		addItem(ModItems.UFO_GREEN);
		addItem(ModItems.UFO_RED);
		addItem(ModItems.TIME_ORB);
		addItem(ModItems.SPELLCARD_POUCH);

		//-------------Items - By Character-------------//

		//-------------Patchouli Knowledge-------------//

		//-------------Kosuzu Motoori-------------//
		addItem(ModItems.YOUKAI_BOOK);

		//-------------Aya Shameimaru-------------//
		addItem(ModItems.TENGU_FAN);
		addItem(ModItems.AYA_CAMERA);

		//-------------Hatate Himekaidou-------------//
		addItem(ModItems.HATATE_CAMERA);

		//-------------Cirno-------------/
		addItem(ModItems.ICICLE_SWORD);
		addItem(ModItems.POPSICLE_STICK);
		addItem(ModItems.WATERMELON_BLADE);
		addItem(ModItems.WATERMELON_SWORD);

		//-------------Elly-------------//
		addItem(ModItems.ELLY_SCYTHE);

		//-------------Flandre Scarlet-------------//
		addItem(ModItems.LAEVATEIN);

		//-------------Hata no Kokoro-------------//
		addItem(ModItems.FOX_MASK);
		addItem(ModItems.FUKU_NO_KAMI_MASK);
		addItem(ModItems.HANNYA_MASK);
		addItem(ModItems.HYOTTOKO_MASK);
		addItem(ModItems.KOOMOTE_MASK);
		addItem(ModItems.MASK_OF_HOPE);
		addItem(ModItems.MONKEY_MASK);
		addItem(ModItems.RAIDEN_MASK);
		addItem(ModItems.UBA_MASK);
		addItem(ModItems.KOKORO_MASKS);

		//-------------Ichirin Kumoi-------------//
		addItem(ModItems.ICHIRIN_UNZAN);
		addItem(ModItems.ICHIRIN_RING);

		//-------------Kaguya Houraisan-------------//
		addItem(ModItems.JEWELED_HOURAI);
		addItem(ModItems.BUDAH_BOWL);
		addItem(ModItems.DRAGON_JEWEL);
		addItem(ModItems.FIRE_ROBE);
		addItem(ModItems.SWALLOW_COWRIE_SHELL);
		addItem(ModItems.SEAMLESS_CEILING_OF_KINKAKUJI);
		addItem(ModItems.RED_STONE_OF_AJA);

		//-------------Kanako Yasaka-------------//
		addItem(ModItems.KANAKO_SHIMENAWA);
		addItem(ModItems.KANAKO_ONBASHIRA);

		//-------------Kasen Ibara-------------//
		addItem(ModItems.IBARAKI_BOX_EMPTY);
		addItem(ModItems.IBARAKI_BOX_FILLED);
		addItem(ModItems.KAPPAS_NOSTRUM);

		//-------------Eirin Yagokoro-------------//
		addItem(ModItems.HOURAI_ELIXIR);
		addItem(ModItems.SYRINGE);
		addItem(ModItems.ORB_ELIXIR);

		//-------------Koishi Komeiji -  Satori Komeiji-------------//
		addItem(ModItems.THIRD_EYE);

		//-------------Komachi Onozuka-------------//
		addItem(ModItems.KOMACHI_SCYTHE);

		//-------------Kyouko Kasodani-------------//
		//-------------Lunasa Prismriver - Merlin Prismriver - Lyrica Prismriver-------------//
		addItem(ModItems.LUNASA_VIOLIN);
		addItem(ModItems.LYRICA_PIANO);
		addItem(ModItems.MERLIN_TRUMPET);

		//-------------Mamizou Futatsuiwa-------------//
		addItem(ModItems.LEAF);

		//-------------Marisa Kirisame-------------//
		addItem(ModItems.MARISA_HAT);

		//-------------Minamitsu Murasa-------------//
		addItem(ModItems.GHOST_DIPPER);
		addItem(ModItems.GHOST_ANCHOR);

		//-------------Momiji Inubashiri-------------//
		addItem(ModItems.MOMIJI_SCIMITAR_SWORD);
		addItem(ModItems.MAPLE_LEAF_SHIELD);

		//-------------Mystia Lorelei-------------//
		addItem(ModItems.GRILLED_LAMPREY);

		//-------------Nue Houjuu-------------//
		addItem(ModItems.NUE_TRIDENT);
		addItem(ModItems.UFO);

		//-------------Nazrin-------------//
		addItem(ModItems.NAZRIN_PENDULUM);
		addItem(ModItems.NAZRIN_STICK);

		//-------------Nitori Kawashiro-------------//
		addItem(ModItems.CATTAIL_PLANT);
		addItem(ModItems.KAPPA_HAT);

		//-------------Suwako Moriya-------------//
		addItem(ModItems.SUWAKO_HAT);

		//-------------Reimu Hakurei-------------//
		addItem(ModItems.HAKUREI_GOHEI);
		addItem(ModItems.SPIRITUAL_STRIKE_TALISMAN);

		//-------------Sanae Kochiya-------------//
		addItem(ModItems.SANAE_GOHEI);
		addItem(ModItems.CHARM_OF_HEALING);

		//-------------Seija Kijin-------------//
		addItem(ModItems.BLOOD_THIRSTY_ORB);
		addItem(ModItems.FOLDING_UMBRELLA);
		addItem(ModItems.SUBSTITUTE_JIZO);
		addItem(ModItems.NIMBLE_FABRIC);
		addItem(ModItems.FAKE_MIRACLE_MALLET);
		addItem(ModItems.SEND_OFF_LANTERN);
		addItem(ModItems.CURSED_DECOY_DOLL);

		//-------------Sakuya Izayoi-------------//
		addItem(ModItems.STOPWATCH);

		//-------------Rin Kaenbyou-------------//
		addItem(ModItems.ORIN_SKULL);

		//-------------Rinnosuke Morichika-------------//
		addItem(ModItems.AMENONUHOKO);
		addItem(ModItems.SWORD_OF_KUSANAGI);

		//-------------Rumia-------------//
		addItem(ModItems.RUMIA_SWORD);

		//-------------Seiga Kaku-------------//
		addItem(ModItems.WALL_PASSING_CHISEL);

		//-------------Seiran-------------//
		addItem(ModItems.MOCHI_HAMMER);

		//-------------Shikieiki Yamaxanadu-------------//
		addItem(ModItems.ROD_REMORSE);

		//-------------Shinmyoumaru Sukuna-------------//
		addItem(ModItems.SHINMYOUMARU_HAT);
		addItem(ModItems.MIRACLE_MALLET);
		addItem(ModItems.SHINMYOUMARU_NEEDLE);

		//-------------Shou Toramaru-------------//
		addItem(ModItems.SHOU_LAMP);

		//-------------Suika Ibuki-------------//
		addItem(ModItems.IBUKI_GOURD);

		//-------------Yuugi Hoshiguma-------------//
		addItem(ModItems.YUUGI_SAKE);

		//-------------Tenshi Hinanawi-------------//
		addItem(ModItems.HISOU_SWORD);
		addItem(ModItems.TENSHI_HAT);
		addItem(ModItems.HEAVENLY_PEACH);

		//-------------Toyosatomimi no Miko-------------//
		addItem(ModItems.MIKO_CLOAK);
		addItem(ModItems.SACRED_SWORD_OF_TOYOSATOMIMI);
		addItem(ModItems.SHICHI_SEIKEN);
		addItem(ModItems.MIKO_STICK);
		addItem(ModItems.TOYOSATOMIMI_HAT);

		//-------------Utsuho Reiuji-------------//
		addItem(ModItems.UTSUHO_WINGS);
		addItem(ModItems.NUCLEAR_BOOTS);
		addItem(ModItems.NUCLEAR_ROD);

		//-------------Youmu Konpaku-------------//
		addItem(ModItems.ROUKANKEN);
		addItem(ModItems.HAKUROUKEN);

		//-------------Yukari Yakumo-------------//
		addItem(ModItems.GAP);
	}

	@SideOnly(Side.CLIENT)
	private void addItem(Item item) {
		item.getSubItems(this, list);
	}

	@SideOnly(Side.CLIENT)
	private void addBlock(Block block) {
		block.getSubBlocks(this, list);
	}
}