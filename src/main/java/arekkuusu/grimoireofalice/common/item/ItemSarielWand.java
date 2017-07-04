/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerProfileCache;
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
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemSarielWand extends ItemSwordOwner {

	private static final String SKULL_TYPE = "SkullType";
	private static final String PLAYER_UUID = "PlayerUUID";

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
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sariel_wand_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sariel_wand_description.name"));
		list.add("");
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.sariel_wand_mode.name") + " " + getName(stack));
		super.addInformation(stack, player, list, p_77624_4_);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!stack.hasTagCompound()) {
			return new ActionResult<>(EnumActionResult.FAIL, stack);
		}

		if(isOwner(stack, player)) {
			player.setActiveHand(hand);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			setProfile(stack, player);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if(facing != EnumFacing.DOWN) {
			Block block = world.getBlockState(pos).getBlock();
			if(block.isReplaceable(world, pos)) {
				facing = EnumFacing.UP;
				pos = pos.down();
			}
			else {
				if(!world.getBlockState(pos).getMaterial().isSolid() && !world.isSideSolid(pos, facing, true)) {
					return EnumActionResult.FAIL;
				}
				pos = pos.offset(facing);
			}

			if(player.canPlayerEdit(pos, facing, stack) && Blocks.SKULL.canPlaceBlockAt(world, pos)) {
				if(!world.isRemote) {
					world.setBlockState(pos, Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, facing), 11);
					int i = 0;

					if(facing == EnumFacing.UP) {
						i = MathHelper.floor((player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
					}

					TileEntity tileentity = world.getTileEntity(pos);

					if(tileentity instanceof TileEntitySkull) {
						TileEntitySkull tileentityskull = (TileEntitySkull) tileentity;

						int type = getType(stack);
						if(type == 3) {
							tileentityskull.setPlayerProfile(getProfile(stack));
						}
						else {
							tileentityskull.setType(type);
						}

						tileentityskull.setSkullRotation(i);
						Blocks.SKULL.checkWitherSpawn(world, pos, tileentityskull);
					}
				}
				player.getCooldownTracker().setCooldown(this, 500);
				return EnumActionResult.SUCCESS;
			}
		}

		return EnumActionResult.FAIL;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(target instanceof EntitySkeleton) {
			setType(stack, 0);
		}
		else if(target instanceof EntityWitherSkeleton) {
			setType(stack, 1);
		}
		else if(target instanceof EntityZombie) {
			setType(stack, 2);
		}
		else if(target instanceof EntityPlayer) {
			setProfile(stack, (EntityPlayer) target);
		}
		else if(target instanceof EntityCreeper) {
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
		if(nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		nbt.setInteger(SKULL_TYPE, type);
		nbt.removeTag(PLAYER_UUID + "Most");
		nbt.removeTag(PLAYER_UUID + "Least");
	}

	private void setProfile(ItemStack stack, EntityPlayer player) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		nbt.setInteger(SKULL_TYPE, 3);
		nbt.setUniqueId(PLAYER_UUID, player.getUniqueID());
	}

	@Nullable
	private GameProfile getProfile(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt != null) {
			UUID uuid = nbt.getUniqueId(PLAYER_UUID);
			return uuid == null ? null : getCache().getProfileByUUID(uuid);
		}
		else {
			return null;
		}
	}

	@SideOnly(Side.CLIENT)
	private String getName(ItemStack stack) {
		int type = getType(stack);
		switch(type) {
			case 3:
				NBTTagCompound nbt = stack.getTagCompound();

				if(nbt != null) {
					UUID uuid = nbt.getUniqueId(PLAYER_UUID);

					if(uuid != null) {
						GameProfile profile = getCache().getProfileByUUID(uuid);

						if(profile != null) {
							return profile.getName();
						}
					}
				}

				return I18n.format("grimoire.tooltip.skull_3.name");
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

	private PlayerProfileCache getCache() {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerProfileCache();
	}
}
