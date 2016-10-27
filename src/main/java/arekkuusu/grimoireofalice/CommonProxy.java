/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import arekkuusu.grimoireofalice.block.*;
import arekkuusu.grimoireofalice.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.entity.*;
import arekkuusu.grimoireofalice.event.CapabilitiesEvent;
import arekkuusu.grimoireofalice.event.YukkuriEvent;
import arekkuusu.grimoireofalice.handler.ConfigHandler;
import arekkuusu.grimoireofalice.handler.WorldGenPlants;
import arekkuusu.grimoireofalice.item.*;
import arekkuusu.grimoireofalice.item.auras.ItemAuraByakuren;
import arekkuusu.grimoireofalice.item.auras.ItemAuraIchirin;
import arekkuusu.grimoireofalice.item.auras.ItemAuraKanako;
import arekkuusu.grimoireofalice.item.auras.ItemAuraMokou;
import arekkuusu.grimoireofalice.item.auras.ItemAuraToyosatomimi;
import arekkuusu.grimoireofalice.item.auras.ItemWingsUtsuho;
import arekkuusu.grimoireofalice.item.crafting.SpecialRecipes;
import arekkuusu.grimoireofalice.item.crafting.VanillaCrafting;
import arekkuusu.grimoireofalice.plugin.danmakucore.item.*;
import arekkuusu.grimoireofalice.potion.PotionElixir;
import arekkuusu.grimoireofalice.item.food.ItemGrilledLamprey;
import arekkuusu.grimoireofalice.item.food.ItemHeavelyPeach;
import arekkuusu.grimoireofalice.item.food.ItemHouraiElixir;
import arekkuusu.grimoireofalice.item.food.ItemIbarakiBoxFilled;
import arekkuusu.grimoireofalice.item.food.ItemIbukiGourd;
import arekkuusu.grimoireofalice.item.food.ItemKappasNostrum;
import arekkuusu.grimoireofalice.item.food.ItemShroomSlice;
import arekkuusu.grimoireofalice.item.food.ItemUltramarineOrbElixir;
import arekkuusu.grimoireofalice.item.masks.ItemFoxMask;
import arekkuusu.grimoireofalice.item.masks.ItemFukuNoKamiMask;
import arekkuusu.grimoireofalice.item.masks.ItemHannyaMask;
import arekkuusu.grimoireofalice.item.masks.ItemHyottokoMask;
import arekkuusu.grimoireofalice.item.masks.ItemKokorosMasks;
import arekkuusu.grimoireofalice.item.masks.ItemKoomoteMask;
import arekkuusu.grimoireofalice.item.masks.ItemMaskOfHope;
import arekkuusu.grimoireofalice.item.masks.ItemMonkeyMask;
import arekkuusu.grimoireofalice.item.masks.ItemRaidenMask;
import arekkuusu.grimoireofalice.item.masks.ItemUbaMask;
import arekkuusu.grimoireofalice.lib.LibBlockName;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

	public static final ItemArmor.ArmorMaterial SOLID_PAPER = EnumHelper.addArmorMaterial("solidPaper", "No", 1000, new int[] {1, 2, 3, 4}, 30,
			SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0);
	public static final Item.ToolMaterial GOLDYRON = EnumHelper.addToolMaterial("goldyron", 3, 1000, 15.0F, 3F, 30);
	public static final Item.ToolMaterial WET_NOODLE = EnumHelper.addToolMaterial("wetNoodle", 3, 1000, 15.0F, 0F, 30);
	public static final Item.ToolMaterial NOT_A_MELEE_WEAPON = EnumHelper.addToolMaterial("weakMaterial", 3, 10, 15.0F, -2F, 30);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new Item3rdEye(),
				new ItemGloriousNipponSteel(),
				new ItemHihiirokane(),
				new ItemShimenawaRope(),
				new ItemTimeOrb(),
				new ItemUFOs(),
				new ItemMod(LibItemName.MASK),
				new ItemGrimoireBook(),
				new ItemYoukaiBook(),
				new ItemMod(LibItemName.VOLATILE_STRING),
				new ItemMod(LibItemName.SOLDIFIED_PAPER),
				new ItemMod(LibItemName.IMPURE_ROCK),
				new ItemIbarakiBoxEmpty(),
				new ItemMod(LibItemName.FULL_POWER),
				new ItemMod(LibItemName.STAR),
				new ItemMod(LibItemName.CHERRY),
				new ItemMod(LibItemName.FAITH),
				new ItemMod(LibItemName.UFO_RED),
				new ItemMod(LibItemName.UFO_BLUE),
				new ItemMod(LibItemName.UFO_GREEN),
				new ItemPatchyBook(),
				new ItemSkull(),
				new ItemNazrinPendulum(),
				new ItemGhostDipper(),
				new ItemWallPassingChisel(),
				new ItemMiracleMallet(),
				new ItemRodOfRemorse(),
				new ItemBudahBoul(Item.ToolMaterial.DIAMOND),
				new ItemDragonJewel(),
				new ItemBloodThirstyOrb(),
				new ItemGapFoldingUmbrella(),
				new ItemSubstituteJizo(),
				new ItemNimbleFabric(),
				new ItemFakeMiracleMallet(),
				new ItemGhastlySendOffLantern(),
				new ItemCursedDecoyDoll(),
				new ItemTenguCamera(LibItemName.AYA_CAMERA),
				new ItemTenguCamera(LibItemName.HATATE_CAMERA),
				new ItemStopWatch(),
				new ItemHakureiGohei(),
				new ItemSanaeGohei(),

				//Food
				new ItemShroomSlice(),
				new ItemGrilledLamprey(),
				new ItemIbarakiBoxFilled(),
				new ItemKappasNostrum(),
				new ItemHeavelyPeach(),
				new ItemIbukiGourd(),
				new ItemSwallowCowrieShell(),
				new ItemUltramarineOrbElixir(),
				new ItemHouraiElixir(),

				//Armor
				new ItemMapleLeafShield(),
				new ItemFoxMask(SOLID_PAPER, 3),
				new ItemRaidenMask(SOLID_PAPER, 3),
				new ItemMonkeyMask(SOLID_PAPER, 3),
				new ItemHyottokoMask(SOLID_PAPER, 3),
				new ItemFukuNoKamiMask(SOLID_PAPER, 3),
				new ItemUbaMask(SOLID_PAPER, 3),
				new ItemHannyaMask(SOLID_PAPER, 3),
				new ItemKoomoteMask(SOLID_PAPER, 3),
				new ItemMaskOfHope(SOLID_PAPER, 3),
				new ItemKokorosMasks(SOLID_PAPER, 3),
				new ItemAuraByakuren(SOLID_PAPER, 3),
				new ItemAuraMokou(SOLID_PAPER, 3),
				new ItemAuraToyosatomimi(SOLID_PAPER, 3),
				new ItemAuraKanako(SOLID_PAPER, 3),
				new ItemAuraIchirin(SOLID_PAPER, 3),
				new ItemSuwakoHat(SOLID_PAPER,3),
				new ItemFireRobe(SOLID_PAPER,3),
				new ItemWingsUtsuho(SOLID_PAPER,5),
				new ItemKappaHat(SOLID_PAPER,3),
				new ItemMarisaHat(SOLID_PAPER,3),
				new ItemMikoCape(SOLID_PAPER,3),

				//Weapons
				new ItemAmenonuhoko(WET_NOODLE),
				new ItemCrestOfYggdrasill(WET_NOODLE),
				new ItemMikoStick(NOT_A_MELEE_WEAPON),
				new ItemMochiHammer(GOLDYRON),
				new ItemMomijisScimitarSword(GOLDYRON),
				new ItemNazrinStick(WET_NOODLE, LibItemName.NAZRIN_STICK),
				new ItemNueTrident(WET_NOODLE),
				new ItemSwordofKusanagi(GOLDYRON),
				new ItemSyringe(NOT_A_MELEE_WEAPON),
				new ItemIchirinRing(NOT_A_MELEE_WEAPON),
				new ItemOnbashira(WET_NOODLE),
				new ItemShichiSeiken(WET_NOODLE),
				new ItemCattailPlant(WET_NOODLE),
				new ItemPopsicleStick(WET_NOODLE),
				new ItemRumiaSword(WET_NOODLE),
				new ItemSarielWand(WET_NOODLE),
				new ItemWatermelonBlade(WET_NOODLE),
				new ItemWatermelonSword(GOLDYRON),
				new ItemSacredToyosatomimi(GOLDYRON),
				new ItemNeedle(NOT_A_MELEE_WEAPON),
				new ItemDeathScythe(WET_NOODLE),
				new ItemSwordRoukanken(WET_NOODLE)
		);

		event.getRegistry().registerAll(
				itemBlock(ModBlocks.HOLY_KEY_STONE),
				itemBlock(ModBlocks.COMPACT_STONE),
				itemBlock(ModBlocks.HOLY_STONE),
				itemBlock(ModBlocks.KYOUMARUBOTAN),
				itemBlock(ModBlocks.ONBASHIRA),
				itemBlock(ModBlocks.PAPER_BLOCK),
				itemBlock(ModBlocks.ROPE_BLOCK),
				itemBlock(ModBlocks.SHROOM),
				itemBlock(ModBlocks.SUGAR_BLOCK),
				itemBlock(ModBlocks.HYPER_CONCENTRATED_MAGIC),
				itemBlock(ModBlocks.ALTAR),
				itemBlock(ModBlocks.PILLAR_ALTAR)
		);

		if(GrimoireOfAlice.danmakuCoreInstalled) {
			event.getRegistry().registerAll(
					new ItemViolin(),
					new ItemPiano(),
					new ItemTrumpet(),
					new ItemTenguFan(),
					new ItemLaevatein(WET_NOODLE),
					new ItemShouLamp(),
					new ItemJeweledHourai(),
					new ItemSpellCardPouch(),
					new ItemEllyScythe(WET_NOODLE),
					new ItemSwordOfHisou(GOLDYRON),
					new ItemLeaf()
			);
		}
	}

	private static Item itemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		Block sugarBlock = new BlockMod(LibBlockName.SUGAR_BLOCK, Material.CLAY)
				.setSound(SoundType.SNOW)
				.setHardness(0.2F)
				.setResistance(5.0F);
		sugarBlock.setHarvestLevel("axe", 1);

		Block hyperConcentratedMagic = new BlockMod(LibBlockName.HYPER_MAGIC, Material.IRON)
				.setSound(SoundType.SNOW)
				.setHardness(0.5F)
				.setResistance(2.0F);
		hyperConcentratedMagic.setHarvestLevel("pickaxe", 3);

		//TODO: Move these somewhere else
		GameRegistry.registerTileEntity(TileCraftingAltar.class, "Crafting_Altar");
		GameRegistry.registerTileEntity(TilePillarAltar.class, "Pillar_Altar");

		event.getRegistry().registerAll(
				new BlockMod(LibBlockName.COMPACT_STONE, Material.ROCK).setHardness(2.0F).setResistance(-1F),
				new BlockHolyKeyStone(),
				new BlockHolyStone(),
				new BlockKyoumarubotan(),
				new BlockOnbashira(),
				new BlockPaper(),
				new BlockRope(),
				new BlockShroom(),
				sugarBlock,
				hyperConcentratedMagic,
				new BlockCraftingAltar(),
				new BlockPillarAltar()
		);
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
				new PotionElixir()
		);
	}

	@SuppressWarnings("UnusedAssignment")
	public void preInit(FMLPreInitializationEvent event) {
		int modEntityID = 0;
		EntityRegistry.registerModEntity(EntityNazrinPendulum.class, "Pendulum", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityGrimoireSpell.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMagicCircle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNeedle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDragonJewel.class, "Jewel", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityUnzanFist.class, "Fist", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityEllyScythe.class, "Scythe", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityCursedDecoyDoll.class, "Doll", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCameraSquare.class, "Camera", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityStopWatch.class, "Watch", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityHakureiOrb.class, "Orb", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityBarrier.class, "Barrier", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenPlants());
		MinecraftForge.EVENT_BUS.register(new YukkuriEvent());
		MinecraftForge.EVENT_BUS.register(new CapabilitiesEvent());
	}

	public void init(FMLInitializationEvent event) {
		VanillaCrafting.booksAndStrings();
		VanillaCrafting.masks();
		SpecialRecipes.init();
	}
}
