/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.ImmutableSet;

import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

@SuppressWarnings("WeakerAccess")
@CleanupDone
public class ShapedRecipe {

	private String row1 = null;
	private String row2 = null;
	private String row3 = null;
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
		List<Object> objects = new LinkedList<>();
		if(mirrored) {
			objects.add(true);
		}

		if(row1 == null && row2 == null && row3 == null) throw new IllegalArgumentException("Please specify at least one grid row for recipe builder");
		if(out == null) throw new IllegalArgumentException("Output not specified");

		Set<Character> row1Chars = ImmutableSet.of();
		Set<Character> row2Chars = ImmutableSet.of();
		Set<Character> row3Chars = ImmutableSet.of();

		if(row1 != null) {
			objects.add(row1);
			row1Chars = new HashSet<>(Arrays.asList(ArrayUtils.toObject(row1.toCharArray())));
		}

		if(row2 != null) {
			objects.add(row2);
			row2Chars = new HashSet<>(Arrays.asList(ArrayUtils.toObject(row2.toCharArray())));
		}

		if(row3 != null) {
			objects.add(row3);
			row3Chars = new HashSet<>(Arrays.asList(ArrayUtils.toObject(row3.toCharArray())));
		}

		Set<Character> mappedCharacters = new HashSet<>(characters.keySet());
		mappedCharacters.add(' ');

		row1Chars.removeAll(mappedCharacters);
		row2Chars.removeAll(mappedCharacters);
		row3Chars.removeAll(mappedCharacters);

		if(!row1Chars.isEmpty()) {
			throw new IllegalArgumentException("The first row has characters which are not mapped. They are: " + row1Chars);
		}

		if(!row2Chars.isEmpty()) {
			throw new IllegalArgumentException("The second row has characters which are not mapped. They are: " + row2Chars);
		}

		if(!row3Chars.isEmpty()) {
			throw new IllegalArgumentException("The third row has characters which are not mapped. They are: " + row3Chars);
		}

		characters.forEach((key, obj) -> {
			objects.add(key);
			objects.add(obj);
		});

		ShapedOreRecipe recipe = new ShapedOreRecipe(out, objects.toArray());
		//noinspection unchecked
		CraftingManager.getInstance().getRecipeList().add(recipe);
	}

	@CleanupDone
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