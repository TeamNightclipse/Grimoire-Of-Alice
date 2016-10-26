/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import arekkuusu.grimoireofalice.lib.LibItemName;

public class ItemSarielWand extends ItemSwordOwner {

	public ItemSarielWand(ToolMaterial material) {
		super(material, LibItemName.SARIEL_WAND);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_PURPLE + "Immense Knowledge, Angel of Death, Commaner of God...");
		list.add(TextFormatting.ITALIC + "Legendary wand from a fallen Angel");
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if(!itemStackIn.hasTagCompound()) return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
		if(isOwner(itemStackIn, player)){
			if(!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX - 5.0, player.posY, player.posZ - 5.0, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX - 5.0, player.posY, player.posZ, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX - 5.0, player.posY, player.posZ + 5.0, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX, player.posY, player.posZ + 5.0, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX, player.posY, player.posZ - 5.0, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX + 5.0, player.posY, player.posZ - 5.0, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX + 5.0, player.posY, player.posZ, false));
				worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, player.posX + 5.0, player.posY, player.posZ + 5.0, false));
			}
			itemStackIn.damageItem(10, player);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		worldIn.setBlockState(pos.up(1), Blocks.SKULL.getDefaultState());
		return EnumActionResult.SUCCESS;
	}

	@Override // Why hit the entity if its going to dye anyway? :p
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		player.attackEntityFrom(DamageSource.magic, 9999);
		entity.attackEntityFrom(DamageSource.magic, 9999);
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
