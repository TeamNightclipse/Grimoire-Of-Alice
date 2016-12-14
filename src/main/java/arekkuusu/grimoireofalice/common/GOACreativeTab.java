/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common;

import java.util.List;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GOACreativeTab extends CreativeTabs {

	private List<ItemStack> list;

	GOACreativeTab() {
		super(LibMod.MODID);
		setNoTitle();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.MIRACLE_MALLET);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(List<ItemStack> list) {
		this.list = list;

		//Blocks
		addBlock(ModBlocks.COMPACT_STONE);
		addBlock(ModBlocks.IMPURE_STONE);
		addBlock(ModBlocks.HIHIIROKANE_BLOCK);
		addBlock(ModBlocks.SUGAR_BLOCK);
		addBlock(ModBlocks.HYPER_CONCENTRATED_MAGIC);
		addBlock(ModBlocks.ONBASHIRA);
		addBlock(ModBlocks.PILLAR_ALTAR);
		addBlock(ModBlocks.HOLY_STONE);
		addBlock(ModBlocks.HOLY_KEY_STONE);
		addBlock(ModBlocks.ALTAR);
		addBlock(ModBlocks.ROPE_BLOCK);
		addBlock(ModBlocks.PAPER_BLOCK);
		addBlock(ModBlocks.KYOUMARUBOTAN);
		addBlock(ModBlocks.SHROOM);
		addItem(ModItems.SHROOM_POWDER);
		addItem(ModItems.MORTAR_AND_PESTLE);

		//Misc
		addItem(ModItems.HIHIIROKANE);
		addItem(ModItems.IMPURE_ROCK);
		addItem(ModItems.TAMAHAGANE_STEEL);
		addItem(ModItems.SOLDIFIED_PAPER);
		addItem(ModItems.PASTE);
		addItem(ModItems.SHIMENAWA_ROPE);
		addItem(ModItems.BLACK_FEATHER);
		addItem(ModItems.NETHER_SHARD);
		addItem(ModItems.HARDENED_LEATHER);
		addItem(ModItems.IRON_NUGGET);
		addItem(ModItems.MASK);
		addItem(ModItems.UFO_BLUE);
		addItem(ModItems.UFO_GREEN);
		addItem(ModItems.UFO_RED);
		addItem(ModItems.TIME_ORB);
		addItem(ModItems.FULL_POWER);
		addItem(ModItems.STAR);
		addItem(ModItems.CHERRY);
		addItem(ModItems.FAITH);
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.POUCH);
		}

		//Items - By Character
		// ** Patchouli Knowledge ** /
		addItem(ModItems.PATCHY_BOOK);
		// **Alice Margatroid ** /
		addItem(ModItems.GRIMOIRE_BOOK);
		// ** Kosuzu Motoori ** /
		addItem(ModItems.YOUKAI_BOOK);
		// **Aya Shameimaru ** /
		addItem(ModItems.TENGU_FAN);
		addItem(ModItems.AYA_CAMERA);
		// ** Hatate Himekaidou ** /
		addItem(ModItems.HATATE_CAMERA);
		// ** Benben Tsukumo ** /
		// ** Chen ** /
		// ** Chiyuri Kitashirakawa ** /
		// ** Cirno ** /
		addItem(ModItems.POPSICLE_STICK);
		addItem(ModItems.WATERMELON_BLADE);
		addItem(ModItems.WATERMELON_SWORD);
		// ** Clownpiece ** /
		// ** Daiyousei ** /
		// ** Doremy Sweet ** /
		// ** Elly ** /
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.ELLY_SCYTHE);
		}
		//** Flandre Scarlet ** /
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.LAEVATEIN);
		}
		// ** Fujiwara no Mokou ** /
		// ** Hata no Kokoro ** /
		addItem(ModItems.FOX_MASK);
		addItem(ModItems.FUKU_NO_KAMI_MASK);
		addItem(ModItems.HANNYA_MASK);
		addItem(ModItems.HYOTTOKO_MASK);
		addItem(ModItems.KOOMOTE_MASK);
		addItem(ModItems.MASK_OF_HOPE);
		addItem(ModItems.MONKEY_MASK);
		addItem(ModItems.RAIDEN_MASK);
		addItem(ModItems.UBA_MASK);
		addItem(ModItems.KOKOROS_MASKS);
		// ** Hecatia Lapislazuli ** /
		// ** Hieda no Akyu ** /
		// ** Hina Kagiyama ** /
		// ** Hong Meiling ** /
		// ** Ichirin Kumoi ** /
		addItem(ModItems.ICHIRIN_UNZAN);
		addItem(ModItems.ICHIRIN_RING);
		// ** Iku Nagae ** /
		// ** Junko ** /
		// ** Kaguya Houraisan ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.JEWELED_HOURAI);
		addItem(ModItems.BUDAH_BOUL);
		addItem(ModItems.DRAGON_JEWEL);
		addItem(ModItems.FIRE_ROBE);
		addItem(ModItems.COWRIE_SHELL);
		// ** Kanako Yasaka ** /
		addItem(ModItems.KANAKO_SHIMENAWA);
		addItem(ModItems.KANAKO_ONBASHIRA);
		// ** Kasen Ibara ** /
		addItem(ModItems.IBARAKI_BOX_EMPTY);
		addItem(ModItems.IBARAKI_BOX_FILLED);
		addItem(ModItems.KAPPAS_NOSTRUM);
		// ** Eirin Yagokoro ** /
		addItem(ModItems.HOURAI_ELIXIR);
		addItem(ModItems.SYRINGE);
		addItem(ModItems.ORB_ELIXIR);
		// ** Keine Kamishirasawa ** /
		// ** Kogasa Tatara ** /
		// ** Koishi Komeiji -  Satori Komeiji ** /
		addItem(ModItems.THIRD_EYE);
		// ** Komachi Onozuka ** /
		addItem(ModItems.DEATH_SCYTHE);
		// ** Kyouko Kasodani ** /
		// ** Lunasa Prismriver - Merlin Prismriver - Lyrica Prismriver ** /
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.LUNASA_VIOLIN);
			addItem(ModItems.LYRICA_PIANO);
			addItem(ModItems.MERLIN_TRUMPET);
		}
		// ** Mamizou Futatsuiwa ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.LEAF);
		// ** Marisa Kirisame ** /
		addItem(ModItems.MARISA_HAT);
		// ** Minamitsu Murasa ** /
		addItem(ModItems.GHOST_DIPPER);
		// ** Momiji Inubashiri ** /
		addItem(ModItems.MOMIJIS_SCIMITAR_SWORD);
		addItem(ModItems.MAPLE_LEAF_SHIELD);
		// ** Mononobe no Futo ** /
		// ** Mokou ** /
		// ** Mystia Lorelei ** /
		addItem(ModItems.GRILLED_LAMPREY);
		// ** Nue Houjuu ** /
		addItem(ModItems.NUE_TRIDENT);
		addItem(ModItems.UFOS);
		// ** Nazrin ** /
		addItem(ModItems.NAZRIN_PENDULUM);
		addItem(ModItems.NAZRIN_STICK_ITEM);
		// ** Nitori Kawashiro ** /
		addItem(ModItems.CATTAIL_PLANT);
		addItem(ModItems.KAPPA_HAT);
		// ** Suwako Moriya ** /
		addItem(ModItems.SUWAKO_HAT);
		// ** Raiko Horikawa ** /
		// ** Ran Yakumo ** /
		// ** Reimu Hakurei ** /
		addItem(ModItems.HAKUREI_GOHEI);
		// ** Sanae Kochiya ** /
		addItem(ModItems.SANAE_GOHEI);
		// ** Seija Kijin ** /
		addItem(ModItems.BLOOD_ORB);
		addItem(ModItems.FOLDING_UMBRELLA);
		addItem(ModItems.SUBSTITUTE_JIZO);
		addItem(ModItems.NIMBLE_FABRIC);
		addItem(ModItems.FAKE_MIRACLE_MALLET);
		addItem(ModItems.GHASTLY_SEND_OFF_LANTERN);
		addItem(ModItems.CURSED_DECOY_DOLL);
		// ** Reisen ** /
		// ** Reisen Udongein Inaba ** /
		// ** Remilia Scarlet ** /
		// ** Sakuya Izayoi ** /
		addItem(ModItems.STOP_WATCH);
		// ** Rin Kaenbyou ** /
		addItem(ModItems.SKULL);
		// ** Sariel ** /
		addItem(ModItems.SARIEL_WAND);
		// ** Rinnosuke Morichika ** /
		addItem(ModItems.AMENONUHOKO);
		addItem(ModItems.SWORD_OF_KUSANAGI);
		// ** Rumia **/
		addItem(ModItems.RUMIA_SWORD);
		// ** Seiga Kaku ** /
		addItem(ModItems.WALL_PASSING_CHISEL);
		// ** Seiran ** /
		addItem(ModItems.MOCHI_HAMMER);
		// ** Shikieiki Yamaxanadu ** /
		addItem(ModItems.ROD_REMORSE);
		// ** Shinki ** /
		addItem(ModItems.SHINKI_WAND);
		// ** Shinmyoumaru Sukuna ** /
		addItem(ModItems.SHINMYOUMARU_HAT);
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.MIRACLE_MALLET);
		addItem(ModItems.SHINMYOUMARU_NEEDLE);
		// ** Shou Toramaru ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.SHOU_LAMP);
		// ** Suika Ibuki ** /
		addItem(ModItems.IBUKI_GOURD);
		// ** Yuugi Hoshiguma ** /
		addItem(ModItems.YUUGI_SAKE);
		// ** Sumireko Usami ** /
		// ** Tenshi Hinanawi ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.HISOU);
		addItem(ModItems.HEAVENLY_PEACH);
		// ** Toyosatomimi no Miko ** /
		addItem(ModItems.MIKO_CLOAK);
		addItem(ModItems.SACRED_TOYOSATOMIMI);
		addItem(ModItems.SHICHI_SEIKEN);
		addItem(ModItems.MIKO_STICK);
		addItem(ModItems.TOYOSATOMIMI_HAT);
		// ** Unzan ** /
		// ** Utsuho Reiuji ** /
		addItem(ModItems.UTSUHO_WINGS);
		// ** Yatsuhashi Tsukumo ** /
		// ** Youmu Konpaku ** /
		addItem(ModItems.ROUKANKEN);
		// ** Yukari Yakumo ** /
		// ** Yuuka Kazami ** /
		// ** Yuyuko Saigyouji ** /
	}

	@SideOnly(Side.CLIENT)
	private void addItem(Item item) {
		item.getSubItems(item, this, list);
	}

	@SideOnly(Side.CLIENT)
	private void addBlock(Block block) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, list);
	}
}