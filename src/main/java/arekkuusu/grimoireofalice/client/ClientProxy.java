/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import arekkuusu.grimoireofalice.CommonProxy;
import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.client.render.*;
import arekkuusu.grimoireofalice.entity.*;
import arekkuusu.grimoireofalice.handler.GuiHandler;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		initRenderers();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(GrimoireOfAlice.instance, new GuiHandler());
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	private void initRenderers() {
		//Armor
		registerItem(ModItems.primordialShield, 0);
		registerItem(ModItems.mapleLeafShield, 0);
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
		registerItem(ModItems.byakurenAura, 0);
		registerItem(ModItems.mokouAura, 0);
		registerItem(ModItems.toyosatomimiAura, 0);
		registerItem(ModItems.kanakoAura, 0);
		registerItem(ModItems.ichirinAura, 0);
        registerItem(ModItems.suwakoHat, 0);
		
		//Items
		registerItem(ModItems.gloriousNipponSteel, 0);
		registerItem(ModItems.hihiirokane, 0);
		registerItem(ModItems.shimenawaRope, 0);
		registerItem(ModItems.timeOrb, 0);
		registerItem(ModItems.uFOs, 0);
		registerItem(ModItems.mask, 0);
		registerItem(ModItems.grimoireBook, 0);
		registerItem(ModItems.youkaiBook, 0);
		registerItem(ModItems.volatileString, 0);
		registerItem(ModItems.soldifiedPaper, 0);
		registerItem(ModItems.impureRock, 0);
		registerItem(ModItems.ibarakiBoxEmpty, 0);
		registerItem(ModItems.fullPower, 0);
		registerItem(ModItems.star, 0);
		registerItem(ModItems.cherry, 0);
		registerItem(ModItems.faith, 0);
		registerItem(ModItems.uFOBlue, 0);
		registerItem(ModItems.uFOGreen, 0);
		registerItem(ModItems.uFORed, 0);
		registerItem(ModItems.leaf, 0);
		registerItem(ModItems.shouLamp, 0);
		registerItem(ModItems.patchyBook, 0);
		registerItem(ModItems.skull, 0);
		registerItem(ModItems.pouch, 0);
        registerItem(ModItems.windStick, 0);
        registerItem(ModItems.nazrinPendulum, 0);
		
		registerItem(ModItems.thirdEye, 0);
		registerItem(ModItems.lunasaViolin, 0);
		registerItem(ModItems.lyricaPiano, 0);
		registerItem(ModItems.merlinTrumpet, 0);
		
		//Food
		registerItem(ModItems.shroomSlice, 0);
		registerItem(ModItems.shroomSlice, 1);
		registerItem(ModItems.grilledLamprey, 0);
		registerItem(ModItems.ibarakiBoxFilled, 0);
		registerItem(ModItems.kappasNostrum, 0);
		registerItem(ModItems.heavenlyPeach, 0);
		
		//Weapons
		registerItem(ModItems.amenonuhoko, 0);
		registerItem(ModItems.crestOfYggdrasill, 0);
		registerItem(ModItems.laevatein, 0);
		registerItem(ModItems.mikoStick, 0);
		registerItem(ModItems.mochiHammer, 0);
		registerItem(ModItems.momijisScimitarSword, 0);
		registerItem(ModItems.nazrinStick, 0);
		registerItem(ModItems.nazrinStickB, 0);
		registerItem(ModItems.nueTrident, 0);
		registerItem(ModItems.swordOfKusanagi, 0);
		registerItem(ModItems.ellyScythe, 0);
		registerItem(ModItems.syringe, 0);
		registerItem(ModItems.ichirinRing, 0);
		registerItem(ModItems.kanakoOnbashira, 0);
		registerItem(ModItems.shichiSeiken, 0);
		registerItem(ModItems.cattailPlant, 0);
		registerItem(ModItems.popsicleStick, 0);
		registerItem(ModItems.rumiaSword, 0);
		registerItem(ModItems.sarielWand, 0);
		registerItem(ModItems.watermelonBlade, 0);
		registerItem(ModItems.watermelonSword, 0);
		
		//Blocks
		registerBlock(ModBlocks.compactStone, 0);
		registerBlock(ModBlocks.holyKeyStone, 0);
		registerBlock(ModBlocks.holyStone, 0);
		registerBlock(ModBlocks.kyoumarubotan, 0);
		registerBlock(ModBlocks.onbashira, 0);
		registerBlock(ModBlocks.onbashiraMiddle, 0);
		registerBlock(ModBlocks.onbashiraTop, 0);
		registerBlock(ModBlocks.paperBlock, 0);
		registerBlock(ModBlocks.ropeBlock, 0);
		registerBlock(ModBlocks.shroom, 0);
		registerBlock(ModBlocks.sugarBlock, 0);
		registerBlock(ModBlocks.hyperconcentratedMagic, 0);
		
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicCircle.class, RenderMagicCircle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLeaf.class, RenderLeaf::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNote.class, RenderNote::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityWind.class, RenderWind::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNazrinPendulum.class, RenderNazrinPendulum::new);
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
