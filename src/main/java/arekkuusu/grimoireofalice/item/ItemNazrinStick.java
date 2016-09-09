/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNazrinStick extends ItemModSword {

	ItemNazrinStick(ToolMaterial material) {
		super(material, LibItemName.NAZRINSTICK);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Rare treasure from an old era");
		list.add(TextFormatting.ITALIC + "By holding it you become the");
		list.add(TextFormatting.ITALIC + "the Little Dowser General");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Use with Nazrin Sticks in both Hands");
			if(!isHoldingItemsBothHands(player, stack)){
				list.add(TextFormatting.DARK_RED + "Inactive");
			}
			else {
				list.add(TextFormatting.DARK_AQUA + "Active");
			}
		} else {
			list.add(TextFormatting.ITALIC + "Shift for details");
		}
	}

	/* In short, I made the Item only work when you
	 * put the same Item in the OFF_HAND and MAIN_HAND.*/
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		if(facing == EnumFacing.UP && !world.isRemote && isHoldingItemsBothHands(player, stack)){
			Block block_layer[] = new Block[10];
			for(int i = 1; i < block_layer.length; i++){
				block_layer[i - 1] = world.getBlockState(pos.down(i)).getBlock();
			}
			player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - -"));
			for(Block block : block_layer){
				if(block instanceof BlockOre){
					player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "You have found " + block.getLocalizedName() + "!"));
				}
			}
			player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - -"));
			stack.damageItem(1, player);
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(isHoldingItemsBothHands(playerIn, itemStackIn) && (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItemStack(new ItemStack(Items.COAL)) || playerIn.experienceLevel > 30)) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 75, 0));
			playerIn.setActiveHand(EnumHand.MAIN_HAND);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(player.experienceLevel > 30) {
				stack.damageItem(1, player);
			}
			else {
				player.inventory.deleteStack(new ItemStack(Items.COAL));
			}
			worldIn.createExplosion(player, player.posX - 5.0, player.posY, player.posZ - 5.0, 2.5F, false);
			worldIn.createExplosion(player, player.posX - 5.0, player.posY, player.posZ, 2.5F, false);
			worldIn.createExplosion(player, player.posX - 5.0, player.posY, player.posZ + 5.0, 2.5F, false);
			worldIn.createExplosion(player, player.posX, player.posY, player.posZ + 5.0, 2.5F, false);
			worldIn.createExplosion(player, player.posX, player.posY, player.posZ - 5.0, 2.5F, false);
			worldIn.createExplosion(player, player.posX + 5.0, player.posY, player.posZ - 5.0, 2.5F, false);
			worldIn.createExplosion(player, player.posX + 5.0, player.posY, player.posZ, 2.5F, false);
			worldIn.createExplosion(player, player.posX + 5.0, player.posY, player.posZ + 5.0, 2.5F, false);
		}
	}
	
	private boolean isHoldingItemsBothHands(EntityPlayer player, ItemStack stack){
		Item main;
		Item off;
		try{
			main = player.getHeldItem(EnumHand.OFF_HAND).getItem();
			off = player.getHeldItem(EnumHand.MAIN_HAND).getItem();
		} catch(NullPointerException e){
			return false;
		}
		if(main instanceof ItemNazrinStick && off instanceof ItemNazrinStick){
			return true;
		}
		return false;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
}
