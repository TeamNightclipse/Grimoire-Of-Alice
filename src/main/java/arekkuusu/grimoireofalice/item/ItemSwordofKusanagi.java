package arekkuusu.grimoireofalice.item;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordofKusanagi extends ItemModSword {

	public ItemSwordofKusanagi(ToolMaterial material) {
		super(material);
		setUnlocalizedName(LibItemName.SWORDOFKUSANAGI);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if( stack.getTagCompound() == null )
	    	stack.setTagCompound( new NBTTagCompound( ) );
	    stack.getTagCompound().setTag("GrimoireOwner", (NBTBase) player.getDisplayName());
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
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
		//player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(!stack.hasTagCompound()) {return stack;}
		EntityPlayer player = (EntityPlayer)entityLiving;
		if(stack.getTagCompound().getString("GrimoireOwner").equals(player.getDisplayName())){
			int hurr = getMaxItemUseDuration(stack);
			float durr = (hurr*6) / 20F;
			durr = (durr * durr + durr * 2.0F) / 3F;
			durr *= 1.5F;
			if (durr > 10F) return stack;
			for(int t=0; t<durr; t++){
				for(int u=0;u<10;u++){
					Random rand0 = new Random();
					player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, rand0.nextDouble(), 0, rand0.nextDouble());
					player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, -rand0.nextDouble(), 0, -rand0.nextDouble());
					player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, rand0.nextDouble(), 0, -rand0.nextDouble());
					player.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+0.5, player.posY, player.posZ+0.5, -rand0.nextDouble(), 0, rand0.nextDouble());
				}
			}
			List list = worldIn.getEntitiesWithinAABB(EntityMob.class, player.getRenderBoundingBox().expand(4.0D, 4.0D, 4.0D));
			for (Object mob : list){
				if(mob instanceof Entity){
					Entity ent = (Entity) mob;
					ent.attackEntityFrom(DamageSource.magic, durr);
					float yaw = ent.rotationYaw;
					float pitch = ent.rotationPitch;
					float f = 1.0F;
					double motionX = (double)(Math.cos(yaw / 180.0F * (float)Math.PI) * Math.cos(pitch / 180.0F * (float)Math.PI) * f);
					double motionZ = (double)(Math.cos(yaw / 180.0F * (float)Math.PI) * Math.cos(pitch / 180.0F * (float)Math.PI) * f);
					ent.setVelocity(-motionX, .3F, -motionZ);
				}
			}
		}
		return stack;
	}
	
}
