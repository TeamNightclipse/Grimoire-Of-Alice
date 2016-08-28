package arekkuusu.grimoireOfAlice.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSwordofKusanagi extends ItemGOASword {

	public ItemSwordofKusanagi(ToolMaterial material) {
		super(material);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if( stack.stackTagCompound == null )
	    	stack.setTagCompound( new NBTTagCompound( ) );
	    stack.stackTagCompound.setString("GrimoireOwner", player.getDisplayName());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GOLD + "Divine sword forged with Hihi'irokane, a mythic metal");
		list.add(EnumChatFormatting.GRAY + "Discovered within the fourth tail of the eight-headed,");
		list.add(EnumChatFormatting.GRAY + "eight-tailed serpent Yamata-no-Orochi when it was slain ");
		list.add(EnumChatFormatting.GRAY + "by the Shinto god Susanoo-no-Mikoto.");
		if(stack.hasTagCompound()) {
			list.add(EnumChatFormatting.ITALIC + "Property of " + stack.stackTagCompound.getString("GrimoireOwner"));
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int i) {
		if(!stack.hasTagCompound()) {return;}
		if(stack.stackTagCompound.getString("GrimoireOwner").equals(player.getDisplayName())){
			int hurr = getMaxItemUseDuration(stack) - i;
			float durr = (hurr*6) / 20F;
			durr = (durr * durr + durr * 2.0F) / 3F;
			durr *= 1.5F;
			if (durr > 10F) return;
			for(int t=0; t<durr; t++){
				for(int u=0;u<10;u++){
					Random rand0 = new Random();
					player.worldObj.spawnParticle("largesmoke", player.posX+0.5, player.posY, player.posZ+0.5, rand0.nextDouble(), 0, rand0.nextDouble());
					player.worldObj.spawnParticle("largesmoke", player.posX+0.5, player.posY, player.posZ+0.5, -rand0.nextDouble(), 0, -rand0.nextDouble());
					player.worldObj.spawnParticle("largesmoke", player.posX+0.5, player.posY, player.posZ+0.5, rand0.nextDouble(), 0, -rand0.nextDouble());
					player.worldObj.spawnParticle("largesmoke", player.posX+0.5, player.posY, player.posZ+0.5, -rand0.nextDouble(), 0, rand0.nextDouble());
				}
			}
			List list = world.getEntitiesWithinAABB(EntityMob.class, player.boundingBox.expand(4.0D, 4.0D, 4.0D));
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
	}
	
}
