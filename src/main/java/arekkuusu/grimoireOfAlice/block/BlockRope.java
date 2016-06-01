/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockRope extends BlockGOABase {

	@SideOnly(Side.CLIENT)
	private IIcon Bottom;
	@SideOnly(Side.CLIENT)
	private IIcon Top;
	@SideOnly(Side.CLIENT)
	private IIcon Front;
	@SideOnly(Side.CLIENT)
	private IIcon Back;
	@SideOnly(Side.CLIENT)
	private IIcon Sides;

	BlockRope(Material material) {
		super(material);
		setHardness(0.5F);
		setStepSound(Block.soundTypeSnow);
		setHarvestLevel("axe", 1);
		setResistance(5.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack stack) {
		int l = MathHelper.floor_double(livingBase.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		Bottom = icon.registerIcon(LibMod.MODID + ":Rope0");
		Top = icon.registerIcon(LibMod.MODID + ":Rope1");
		Front = icon.registerIcon(LibMod.MODID + ":Rope2");
		Back = icon.registerIcon(LibMod.MODID + ":Rope3");
		Sides = icon.registerIcon(LibMod.MODID + ":Rope4");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(meta > 5) {
			meta = 0;
		}
		if(side == 0) {
			return Bottom;
		}
		else if(side == 1) {
			return Top;
		}
		else if(side == 2) {
			return meta == 0 ? Front : meta == 1 ? Sides : meta == 2 ? Back : Sides;
		}
		else if(side == 3) {
			return meta == 0 ? Back : meta == 1 ? Sides : meta == 2 ? Front : Sides;
		}
		else if(side == 4) {
			return meta == 0 ? Sides : meta == 1 ? Back : meta == 2 ? Sides : Front;
		}
		else if(side == 5) { return meta == 0 ? Sides : meta == 1 ? Front : meta == 2 ? Sides : Back; }
		return null;
	}
}
