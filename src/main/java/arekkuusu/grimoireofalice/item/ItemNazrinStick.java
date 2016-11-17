/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemNazrinStick extends ItemModSword {

	public static final String TYPE = "type";

	@SuppressWarnings("ConstantConditions")
	@GameRegistry.ItemStackHolder(value = LibMod.MODID + ":" + LibItemName.NAZRIN_STICK, nbt = "{" + TYPE + ":0b}")
	public static final ItemStack TYPEA = new ItemStack(Item.getItemFromBlock(Blocks.BEDROCK));

	@SuppressWarnings("ConstantConditions")
	@GameRegistry.ItemStackHolder(value = LibMod.MODID + ":" + LibItemName.NAZRIN_STICK, nbt = "{" + TYPE + ":1b}")
	public static final ItemStack TYPEB = new ItemStack(Item.getItemFromBlock(Blocks.BEDROCK));

	public ItemNazrinStick(ToolMaterial material, String id) {
		super(material, id);
		addPropertyOverride(new ResourceLocation("stick"),
				(stack, world, entity) -> stack.hasTagCompound() ? getType(stack) : 0F);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		subItems.add(TYPEA);
		subItems.add(TYPEB);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.nazrin_stick_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_stick_description_top.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_stick_description_bottom.name"));
		if(GuiScreen.isShiftKeyDown()) {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_stick_use.name"));
			if(!isHoldingItemsBothHands(player)) {
				list.add(TextFormatting.DARK_RED + I18n.format("grimoire.tooltip.nazrin_stick_inactive.name"));
			}
			else {
				list.add(TextFormatting.DARK_AQUA + I18n.format("grimoire.tooltip.nazrin_stick_active.name"));
			}
		}
		else {
			list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.nazrin_stick_shift.name"));
		}
	}

	/* In short, I made the Item only work when you
	 * put the two Nazrin Items in the OFF_HAND and MAIN_HAND.*/
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x,
			float y, float z) {
		if(facing == EnumFacing.UP && isHoldingItemsBothHands(player)) {
			if(!world.isRemote) {
				List<Block> blockLayer = new ArrayList<>(10);
				for(int i = 1; i < 10; i++) {
					Block block = world.getBlockState(pos.down(i)).getBlock();
					if(block == null) continue;
						boolean isOre = Arrays.stream(OreDictionary.getOreIDs(new ItemStack(block)))
								.mapToObj(OreDictionary::getOreName)
								.anyMatch(s -> s.startsWith("ore"));
					if(isOre) {
						blockLayer.add(block);
					}
				}
				player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - -"));
				if(!blockLayer.isEmpty()) {
					blockLayer.forEach(block -> player.addChatComponentMessage(
							new TextComponentString(TextFormatting.GOLD + "You have found " + block.getLocalizedName() + "!")));
				}
				else {
					player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "You didn't find anything."));
				}
				player.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - -"));
				stack.damageItem(1, player);
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.FAIL;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30, 0));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(isHoldingItemsBothHands(playerIn) && (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItemStack(new ItemStack(Items.COAL))
				|| playerIn.experienceLevel > 30)) {
			playerIn.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
		}
		return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;

			if(!player.capabilities.isCreativeMode) {
				if(player.experienceLevel > 30) {
					stack.damageItem(1, player);
				}
				else {
					player.inventory.deleteStack(new ItemStack(Items.COAL));
				}
			}
			if(!worldIn.isRemote) {
				EntityMagicCircle circle = new EntityMagicCircle(worldIn, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 50);
				worldIn.spawnEntityInWorld(circle);
			}
			player.getCooldownTracker().setCooldown(this, 50);
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

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + getType(stack);
	}

	private boolean isHoldingItemsBothHands(EntityPlayer player) {
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		return main != null && off != null && main.isItemEqualIgnoreDurability(off) && getType(main) != getType(off);
	}

	@SuppressWarnings("ConstantConditions")
	public byte getType(ItemStack stack) {
		return !stack.hasTagCompound() ? 0 : stack.getTagCompound().getByte(TYPE);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
