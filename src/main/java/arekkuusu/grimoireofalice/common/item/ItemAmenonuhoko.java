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
import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		RayTraceResult raytraceresult = traceResult(worldIn, playerIn);
		if(raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
		}
		else {
			createBoulder(playerIn, worldIn, raytraceresult.getBlockPos());
			return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
		}
	}

	@Nullable
	private RayTraceResult traceResult(World worldIn, EntityPlayer playerIn) {
		float pitch = playerIn.rotationPitch;
		float yaw = playerIn.rotationYaw;
		double x = playerIn.posX;
		double y = playerIn.posY + (double) playerIn.getEyeHeight();
		double z = playerIn.posZ;
		Vec3d pos = new Vec3d(x, y, z);
		float f2 = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
		float f3 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
		float f4 = -MathHelper.cos(-pitch * 0.017453292F);
		float yDir = MathHelper.sin(-pitch * 0.017453292F);
		float xDir = f3 * f4;
		float zDir = f2 * f4;
		double length = 15D;
		Vec3d vec3d1 = pos.addVector(xDir * length, yDir * length, zDir * length);
		return worldIn.rayTraceBlocks(pos, vec3d1, false, true, false);
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
			if(worldIn.isAirBlock(pos) || worldIn.getBlockState(pos).getMaterial() == Material.WATER) {
				return blockInfoIn;
			} else return null;
		}
	}
}
