package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arekkuusu.grimoireOfAlice.GrimoireOfAlice;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event;

public class ItemIbarakiBoxEmpty extends Item{

	private Block isFull;
	
	public ItemIbarakiBoxEmpty(Block aBlock) {
		isFull = aBlock;
		setMaxStackSize(1);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.GREEN + "Cure all illnesses or heal any injuries");
		list.add(EnumChatFormatting.GREEN + "to the person who drinks from it");
		list.add(EnumChatFormatting.DARK_PURPLE + "In exchange for curing illnesses, the personality of the one who");
		list.add(EnumChatFormatting.DARK_PURPLE + "drinks from will temporarily become like an Oni's");
		list.add(EnumChatFormatting.DARK_RED + "Use like bucket");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack_, World world, EntityPlayer player) {
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);

        if (movingobjectposition == null) {
            return stack_;
        } else {
            FillBucketEvent event = new FillBucketEvent(player, stack_, world, movingobjectposition);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return stack_;
            }

            if (event.getResult() == Event.Result.ALLOW) {
                if (player.capabilities.isCreativeMode) {
                    return stack_;
                }

                if (--stack_.stackSize <= 0) {
                    return event.result;
                }

                if (!player.inventory.addItemStackToInventory(event.result)) {
                    player.dropPlayerItemWithRandomChoice(event.result, false);
                }

                return stack_;
            }
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k)) {
                    return stack_;
                }

                if (flag) {
                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack_)) {
                        return stack_;
                    }

                    Material material = world.getBlock(i, j, k).getMaterial();
                    int l = world.getBlockMetadata(i, j, k);

                    if (material == Material.water && l == 0) {
                        world.setBlockToAir(i, j, k);
                        return this.func_150910_a(stack_, player, GOAItem.ibarakiBoxFilled);
                    }

                    if (material == Material.lava && l == 0) {
                        world.setBlockToAir(i, j, k);
                        return this.func_150910_a(stack_, player, GOAItem.ibarakiBoxFilled);
                    }
                } else {
                    if (this.isFull == Blocks.air) {
                        return new ItemStack(this);
                    }
                }
            }

            return stack_;
        }
    }
	
	private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_) {
        if (p_150910_2_.capabilities.isCreativeMode) {
            return p_150910_1_;
        } else if (--p_150910_1_.stackSize <= 0) {
            return new ItemStack(p_150910_3_);
        } else {
            if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_))) {
                p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
            }

            return p_150910_1_;
        }
    }
	
}
