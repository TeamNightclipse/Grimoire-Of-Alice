/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import java.util.Optional;
import java.util.function.Consumer;

import arekkuusu.grimoireOfAlice.tile.TileEntityHolyStone;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

@CleanupDone
public class BlockHolyStone extends BlockGOABase implements ITileEntityProvider {

	BlockHolyStone() {
		super(Material.rock);
		setHardness(2.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);
		float size = 3F / 16F;
		setBlockBounds(size, size, size, 1F - size, 1F - size, 1F - size);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityHolyStone();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int S, float X, float Y, float Z) {
		ItemStack stack = player.getCurrentEquippedItem();
		if(!world.isRemote && stack != null) {
			Optional<Consumer<EntityPlayer>> effect = getEffectForItem(stack.getItem());
			if(effect.isPresent()) {
				--stack.stackSize;
				effect.get().accept(player);
				player.worldObj.playSoundAtEntity(player, "random.levelup", 0.1F, 1.0F);
				return true;
			}
		}

		return false;
	}

	private Optional<Consumer<EntityPlayer>> getEffectForItem(Item item) {
		if(item == Items.gold_nugget) {
			return Optional.of(player -> player.addExperience(5));
		}
		else if(item == Items.gold_ingot) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2490, 0)));
		}
		else if(item == Items.iron_ingot) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(Potion.resistance.id, 2490, 0)));
		}
		else if(item == Items.blaze_powder) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 2490, 0)));
		}
		else if(item == Items.speckled_melon) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2490, 0)));
		}
		else if(item == Items.diamond) {
			return Optional.of(player -> player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2490, 4)));
		}

		return Optional.empty();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 || side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(getTextureName() + "_side");
	}
}
