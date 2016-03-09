package arekkuusu.grimoireOfAlice.item;

import arekkuusu.grimoireOfAlice.entity.projectile.EntityThrowingNeedleDoll;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemThrowingNeedleDoll extends ItemGOABase{

	public ItemThrowingNeedleDoll(){
		
	super();
	setMaxStackSize(1);
	setCreativeTab(CreativeTabs.tabCombat);
	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack){
		
		return EnumRarity.uncommon;
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
	if (!player.capabilities.isCreativeMode) {
		
	--stack.stackSize;
	
	}

	world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

	if (!world.isRemote) {
		
	world.spawnEntityInWorld(new EntityThrowingNeedleDoll(world, player));
	
	}

	return stack;
	
	}
	
}
