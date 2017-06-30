package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityIceBlock;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemIcicleSword extends ItemModSword implements IOwnedBy {

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
			attacker.world.spawnEntity(iceBlock);
		}
		return true;
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return OreDictionary.itemMatches(new ItemStack(Blocks.ICE), repair, false) || super.getIsRepairable(toRepair, repair);
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters.CIRNO;
	}
}
