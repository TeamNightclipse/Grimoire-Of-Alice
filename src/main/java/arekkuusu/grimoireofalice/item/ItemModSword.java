package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemModSword extends ItemSword {

	public ItemModSword(ToolMaterial material, String id) {
		super(material);
		setRegistryName(id);
		setUnlocalizedName(id);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
}
