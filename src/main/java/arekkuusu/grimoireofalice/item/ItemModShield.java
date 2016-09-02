package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.item.ItemShield;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemModShield extends ItemShield {

	public ItemModShield(String id) {
		super();
		setRegistryName(id);
		setUnlocalizedName(id);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
}
