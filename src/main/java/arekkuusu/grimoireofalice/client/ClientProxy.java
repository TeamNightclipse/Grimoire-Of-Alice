/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import arekkuusu.grimoireofalice.client.fx.*;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.core.ISidedProxy;
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.common.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.client.render.*;
import arekkuusu.grimoireofalice.client.render.tile.TileCraftingAltarRenderer;
import arekkuusu.grimoireofalice.client.render.tile.TilePillarAltarRenderer;
import arekkuusu.grimoireofalice.common.entity.*;
import arekkuusu.grimoireofalice.client.handler.MalletClientEvent;
import arekkuusu.grimoireofalice.client.handler.TextureStitcher;
import arekkuusu.grimoireofalice.common.core.handler.GuiHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy implements ISidedProxy {

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		initRenderers();
		MinecraftForge.EVENT_BUS.register(new TextureStitcher());
		MinecraftForge.EVENT_BUS.register(new MalletClientEvent());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(GrimoireOfAlice.instance, new GuiHandler());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerItemModels(ModelRegistryEvent event) {
		//Armor
		registerItem(ModItems.MAPLE_LEAF_SHIELD);
		registerItem(ModItems.FOX_MASK);
		registerItem(ModItems.FUKU_NO_KAMI_MASK);
		registerItem(ModItems.HANNYA_MASK);
		registerItem(ModItems.HYOTTOKO_MASK);
		registerItem(ModItems.KOKOROS_MASKS);
		registerItem(ModItems.KOOMOTE_MASK);
		registerItem(ModItems.MASK_OF_HOPE);
		registerItem(ModItems.MONKEY_MASK);
		registerItem(ModItems.RAIDEN_MASK);
		registerItem(ModItems.UBA_MASK);
		registerItem(ModItems.TOYOSATOMIMI_HAT);
		registerItem(ModItems.KANAKO_SHIMENAWA);
		registerItem(ModItems.ICHIRIN_UNZAN);
		registerItem(ModItems.SUWAKO_HAT);
		registerItem(ModItems.FIRE_ROBE);
		registerItem(ModItems.UTSUHO_WINGS);
		registerItem(ModItems.KAPPA_HAT);
		registerItem(ModItems.MARISA_HAT);
		registerItem(ModItems.MIKO_CLOAK);
		registerItem(ModItems.SHINMYOUMARU_HAT);
		registerItem(ModItems.NUCLEAR_BOOTS);
		registerItem(ModItems.THIRD_EYE);
		registerItem(ModItems.TENSHI_HAT);

		//Items
		registerItem(ModItems.TAMAHAGANE_STEEL);
		registerItem(ModItems.HIHIIROKANE);
		registerItem(ModItems.SHIMENAWA_ROPE);
		registerItem(ModItems.TIME_ORB);
		registerItem(ModItems.MASK);
		registerItem(ModItems.GRIMOIRE_BOOK);
		registerItem(ModItems.YOUKAI_BOOK);
		registerItem(ModItems.PASTE);
		registerItem(ModItems.SOLDIFIED_PAPER);
		registerItem(ModItems.IMPURE_ROCK);
		registerItem(ModItems.IBARAKI_BOX_EMPTY);
		registerItem(ModItems.FULL_POWER);
		registerItem(ModItems.STAR);
		registerItem(ModItems.CHERRY);
		registerItem(ModItems.FAITH);
		registerItem(ModItems.UFO_BLUE);
		registerItem(ModItems.UFO_GREEN);
		registerItem(ModItems.UFO_RED);
		registerItem(ModItems.UFOS);
		registerItem(ModItems.PATCHY_BOOK);
		registerItem(ModItems.SKULL);
		registerItem(ModItems.NAZRIN_PENDULUM);
		registerItem(ModItems.WALL_PASSING_CHISEL);
		registerItem(ModItems.ROD_REMORSE);
		registerItem(ModItems.BUDAH_BOUL);
		registerItem(ModItems.DRAGON_JEWEL);
		registerItem(ModItems.BLOOD_ORB);
		registerItem(ModItems.FOLDING_UMBRELLA);
		registerItem(ModItems.SUBSTITUTE_JIZO);
		registerItem(ModItems.NIMBLE_FABRIC);
		registerItem(ModItems.FAKE_MIRACLE_MALLET);
		registerItem(ModItems.GHASTLY_SEND_OFF_LANTERN);
		registerItem(ModItems.CURSED_DECOY_DOLL);
		registerItem(ModItems.HAKUREI_GOHEI);
		registerItem(ModItems.SANAE_GOHEI);
		registerItem(ModItems.MORTAR_AND_PESTLE);
		registerItem(ModItems.BLACK_FEATHER);
		registerItem(ModItems.NETHER_SHARD);
		registerItem(ModItems.IRON_NUGGET);
		registerItem(ModItems.HARDENED_LEATHER);
		registerItem(ModItems.SWALLOW_EGG);
		registerItem(ModItems.COWRIE_SHELL);
		registerItem(ModItems.DRAGON_SCALE);
		registerItem(ModItems.CHARM_OF_HEALING);
		registerItem(ModItems.SPIRITUAL_STRIKE_TALISMAN);
		registerItem(ModItems.GAP);
		registerItem(ModItems.SEAMLESS_CEILING_KINKAKU_JI);
		registerItem(ModItems.GHOST_DIPPER);

		//Food
		registerItemWithTypes(ModItems.SHROOM_POWDER);
		registerItem(ModItems.GRILLED_LAMPREY);
		registerItem(ModItems.IBARAKI_BOX_FILLED);
		registerItem(ModItems.KAPPAS_NOSTRUM);
		registerItem(ModItems.HEAVENLY_PEACH);
		registerItem(ModItems.IBUKI_GOURD);
		registerItem(ModItems.SWALLOW_COWRIE_SHELL);
		registerItem(ModItems.ORB_ELIXIR);
		registerItem(ModItems.HOURAI_ELIXIR);
		registerItem(ModItems.YUUGI_SAKE);

		//Weapons
		registerItem(ModItems.AMENONUHOKO);
		registerItem(ModItems.MIKO_STICK);
		registerItem(ModItems.MOCHI_HAMMER);
		registerItem(ModItems.MOMIJIS_SCIMITAR_SWORD);
		registerItem(ModItems.NAZRIN_STICK_ITEM);
		registerItem(ModItems.NUE_TRIDENT);
		registerItem(ModItems.SWORD_OF_KUSANAGI);
		registerItem(ModItems.SYRINGE);
		registerItem(ModItems.ICHIRIN_RING);
		registerItem(ModItems.KANAKO_ONBASHIRA);
		registerItem(ModItems.SHICHI_SEIKEN);
		registerItem(ModItems.CATTAIL_PLANT);
		registerItem(ModItems.POPSICLE_STICK);
		registerItem(ModItems.RUMIA_SWORD);
		registerItem(ModItems.SARIEL_WAND);
		registerItem(ModItems.WATERMELON_BLADE);
		registerItem(ModItems.WATERMELON_SWORD);
		registerItem(ModItems.SACRED_TOYOSATOMIMI);
		registerItem(ModItems.SHINMYOUMARU_NEEDLE);
		registerItem(ModItems.DEATH_SCYTHE);
		registerItem(ModItems.ROUKANKEN);
		registerItem(ModItems.ICICLE_SWORD);
		registerItem(ModItems.GHOST_ANCHOR);

		//Blocks
		registerBlock(ModBlocks.COMPACT_STONE);
		registerBlock(ModBlocks.HOLY_KEY_STONE);
		registerBlock(ModBlocks.HOLY_STONE);
		registerBlock(ModBlocks.KYOUMARUBOTAN);
		registerBlock(ModBlocks.ONBASHIRA);
		registerBlock(ModBlocks.PAPER_BLOCK);
		registerBlock(ModBlocks.ROPE_BLOCK);
		registerShroom(ModBlocks.SHROOM);
		registerBlock(ModBlocks.SUGAR_BLOCK);
		registerBlock(ModBlocks.HYPER_CONCENTRATED_MAGIC);
		registerBlock(ModBlocks.ALTAR);
		registerBlock(ModBlocks.PILLAR_ALTAR);
		registerBlock(ModBlocks.IMPURE_STONE);
		registerBlock(ModBlocks.HIHIIROKANE_BLOCK);
		registerBlock(ModBlocks.DRAGON_STONE);

		if(GrimoireOfAlice.danmakuCoreInstalled) {
			registerItem(ModItems.LEAF);
			registerItem(ModItems.MIRACLE_MALLET);
			registerItem(ModItems.STOP_WATCH);
			registerItem(ModItems.LUNASA_VIOLIN);
			registerItem(ModItems.LYRICA_PIANO);
			registerItem(ModItems.MERLIN_TRUMPET);
			registerItem(ModItems.TENGU_FAN);
			registerItem(ModItems.SHOU_LAMP);
			registerItem(ModItems.LAEVATEIN);
			registerItem(ModItems.JEWELED_HOURAI);
			registerItem(ModItems.POUCH);
			registerItem(ModItems.ELLY_SCYTHE);
			registerItem(ModItems.HISOU);
			registerItem(ModItems.NUCLEAR_ROD);
			registerItem(ModItems.AYA_CAMERA);
			registerItem(ModItems.HATATE_CAMERA);
			registerItem(ModItems.HAKUROUKEN);
			registerItem(ModItems.RED_STONE_OF_AJA);
		}
	}

	@SideOnly(Side.CLIENT)
	private void initRenderers() {
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicCircle.class, RenderMagicCircle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrimoireSpell.class, RenderGrimoireSpell::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNazrinPendulum.class, RenderNazrinPendulum::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonJewel.class, RenderDragonJewel::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityUnzanFist.class, RenderUnzanFist::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityEllyScythe.class, RenderEllyScytheProyectile::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCursedDecoyDoll.class, RenderCursedDecoyDoll::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHakureiOrb.class, RenderHakureiOrb::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBarrier.class, RenderBarrier::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMiracleLantern.class, RenderMiracleLantern::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherSoul.class, RenderNetherSoul::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFierySword.class, RenderFierySword::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityIceBlock.class, RenderIceBlock::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMiracleCircle.class, RenderMiracleCircle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritualStrikeTalisman.class, RenderSpiritualStrikeTalisman::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGap.class, RenderGap::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityKinkakuJiCeiling.class, RenderKinkakuJiCeiling::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityYoukaiBook.class, RenderYoukaiBook::new);
		if(GrimoireOfAlice.danmakuCoreInstalled) {
			RenderingRegistry.registerEntityRenderingHandler(EntityStopWatch.class, RenderStopWatch::new);
			RenderingRegistry.registerEntityRenderingHandler(EntityCameraSquare.class, RenderCameraSquare::new);			
		}
		//Tiles
		ClientRegistry.bindTileEntitySpecialRenderer(TileCraftingAltar.class, new TileCraftingAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TilePillarAltar.class, new TilePillarAltarRenderer());
	}

	@SideOnly(Side.CLIENT)
	private static void registerItem(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerBlock(Block block) {
		Item iBlock = Item.getItemFromBlock(block);
		if(iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		ModelLoader.setCustomModelResourceLocation(iBlock, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void registerItemWithTypes(Item item) {
		for(int i = 0; i < 16; i++) {
			ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(LibMod.MODID + ":shroompowder_" + i, "inventory"));
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registerShroom(Block block) {
		Item iBlock = Item.getItemFromBlock(block);
		if(iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		for(int i = 0; i < 16; i++) {
			ModelLoader.setCustomModelResourceLocation(iBlock, i, new ModelResourceLocation(LibMod.MODID + ":shroom_" + i, "inventory"));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void sparkleFX(ParticleFX particleFX, @Nullable Entity entity, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		if (!doParticle()) return;
		Particle particle;
		switch (particleFX) {
			case SHINMYOUMARU_SPARKLE:
				particle = new ShinmyoumaruSpark(Minecraft.getMinecraft().world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				break;
			case RED_MIST:
				if (entity == null) return;
				particle = new RedMist(Minecraft.getMinecraft().world, entity, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				break;
			case NEEDLE_SWING:
				particle = new NeedleSwing(Minecraft.getMinecraft().renderEngine, Minecraft.getMinecraft().world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn);
				break;
			case RED_GAS:
				particle = new RedGas(Minecraft.getMinecraft().world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				break;
			case NETHER_FIRE:
				particle = new NetherFire(Minecraft.getMinecraft().world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				break;
			default:
				particle = new Particle(Minecraft.getMinecraft().world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void displayRecordText(ITextComponent text) {
		Minecraft.getMinecraft().ingameGUI.setRecordPlaying(text.getFormattedText(), false);
	}

	@SideOnly(Side.CLIENT)
	private boolean doParticle() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
			return false;

		float chance = 1F;
		if (Minecraft.getMinecraft().gameSettings.particleSetting == 1)
			chance = 0.6F;
		else if (Minecraft.getMinecraft().gameSettings.particleSetting == 2)
			chance = 0.2F;

		return chance == 1F || Math.random() < chance;
	}
}
