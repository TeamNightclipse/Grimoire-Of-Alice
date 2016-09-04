/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import java.util.Locale;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import arekkuusu.grimoireofalice.CommonProxy;
import arekkuusu.grimoireofalice.item.ModItems;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		initRenderers();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	private void initRenderers() {
		//Masks
		registerItem(ModItems.foxMask, 0);
		registerItem(ModItems.fukuNoKamiMask, 0);
		registerItem(ModItems.hannyaMask, 0);
		registerItem(ModItems.hyottokoMask, 0);
		registerItem(ModItems.kokorosMasks, 0);
		registerItem(ModItems.koomoteMask, 0);
		registerItem(ModItems.maskOfHope, 0);
		registerItem(ModItems.monkeyMask, 0);
		registerItem(ModItems.raidenMask, 0);
		registerItem(ModItems.ubaMask, 0);
		//Items
		registerItem(ModItems.uFOs, 0);
		registerItem(ModItems.shimenawaRope, 0);
		//Weapons
		registerItem(ModItems.crestOfYggdrasill, 0);
		registerItem(ModItems.mochiHammer, 0);
		registerItem(ModItems.momijisScimitarSword, 0);
		registerItem(ModItems.primordialShield, 0);
		registerItem(ModItems.mapleLeafShield, 0);
		//Blocks
		registerBlock(ModBlocks.compactStone, 0);
		registerBlock(ModBlocks.holyKeyStone, 0);
	}
	
	private void registerItem(Item item, int damage) {
		ModelLoader.setCustomModelResourceLocation(item, damage, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private void registerBlock(Block block, int meta) {
		Item iBlock = Item.getItemFromBlock(block);
		if(iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		ModelLoader.setCustomModelResourceLocation(iBlock, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
}
