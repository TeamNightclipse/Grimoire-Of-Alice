package arekkuusu.grimoireofalice.common;

import arekkuusu.grimoireofalice.common.block.*;
import arekkuusu.grimoireofalice.common.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.common.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.common.item.*;
import arekkuusu.grimoireofalice.common.item.food.*;
import arekkuusu.grimoireofalice.common.item.masks.*;
import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.item.*;
import arekkuusu.grimoireofalice.common.potion.PotionElixir;
import arekkuusu.grimoireofalice.common.potion.PotionRadiationPoisoning;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonRegistration {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new Item3rdEye(),
				new ItemTamahaganeSteel(),
				new ItemHihiirokane(),
				new ItemShimenawaRope(),
				new ItemTimeOrb(),
				new ItemMod(LibItemName.MASK),
				new ItemGrimoireBook(),
				new ItemYoukaiBook(),
				new ItemMod(LibItemName.PASTE),
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
				new ItemUFOs(),
				new ItemPatchyBook(),
				new ItemSkull(),
				new ItemNazrinPendulum(),
				new ItemGhostDipper(),
				new ItemWallPassingChisel(),
				new ItemRodOfRemorse(),
				new ItemBudahBoul(ModMaterials.BUDAH_BOUL),
				new ItemDragonJewel(),
				new ItemBloodThirstyOrb(),
				new ItemGapFoldingUmbrella(),
				new ItemSubstituteJizo(),
				new ItemNimbleFabric(),
				new ItemFakeMiracleMallet(),
				new ItemGhastlySendOffLantern(),
				new ItemCursedDecoyDoll(),
				new ItemHakureiGohei(),
				new ItemSanaeGohei(),
				new ItemMortarPestle(),
				new ItemMod(LibItemName.BLACK_FEATHER),
				new ItemMod(LibItemName.NETHER_SHARD),
				new ItemMod(LibItemName.IRON_NUGGET),
				new ItemMod(LibItemName.HARDENED_LEATHER),

				//Food
				new ItemShroomPowder(),
				new ItemGrilledLamprey(),
				new ItemIbarakiBoxFilled(),
				new ItemKappasNostrum(),
				new ItemHeavelyPeach(),
				new ItemIbukiGourd(),
				new ItemSwallowCowrieShell(),
				new ItemUltramarineOrbElixir(),
				new ItemHouraiElixir(),
				new ItemYuugiSake(),

				//Armor
				new ItemMapleLeafShield(),
				new ItemFoxMask(ModMaterials.MASK, 3),
				new ItemRaidenMask(ModMaterials.MASK, 3),
				new ItemMonkeyMask(ModMaterials.MASK, 3),
				new ItemHyottokoMask(ModMaterials.MASK, 3),
				new ItemFukuNoKamiMask(ModMaterials.MASK, 3),
				new ItemUbaMask(ModMaterials.MASK, 3),
				new ItemHannyaMask(ModMaterials.MASK, 3),
				new ItemKoomoteMask(ModMaterials.MASK, 3),
				new ItemMaskOfHope(ModMaterials.MASK, 3),
				new ItemKokorosMasks(ModMaterials.STRONG_HIHIIROKANE, 3),
				new ItemToyosatomimiHat(ModMaterials.WEAK_PAPER, 3),
				new ItemKanakoShimenawa(ModMaterials.ENSHRINED_SHIMENAWA, 3),
				new ItemIchirinUnzan(ItemArmor.ArmorMaterial.DIAMOND, 3),
				new ItemSuwakoHat(ModMaterials.STRONG_LEATHER, 3),
				new ItemFireRobe(ModMaterials.FIRE_LEATHER, 3),
				new ItemUtsuhoWings(ModMaterials.FIRE_LEATHER, 5),
				new ItemKappaHat(ModMaterials.WEAK_PAPER, 3),
				new ItemMarisaHat(ModMaterials.STRONG_LEATHER, 3),
				new ItemToyosatomimiCloak(ModMaterials.WEAK_PAPER, 3),
				new ItemShinmyoumaruHat(ModMaterials.STRONG_LEATHER, 5),
				new ItemNuclearBoots(ModMaterials.WEAK_PAPER, 5),

				//Weapons
				new ItemAmenonuhoko(Item.ToolMaterial.DIAMOND),
				new ItemToyosatomimiStick(ModMaterials.WEAK_MATERIAL),
				new ItemMochiHammer(ModMaterials.STRONG_STONE),
				new ItemMomijisScimitarSword(ModMaterials.STRONG_IRON),
				new ItemNazrinStick(Item.ToolMaterial.STONE, LibItemName.NAZRIN_STICK),
				new ItemNueTrident(Item.ToolMaterial.DIAMOND),
				new ItemSwordofKusanagi(ModMaterials.STRONG_GOLD),
				new ItemSyringe(ModMaterials.WEAK_MATERIAL),
				new ItemIchirinRing(ModMaterials.WEAK_MATERIAL),
				new ItemOnbashira(Item.ToolMaterial.STONE),
				new ItemShichiSeiken(ModMaterials.NOODLE),
				new ItemCattailPlant(Item.ToolMaterial.WOOD),
				new ItemPopsicleStick(Item.ToolMaterial.WOOD),
				new ItemRumiaSword(ModMaterials.STRONG_GOLD),
				new ItemSarielWand(ModMaterials.NOODLE),
				new ItemWatermelonBlade(Item.ToolMaterial.STONE),
				new ItemWatermelonSword(Item.ToolMaterial.DIAMOND),
				new ItemSacredToyosatomimi(ModMaterials.STRONG_GOLD),
				new ItemShinmyoumaruNeedle(Item.ToolMaterial.IRON),
				new ItemDeathScythe(Item.ToolMaterial.DIAMOND),
				new ItemSwordRoukanken(ModMaterials.STRONG_IRON)
		);

		event.getRegistry().registerAll(
				itemBlock(ModBlocks.HOLY_KEY_STONE),
				itemBlock(ModBlocks.COMPACT_STONE),
				itemBlock(ModBlocks.HOLY_STONE),
				itemBlock(ModBlocks.KYOUMARUBOTAN),
				itemBlockOnbashira(ModBlocks.ONBASHIRA),
				itemBlock(ModBlocks.PAPER_BLOCK),
				itemBlock(ModBlocks.ROPE_BLOCK),
				itemBlockShroom(ModBlocks.SHROOM),
				itemBlock(ModBlocks.SUGAR_BLOCK),
				itemBlock(ModBlocks.HYPER_CONCENTRATED_MAGIC),
				itemBlock(ModBlocks.ALTAR),
				itemBlock(ModBlocks.PILLAR_ALTAR),
				itemBlock(ModBlocks.IMPURE_STONE),
				itemBlock(ModBlocks.HIHIIROKANE_BLOCK)
		);

		if (GrimoireOfAlice.danmakuCoreInstalled) {
			event.getRegistry().registerAll(
					new ItemStopWatch(),
					new ItemMiracleMallet(),
					new ItemViolin(),
					new ItemPiano(),
					new ItemTrumpet(),
					new ItemTenguFan(),
					new ItemLaevatein(ModMaterials.NOODLE),
					new ItemShouLamp(),
					new ItemJeweledHourai(),
					new ItemSpellCardPouch(),
					new ItemEllyScythe(ModMaterials.NOODLE),
					new ItemSwordOfHisou(ModMaterials.STRONG_GOLD),
					new ItemLeaf(),
					new ItemNuclearRod(),
					new ItemTenguCamera(LibItemName.AYA_CAMERA),
					new ItemTenguCamera(LibItemName.HATATE_CAMERA)
			);
		}
	}

	private static Item itemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	private static Item itemBlockShroom(Block block) {
		return new ItemBlockShroom(block).setRegistryName(block.getRegistryName());
	}

	private static Item itemBlockOnbashira(Block block) {
		return new ItemBlockOnbashira(block).setRegistryName(block.getRegistryName());
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
				new BlockPillarAltar(),
				new BlockImpureRock(),
				new BlockHihiirokane()
		);
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
				new PotionElixir(),
				new PotionRadiationPoisoning()
		);
	}
}
