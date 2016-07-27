package arekkuusu.grimoireOfAlice.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemUFOs extends ItemPoint{

	ItemUFOs() {
		super(EnumRarity.rare);
		setMaxStackSize(1);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.DARK_AQUA + "Gathers items around the player");
		list.add(EnumChatFormatting.DARK_AQUA + "in a very high range.");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int idk, boolean yus) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getCurrentEquippedItem() == stack) {
				itemsInRange(world, player, 10);
			}
		}
	}
	
	private void itemsInRange(World world, EntityPlayer player, double bdouble) {
		List aList = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(player.posX - bdouble, player.posY - bdouble, player.posZ - bdouble, player.posX + bdouble, player.posY + bdouble, player.posZ + bdouble));
		Iterator anIterator = aList.iterator();
		while(anIterator.hasNext()){
			EntityItem item = (EntityItem) anIterator.next();
			if (!stackHasRoom(item.getEntityItem(), player)) {
                continue;
            }
            if (item.delayBeforeCanPickup > 0) {
                item.delayBeforeCanPickup = 0;
            }
            if (player.getDistanceToEntity(item) < 1.5D) {
                continue;
            }
            givePlayerItems(item, player);
		}
	}
	
	private void givePlayerItems(Entity item, EntityPlayer player) {
        player.worldObj.spawnParticle("mobSpell", item.posX + 0.5D + player.worldObj.rand.nextGaussian() / 8, item.posY + 0.2D, item.posZ + 0.5D + player.worldObj.rand.nextGaussian() / 8, 0.9D, 0.9D, 0.0D);
        player.getLookVec();
        double x = player.posX + player.getLookVec().xCoord * 0.2D;
        double y = player.posY - player.height / 2F;
        double z = player.posZ + player.getLookVec().zCoord * 0.2D;
        item.setPosition(x, y, z);
        player.worldObj.playSoundAtEntity(player, "random.levelup", 0.1F, 0.5F * ((player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.7F + 1.8F));
	}
	
	private boolean stackHasRoom(ItemStack item, EntityPlayer player) {
		int free = item.stackSize;
	    for (ItemStack stack : player.inventory.mainInventory) {
	    	if (stack == null) {
                continue;
	    	}
	    	if (stack.getItem() == item.getItem() && stack.getItemDamage() == item.getItemDamage()) {
	    		if (stack.stackSize + free <= stack.getMaxStackSize()){
	    			return true;
	    		} else {
	    			int count = stack.stackSize;
	                while (count < stack.getMaxStackSize()) {
	                	count++;
	                    free--;
	                    if (free == 0)
	                    	return true;
	                    }
	                }
	            }
	        }
	        for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
	        	if (player.inventory.mainInventory[slot] == null)
	        		return true;
	        	}
	        return false;
	}
	
}
