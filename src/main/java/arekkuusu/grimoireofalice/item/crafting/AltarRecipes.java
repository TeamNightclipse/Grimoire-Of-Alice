package arekkuusu.grimoireofalice.item.crafting;

import arekkuusu.grimoireofalice.item.ModItems;
import com.sun.istack.internal.NotNull;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class AltarRecipes {

	private final ArrayList<ItemStack> itemStackList;

	public AltarRecipes(@NotNull ArrayList<ItemStack> itemStackList){
		this.itemStackList = itemStackList;
	}

	@NotNull
	public ItemStack getResult(){
		if(itemStackList.contains(new ItemStack(ModItems.SOLDIFIED_PAPER))) {
			System.out.println("I contain stuff");
		}

		return itemStackList.get(0);
	}
}
