/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCrestOfYggdrasill extends ItemModSword {

	ItemCrestOfYggdrasill(ToolMaterial material) {
		super(material, LibItemName.CRESTOFYGGDRASILL);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Contains time itself");
		list.add(TextFormatting.GRAY + "Once used by a great God to");
		list.add(TextFormatting.GRAY + "manipulate space and time");
		list.add(TextFormatting.RED + "[WARNING] EXPLOSIVE [WARNING]");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		worldIn.spawnEntityInWorld(new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY + 3, playerIn.posZ, true)); //Apparently needs to be summoned on both sides?
		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}


	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 4));
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		for(float i = 0; i < 5; i++) {
			worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY() - 4 + i, pos.getZ() + 0.5, 8.0F, true);
		}
		return true;
	}
}
