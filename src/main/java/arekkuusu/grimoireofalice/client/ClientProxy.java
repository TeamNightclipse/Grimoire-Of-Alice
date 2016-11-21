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
import arekkuusu.grimoireofalice.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.client.render.RenderBarrier;
import arekkuusu.grimoireofalice.client.render.RenderCameraSquare;
import arekkuusu.grimoireofalice.client.render.RenderCursedDecoyDoll;
import arekkuusu.grimoireofalice.client.render.RenderDragonJewel;
import arekkuusu.grimoireofalice.client.render.RenderEllyScytheProyectile;
import arekkuusu.grimoireofalice.client.render.RenderGrimoireSpell;
import arekkuusu.grimoireofalice.client.render.RenderHakureiOrb;
import arekkuusu.grimoireofalice.client.render.RenderMagicCircle;
import arekkuusu.grimoireofalice.client.render.RenderNazrinPendulum;
import arekkuusu.grimoireofalice.client.render.RenderNeedle;
import arekkuusu.grimoireofalice.client.render.RenderStopWatch;
import arekkuusu.grimoireofalice.client.render.RenderUnzanFist;
import arekkuusu.grimoireofalice.client.render.tile.TileCraftingAltarRenderer;
import arekkuusu.grimoireofalice.client.render.tile.TilePillarAltarRenderer;
import arekkuusu.grimoireofalice.entity.EntityBarrier;
import arekkuusu.grimoireofalice.entity.EntityCameraSquare;
import arekkuusu.grimoireofalice.entity.EntityCursedDecoyDoll;
import arekkuusu.grimoireofalice.entity.EntityDragonJewel;
import arekkuusu.grimoireofalice.entity.EntityEllyScythe;
import arekkuusu.grimoireofalice.entity.EntityGrimoireSpell;
import arekkuusu.grimoireofalice.entity.EntityHakureiOrb;
import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.entity.EntityNazrinPendulum;
import arekkuusu.grimoireofalice.entity.EntityNeedle;
import arekkuusu.grimoireofalice.entity.EntityStopWatch;
import arekkuusu.grimoireofalice.entity.EntityUnzanFist;
import arekkuusu.grimoireofalice.event.MalletEvent;
import arekkuusu.grimoireofalice.handler.GuiHandler;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		initRenderers();
		MinecraftForge.EVENT_BUS.register(new MalletEvent());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(GrimoireOfAlice.instance, new GuiHandler());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		//Armor
		registerItem(ModItems.MAPLE_LEAF_SHIELD, 0);
		registerItem(ModItems.FOX_MASK, 0);
		registerItem(ModItems.FUKU_NO_KAMI_MASK, 0);
		registerItem(ModItems.HANNYA_MASK, 0);
		registerItem(ModItems.HYOTTOKO_MASK, 0);
		registerItem(ModItems.KOKOROS_MASKS, 0);
		registerItem(ModItems.KOOMOTE_MASK, 0);
		registerItem(ModItems.MASK_OF_HOPE, 0);
		registerItem(ModItems.MONKEY_MASK, 0);
		registerItem(ModItems.RAIDEN_MASK, 0);
		registerItem(ModItems.UBA_MASK, 0);
		registerItem(ModItems.BYAKUREN_AURA, 0);
		registerItem(ModItems.MOKOU_AURA, 0);
		registerItem(ModItems.TOYOSATOMIMI_AURA, 0);
		registerItem(ModItems.KANAKO_AURA, 0);
		registerItem(ModItems.ICHIRIN_AURA, 0);
		registerItem(ModItems.SUWAKO_HAT, 0);
		registerItem(ModItems.FIRE_ROBE, 0);
		registerItem(ModItems.UTSUHO_AURA, 0);
		registerItem(ModItems.KAPPA_HAT, 0);
		registerItem(ModItems.MARISA_HAT, 0);
		registerItem(ModItems.MIKO_CAPE, 0);

		//Items
		registerItem(ModItems.TAMAHAGANE_STEEL, 0);
		registerItem(ModItems.HIHIIROKANE, 0);
		registerItem(ModItems.SHIMENAWA_ROPE, 0);
		registerItem(ModItems.TIME_ORB, 0);
		registerItem(ModItems.UFOS, 0);
		registerItem(ModItems.MASK, 0);
		registerItem(ModItems.GRIMOIRE_BOOK, 0);
		registerItem(ModItems.YOUKAI_BOOK, 0);
		registerItem(ModItems.VOLATILE_STRING, 0);
		registerItem(ModItems.SOLDIFIED_PAPER, 0);
		registerItem(ModItems.IMPURE_ROCK, 0);
		registerItem(ModItems.IBARAKI_BOX_EMPTY, 0);
		registerItem(ModItems.FULL_POWER, 0);
		registerItem(ModItems.STAR, 0);
		registerItem(ModItems.CHERRY, 0);
		registerItem(ModItems.FAITH, 0);
		registerItem(ModItems.UFO_BLUE, 0);
		registerItem(ModItems.UFO_GREEN, 0);
		registerItem(ModItems.UFO_RED, 0);
		registerItem(ModItems.LEAF, 0);
		registerItem(ModItems.PATCHY_BOOK, 0);
		registerItem(ModItems.SKULL, 0);
		registerItem(ModItems.NAZRIN_PENDULUM, 0);
		registerItem(ModItems.WALL_PASSING_CHISEL, 0);
		registerItem(ModItems.MIRACLE_MALLET, 0);
		registerItem(ModItems.ROD_REMORSE, 0);
		registerItem(ModItems.BUDAH_BOUL, 0);
		registerItem(ModItems.DRAGON_JEWEL, 0);
		registerItem(ModItems.BLOOD_ORB, 0);
		registerItem(ModItems.FOLDING_UMBRELLA, 0);
		registerItem(ModItems.SUBSTITUTE_JIZO, 0);
		registerItem(ModItems.NIMBLE_FABRIC, 0);
		registerItem(ModItems.FAKE_MIRACLE_MALLET, 0);
		registerItem(ModItems.GHASTLY_SEND_OFF_LANTERN, 0);
		registerItem(ModItems.CURSED_DECOY_DOLL, 0);
		registerItem(ModItems.AYA_CAMERA, 0);
		registerItem(ModItems.HATATE_CAMERA, 0);
		registerItem(ModItems.STOP_WATCH, 0);
		registerItem(ModItems.HAKUREI_GOHEI, 0);
		registerItem(ModItems.SANAE_GOHEI, 0);
		registerItem(ModItems.MORTAR_AND_PESTLE, 0);

		registerItem(ModItems.GHOST_DIPPER, 0);
		registerItem(ModItems.THIRD_EYE, 0);

		//Food
		registerItemWithTypes(ModItems.SHROOM_POWDER, 17);
		registerItem(ModItems.GRILLED_LAMPREY, 0);
		registerItem(ModItems.IBARAKI_BOX_FILLED, 0);
		registerItem(ModItems.KAPPAS_NOSTRUM, 0);
		registerItem(ModItems.HEAVENLY_PEACH, 0);
		registerItem(ModItems.IBUKI_GOURD, 0);
		registerItem(ModItems.COWRIE_SHELL, 0);
		registerItem(ModItems.ORB_ELIXIR, 0);
		registerItem(ModItems.HOURAI_ELIXIR, 0);

		//Weapons
		registerItem(ModItems.AMENONUHOKO, 0);
		registerItem(ModItems.CREST_OF_YGGDRASILL, 0);
		registerItem(ModItems.MIKO_STICK, 0);
		registerItem(ModItems.MOCHI_HAMMER, 0);
		registerItem(ModItems.MOMIJIS_SCIMITAR_SWORD, 0);
		registerItem(ModItems.NAZRIN_STICK_ITEM, 0);
		registerItem(ModItems.NUE_TRIDENT, 0);
		registerItem(ModItems.SWORD_OF_KUSANAGI, 0);
		registerItem(ModItems.SYRINGE, 0);
		registerItem(ModItems.ICHIRIN_RING, 0);
		registerItem(ModItems.KANAKO_ONBASHIRA, 0);
		registerItem(ModItems.SHICHI_SEIKEN, 0);
		registerItem(ModItems.CATTAIL_PLANT, 0);
		registerItem(ModItems.POPSICLE_STICK, 0);
		registerItem(ModItems.RUMIA_SWORD, 0);
		registerItem(ModItems.SARIEL_WAND, 0);
		registerItem(ModItems.WATERMELON_BLADE, 0);
		registerItem(ModItems.WATERMELON_SWORD, 0);
		registerItem(ModItems.SACRED_TOYOSATOMIMI, 0);
		registerItem(ModItems.NEEDLE, 0);
		registerItem(ModItems.DEATH_SCYTHE, 0);
		registerItem(ModItems.ROUKANKEN, 0);

		//Blocks
		registerBlock(ModBlocks.COMPACT_STONE, 0);
		registerBlock(ModBlocks.HOLY_KEY_STONE, 0);
		registerBlock(ModBlocks.HOLY_STONE, 0);
		registerBlock(ModBlocks.KYOUMARUBOTAN, 0);
		registerBlock(ModBlocks.ONBASHIRA, 0);
		registerBlock(ModBlocks.PAPER_BLOCK, 0);
		registerBlock(ModBlocks.ROPE_BLOCK, 0);
		registerBlockWithColorTypes(ModBlocks.SHROOM, 16);
		registerBlock(ModBlocks.SUGAR_BLOCK, 0);
		registerBlock(ModBlocks.HYPER_CONCENTRATED_MAGIC, 0);
		registerBlock(ModBlocks.ALTAR, 0);
		registerBlock(ModBlocks.PILLAR_ALTAR, 0);
		registerBlock(ModBlocks.IMPURE_STONE, 0);

		if(GrimoireOfAlice.danmakuCoreInstalled) {
			registerItem(ModItems.LUNASA_VIOLIN, 0);
			registerItem(ModItems.LYRICA_PIANO, 0);
			registerItem(ModItems.MERLIN_TRUMPET, 0);
			registerItem(ModItems.TENGU_FAN, 0);
			registerItem(ModItems.SHOU_LAMP, 0);
			registerItem(ModItems.LAEVATEIN, 0);
			registerItem(ModItems.JEWELED_HOURAI, 0);
			registerItem(ModItems.POUCH, 0);
			registerItem(ModItems.ELLY_SCYTHE, 0);
			registerItem(ModItems.HISOU, 0);
		}
	}

	@SideOnly(Side.CLIENT)
	private void initRenderers() {
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicCircle.class, RenderMagicCircle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrimoireSpell.class, RenderGrimoireSpell::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNazrinPendulum.class, RenderNazrinPendulum::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNeedle.class, RenderNeedle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonJewel.class, RenderDragonJewel::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityUnzanFist.class, RenderUnzanFist::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityEllyScythe.class, RenderEllyScytheProyectile::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCursedDecoyDoll.class, RenderCursedDecoyDoll::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCameraSquare.class, RenderCameraSquare::new);
		if(GrimoireOfAlice.danmakuCoreInstalled)
			RenderingRegistry.registerEntityRenderingHandler(EntityStopWatch.class, RenderStopWatch::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHakureiOrb.class, RenderHakureiOrb::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBarrier.class, RenderBarrier::new);
		//Tiles
		ClientRegistry.bindTileEntitySpecialRenderer(TileCraftingAltar.class, new TileCraftingAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TilePillarAltar.class, new TilePillarAltarRenderer());
	}

	@SideOnly(Side.CLIENT)
	private static void registerItem(Item item, int damage) {
		ModelLoader.setCustomModelResourceLocation(item, damage, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerBlock(Block block, int meta) {
		Item iBlock = Item.getItemFromBlock(block);
		if(iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		ModelLoader.setCustomModelResourceLocation(iBlock, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerItemWithTypes(Item item, int damage) {
		for(int i = 0; i < damage; i++) {
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(LibMod.MODID + ":shroompowder_" + i, "inventory"));
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registerBlockWithColorTypes(Block block, int meta) {
		Item iBlock = Item.getItemFromBlock(block);
		if(iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		for(int i = 0; i < meta; i++) {
			ModelLoader.setCustomModelResourceLocation(iBlock, i, new ModelResourceLocation(LibMod.MODID + ":shroom_" + i, "inventory"));
		}
	}
}
