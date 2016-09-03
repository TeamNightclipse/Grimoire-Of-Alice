/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAmenonuhoko extends ItemModSword {

	ItemAmenonuhoko(ToolMaterial material) {
		super(material, LibItemName.AMENONUHOKO);
		setMaxDamage(200);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if(stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
	    stack.getTagCompound().setString("GrimoireOwner", player.getDisplayName().getFormattedText());
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Heavenly jeweled spear");
		list.add(TextFormatting.GRAY + "Created by Elder Gods");
		list.add(TextFormatting.ITALIC + "Once used to raise the");
		list.add(TextFormatting.ITALIC + "primordial land-mass,");
		list.add(TextFormatting.ITALIC + "Onogoro-shima, from the sea");
		if(stack.hasTagCompound()) {
			list.add(TextFormatting.ITALIC + "Property of " + stack.getTagCompound().getString("GrimoireOwner"));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(stack.getItemDamage() < stack.getMaxDamage()) {
			stack.setItemDamage(stack.getItemDamage() - 1);
		}

		if(selected && entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer)entity;
			if(!world.isRemote && !entityplayer.capabilities.isCreativeMode) {
				ItemStack held = entityplayer.getHeldItemMainhand();
				if(held != null && held.getItem() == ModItems.amenonuhoko) {
					if(stack.getItemDamage() > 0) {
						entityplayer.fallDistance = 0.0F;
					}
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(itemStackIn.getItemDamage() == 0) {
			itemStackIn.damageItem(199, playerIn);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		if(!stack.hasTagCompound()) return EnumActionResult.FAIL;

		//TODO: Replace with structure
		if(stack.getItemDamage() == 0 && stack.getTagCompound().getString("GrimoireOwner").equals(player.getDisplayName().getFormattedText())) {
			int side = facing.getIndex();
			if(side == 0) {
				--y;
			}

			if(side == 1) {
				++y;
			}

			if(side == 2) {
				--z;
			}

			if(side == 3) {
				++z;
			}

			if(side == 4) {
				--x;
			}

			if(side == 5) {
				++x;
			}

			if(!player.canPlayerEdit(new BlockPos(x, y, z), facing, stack)) {
				return EnumActionResult.PASS;
			}
			else {
				world.playSound(player, new BlockPos(x + 0.5D, y + 0.5D, z + 0.5D),
						SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

				//Layer1
				replaceAirComact(world, x - 1, y, z);
				replaceAirComact(world, x, y, z - 1);
				replaceAirComact(world, x, y, z);
				replaceAirComact(world, x, y, z + 1);
				replaceAirComact(world, x + 1, y, z);

				//Layer2/3/4
				for(int i = 1; i <= 3; i++) {

					for(int j = -1; j <= 1; j++) {
						for(int k = -1; k <= 1; k++) {
							replaceAirComact(world, x + j, y + i, z + k);
						}
					}

					replaceAirComact(world, x - 2, y + i, z);
					replaceAirComact(world, x, y + i, z - 2);
					replaceAirComact(world, x, y + i, z + 2);
					replaceAirComact(world, x + 2, y + i, z);
				}

				//Layer3 Corner
				replaceAirComact(world, x - 2, y + 2, z - 1);
				replaceAirComact(world, x - 2, y + 2, z + 1);

				replaceAirComact(world, x - 1, y + 2, z - 2);
				replaceAirComact(world, x - 1, y + 2, z + 2);

				replaceAirComact(world, x + 1, y + 2, z - 2);
				replaceAirComact(world, x + 1, y + 2, z + 2);

				replaceAirComact(world, x + 2, y + 2, z + 1);
				replaceAirComact(world, x + 2, y + 2, z - 1);

				//Layer5
				replaceAirComact(world, x - 1, y + 4, z);
				replaceAirComact(world, x, y + 4, z - 1);
				replaceAirComact(world, x, y + 4, z);
				replaceAirComact(world, x, y + 4, z + 1);
				replaceAirComact(world, x + 1, y + 4, z);

				stack.damageItem(199, player);
			}
			return EnumActionResult.SUCCESS;
		}
		else {
			return EnumActionResult.PASS;
		}
	}

	private void replaceAirComact(World world, float x, float y, float z) {
		BlockPos pos = new BlockPos(x, y, z);
		if(world.isAirBlock(pos)) {
			world.setBlockState(pos, ModBlocks.compactStone.getDefaultState());
		}
	}
}
