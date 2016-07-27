package arekkuusu.grimoireOfAlice.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSwordofKusanagi extends ItemGOASword {

	public ItemSwordofKusanagi(ToolMaterial material) {
		super(material);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.WHITE + "Divine sword forged with Hihi'irokane, a mythic metal,");
		list.add(EnumChatFormatting.WHITE + "Proof of the Emperor's divinity and right to Japan's throne");
		list.add(EnumChatFormatting.GOLD + "discovered within the fourth tail of the eight-headed,");
		list.add(EnumChatFormatting.GOLD + "eight-tailed serpent Yamata-no-Orochi when it was slain ");
		list.add(EnumChatFormatting.GOLD + "by the Shinto god Susanoo-no-Mikoto.");
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
		for(int u=0;u<10;u++){
			Random rand0 = new Random();
			player.worldObj.spawnParticle("largesmoke", player.posX, player.posY, player.posZ, rand0.nextDouble(), rand0.nextDouble(), rand0.nextDouble());
			player.worldObj.spawnParticle("largesmoke", player.posX, player.posY, player.posZ, -rand0.nextDouble(), rand0.nextDouble(), -rand0.nextDouble());
			player.worldObj.spawnParticle("largesmoke", player.posX, player.posY, player.posZ, rand0.nextDouble(), rand0.nextDouble(), -rand0.nextDouble());
			player.worldObj.spawnParticle("largesmoke", player.posX, player.posY, player.posZ, -rand0.nextDouble(), rand0.nextDouble(), rand0.nextDouble());
		}
		MinecraftServer.getServer().worldServerForDimension(player.dimension).setRainStrength(1F);
	}
	
}
