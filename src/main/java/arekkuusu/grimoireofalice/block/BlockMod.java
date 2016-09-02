package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.GOACreativeTab;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMod extends Block {

	public int light;
	
	public BlockMod(Material par2Material) {
		super(par2Material);
		setCreativeTab(GOACreativeTab.INSTANCE);
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		GameRegistry.registerBlock(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}
	
	@Override
	public Block setLightLevel(float p_149715_1_) {
		light = (int) (p_149715_1_ * 15);
		return super.setLightLevel(p_149715_1_);
	}
	
}
