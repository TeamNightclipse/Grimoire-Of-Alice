/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import java.util.List;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibMod;
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
		return new ItemStack(ModItems.GRIMOIRE_BOOK);
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
		addBlock(ModBlocks.HOLY_STONE);
		addBlock(ModBlocks.KYOUMARUBOTAN);
		addBlock(ModBlocks.ONBASHIRA);
		addBlock(ModBlocks.ALTAR);
		addBlock(ModBlocks.PILLAR_ALTAR);
		addBlock(ModBlocks.PAPER_BLOCK);
		addBlock(ModBlocks.ROPE_BLOCK);
		addBlock(ModBlocks.SUGAR_BLOCK);
		addBlock(ModBlocks.HYPER_CONCENTRATED_MAGIC);
		addBlock(ModBlocks.SHROOM);
		addItem(ModItems.SHROOM_SLICE);

		//Item
		addItem(ModItems.THIRD_EYE);
		addItem(ModItems.GLORIOUS_NIPPON_STEEL);
		addItem(ModItems.HIHIIROKANE);
		addItem(ModItems.SHIMENAWA_ROPE);
		addItem(ModItems.TIME_ORB);
		addItem(ModItems.UFOS);
		addItem(ModItems.MASK);
		addItem(ModItems.GRIMOIRE_BOOK);
		addItem(ModItems.YOUKAI_BOOK);
		addItem(ModItems.VOLATILE_STRING);
		addItem(ModItems.SOLDIFIED_PAPER);
		addItem(ModItems.IMPURE_ROCK);
		addItem(ModItems.FULL_POWER);
		addItem(ModItems.STAR);
		addItem(ModItems.CHERRY);
		addItem(ModItems.FAITH);
		addItem(ModItems.UFO_BLUE);
		addItem(ModItems.UFO_GREEN);
		addItem(ModItems.UFO_RED);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.LEAF);
		}
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.LUNASA_VIOLIN);
			addItem(ModItems.LYRICA_PIANO);
			addItem(ModItems.MERLIN_TRUMPET);
			addItem(ModItems.SHOU_LAMP);
		}
		addItem(ModItems.PATCHY_BOOK);
		addItem(ModItems.SKULL);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.POUCH);
		}
		addItem(ModItems.TENGU_FAN);
		addItem(ModItems.HAKUREI_GOHEI);
		addItem(ModItems.SANAE_GOHEI);
        addItem(ModItems.NAZRIN_PENDULUM);
		addItem(ModItems.GHOST_DIPPER);
		addItem(ModItems.WALL_PASSING_CHISEL);
		addItem(ModItems.MIRACLE_MALLET);
		addItem(ModItems.ROD_REMORSE);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.JEWELED_HOURAI);
		}
		addItem(ModItems.BUDAH_BOUL);
		addItem(ModItems.DRAGON_JEWEL);
		addItem(ModItems.BLOOD_ORB);
		addItem(ModItems.FOLDING_UMBRELLA);
		addItem(ModItems.SUBSTITUTE_JIZO);
		addItem(ModItems.NIMBLE_FABRIC);
		addItem(ModItems.FAKE_MIRACLE_MALLET);
		addItem(ModItems.GHASTLY_SEND_OFF_LANTERN);
		addItem(ModItems.CURSED_DECOY_DOLL);
		addItem(ModItems.AYA_CAMERA);
		addItem(ModItems.HATATE_CAMERA);
		addItem(ModItems.STOP_WATCH);
		
		//Food
		addItem(ModItems.GRILLED_LAMPREY);
		addItem(ModItems.IBARAKI_BOX_EMPTY);
		addItem(ModItems.IBARAKI_BOX_FILLED);
		addItem(ModItems.KAPPAS_NOSTRUM);
		addItem(ModItems.HEAVENLY_PEACH);
		addItem(ModItems.IBUKI_GOURD);
		addItem(ModItems.COWRIE_SHELL);
		addItem(ModItems.ORB_ELIXIR);
		addItem(ModItems.HOURAI_ELIXIR);
		
		//Armor
		addItem(ModItems.MAPLE_LEAF_SHIELD);
		addItem(ModItems.FOX_MASK);
		addItem(ModItems.FUKU_NO_KAMI_MASK);
		addItem(ModItems.HANNYA_MASK);
		addItem(ModItems.HYOTTOKO_MASK);
		addItem(ModItems.KOKOROS_MASKS);
		addItem(ModItems.KOOMOTE_MASK);
		addItem(ModItems.MASK_OF_HOPE);
		addItem(ModItems.MONKEY_MASK);
		addItem(ModItems.RAIDEN_MASK);
		addItem(ModItems.UBA_MASK);
		addItem(ModItems.BYAKUREN_AURA);
		addItem(ModItems.MOKOU_AURA);
		addItem(ModItems.TOYOSATOMIMI_AURA);
		addItem(ModItems.UTSUHO_AURA);
		addItem(ModItems.KANAKO_AURA);
		addItem(ModItems.ICHIRIN_AURA);
        addItem(ModItems.SUWAKO_HAT);
		addItem(ModItems.FIRE_ROBE);
		addItem(ModItems.KAPPA_HAT);
		addItem(ModItems.MARISA_HAT);
		addItem(ModItems.MIKO_CAPE);
		
		//Weapons
		addItem(ModItems.AMENONUHOKO);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.LAEVATEIN);
		}
		addItem(ModItems.MIKO_STICK);
		addItem(ModItems.MOCHI_HAMMER);
		addItem(ModItems.MOMIJIS_SCIMITAR_SWORD);
		addItem(ModItems.NAZRIN_STICK);
		addItem(ModItems.NAZRIN_STICK_B);
		addItem(ModItems.NUE_TRIDENT);
		addItem(ModItems.SWORD_OF_KUSANAGI);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.ELLY_SCYTHE);
		}
		addItem(ModItems.SYRINGE);
		addItem(ModItems.ICHIRIN_RING);
		addItem(ModItems.KANAKO_ONBASHIRA);
		addItem(ModItems.SHICHI_SEIKEN);
		addItem(ModItems.CATTAIL_PLANT);
		addItem(ModItems.POPSICLE_STICK);
		addItem(ModItems.RUMIA_SWORD);
		addItem(ModItems.SARIEL_WAND);
		addItem(ModItems.WATERMELON_BLADE);
		addItem(ModItems.WATERMELON_SWORD);
		addItem(ModItems.SACRED_TOYOSATOMIMI);
		addItem(ModItems.NEEDLE);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			addItem(ModItems.HISOU);
		}
		addItem(ModItems.DEATH_SCYTHE);
		addItem(ModItems.ROUKANKEN);
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