package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityIceBlock;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemIcicleSword extends ItemBaseSword  {

	public ItemIcicleSword(ToolMaterial material) {
		super(material, LibName.ICICLE_SWORD);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		if(!attacker.world.isRemote) {
			EntityIceBlock iceBlock = new EntityIceBlock(attacker.world, target, target.width, target.height);
			attacker.world.spawnEntity(iceBlock);
		}
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return OreDictionary.itemMatches(new ItemStack(Blocks.ICE), repair, false) || super.getIsRepairable(toRepair, repair);
	}
}
