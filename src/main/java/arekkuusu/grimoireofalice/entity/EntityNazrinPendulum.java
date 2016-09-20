package arekkuusu.grimoireofalice.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class EntityNazrinPendulum extends Entity {

    public EntityPlayer user;

    public EntityNazrinPendulum(World worldIn, EntityPlayer player) {
        super(worldIn);
        this.user = player;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(user == null) {
            stopEntity();
        }
        else {
            if(ticksExisted > 10 && user.isSneaking()) {
                stopEntity();
            }
            else if(user.hurtTime > 0) {
                stopEntity();
            }
        }

        //FIXME: IllegalArgumentException: Stack can not be null! EntityNazrinPendulum.onUpdate(EntityNazrinPendulum.java:47)
        //This is all most likely wrong and will be fixed soon
        List<Block> blockLayer = new ArrayList<>(10);
        BlockPos pos = new BlockPos(posX, posY, posZ);
        for(int i = 1; i < 10; i++){
            Block block = worldObj.getBlockState(pos.down(i)).getBlock();
			ItemStack stack = new ItemStack(block);

			//noinspection ConstantConditions Liar
			if(stack.getItem() == null) {
				continue;
			}

			boolean isOre = Arrays.stream(OreDictionary.getOreIDs(new ItemStack(block)))
                    .mapToObj(OreDictionary::getOreName)
                    .anyMatch(s -> s.startsWith("ore"));

            if(isOre) {
                blockLayer.add(block);
            }
        }

        if(!blockLayer.isEmpty()) {
            for(Block block : blockLayer){
                Random rand = new Random();
                if (rand.nextInt(8) == 4) { //Entity should give some kind of signal when an ore is found
                    worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 0.0D, 0.5D, 0.0D);
                }
            }
        }
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected void entityInit() {

    }

    private void stopEntity() {
        if(!worldObj.isRemote) {
			if(user != null) {
				if(user.capabilities.isCreativeMode) {
					setDead();
					return;
				}
				if(!user.inventory.addItemStackToInventory(new ItemStack(ModItems.nazrinPendulum, 1))) {
					user.dropItem(ModItems.nazrinPendulum, 1);
				}
			}
			else {
				dropItem(ModItems.nazrinPendulum, 1);
				setDead();
			}
		}
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
