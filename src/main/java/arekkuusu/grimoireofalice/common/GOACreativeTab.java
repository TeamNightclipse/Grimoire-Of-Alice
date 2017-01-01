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
		setBackgroundImageName("item_search.png");
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
		return true;
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
		addItem(ModItems.PATCHY_BOOK); //Kinda Useless
		// **Alice Margatroid ** /
		addItem(ModItems.GRIMOIRE_BOOK); //Useless
		// ** Kosuzu Motoori ** /
		addItem(ModItems.YOUKAI_BOOK); //Mega Useless
		// **Aya Shameimaru ** /
		addItem(ModItems.TENGU_FAN); //Done
		addItem(ModItems.AYA_CAMERA); //Done
		// ** Hatate Himekaidou ** /
		addItem(ModItems.HATATE_CAMERA); //Done
		// ** Benben Tsukumo ** /
		// ** Chen ** /
		// ** Chiyuri Kitashirakawa ** /
		// ** Cirno ** /
		addItem(ModItems.POPSICLE_STICK); //Done
		addItem(ModItems.WATERMELON_BLADE); //Done
		addItem(ModItems.WATERMELON_SWORD); //Done
		// ** Clownpiece ** /
		// ** Daiyousei ** /
		// ** Doremy Sweet ** /
		// ** Elly ** /
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.ELLY_SCYTHE); //Done
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
		addItem(ModItems.ICHIRIN_UNZAN); //Done
		addItem(ModItems.ICHIRIN_RING); //Done
		// ** Iku Nagae ** /
		// ** Junko ** /
		// ** Kaguya Houraisan ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.JEWELED_HOURAI); //Done
		addItem(ModItems.BUDAH_BOUL);
		addItem(ModItems.DRAGON_JEWEL);
		addItem(ModItems.FIRE_ROBE); //Done
		addItem(ModItems.COWRIE_SHELL); //Done
		// ** Kanako Yasaka ** /
		addItem(ModItems.KANAKO_SHIMENAWA); //Done
		addItem(ModItems.KANAKO_ONBASHIRA); //Done
		// ** Kasen Ibara ** /
		addItem(ModItems.IBARAKI_BOX_EMPTY); //Done
		addItem(ModItems.IBARAKI_BOX_FILLED); //Done
		addItem(ModItems.KAPPAS_NOSTRUM); //Done
		// ** Eirin Yagokoro ** /
		addItem(ModItems.HOURAI_ELIXIR); //Done
		addItem(ModItems.SYRINGE); //Done
		addItem(ModItems.ORB_ELIXIR); //Done
		// ** Keine Kamishirasawa ** /
		// ** Kogasa Tatara ** /
		// ** Koishi Komeiji -  Satori Komeiji ** /
		addItem(ModItems.THIRD_EYE); //Done
		// ** Komachi Onozuka ** /
		addItem(ModItems.DEATH_SCYTHE); //Done
		// ** Kyouko Kasodani ** /
		// ** Lunasa Prismriver - Merlin Prismriver - Lyrica Prismriver ** /
		if (GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.LUNASA_VIOLIN); //Done
			addItem(ModItems.LYRICA_PIANO); //Done
			addItem(ModItems.MERLIN_TRUMPET); //Done
		}
		// ** Mamizou Futatsuiwa ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.LEAF); //Done
		// ** Marisa Kirisame ** /
		addItem(ModItems.MARISA_HAT); //Done
		// ** Minamitsu Murasa ** /
		addItem(ModItems.GHOST_DIPPER); //Done
		// ** Momiji Inubashiri ** /
		addItem(ModItems.MOMIJIS_SCIMITAR_SWORD); //Done
		addItem(ModItems.MAPLE_LEAF_SHIELD); //Done
		// ** Mononobe no Futo ** /
		// ** Mokou ** /
		// ** Mystia Lorelei ** /
		addItem(ModItems.GRILLED_LAMPREY); //Done
		// ** Nue Houjuu ** /
		addItem(ModItems.NUE_TRIDENT); //Done
		addItem(ModItems.UFOS);
		// ** Nazrin ** /
		addItem(ModItems.NAZRIN_PENDULUM); //Done
		addItem(ModItems.NAZRIN_STICK_ITEM); //Done
		// ** Nitori Kawashiro ** /
		addItem(ModItems.CATTAIL_PLANT); //Done
		addItem(ModItems.KAPPA_HAT); //Done
		// ** Suwako Moriya ** /
		addItem(ModItems.SUWAKO_HAT); //Done
		// ** Raiko Horikawa ** /
		// ** Ran Yakumo ** /
		// ** Reimu Hakurei ** /
		addItem(ModItems.HAKUREI_GOHEI); //Done
		// ** Sanae Kochiya ** /
		addItem(ModItems.SANAE_GOHEI); //TODO: Make it cause miracles somehow
		// ** Seija Kijin ** /
		addItem(ModItems.BLOOD_ORB); //Done
		addItem(ModItems.FOLDING_UMBRELLA); //Done
		addItem(ModItems.SUBSTITUTE_JIZO); //Done
		addItem(ModItems.NIMBLE_FABRIC); //Done
		addItem(ModItems.FAKE_MIRACLE_MALLET); //Done
		addItem(ModItems.GHASTLY_SEND_OFF_LANTERN); //Done
		addItem(ModItems.CURSED_DECOY_DOLL); //Done
		// ** Reisen ** /
		// ** Reisen Udongein Inaba ** /
		// ** Remilia Scarlet ** /
		// ** Sakuya Izayoi ** /
		addItem(ModItems.STOP_WATCH); //TODO: Throwable entities get buggy the first 5 seconds under the time stop effect.
		// ** Rin Kaenbyou ** /
		addItem(ModItems.SKULL); //TODO: Make it spawn homing skulls that only attack mobs that target the player
		// ** Sariel ** /
		addItem(ModItems.SARIEL_WAND); //Done
		// ** Rinnosuke Morichika ** /
		addItem(ModItems.AMENONUHOKO); //Done
		addItem(ModItems.SWORD_OF_KUSANAGI); //Done
		// ** Rumia **/
		addItem(ModItems.RUMIA_SWORD); //Done
		// ** Seiga Kaku ** /
		addItem(ModItems.WALL_PASSING_CHISEL); //Done
		// ** Seiran ** /
		addItem(ModItems.MOCHI_HAMMER); //Done
		// ** Shikieiki Yamaxanadu ** /
		addItem(ModItems.ROD_REMORSE); //Done
		// ** Shinki ** /
		addItem(ModItems.SHINKI_WAND); //TODO: Come up with something to do with it
		// ** Shinmyoumaru Sukuna ** /
		addItem(ModItems.SHINMYOUMARU_HAT); //Done
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.MIRACLE_MALLET); //Done
		addItem(ModItems.SHINMYOUMARU_NEEDLE); //TODO: Shoot "Fishing Rod"
		// ** Shou Toramaru ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.SHOU_LAMP); //Done
		// ** Suika Ibuki ** /
		addItem(ModItems.IBUKI_GOURD); //Done
		// ** Yuugi Hoshiguma ** /
		addItem(ModItems.YUUGI_SAKE); //Done
		// ** Sumireko Usami ** /
		// ** Tenshi Hinanawi ** /
		if (GrimoireOfAlice.danmakuCoreInstalled)
			addItem(ModItems.HISOU); //Done
		addItem(ModItems.HEAVENLY_PEACH); //Done
		// ** Toyosatomimi no Miko ** /
		addItem(ModItems.MIKO_CLOAK); //Done
		addItem(ModItems.SACRED_TOYOSATOMIMI); //Done
		addItem(ModItems.SHICHI_SEIKEN); //Done
		addItem(ModItems.MIKO_STICK); //Done
		addItem(ModItems.TOYOSATOMIMI_HAT); //Done
		// ** Unzan ** /
		// ** Utsuho Reiuji ** /
		addItem(ModItems.UTSUHO_WINGS); //Done
		addItem(ModItems.NUCLEAR_BOOTS); //Done
		addItem(ModItems.NUCLEAR_ROD); //Done
		// ** Yatsuhashi Tsukumo ** /
		// ** Youmu Konpaku ** /
		addItem(ModItems.ROUKANKEN); //Done
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