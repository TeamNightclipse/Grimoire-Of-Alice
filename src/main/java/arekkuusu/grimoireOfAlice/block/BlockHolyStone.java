/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyStone;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHolyStone extends BlockGOABase implements ITileEntityProvider{

	public BlockHolyStone(Material material) {
		super(material);
		setHardness(2.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
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
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
		world.removeTileEntity(x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int S, float X, float Y, float Z) {
		if (world.isRemote){
		ItemStack stack = player.getCurrentEquippedItem();
		if(stack != null && stack.getItem() == Items.gold_nugget) {
			--stack.stackSize;
			player.addExperience(5);
			return true;
			} else if(stack != null && stack.getItem() == Items.gold_ingot) {
				--stack.stackSize;
				player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2490, 0));
				return true;
				} else if(stack != null && stack.getItem() == Items.iron_ingot) {
					--stack.stackSize;
					player.addPotionEffect(new PotionEffect(Potion.resistance.id, 2490, 0));
					return true;
					} else if(stack != null && stack.getItem() == Items.blaze_powder) {
						--stack.stackSize;
						player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 2490, 0));
						return true;
						} else if(stack != null && stack.getItem() == Items.speckled_melon) {
							--stack.stackSize;
							player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2490, 0));
							return true;
							} else if(stack != null && stack.getItem() == Items.diamond) {
								--stack.stackSize;
								player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2490, 4));
								return true;
								} else {
										return false;
									}
		} else {
			return false;
			}
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? Blocks.stone.getBlockTextureFromSide(side) : side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(getTextureName() + "_side");
	}

}
