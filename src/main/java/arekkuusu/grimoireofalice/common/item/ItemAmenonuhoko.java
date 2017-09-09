/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.core.format.ItemFlavor;
import arekkuusu.grimoireofalice.common.core.format.FormattedString;
import arekkuusu.grimoireofalice.common.core.helper.MiscHelper;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import com.google.common.collect.ImmutableList;
import net.katsstuff.danmakucore.entity.living.boss.EnumTouhouCharacters;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;

import static net.minecraft.item.EnumRarity.RARE;
import static net.minecraft.util.text.TextFormatting.WHITE;

public class ItemAmenonuhoko extends ItemSwordOwner implements IOwnedBy {

	public static final ResourceLocation STRUCTURE_BOULDER = new ResourceLocation(LibMod.MOD_ID, "boulder");

	public ItemAmenonuhoko(ToolMaterial material) {
		super(material, LibItemName.AMENONUHOKO, ItemFlavor.simpleBuilder().common(
				FormattedString.withItalics(WHITE, "grimoire.tooltip.amenonuhoko_header.name")).shift(
				ImmutableList.of(FormattedString.withItalics("grimoire.tooltip.amenonuhoko_description_top.name"),
						FormattedString.withItalics("grimoire.tooltip.amenonuhoko_description_mid.name"),
						FormattedString.withItalics("grimoire.tooltip.amenonuhoko_description_bottom.name"))).effect(true).rarity(RARE).build());
		setNoRepair();
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		super.onUpdate(stack, world, entity, slot, selected);
		if(selected && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if(!world.isRemote && !player.capabilities.isCreativeMode && player.getCooldownTracker().hasCooldown(this)) {
				player.fallDistance = 0.0F;
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(isOwner(stack, player)) {
			RayTraceResult rayTraceResult = MiscHelper.rayTraceLook(player, 15.0D);
			if(rayTraceResult == null) {
				return new ActionResult<>(EnumActionResult.PASS, stack);
			}
			else if(rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
				createBoulder(player, world, rayTraceResult.getBlockPos());
				return new ActionResult<>(EnumActionResult.SUCCESS, stack);
			}
		}
		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	private void createBoulder(EntityPlayer player, World world, BlockPos pos) {
		player.playSound(SoundEvents.BLOCK_ANVIL_FALL, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

		if(!world.isRemote) {
			TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
			Template template = templatemanager.getTemplate(world.getMinecraftServer(), STRUCTURE_BOULDER);
			template.addBlocksToWorld(world, pos.add(-2, -2, -2), new BoulderTemplate(), new PlacementSettings(), 2);
		}

		player.getCooldownTracker().setCooldown(this, 50);
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	private static class BoulderTemplate implements ITemplateProcessor {

		@Nullable
		@Override
		public Template.BlockInfo processBlock(World world, BlockPos pos, Template.BlockInfo blockInfoIn) {
			if(world.getBlockState(pos).getBlock().isReplaceable(world, pos) || world.getBlockState(pos).getMaterial() == Material.WATER) {
				return blockInfoIn;
			}
			else {
				return null;
			}
		}
	}

	@Override
	public EnumTouhouCharacters character(ItemStack stack) {
		return EnumTouhouCharacters.RINNOSUKE_MORICHIKA;
	}
}
