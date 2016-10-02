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
		return new ItemStack(ModItems.grimoireBook);
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
		addBlock(ModBlocks.compactStone);
		addBlock(ModBlocks.holyStone);
		addBlock(ModBlocks.kyoumarubotan);
		addBlock(ModBlocks.onbashira);
		addBlock(ModBlocks.paperBlock);
		addBlock(ModBlocks.ropeBlock);
		addBlock(ModBlocks.sugarBlock);
		addBlock(ModBlocks.hyperconcentratedMagic);
		addBlock(ModBlocks.shroom);
		addItem(ModItems.shroomSlice); //Goes here for being related to a Block

		//Item
		addItem(ModItems.thirdEye);
		addItem(ModItems.gloriousNipponSteel);
		addItem(ModItems.hihiirokane);
		addItem(ModItems.shimenawaRope);
		addItem(ModItems.timeOrb);
		addItem(ModItems.uFOs);
		addItem(ModItems.mask);
		addItem(ModItems.grimoireBook);
		addItem(ModItems.youkaiBook);
		addItem(ModItems.volatileString);
		addItem(ModItems.soldifiedPaper);
		addItem(ModItems.impureRock);
		addItem(ModItems.fullPower);
		addItem(ModItems.star);
		addItem(ModItems.cherry);
		addItem(ModItems.faith);
		addItem(ModItems.uFOBlue);
		addItem(ModItems.uFOGreen);
		addItem(ModItems.uFORed);
		addItem(ModItems.leaf);
		addItem(ModItems.lunasaViolin);
		addItem(ModItems.lyricaPiano);
		addItem(ModItems.merlinTrumpet);
		addItem(ModItems.shouLamp);
		addItem(ModItems.patchyBook);
		addItem(ModItems.skull);
		addItem(ModItems.pouch);
		addItem(ModItems.windStick);
        addItem(ModItems.nazrinPendulum);
		addItem(ModItems.ghostDipper);
		addItem(ModItems.wallPassingchisel);
		addItem(ModItems.miracleMallet);
		addItem(ModItems.rodRemorse);
		addItem(ModItems.jeweledHourai);
		addItem(ModItems.budahBoul);
		addItem(ModItems.dragonJewel);
		addItem(ModItems.bloodOrb);
		addItem(ModItems.foldingUmbrella);
		
		//Food
		addItem(ModItems.grilledLamprey);
		addItem(ModItems.ibarakiBoxEmpty);
		addItem(ModItems.ibarakiBoxFilled);
		addItem(ModItems.kappasNostrum);
		addItem(ModItems.heavenlyPeach);
		addItem(ModItems.ibukiGourd);
		addItem(ModItems.cowrieShell);
		addItem(ModItems.orbElixir);
		addItem(ModItems.houraiElixir);
		
		//Armor
		addItem(ModItems.mapleLeafShield);
		addItem(ModItems.foxMask);
		addItem(ModItems.fukuNoKamiMask);
		addItem(ModItems.hannyaMask);
		addItem(ModItems.hyottokoMask);
		addItem(ModItems.kokorosMasks);
		addItem(ModItems.koomoteMask);
		addItem(ModItems.maskOfHope);
		addItem(ModItems.monkeyMask);
		addItem(ModItems.raidenMask);
		addItem(ModItems.ubaMask);
		addItem(ModItems.byakurenAura);
		addItem(ModItems.mokouAura);
		addItem(ModItems.toyosatomimiAura);
		addItem(ModItems.utsuhoAura);
		addItem(ModItems.kanakoAura);
		addItem(ModItems.ichirinAura);
        addItem(ModItems.suwakoHat);
		addItem(ModItems.fireRobe);
		
		//Weapons
		addItem(ModItems.amenonuhoko);
		addItem(ModItems.laevatein);
		addItem(ModItems.mikoStick);
		addItem(ModItems.mochiHammer);
		addItem(ModItems.momijisScimitarSword);
		addItem(ModItems.nazrinStick);
		addItem(ModItems.nazrinStickB);
		addItem(ModItems.nueTrident);
		addItem(ModItems.swordOfKusanagi);
		addItem(ModItems.ellyScythe);
		addItem(ModItems.syringe);
		addItem(ModItems.ichirinRing);
		addItem(ModItems.kanakoOnbashira);
		addItem(ModItems.shichiSeiken);
		addItem(ModItems.cattailPlant);
		addItem(ModItems.popsicleStick);
		addItem(ModItems.rumiaSword);
		addItem(ModItems.sarielWand);
		addItem(ModItems.watermelonBlade);
		addItem(ModItems.watermelonSword);
		addItem(ModItems.sacredToyosatomimi);
		addItem(ModItems.needle);
		addItem(ModItems.hisou);
		addItem(ModItems.deathScythe);
		addItem(ModItems.roukanken);
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