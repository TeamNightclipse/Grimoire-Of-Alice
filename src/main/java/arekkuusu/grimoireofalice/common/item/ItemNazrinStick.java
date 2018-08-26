/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.Block;
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
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemNazrinStick extends ItemBaseSword {

	private static final String TYPE = "type";

	@SuppressWarnings("ConstantConditions")
	@ItemStackHolder(value = LibMod.MOD_ID + ":" + LibName.NAZRIN_STICK, nbt = "{" + TYPE + ":0b}")
	public static final ItemStack TYPEA = new ItemStack(Item.getItemFromBlock(Blocks.BEDROCK));

	@SuppressWarnings("ConstantConditions")
	@ItemStackHolder(value = LibMod.MOD_ID + ":" + LibName.NAZRIN_STICK, nbt = "{" + TYPE + ":1b}")
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
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if(isInCreativeTab(tab)) {
			subItems.add(TYPEA);
			subItems.add(TYPEB);
		}
	}

	/* In short, I made the Item only work when you
	 * put the two Nazrin Items in the OFF_HAND and MAIN_HAND.*/
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		if(facing == EnumFacing.UP && isHoldingItemsBothHands(player)) {
			if(!world.isRemote) {
				List<Block> blockLayer = new ArrayList<>(10);
				for(int i = 1; i < 10; i++) {
					Block block = world.getBlockState(pos.down(i)).getBlock();
					boolean isOre = Arrays.stream(OreDictionary.getOreIDs(new ItemStack(block)))
							.mapToObj(OreDictionary::getOreName)
							.anyMatch(s -> s.startsWith("ore"));
					if(isOre) {
						blockLayer.add(block);
					}
				}
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - -"));
				if(!blockLayer.isEmpty()) {
					blockLayer.forEach(block -> player.sendMessage(
							new TextComponentString(TextFormatting.GOLD + "You have found " + block.getLocalizedName() + "!")));
				}
				else {
					player.sendMessage(new TextComponentString(TextFormatting.GOLD + "You didn't find anything."));
				}
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "- - - - - - - - - - - - - - -"));
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
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(player.isSneaking() && isHoldingItemsBothHands(player)
				&& (player.capabilities.isCreativeMode || player.inventory.hasItemStack(new ItemStack(Items.COAL))
				|| player.experienceLevel > 30)) {
			player.setActiveHand(hand);
			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<>(EnumActionResult.FAIL, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;

			if(!player.capabilities.isCreativeMode) {
				if(player.experienceLevel > 30) {
					stack.damageItem(1, player);
				}
				else {
					player.inventory.deleteStack(new ItemStack(Items.COAL));
				}
			}
			if(!world.isRemote) {
				EntityMagicCircle circle = new EntityMagicCircle(world, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 50);
				world.spawnEntity(circle);
			}
			player.getCooldownTracker().setCooldown(this, 50);
			world.createExplosion(player, player.posX - 5.0, player.posY, player.posZ - 5.0, 2.5F, false);
			world.createExplosion(player, player.posX - 5.0, player.posY, player.posZ, 2.5F, false);
			world.createExplosion(player, player.posX - 5.0, player.posY, player.posZ + 5.0, 2.5F, false);
			world.createExplosion(player, player.posX, player.posY, player.posZ + 5.0, 2.5F, false);
			world.createExplosion(player, player.posX, player.posY, player.posZ - 5.0, 2.5F, false);
			world.createExplosion(player, player.posX + 5.0, player.posY, player.posZ - 5.0, 2.5F, false);
			world.createExplosion(player, player.posX + 5.0, player.posY, player.posZ, 2.5F, false);
			world.createExplosion(player, player.posX + 5.0, player.posY, player.posZ + 5.0, 2.5F, false);
		}
	}

	@Override
	public String getTranslationKey(ItemStack stack) {
		return super.getTranslationKey(stack) + "." + getType(stack);
	}

	private boolean isHoldingItemsBothHands(EntityPlayer player) {
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		return !main.isEmpty() && !off.isEmpty() && main.isItemEqualIgnoreDurability(off) && getType(main) != getType(off);
	}

	@SuppressWarnings("ConstantConditions")
	public byte getType(ItemStack stack) {
		return !stack.hasTagCompound() ? 0 : stack.getTagCompound().getByte(TYPE);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72_000;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
