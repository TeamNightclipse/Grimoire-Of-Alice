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
import java.util.UUID;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.UsernameCache;
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
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setUniqueId("GrimoireOwner", player.getUniqueID());
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
			UUID ownerUuid = stack.getTagCompound().getUniqueId("GrimoireOwner");
			if(ownerUuid != null) {
				if(UsernameCache.containsUUID(ownerUuid)) {
					list.add(TextFormatting.ITALIC + "Property of " + UsernameCache.getLastKnownUsername(ownerUuid));
				}
			}
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
			EntityPlayer player = (EntityPlayer)entity;

			if(!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			NBTTagCompound compound = stack.getTagCompound();
			//noinspection ConstantConditions
			if(!compound.hasKey("GrimoireOwner")) {
				compound.setUniqueId("GrimoireOwner", player.getUniqueID());
			}

			if(!world.isRemote && !player.capabilities.isCreativeMode) {
				ItemStack held = player.getHeldItemMainhand();
				if(held != null && held.getItem() == ModItems.amenonuhoko) {
					if(stack.getItemDamage() > 0) {
						player.fallDistance = 0.0F;
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
		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!stack.hasTagCompound()) return EnumActionResult.FAIL;

		//TODO: Replace with structure, structure is already in assets
		if(stack.getItemDamage() == 0 && player.getUniqueID().equals(stack.getTagCompound().getUniqueId("GrimoireOwner"))) {
			pos = pos.offset(facing);

			if(!player.canPlayerEdit(new BlockPos(hitX, hitY, hitZ), facing, stack)) {
				return EnumActionResult.PASS;
			}
			else {
				player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

				if(!world.isRemote) {
					//Layer1
					replaceAirComact(world, pos.west());
					replaceAirComact(world, pos.south());
					replaceAirComact(world, pos);
					replaceAirComact(world, pos.north());
					replaceAirComact(world, pos.east());
				}

				//Layer2/3/4
				for(int i = 1; i <= 3; i++) {

					for(int j = -1; j <= 1; j++) {
						for(int k = -1; k <= 1; k++) {
							replaceAirComact(world, pos.add(j, i, k));
						}
					}

					replaceAirComact(world, pos.add(-2, i, 0));
					replaceAirComact(world, pos.add(0, i, -2));
					replaceAirComact(world, pos.add(0, i, 2));
					replaceAirComact(world, pos.add(2, i, 0));
				}

				//Layer3 Corner
				replaceAirComact(world, pos.add(-2, 2, -1));
				replaceAirComact(world, pos.add(-2, 2, 1));

				replaceAirComact(world, pos.add(-1, 2, -2));
				replaceAirComact(world, pos.add(-1, 2, 2));

				replaceAirComact(world, pos.add(1, 2, -2));
				replaceAirComact(world, pos.add(1, 2, 2));

				replaceAirComact(world, pos.add(2, 2, -1));
				replaceAirComact(world, pos.add(2, 2, 1));

				//Layer5
				replaceAirComact(world, pos.add(-1, 4, 0));
				replaceAirComact(world, pos.add(0, 4, -1));
				replaceAirComact(world, pos.add(0, 4, 0));
				replaceAirComact(world, pos.add(0, 4, 1));
				replaceAirComact(world, pos.add(1, 4, 0));

				stack.damageItem(199, player);
			}
			return EnumActionResult.SUCCESS;
		}
		else {
			return EnumActionResult.PASS;
		}
	}

	private void replaceAirComact(World world, BlockPos pos) {
		if(world.isAirBlock(pos)) {
			world.setBlockState(pos, ModBlocks.compactStone.getDefaultState());
		}
	}
}
