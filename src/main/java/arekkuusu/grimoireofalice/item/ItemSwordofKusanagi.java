/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordofKusanagi extends ItemModSword {

	public ItemSwordofKusanagi(ToolMaterial material) {
		super(material, LibItemName.SWORDOFKUSANAGI);
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if(stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
	    stack.getTagCompound().setString("GrimoireOwner", player.getDisplayName().getFormattedText());
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Divine sword forged with Hihi'irokane, a mythic metal");
		list.add(TextFormatting.GRAY + "Discovered within the fourth tail of the eight-headed,");
		list.add(TextFormatting.GRAY + "eight-tailed serpent Yamata-no-Orochi when it was slain ");
		list.add(TextFormatting.GRAY + "by the Shinto god Susanoo-no-Mikoto.");
		if(stack.hasTagCompound()) {
			list.add(TextFormatting.ITALIC + "Property of " + stack.getTagCompound().getString("GrimoireOwner"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@SuppressWarnings("ConstantConditions")
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(!stack.hasTagCompound()) return;
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			if(stack.getTagCompound().getString("GrimoireOwner").equals(player.getDisplayName().getFormattedText())){
				int hurr = getMaxItemUseDuration(stack) - timeLeft;
				float durr = (hurr*6) / 20F;
				durr = (durr * durr + durr * 2.0F) / 3F;
				durr *= 1.5F;
				if (durr > 10F) return;
				for(int t=0; t<durr; t++){
					for(int u=0;u<10;u++){
						Random rand0 = player.getRNG();
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, rand0.nextDouble(), 0, rand0.nextDouble());
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, -rand0.nextDouble(), 0, -rand0.nextDouble());
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, rand0.nextDouble(), 0, -rand0.nextDouble());
						player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, -rand0.nextDouble(), 0, rand0.nextDouble());
					}
				}
				List<EntityMob> list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().expandXyz(4.0D));
				for (EntityMob mob : list){
					mob.attackEntityFrom(DamageSource.magic, durr);
					float yaw = mob.rotationYaw;
					float pitch = mob.rotationPitch;
					//FIXME: Is this right? Shouldn't one of these be MathHelper.sin, also only calculate the radians once
					double motionX = MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI);
					double motionZ = MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI);
					mob.motionX = -motionX;
					mob.motionY = .3F;
					mob.motionZ = -motionZ;
				}
			}
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }
	
}
