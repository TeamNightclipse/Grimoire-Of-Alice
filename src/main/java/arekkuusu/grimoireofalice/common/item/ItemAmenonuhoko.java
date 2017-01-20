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

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.amenonuhoko_header.name"));
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if (isOwner(itemStackIn, player)) {
			double range = 15.0D;
			Vec3d look = player.getLookVec();
			Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			Vec3d vec3d1 = new Vec3d(player.posX + look.xCoord * range, player.posY + player.getEyeHeight() + look.yCoord * range, player.posZ + look.zCoord * range);
			RayTraceResult raytraceresult = player.worldObj.rayTraceBlocks(vec3d, vec3d1, false, true, false);
			if (raytraceresult == null) return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
			else if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
				createBoulder(player, worldIn, raytraceresult.getBlockPos());
				return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
			}
		}
		return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
	}

	private void createBoulder(EntityPlayer player, World world, BlockPos pos) {
		player.playSound(SoundEvents.BLOCK_ANVIL_FALL, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

		if (!world.isRemote) {
			TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
			Template template = templatemanager.getTemplate(world.getMinecraftServer(), ResourceLocations.STRUCTURE_BOULDER);
			template.addBlocksToWorld(world, pos.add(-2, -2, -2), new BoulderTemplate(), new PlacementSettings(), 2);
		}

		player.getCooldownTracker().setCooldown(this, 50);
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	private class BoulderTemplate implements ITemplateProcessor {

		@Nullable
		@Override
		public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
			if(worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) || worldIn.getBlockState(pos).getMaterial() == Material.WATER) {
				return blockInfoIn;
			} else return null;
		}
	}
}
