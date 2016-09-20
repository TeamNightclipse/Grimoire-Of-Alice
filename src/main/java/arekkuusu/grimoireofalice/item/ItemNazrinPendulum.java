package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.entity.EntityNazrinPendulum;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemNazrinPendulum extends ItemMod {

	public ItemNazrinPendulum() {
		super(LibItemName.NAZRINPENDULUM);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote) {
			EntityNazrinPendulum pendulum = new EntityNazrinPendulum(worldIn, playerIn);
			pendulum.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
			worldIn.spawnEntityInWorld(pendulum);
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}
}
