/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice;

import arekkuusu.grimoireofalice.block.BlockHolyKeyStone;
import arekkuusu.grimoireofalice.block.BlockHolyStone;
import arekkuusu.grimoireofalice.block.BlockKyoumarubotan;
import arekkuusu.grimoireofalice.block.BlockMod;
import arekkuusu.grimoireofalice.block.BlockOnbashira;
import arekkuusu.grimoireofalice.block.BlockOnbashiraMiddle;
import arekkuusu.grimoireofalice.block.BlockOnbashiraTop;
import arekkuusu.grimoireofalice.block.BlockPaper;
import arekkuusu.grimoireofalice.block.BlockRope;
import arekkuusu.grimoireofalice.block.BlockShroom;
import arekkuusu.grimoireofalice.entity.*;
import arekkuusu.grimoireofalice.event.YukkuriEvent;
import arekkuusu.grimoireofalice.handler.WorldGenPlants;
import arekkuusu.grimoireofalice.item.*;
import arekkuusu.grimoireofalice.item.auras.ItemAuraByakuren;
import arekkuusu.grimoireofalice.item.auras.ItemAuraIchirin;
import arekkuusu.grimoireofalice.item.auras.ItemAuraKanako;
import arekkuusu.grimoireofalice.item.auras.ItemAuraMokou;
import arekkuusu.grimoireofalice.item.auras.ItemAuraToyosatomimi;
import arekkuusu.grimoireofalice.item.auras.ItemWingsUtsuho;
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
import net.minecraft.item.ItemCloth;
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

	public static final ItemArmor.ArmorMaterial SOLID_PAPER = EnumHelper.addArmorMaterial("solidPaper", "No", 1000, new int[] {1,2,3,4}, 30, SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0);
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
				new ItemLeaf(),
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
				new ItemPrimordialShield(),
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
				new ItemNazrinStick(WET_NOODLE, LibItemName.NAZRIN_STICKB),
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

		if(GrimoireOfAlice.danmakuCoreInstalled) { //FIXME: Always false
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
					new ItemSwordOfHisou(GOLDYRON)
			);
		}
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		Block compactStone = new BlockMod(LibBlockName.COMPACTSTONE, Material.ROCK).setHardness(2.0F).setResistance(-1F);
		Block holyKeyStone = new BlockHolyKeyStone();
		Block holyStone = new BlockHolyStone();
		Block kyoumarubotan = new BlockKyoumarubotan();
		Block onbashira = new BlockOnbashira();
		Block onbashiraMiddle = new BlockOnbashiraMiddle();
		Block onbashiraTop = new BlockOnbashiraTop();
		Block paperBlock = new BlockPaper();
		Block ropeBlock = new BlockRope();
		Block shroom = new BlockShroom();

		Block sugarBlock = new BlockMod(LibBlockName.SUGARBLOCK, Material.CLAY).setSound(SoundType.SNOW).setHardness(0.2F).setResistance(5.0F);
		sugarBlock.setHarvestLevel("axe", 1);

		Block hyperconcentratedMagic = new BlockMod(LibBlockName.HYPERMAGIC, Material.IRON).setSound(SoundType.SNOW).setHardness(0.5F).setResistance(2.0F);
		hyperconcentratedMagic.setHarvestLevel("pickaxe", 3);

		GameRegistry.register(new ItemBlock(compactStone).setRegistryName(compactStone.getRegistryName()));
		GameRegistry.register(new ItemBlock(holyKeyStone).setRegistryName(holyKeyStone.getRegistryName()));
		GameRegistry.register(new ItemBlock(holyStone).setRegistryName(holyStone.getRegistryName()));
		GameRegistry.register(new ItemBlock(kyoumarubotan).setRegistryName(kyoumarubotan.getRegistryName()));
		GameRegistry.register(new ItemBlock(onbashira).setRegistryName(onbashira.getRegistryName()));
		GameRegistry.register(new ItemBlock(onbashiraMiddle).setRegistryName(onbashiraMiddle.getRegistryName()));
		GameRegistry.register(new ItemBlock(onbashiraTop).setRegistryName(onbashiraTop.getRegistryName()));
		GameRegistry.register(new ItemBlock(paperBlock).setRegistryName(paperBlock.getRegistryName()));
		GameRegistry.register(new ItemBlock(ropeBlock).setRegistryName(ropeBlock.getRegistryName()));
		GameRegistry.register(new ItemCloth(shroom).setRegistryName(shroom.getRegistryName()));
		GameRegistry.register(new ItemBlock(sugarBlock).setRegistryName(sugarBlock.getRegistryName()));
		GameRegistry.register(new ItemBlock(hyperconcentratedMagic).setRegistryName(hyperconcentratedMagic.getRegistryName()));

		event.getRegistry().registerAll(
				compactStone,
				holyKeyStone,
				holyStone,
				kyoumarubotan,
				onbashira,
				onbashiraMiddle,
				onbashiraTop,
				paperBlock,
				ropeBlock,
				shroom,
				sugarBlock,
				hyperconcentratedMagic
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
		EntityRegistry.registerModEntity(EntityLeaf.class, "Leaf", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityAnimalShot.class, "Shot", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNazrinPendulum.class, "Pendulum", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityGrimoireSpell.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMagicCircle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityNeedle.class, "Spell", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDragonJewel.class, "Beam", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityUnzanFist.class, "Fist", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true); // Wont move properly
		EntityRegistry.registerModEntity(EntityEllyScythe.class, "Scythe", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true); //Set to 1, but might have to increase it...
		EntityRegistry.registerModEntity(EntityCursedDecoyDoll.class, "Doll", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCameraSquare.class, "Camera", ++modEntityID, GrimoireOfAlice.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityStopWatch.class, "Watch", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityHakureiOrb.class, "Orb", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityBarrier.class, "Barrier", ++modEntityID, GrimoireOfAlice.instance, 64, 1, true);
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldGenPlants());
		MinecraftForge.EVENT_BUS.register(new YukkuriEvent());
	}
	
	public void init(FMLInitializationEvent event) {
		VanillaCrafting.booksAndStrings();
		VanillaCrafting.masks();
	}
}
