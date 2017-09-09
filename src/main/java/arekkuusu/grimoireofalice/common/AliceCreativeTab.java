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
		setNoTitle();
		setBackgroundImageName("item_search.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.miracle_mallet);
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		this.list = list;
		//Blocks
		addBlock(ModBlocks.compact_stone);
		addBlock(ModBlocks.impure_stone);
		addBlock(ModBlocks.hihiirokane_block);
		addBlock(ModBlocks.sugar_block);
		addBlock(ModBlocks.hyper_magic);
		addBlock(ModBlocks.onbashira);
		addBlock(ModBlocks.pillar_altar);
		addBlock(ModBlocks.holy_stone);
		addBlock(ModBlocks.holy_key_stone);
		addBlock(ModBlocks.crafting_altar);
		addBlock(ModBlocks.rope_block);
		addBlock(ModBlocks.paper_block);
		addBlock(ModBlocks.kyoumarubotan);
		addBlock(ModBlocks.shroom);
		addItem(ModItems.shroom_powder);
		addItem(ModItems.mortar_n_pestle);

		//Misc
		addItem(ModItems.hihiirokane);
		addItem(ModItems.impure_rock);
		addItem(ModItems.tamahagane_steel);
		addItem(ModItems.solidified_paper);
		addItem(ModItems.paste);
		addItem(ModItems.shimenawa_rope);
		addItem(ModItems.black_feather);
		addItem(ModItems.cowrie_shell);
		addItem(ModItems.swallow_egg);
		addItem(ModItems.dragon_scale);
		addBlock(ModBlocks.dragon_stone);
		addItem(ModItems.nether_shard);
		addItem(ModItems.hardened_leather);
		addItem(ModItems.iron_nugget);
		addItem(ModItems.mask);
		addItem(ModItems.ufo_blue);
		addItem(ModItems.ufo_green);
		addItem(ModItems.ufo_red);
		addItem(ModItems.time_orb);
		addItem(ModItems.full_power_item);
		addItem(ModItems.star_item);
		addItem(ModItems.cherry_item);
		addItem(ModItems.faith_item);
		addItem(ModItems.spellcard_pouch);

		//-------------Items - By Character-------------//

		//-------------Patchouli Knowledge-------------//
		addItem(ModItems.patchy_book);

		//-------------Alice Margatroid-------------//
		addItem(ModItems.grimoire_book);

		//-------------Kosuzu Motoori-------------//
		addItem(ModItems.youkai_book);

		//-------------Aya Shameimaru-------------//
		addItem(ModItems.tengu_fan);
		addItem(ModItems.aya_camera);

		//-------------Hatate Himekaidou-------------//
		addItem(ModItems.hatate_camera);

		//-------------Cirno-------------/
		addItem(ModItems.icicle_sword);
		addItem(ModItems.popsicle_stick);
		addItem(ModItems.watermelon_blade);
		addItem(ModItems.watermelon_sword);

		//-------------Elly-------------//
		addItem(ModItems.elly_scythe);

		//-------------Flandre Scarlet-------------//
		addItem(ModItems.laevatein);

		//-------------Hata no Kokoro-------------//
		addItem(ModItems.fox_mask);
		addItem(ModItems.fuku_no_kami_mask);
		addItem(ModItems.hannya_mask);
		addItem(ModItems.hyottoko_mask);
		addItem(ModItems.koomote_mask);
		addItem(ModItems.mask_of_hope);
		addItem(ModItems.monkey_mask);
		addItem(ModItems.raiden_mask);
		addItem(ModItems.uba_mask);
		addItem(ModItems.kokoro_masks);

		//-------------Ichirin Kumoi-------------//
		addItem(ModItems.ichirin_unzan);
		addItem(ModItems.ichirin_ring);

		//-------------Kaguya Houraisan-------------//
		addItem(ModItems.jeweled_hourai);
		addItem(ModItems.budah_bowl);
		addItem(ModItems.dragon_jewel);
		addItem(ModItems.fire_robe);
		addItem(ModItems.swallow_cowrie_shell);
		addItem(ModItems.seamless_ceiling_of_kinkakuji);
		addItem(ModItems.red_stone_of_aja);

		//-------------Kanako Yasaka-------------//
		addItem(ModItems.kanako_shimenawa);
		addItem(ModItems.kanako_onbashira);

		//-------------Kasen Ibara-------------//
		addItem(ModItems.ibaraki_box_empty);
		addItem(ModItems.ibaraki_box_filled);
		addItem(ModItems.kappas_nostrum);

		//-------------Eirin Yagokoro-------------//
		addItem(ModItems.hourai_elixir);
		addItem(ModItems.syringe);
		addItem(ModItems.orb_elixir);

		//-------------Koishi Komeiji -  Satori Komeiji-------------//
		addItem(ModItems.third_eye);

		//-------------Komachi Onozuka-------------//
		addItem(ModItems.komachi_scythe);

		//-------------Kyouko Kasodani-------------//
		//-------------Lunasa Prismriver - Merlin Prismriver - Lyrica Prismriver-------------//
		addItem(ModItems.lunasa_violin);
		addItem(ModItems.lyrica_piano);
		addItem(ModItems.merlin_trumpet);

		//-------------Mamizou Futatsuiwa-------------//
		addItem(ModItems.leaf_item);

		//-------------Marisa Kirisame-------------//
		addItem(ModItems.marisa_hat);

		//-------------Minamitsu Murasa-------------//
		addItem(ModItems.ghost_dipper);
		addItem(ModItems.ghost_anchor);

		//-------------Momiji Inubashiri-------------//
		addItem(ModItems.momiji_scimitar_sword);
		addItem(ModItems.maple_leaf_shield);

		//-------------Mystia Lorelei-------------//
		addItem(ModItems.grilled_lamprey);

		//-------------Nue Houjuu-------------//
		addItem(ModItems.nue_trident);
		addItem(ModItems.ufo);

		//-------------Nazrin-------------//
		addItem(ModItems.nazrin_pendulum);
		addItem(ModItems.nazrin_stick);

		//-------------Nitori Kawashiro-------------//
		addItem(ModItems.cattail_plant);
		addItem(ModItems.kappa_hat);

		//-------------Suwako Moriya-------------//
		addItem(ModItems.suwako_hat);

		//-------------Reimu Hakurei-------------//
		addItem(ModItems.hakurei_gohei);
		addItem(ModItems.spiritual_strike_talisman);

		//-------------Sanae Kochiya-------------//
		addItem(ModItems.sanae_gohei);
		addItem(ModItems.charm_of_healing);

		//-------------Seija Kijin-------------//
		addItem(ModItems.blood_thirsty_orb);
		addItem(ModItems.folding_umbrella);
		addItem(ModItems.substitute_jizo);
		addItem(ModItems.nimble_fabric);
		addItem(ModItems.fake_miracle_mallet);
		addItem(ModItems.send_off_lantern);
		addItem(ModItems.cursed_decoy_doll);

		//-------------Sakuya Izayoi-------------//
		addItem(ModItems.stopwatch);

		//-------------Rin Kaenbyou-------------//
		addItem(ModItems.orin_skull);

		//-------------Sariel-------------//
		addItem(ModItems.sariel_wand);

		//-------------Rinnosuke Morichika-------------//
		addItem(ModItems.amenonuhoko);
		addItem(ModItems.sword_of_kusanagi);

		//-------------Rumia-------------//
		addItem(ModItems.rumia_sword);

		//-------------Seiga Kaku-------------//
		addItem(ModItems.wall_passing_chisel);

		//-------------Seiran-------------//
		addItem(ModItems.mochi_hammer);

		//-------------Shikieiki Yamaxanadu-------------//
		addItem(ModItems.rod_of_remorse);

		//-------------Shinmyoumaru Sukuna-------------//
		addItem(ModItems.shinmyoumaru_hat);
		addItem(ModItems.miracle_mallet);
		addItem(ModItems.shinmyoumaru_needle);

		//-------------Shou Toramaru-------------//
		addItem(ModItems.shou_lamp);

		//-------------Suika Ibuki-------------//
		addItem(ModItems.ibuki_gourd);

		//-------------Yuugi Hoshiguma-------------//
		addItem(ModItems.yuugi_sake);

		//-------------Tenshi Hinanawi-------------//
		addItem(ModItems.hisou_sword);
		addItem(ModItems.tenshi_hat);
		addItem(ModItems.heavenly_peach);

		//-------------Toyosatomimi no Miko-------------//
		addItem(ModItems.miko_cloak);
		addItem(ModItems.sacred_sword_of_toyosatomimi);
		addItem(ModItems.shichi_seiken);
		addItem(ModItems.miko_stick);
		addItem(ModItems.toyosatomimi_hat);

		//-------------Utsuho Reiuji-------------//
		addItem(ModItems.utsuho_wings);
		addItem(ModItems.nuclear_boots);
		addItem(ModItems.nuclear_rod);

		//-------------Youmu Konpaku-------------//
		addItem(ModItems.roukanken);
		addItem(ModItems.hakurouken);

		//-------------Yukari Yakumo-------------//
		addItem(ModItems.gap);
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