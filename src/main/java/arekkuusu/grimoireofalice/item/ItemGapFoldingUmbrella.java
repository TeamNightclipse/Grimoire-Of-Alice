package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ItemGapFoldingUmbrella extends ItemMod {

	ItemGapFoldingUmbrella() {
		super(LibItemName.FOLDINGUMBRELLA);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		addPropertyOverride(new ResourceLocation("playing"), (stack, world, entity) ->
				entity != null && entity instanceof EntityPlayer &&((EntityPlayer)entity).getCooldownTracker().hasCooldown(stack.getItem()) ? 1F : 0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Magical umbrella that teleports players");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Right click to teleport up to 40 blocks");
			list.add(TextFormatting.ITALIC + "to the direction you are looking");
		} else {
			list.add(TextFormatting.ITALIC + "SHIFT for details");
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		Vec3d look = playerIn.getLookVec();
		float distance = 40F;
		double dx = playerIn.posX + (look.xCoord * distance);
		double dy = playerIn.posY + 1 + (look.yCoord * distance);
		double dz = playerIn.posZ + (look.zCoord * distance);
		if(isSafe(worldIn, dx, dy, dz)) {
			playerIn.setPosition(dx, dy, dz);
		}
		itemStackIn.damageItem(10, playerIn);
		worldIn.playSound(null, new BlockPos(playerIn.posX + 0.5D, playerIn.posY + 0.5D, playerIn.posZ + 0.5D),
				SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		playerIn.getCooldownTracker().setCooldown(this,10);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	private boolean isSafe(World world, double x, double y, double z){
		BlockPos pos = new BlockPos(x, y, z);
		Block block = world.getBlockState(pos).getBlock();
		return block == Blocks.AIR || block == Blocks.DOUBLE_PLANT || block == Blocks.TALLGRASS;
	}
}
