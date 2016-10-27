/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(LibMod.MODID)
public final class ModItems {

	//Item
	@ObjectHolder(LibItemName.GRIMOIRE_BOOK)
	public static final Item GRIMOIRE_BOOK = new Item();
	@ObjectHolder(LibItemName.VOLATILE_STRING)
	public static final Item VOLATILE_STRING = new Item();
	@ObjectHolder(LibItemName.SOLDIFIED_PAPER)
	public static final Item SOLDIFIED_PAPER = new Item();
	@ObjectHolder(LibItemName.GLORIOUS_NIPPON_STEEL)
	public static final Item GLORIOUS_NIPPON_STEEL = new Item();
	@ObjectHolder(LibItemName.SHIMENAWA_ROPE)
	public static final Item SHIMENAWA_ROPE = new Item();
	@ObjectHolder(LibItemName.YOUKAI_BOOK)
	public static final Item YOUKAI_BOOK = new Item();
	@ObjectHolder(LibItemName.MASK)
	public static final Item MASK = new Item();
	@ObjectHolder(LibItemName.HIHIIROKANE)
	public static final Item HIHIIROKANE = new Item();
	@ObjectHolder(LibItemName.IBARAKI_BOX_EMPTY)
	public static final Item IBARAKI_BOX_EMPTY = new Item();
	@ObjectHolder(LibItemName.IMPURE_ROCK)
	public static final Item IMPURE_ROCK = new Item();
	@ObjectHolder(LibItemName.LUNASA_VIOLIN)
	public static final Item LUNASA_VIOLIN = new Item();
	@ObjectHolder(LibItemName.LYRICA_PIANO)
	public static final Item LYRICA_PIANO = new Item();
	@ObjectHolder(LibItemName.MERLIN_TRUMPET)
	public static final Item MERLIN_TRUMPET = new Item();
	@ObjectHolder(LibItemName.SHOU_LAMP)
	public static final Item SHOU_LAMP = new Item();
	@ObjectHolder(LibItemName.PATCHY_BOOK)
	public static final Item PATCHY_BOOK = new Item();
	@ObjectHolder(LibItemName.SKULL)
	public static final Item SKULL = new Item();
	@ObjectHolder(LibItemName.POUCH)
	public static final Item POUCH = new Item();
	@ObjectHolder(LibItemName.TENGU_FAN)
	public static final Item TENGU_FAN = new Item();
	@ObjectHolder(LibItemName.NAZRIN_PENDULUM)
	public static final Item NAZRIN_PENDULUM = new Item();
	@ObjectHolder(LibItemName.GHOST_DIPPER)
	public static final Item GHOST_DIPPER = new Item();
	@ObjectHolder(LibItemName.WALL_PASSING_CHISEL)
	public static final Item WALL_PASSING_CHISEL = new Item();
	@ObjectHolder(LibItemName.MIRACLE_MALLET)
	public static final Item MIRACLE_MALLET = new Item();
	@ObjectHolder(LibItemName.ROD_REMORSE)
	public static final Item ROD_REMORSE = new Item();
	@ObjectHolder(LibItemName.JEWELED_HOURAI)
	public static final Item JEWELED_HOURAI = new Item();
	@ObjectHolder(LibItemName.BUDAH_BOUL)
	public static final Item BUDAH_BOUL = new Item();
	@ObjectHolder(LibItemName.DRAGON_JEWEL)
	public static final Item DRAGON_JEWEL = new Item();
	@ObjectHolder(LibItemName.STOP_WATCH)
	public static final Item STOP_WATCH = new Item();
	//Food
	@ObjectHolder(LibItemName.SHROOM_SLICE)
	public static final Item SHROOM_SLICE = new Item();
	@ObjectHolder(LibItemName.GRILLED_LAMPREY)
	public static final Item GRILLED_LAMPREY = new Item();
	@ObjectHolder(LibItemName.IBARAKI_BOX_FILLED)
	public static final Item IBARAKI_BOX_FILLED = new Item();
	@ObjectHolder(LibItemName.KAPPAS_NOSTRUM)
	public static final Item KAPPAS_NOSTRUM = new Item();
	@ObjectHolder(LibItemName.HEAVENLY_PEACH)
	public static final Item HEAVENLY_PEACH = new Item();
	@ObjectHolder(LibItemName.IBUKI_GOURD)
	public static final Item IBUKI_GOURD = new Item();
	@ObjectHolder(LibItemName.COWRIE_SHELL)
	public static final Item COWRIE_SHELL = new Item();
	@ObjectHolder(LibItemName.ORB_ELIXIR)
	public static final Item ORB_ELIXIR = new Item();
	@ObjectHolder(LibItemName.HOURAI_ELIXIR)
	public static final Item HOURAI_ELIXIR = new Item();
	//Armor
	@ObjectHolder(LibItemName.FOX_MASK)
	public static final Item FOX_MASK = new Item();
	@ObjectHolder(LibItemName.RAIDEN_MASK)
	public static final Item RAIDEN_MASK = new Item();
	@ObjectHolder(LibItemName.MONKEY_MASK)
	public static final Item MONKEY_MASK = new Item();
	@ObjectHolder(LibItemName.HYOTTOKO_MASK)
	public static final Item HYOTTOKO_MASK = new Item();
	@ObjectHolder(LibItemName.FUKUNOKAMI_MASK)
	public static final Item FUKU_NO_KAMI_MASK = new Item();
	@ObjectHolder(LibItemName.UBA_MASK)
	public static final Item UBA_MASK = new Item();
	@ObjectHolder(LibItemName.HANNYA_MASK)
	public static final Item HANNYA_MASK = new Item();
	@ObjectHolder(LibItemName.KOOMOTE_MASK)
	public static final Item KOOMOTE_MASK = new Item();
	@ObjectHolder(LibItemName.MASKOF_HOPE)
	public static final Item MASK_OF_HOPE = new Item();
	@ObjectHolder(LibItemName.KOKOROS_MASKS)
	public static final Item KOKOROS_MASKS = new Item();
	@ObjectHolder(LibItemName.MAPLE_LEAF_SHIELD)
	public static final Item MAPLE_LEAF_SHIELD = new Item();
	@ObjectHolder(LibItemName.AURA_BYAKUREN)
	public static final Item BYAKUREN_AURA = new Item();
	@ObjectHolder(LibItemName.AURA_MOKOU)
	public static final Item MOKOU_AURA = new Item();
	@ObjectHolder(LibItemName.AURA_TOYOSATOMIMI)
	public static final Item TOYOSATOMIMI_AURA = new Item();
	@ObjectHolder(LibItemName.AURA_KANAKO)
	public static final Item KANAKO_AURA = new Item();
	@ObjectHolder(LibItemName.AURA_ICHIRIN)
	public static final Item ICHIRIN_AURA = new Item();
	@ObjectHolder(LibItemName.SUWAKO_HAT)
	public static final Item SUWAKO_HAT = new Item();
	@ObjectHolder(LibItemName.FIRE_ROBE)
	public static final Item FIRE_ROBE = new Item();
	@ObjectHolder(LibItemName.AURA_UTSUHO)
	public static final Item UTSUHO_AURA = new Item();
	@ObjectHolder(LibItemName.KAPPA_HAT)
	public static final Item KAPPA_HAT = new Item();
	@ObjectHolder(LibItemName.MARISA_HAT)
	public static final Item MARISA_HAT = new Item();
	@ObjectHolder(LibItemName.MIKO_CAPE)
	public static final Item MIKO_CAPE = new Item();
	//Weapons
	@ObjectHolder(LibItemName.MOCHI_HAMMER)
	public static final Item MOCHI_HAMMER = new Item();
	@ObjectHolder(LibItemName.MOMIJIS_SCIMITAR_SWORD)
	public static final Item MOMIJIS_SCIMITAR_SWORD = new Item();
	@ObjectHolder(LibItemName.LAEVATEIN)
	public static final Item LAEVATEIN = new Item();
	@ObjectHolder(LibItemName.NAZRIN_STICK)
	public static final Item NAZRIN_STICK_ITEM = new Item();
	@ObjectHolder(LibItemName.ELLY_SCYTHE)
	public static final Item ELLY_SCYTHE = new Item();
	@ObjectHolder(LibItemName.MIKO_STICK)
	public static final Item MIKO_STICK = new Item();
	@ObjectHolder(LibItemName.CREST_OF_YGGDRASILL)
	public static final Item CREST_OF_YGGDRASILL = new Item();
	@ObjectHolder(LibItemName.AMENONUHOKO)
	public static final Item AMENONUHOKO = new Item();
	@ObjectHolder(LibItemName.NEEDLE)
	public static final Item NEEDLE = new Item();
	@ObjectHolder(LibItemName.NUE_TRIDENT)
	public static final Item NUE_TRIDENT = new Item();
	@ObjectHolder(LibItemName.SWORD_OF_KUSANAGI)
	public static final Item SWORD_OF_KUSANAGI = new Item();
	@ObjectHolder(LibItemName.SYRINGE)
	public static final Item SYRINGE = new Item();
	@ObjectHolder(LibItemName.ICHIRIN_RING)
	public static final Item ICHIRIN_RING = new Item();
	@ObjectHolder(LibItemName.KANAKO_ONBASHIRA)
	public static final Item KANAKO_ONBASHIRA = new Item();
	@ObjectHolder(LibItemName.SHICHI_SEIKEN)
	public static final Item SHICHI_SEIKEN = new Item();
	@ObjectHolder(LibItemName.CATTAIL_PLANT)
	public static final Item CATTAIL_PLANT = new Item();
	@ObjectHolder(LibItemName.POPSICLE_STICK)
	public static final Item POPSICLE_STICK = new Item();
	@ObjectHolder(LibItemName.RUMIA_SWORD)
	public static final Item RUMIA_SWORD = new Item();
	@ObjectHolder(LibItemName.SARIEL_WAND)
	public static final Item SARIEL_WAND = new Item();
	@ObjectHolder(LibItemName.WATERMELON_BLADE)
	public static final Item WATERMELON_BLADE = new Item();
	@ObjectHolder(LibItemName.WATERMELON_SWORD)
	public static final Item WATERMELON_SWORD = new Item();
	@ObjectHolder(LibItemName.SACRED_TOYOSATOMIMI)
	public static final Item SACRED_TOYOSATOMIMI = new Item();
	@ObjectHolder(LibItemName.HISOU)
	public static final Item HISOU = new Item();
	@ObjectHolder(LibItemName.DEATH_SCYTHE)
	public static final Item DEATH_SCYTHE = new Item();
	@ObjectHolder(LibItemName.ROUKANKEN)
	public static final Item ROUKANKEN = new Item();
	//PointItem
	@ObjectHolder(LibItemName.FULL_POWER)
	public static final Item FULL_POWER = new Item();
	@ObjectHolder(LibItemName.STAR)
	public static final Item STAR = new Item();
	@ObjectHolder(LibItemName.CHERRY)
	public static final Item CHERRY = new Item();
	@ObjectHolder(LibItemName.TIME_ORB)
	public static final Item TIME_ORB = new Item();
	@ObjectHolder(LibItemName.FAITH)
	public static final Item FAITH = new Item();
	@ObjectHolder(LibItemName.UFO_RED)
	public static final Item UFO_RED = new Item();
	@ObjectHolder(LibItemName.UFO_BLUE)
	public static final Item UFO_BLUE = new Item();
	@ObjectHolder(LibItemName.UFO_GREEN)
	public static final Item UFO_GREEN = new Item();
	@ObjectHolder(LibItemName.UFOS)
	public static final Item UFOS = new Item();
	@ObjectHolder(LibItemName.EYE)
	public static final Item THIRD_EYE = new Item();
	@ObjectHolder(LibItemName.LEAF)
	public static final Item LEAF = new Item();
	@ObjectHolder(LibItemName.BLOOD_THIRSTY_ORB)
	public static final Item BLOOD_ORB = new Item();
	@ObjectHolder(LibItemName.FOLDING_UMBRELLA)
	public static final Item FOLDING_UMBRELLA = new Item();
	@ObjectHolder(LibItemName.SUBSTITUTE_JIZO)
	public static final Item SUBSTITUTE_JIZO = new Item();
	@ObjectHolder(LibItemName.NIMBLE_FABRIC)
	public static final Item NIMBLE_FABRIC = new Item();
	@ObjectHolder(LibItemName.FAKE_MIRACLE_MALLET)
	public static final Item FAKE_MIRACLE_MALLET = new Item();
	@ObjectHolder(LibItemName.SEND_OFF_LANTERN)
	public static final Item GHASTLY_SEND_OFF_LANTERN = new Item();
	@ObjectHolder(LibItemName.DECOY_DOLL)
	public static final Item CURSED_DECOY_DOLL = new Item();
	@ObjectHolder(LibItemName.AYA_CAMERA)
	public static final Item AYA_CAMERA = new Item();
	@ObjectHolder(LibItemName.HATATE_CAMERA)
	public static final Item HATATE_CAMERA = new Item();
	@ObjectHolder(LibItemName.HAKUREI_GOHEI)
	public static final Item HAKUREI_GOHEI = new Item();
	@ObjectHolder(LibItemName.SANAE_GOHEI)
	public static final Item SANAE_GOHEI = new Item();
}
