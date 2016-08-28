package arekkuusu.grimoireOfAlice.item.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import arekkuusu.grimoireOfAlice.handler.ConfigHandler;
import arekkuusu.grimoireOfAlice.item.GOAItem;
//import thKaguyaMod.init.THKaguyaItems;

public class THCrafting {

	public static void pointsAndItems() {
		/*//@formatter:off
		if(ConfigHandler.pointItemRecipes){
		shaped().grid("GGG", "GGG", "GGG")
			.where('G').mapsTo(THKaguyaItems.power_up_item_big)
		.outputs(GOAItem.FullPower).build();
		
		shaped().grid("GIG", "I I", "GIG")
			.where('G').mapsTo(THKaguyaItems.point_item)
			.where('I').mapsTo(THKaguyaItems.shot_material)
		.outputs(GOAItem.Star).build();
		
		shaped().grid("GGG", "GHG", "GGG")
			.where('G').mapsTo(GOAItem.Star)
			.where('H').mapsTo("dyePink")
		.outputs(GOAItem.Cherry).build();
		
		shaped().grid("GGG", "GHG", "GGG")
			.where('H').mapsTo(GOAItem.FullPower)
			.where('G').mapsTo(GOAItem.Star)
		.outputs(GOAItem.TimeOrb).build();
		
		shaped().grid("GGG", "GHG", "GGG")
			.where('G').mapsTo(GOAItem.Cherry)
			.where('H').mapsTo(THKaguyaItems.soulTorch)
		.outputs(GOAItem.Faith).build();
		
		shapeless()
		    .add(GOAItem.FullPower)
	    .outputs(THKaguyaItems.bomb_item).build();
		
		shapeless()
			.add(GOAItem.UFORed)
			.add(GOAItem.UFOBlue)
			.add(GOAItem.UFOGreen)
		.outputs(GOAItem.UFOs).build();
		
		ufo().where('U').mapsTo("dyeRed").outputs(GOAItem.UFORed).build();
		ufo().where('U').mapsTo("dyeBlue").outputs(GOAItem.UFOBlue).build();
		ufo().where('U').mapsTo("dyeGreen").outputs(GOAItem.UFOGreen).build();
		}*/
		//@formatter:on
	}
	
	private static ShapedRecipe shaped() {
		return new ShapedRecipe();
	}

	private static ShapelessRecipe shapeless() {
		return new ShapelessRecipe();
	}
	
	/*private static ShapedRecipe ufo() {
		return new ShapedRecipe().grid("IOI", "UHU", "SSS")
				.where('I').mapsTo(GOAItem.TimeOrb)
				.where('H').mapsTo(THKaguyaItems.diffusion_amulet)
				.where('S').mapsTo(GOAItem.Star)
				.where('O').mapsTo(Blocks.glass);
	}*/
	
}
