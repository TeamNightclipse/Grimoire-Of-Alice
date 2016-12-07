/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAmenonuhoko extends ItemSwordOwner {

	public ItemAmenonuhoko(ToolMaterial material) {
		super(material, LibItemName.AMENONUHOKO);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.amenonuhoko_header.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.amenonuhoko_description_top.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.amenonuhoko_description_mid.name"));
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.amenonuhoko_description_bottom.name"));
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.amenonuhoko_shift.name"));
		}
		super.addInformation(stack, player, list, p_77624_4_);
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
		super.onUpdate(stack, world, entity, slot, selected);
		if(selected && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(!world.isRemote && !player.capabilities.isCreativeMode && player.getCooldownTracker().hasCooldown(this)) {
				player.fallDistance = 0.0F;
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		if(!stack.hasTagCompound()) return EnumActionResult.FAIL;

		//TODO: Replace with structure, structure is already in assets, How?
		if(isOwner(stack, player)) {
			pos = pos.offset(facing);

			if(!player.canPlayerEdit(new BlockPos(hitX, hitY, hitZ), facing, stack)) return EnumActionResult.PASS;
			else {
				player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

				if(!world.isRemote) {
					//Layer1
					replaceAirCompact(world, pos.west());
					replaceAirCompact(world, pos.south());
					replaceAirCompact(world, pos);
					replaceAirCompact(world, pos.north());
					replaceAirCompact(world, pos.east());

					//Layer2/3/4
					for(int i = 1; i <= 3; i++) {

						for(int j = -1; j <= 1; j++) {
							for(int k = -1; k <= 1; k++) {
								replaceAirCompact(world, pos.add(j, i, k));
							}
						}

						replaceAirCompact(world, pos.add(-2, i, 0));
						replaceAirCompact(world, pos.add(0, i, -2));
						replaceAirCompact(world, pos.add(0, i, 2));
						replaceAirCompact(world, pos.add(2, i, 0));
					}

					//Layer3 Corner
					replaceAirCompact(world, pos.add(-2, 2, -1));
					replaceAirCompact(world, pos.add(-2, 2, 1));

					replaceAirCompact(world, pos.add(-1, 2, -2));
					replaceAirCompact(world, pos.add(-1, 2, 2));

					replaceAirCompact(world, pos.add(1, 2, -2));
					replaceAirCompact(world, pos.add(1, 2, 2));

					replaceAirCompact(world, pos.add(2, 2, -1));
					replaceAirCompact(world, pos.add(2, 2, 1));

					//Layer5
					replaceAirCompact(world, pos.add(-1, 4, 0));
					replaceAirCompact(world, pos.add(0, 4, -1));
					replaceAirCompact(world, pos.add(0, 4, 0));
					replaceAirCompact(world, pos.add(0, 4, 1));
					replaceAirCompact(world, pos.add(1, 4, 0));
				}

				player.getCooldownTracker().setCooldown(this, 199);
			}
			return EnumActionResult.SUCCESS;
		}
		else return EnumActionResult.PASS;
	}

	private void replaceAirCompact(World world, BlockPos pos) {
		if(world.isAirBlock(pos)) {
			world.setBlockState(pos, ModBlocks.COMPACT_STONE.getDefaultState());
		}
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
