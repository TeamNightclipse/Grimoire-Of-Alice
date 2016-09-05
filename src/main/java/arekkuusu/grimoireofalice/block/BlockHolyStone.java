/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHolyStone extends BlockMod {

	BlockHolyStone() {
		super(LibBlockName.HOLYSTONE,Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);
		//float size = 3F / 16F;
		//setBlockBounds(size, size, size, 1F - size, 1F - size, 1F - size);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Magical stone with its own weak gravity field");
		list.add(TextFormatting.ITALIC + "Accepts gifts as items");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote && heldItem != null) {
			Optional<Consumer<EntityPlayer>> effect = getEffectForItem(heldItem.getItem());
			if(effect.isPresent()) {
				--heldItem.stackSize;
				effect.get().accept(player);
				player.worldObj.playSound(player, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 0.1F, 1.0F);
				return true;
			}
		}

		return false;
	}

	private Optional<Consumer<EntityPlayer>> getEffectForItem(Item item) {
		if(item == Items.GOLD_NUGGET) {
			return Optional.of(player -> player.addExperience(5));
		}
		else if(item == Items.GOLD_INGOT) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2490, 0)));
		}
		else if(item == Items.IRON_INGOT) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2490, 0)));
		}
		else if(item == Items.BLAZE_POWDER) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2490, 0)));
		}
		else if(item == Items.SPECKLED_MELON) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2490, 0)));
		}
		else if(item == Items.DIAMOND) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2490, 4)));
		}

		return Optional.empty();
	}
}
