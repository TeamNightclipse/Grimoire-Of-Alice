package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockCraftingAltar extends BlockMod {
    public BlockCraftingAltar() {
        super(LibBlockName.CRAFTINGALTAR, Material.ROCK);
        setHardness(2.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("axe", 1);
        setResistance(2000.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
        list.add(TextFormatting.GOLD + "Not your everyday crafting altar");
        list.add(TextFormatting.ITALIC + "Aghhhh place me down!");
    }
}
