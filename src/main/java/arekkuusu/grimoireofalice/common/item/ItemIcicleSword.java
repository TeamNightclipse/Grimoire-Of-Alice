package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityIceBlock;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemIcicleSword extends ItemModSword {

	public ItemIcicleSword(ToolMaterial material) {
		super(material, LibItemName.ICICLE_SWORD);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.icicle_sword_header.name"));
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		if (!attacker.world.isRemote) {
			EntityIceBlock iceBlock = new EntityIceBlock(attacker.world, target, target.width, target.height);
			attacker.world.spawnEntityInWorld(iceBlock);
		}
		return true;
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return OreDictionary.itemMatches(new ItemStack(Blocks.ICE), repair, false) || super.getIsRepairable(toRepair, repair);
	}
}
