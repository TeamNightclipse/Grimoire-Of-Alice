/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.plugin.danmakucore.item;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.entity.EntityEllyScythe;
import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.item.ItemModSword;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.katsstuff.danmakucore.data.AbstractVector3;
import net.katsstuff.danmakucore.data.Vector3;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.helper.DanmakuHelper;
import net.katsstuff.danmakucore.helper.TouhouHelper;
import net.katsstuff.danmakucore.lib.LibColor;
import net.katsstuff.danmakucore.lib.data.LibShotData;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEllyScythe extends ItemModSword {

	public ItemEllyScythe(ToolMaterial material) {
		super(material, LibItemName.ELLY_SCYTHE);
		setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "War ma fé, heman zo eun Anko drouk");
		if(GuiScreen.isShiftKeyDown()){
			list.add(TextFormatting.ITALIC + "Oberour ar maro known as the grave");
			list.add(TextFormatting.ITALIC + "yard watcher, they said that he");
			list.add(TextFormatting.ITALIC + "protects the graveyard and the souls");
			list.add(TextFormatting.ITALIC + "around it for some unknown reason and");
			list.add(TextFormatting.ITALIC + "collects the lost souls on his land");
		} else {
			list.add(TextFormatting.ITALIC + "Shift for details");
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		if(user instanceof EntityPlayer) {
			if(target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 64, 0));
			}
			else {
				target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 64, 0));
			}
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 64, 0));
			user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 64, 3));
		}
		return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			if(player.isSneaking()) {
				int duration = getMaxItemUseDuration(stack) - timeLeft;
				float durationSeconds = duration / 20F;
				//TODO: What does this do?
				durationSeconds = (durationSeconds * durationSeconds + durationSeconds * 2.0F) / 3F;
				if (durationSeconds < 0.1F) return;

				boolean critical = false;
				if (durationSeconds > 1.5F) {
					durationSeconds = 1.5F;
					critical = true;
				}
				durationSeconds *= 1.5F;

				DanmakuHelper.playShotSound(player);
				if (!worldIn.isRemote) {
					EntityEllyScythe scythe = new EntityEllyScythe(worldIn, player, stack, durationSeconds);
					scythe.setCritical(critical);
					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack) > 0) {
						scythe.setFire(100);
					}
					Vec3d look = player.getLookVec();
					float distance = 1.5F;
					double dx = player.posX + (look.xCoord * distance);
					double dz = player.posZ + (look.zCoord * distance);
					scythe.setPosition(dx, player.posY + 1.5, dz);
					worldIn.spawnEntityInWorld(scythe);
				}

				if (!player.capabilities.isCreativeMode) {
					if (--stack.stackSize == 0) {
						stack = null;
					}
					player.inventory.mainInventory[player.inventory.currentItem] = stack;
				}
			} else if(!worldIn.isRemote) {
				for(int i = 0; i < 25; i++) {
					DanmakuHelper.playShotSound(player);
					spawnGroundDanmaku(player);
				}
				EntityMagicCircle circle = new EntityMagicCircle(worldIn, player, EntityMagicCircle.EnumTextures.RED_NORMAL, 50);
				worldIn.spawnEntityInWorld(circle);
				player.getCooldownTracker().setCooldown(this, 50);
			}
		}
	}

	//Taken from DanmakuCore in SpellcardEntityDelusionEnlightenment
	private void spawnGroundDanmaku(EntityPlayer player) {
		AbstractVector3 angle = Vector3.getVecWithoutY(Vector3.randomVector());
		Random random = new Random();
		Vector3 posSource = new Vector3(player).offset(angle, random.nextDouble() * 16);
		Vector3 posReach = posSource.offset(Vector3.Down(), 16);

		RayTraceResult ray = player.worldObj.rayTraceBlocks(posSource.toVec3d(), posReach.toVec3d());

		Vector3 spawnPos = ray != null ?
				new Vector3(ray.hitVec) :
				posReach;

		EntityDanmaku danmaku = DanmakuBuilder.builder()
				.setUser(player)
				.setAngle(Vector3.Up())
				.setMovementData(0.2D)
				.setPos(spawnPos)
				.setShot(LibShotData.SHOT_SCALE.setColor(LibColor.COLOR_SATURATED_RED))
				.build().asEntity();

		player.worldObj.spawnEntityInWorld(danmaku);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
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
