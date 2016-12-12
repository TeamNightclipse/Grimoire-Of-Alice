/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;
import java.util.UUID;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSarielWand extends ItemSwordOwner {

	private final String SKULL_TYPE = "SkullType";
	private final String PLAYER_UUID = "PlayerUUID";
	private final String PLAYER_NAME = "PlayerName";

	public ItemSarielWand(ToolMaterial material) {
		super(material, LibItemName.SARIEL_WAND);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sariel_wand_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sariel_wand_description.name"));
		list.add("");
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sariel_wand_mode.name") + " " + getName(stack));
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player, EnumHand hand) {
		if (!itemStackIn.hasTagCompound()) return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
		if (isOwner(itemStackIn, player)) {
			player.setActiveHand(hand);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			setProfile(stack, player);
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.DOWN) {
			Block block = worldIn.getBlockState(pos).getBlock();
			if (block.isReplaceable(worldIn, pos)) {
				facing = EnumFacing.UP;
				pos = pos.down();
			} else {
				if (!worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.isSideSolid(pos, facing, true)) {
					return EnumActionResult.FAIL;
				}
				pos = pos.offset(facing);
			}

			if (playerIn.canPlayerEdit(pos, facing, stack) && Blocks.SKULL.canPlaceBlockAt(worldIn, pos)) {
				if (!worldIn.isRemote) {
					worldIn.setBlockState(pos, Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, facing), 11);
					int i = 0;

					if (facing == EnumFacing.UP) {
						i = MathHelper.floor_double((double) (playerIn.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
					}

					TileEntity tileentity = worldIn.getTileEntity(pos);

					if (tileentity instanceof TileEntitySkull) {
						TileEntitySkull tileentityskull = (TileEntitySkull) tileentity;

						int type = getType(stack);
						if (type == 3) {
							tileentityskull.setPlayerProfile(getProfile(stack));
						}
						else {
							tileentityskull.setType(type);
						}

						tileentityskull.setSkullRotation(i);
						Blocks.SKULL.checkWitherSpawn(worldIn, pos, tileentityskull);
					}
				}
				playerIn.getCooldownTracker().setCooldown(this, 500);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (target instanceof EntitySkeleton) {
			EntitySkeleton skeleton = (EntitySkeleton) target;
			if (skeleton.getSkeletonType() == SkeletonType.NORMAL) {
				setType(stack, 0);
			} else {
				setType(stack, 1);
			}
		} else if (target instanceof EntityZombie) {
			setType(stack, 2);
		} else if (target instanceof EntityPlayer) {
			setProfile(stack, (EntityPlayer) target);
		} else if (target instanceof EntityCreeper) {
			setType(stack, 4);
		}
		return true;
	}

	private int getType(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		return nbt == null ? 0 : nbt.getInteger(SKULL_TYPE);
	}

	private void setType(ItemStack stack, int type) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		nbt.setInteger(SKULL_TYPE, type);
	}

	private void setProfile(ItemStack stack, EntityPlayer player) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		nbt.setInteger(SKULL_TYPE, 3);
		nbt.setString(PLAYER_NAME, player.getGameProfile().getName());
		nbt.setString(PLAYER_UUID, player.getGameProfile().getId().toString());
	}

	private GameProfile getProfile(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		return nbt == null ? null : new GameProfile(UUID.fromString(nbt.getString(PLAYER_UUID)), nbt.getString(PLAYER_NAME));
	}

	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
	private String getName(ItemStack stack) {
		int type = getType(stack);
		switch (type) {
			case 3:
				NBTTagCompound nbt = stack.getTagCompound();
				return nbt != null && nbt.hasKey(PLAYER_NAME)
						? nbt.getString(PLAYER_NAME)
						: I18n.format("grimoire.tooltip.skull_3.name");
			default:
				return I18n.format("grimoire.tooltip.skull_" + type + ".name");
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
