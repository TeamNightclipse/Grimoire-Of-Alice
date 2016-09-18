package arekkuusu.grimoireofalice.plugin.touhou;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.lib.LibGuiID;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSpellCardPouch extends Item {

	public ItemSpellCardPouch() {
		super();
		setRegistryName(LibItemName.POUCH);
		setUnlocalizedName(LibItemName.POUCH);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
		setMaxStackSize(1);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}
   
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote) {
			if (!playerIn.isSneaking()) {
				playerIn.openGui(GrimoireOfAlice.instance, LibGuiID.POUCH_BAG, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
}
