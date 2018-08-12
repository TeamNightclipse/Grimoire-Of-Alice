/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;

public class ItemShichiSeiken extends ItemBaseSword  {

	public ItemShichiSeiken(ToolMaterial material) {
		super(material, LibName.SHICHI_SEIKEN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		stack.damageItem(1, entityLiving);
		if(ConfigHandler.grimoireOfAlice.features.allowGoodDrops) {
			boolean isOre = Arrays.stream(OreDictionary.getOreIDs(new ItemStack(state.getBlock())))
					.mapToObj(OreDictionary::getOreName)
					.anyMatch(s -> s.startsWith("ore"));
			Item item = state.getBlock().getItemDropped(state, world.rand, 0);
			if(!world.isRemote && item != Items.AIR && isOre) {
				for(int i = 0; i < world.rand.nextInt(3); i++) {
					EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(item));
					world.spawnEntity(entityItem);
				}
			}
		}
		return super.onBlockDestroyed(stack, world, state, pos, entityLiving);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}
}
