/**
 * This class was created by <Katrix>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ShapedRecipe {

	private String row1 = "";
	private String row2 = "";
	private String row3 = "";
	private ItemStack out = null;
	private boolean mirrored = false;

	private final Map<Character, Object> characters = new HashMap<>();

	public ShapedRecipe grid(String row1, String row2, String row3) {
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		return this;
	}

	public ShapedRecipe grid(String row1, String row2) {
		this.row1 = row1;
		this.row2 = row2;
		return this;
	}

	public ShapedRecipe grid(String row1) {
		this.row1 = row1;
		return this;
	}

	public ShapedRecipe map(char character, Block block) {
		characters.put(character, block);
		return this;
	}

	public ShapedRecipe map(char character, Item item) {
		characters.put(character, item);
		return this;
	}

	public ShapedRecipe map(char character, ItemStack stack) {
		characters.put(character, stack);
		return this;
	}

	public ShapedRecipe map(char character, String string) {
		characters.put(character, string);
		return this;
	}

	public RecipePairing where(char character) {
		return new RecipePairing(character, this);
	}

	public ShapedRecipe mirrored(boolean bool) {
		mirrored = bool;
		return this;
	}

	public ShapedRecipe outputs(Block out) {
		return outputs(new ItemStack(out));
	}

	public ShapedRecipe outputs(Item out) {
		return outputs(new ItemStack(out));
	}

	public ShapedRecipe outputs(ItemStack out) {
		this.out = out;
		return this;
	}

	public void build() throws IllegalArgumentException {
		List<String> rows = ImmutableList.of(row1, row2, row3);
		if(rows.stream().allMatch(String::isEmpty)) throw new IllegalArgumentException("Please specify at least one grid row for recipe builder");
		rows = rows.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

		List<Object> objects = new ArrayList<>();

		//A true is added to the recipe objects if it is mirrored
		if(mirrored) {
			objects.add(true);
		}

		//We need a new set that contains all the allowed chars, we make a new set as we have to add space to the set
		Set<Character> allowedCharacters = new HashSet<>(characters.keySet());
		allowedCharacters.add(' ');

		for(String row : rows) {

			//We iterate over all the chars and check if the exists
			for(char rowChar : row.toCharArray()) {
				if(!allowedCharacters.contains(rowChar)) {
					throw new IllegalArgumentException("Found a row where not all characters were mapped: " + row);
				}
			}

			objects.add(row);
		}

		characters.forEach((key, obj) -> {
			objects.add(key);
			objects.add(obj);
		});

		ShapedOreRecipe recipe = new ShapedOreRecipe(out, objects.toArray());
		CraftingManager.getInstance().getRecipeList().add(recipe);
	}

	public static class RecipePairing {

		private final char character;
		private final ShapedRecipe recipe;

		private RecipePairing(char character, ShapedRecipe recipe) {
			this.character = character;
			this.recipe = recipe;
		}

		public ShapedRecipe mapsTo(Block block) {
			return recipe.map(character, block);
		}

		public ShapedRecipe mapsTo(Item item) {
			return recipe.map(character, item);
		}

		public ShapedRecipe mapsTo(ItemStack stack) {
			return recipe.map(character, stack);
		}

		public ShapedRecipe mapsTo(String string) {
			return recipe.map(character, string);
		}
	}
}