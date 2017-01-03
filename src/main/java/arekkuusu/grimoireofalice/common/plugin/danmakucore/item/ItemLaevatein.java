/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.entity.EntityFierySword;
import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.item.ItemModSword;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuTemplate;
import net.katsstuff.danmakucore.helper.DanmakuCreationHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

public class ItemLaevatein extends ItemModSword {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public ItemLaevatein(ToolMaterial material) {
		super(material, LibItemName.LAEVATEIN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.laevatein_header.name"));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(entity instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer)entity;
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		boolean isCreative = playerIn.capabilities.isCreativeMode;

		ItemStack fireCharge = new ItemStack(Items.FIRE_CHARGE);
		if (playerIn.isSneaking()) {
			if (isCreative || playerIn.inventory.hasItemStack(fireCharge)) {
				playerIn.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1F, itemRand.nextFloat() * 0.1F + 0.8F);
				if (!worldIn.isRemote) {
					for (int i = 0; i < 3; i++) {
						DanmakuTemplate danmaku = DanmakuTemplate.builder()
								.setUser(playerIn)
								.setShot(LibShotData.SHOT_SPHERE_DARK.setColor(LibColor.COLOR_SATURATED_RED).setSize(2F))
								.build();

						DanmakuCreationHelper.createRandomRingShot(danmaku, 1, 10, 5);
					}

					EntityMagicCircle circle = new EntityMagicCircle(worldIn, playerIn, EntityMagicCircle.EnumTextures.RED_NORMAL, 15);
					worldIn.spawnEntityInWorld(circle);
				}

				if (!isCreative) {
					//noinspection ConstantConditions
					if (playerIn.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
						//noinspection ConstantConditions
						playerIn.getCapability(ITEM_HANDLER_CAPABILITY, null).extractItem(getSlotFor(playerIn, fireCharge), 1, false);
					}
				}
				itemStackIn.damageItem(10, playerIn);
				playerIn.getCooldownTracker().setCooldown(this, 40);
			}
		}
		else {
			playerIn.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1F, itemRand.nextFloat() * 0.1F + 0.8F);
			if (!worldIn.isRemote) {
				EntityFierySword fierySword = new EntityFierySword(worldIn, playerIn);
				worldIn.spawnEntityInWorld(fierySword);
			}
			itemStackIn.damageItem(1, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, 30);
		}
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	private int getSlotFor(EntityPlayer player, ItemStack stack) {
		for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
			if (player.inventory.mainInventory[i] != null && stack.getItem() == player.inventory.mainInventory[i].getItem()
					&& ItemStack.areItemStackTagsEqual(stack, player.inventory.mainInventory[i])) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		BlockPos block = pos.offset(facing);

		if(!player.canPlayerEdit(block, facing, stack)) return EnumActionResult.PASS;
		else {
			boolean success = false;
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					BlockPos newPos = block.add(i, 0, j);
					if(world.isAirBlock(newPos)) {
						world.setBlockState(newPos, Blocks.FIRE.getDefaultState());
						success = true;
					}
				}
			}

			if(success) {
				player.playSound(SoundEvents.ENTITY_BLAZE_DEATH, 0.5F, itemRand.nextFloat() * 0.4F + 0.8F);
			}
			stack.damageItem(1, player);
			return success ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.setFire(3 + itemRand.nextInt(3));
		stack.damageItem(1, user);
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.BLAZE_ROD;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
