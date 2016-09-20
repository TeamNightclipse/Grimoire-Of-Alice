/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
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

    public EntityNazrinPendulum(World worldIn) {
        super(worldIn);
    }

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

        List<Block> blockLayer = new ArrayList<>(20);
        BlockPos pos = new BlockPos(posX, posY, posZ);
        for(int i = 1; i < 20; i++){
            Block block = worldObj.getBlockState(pos.down(i)).getBlock();
			ItemStack stack = new ItemStack(block);

			//noinspection ConstantConditions Liar
			if(stack.getItem() == null) {
				continue;
			}

			boolean isOre = Arrays.stream(OreDictionary.getOreIDs(new ItemStack(block)))
                    .mapToObj(OreDictionary::getOreName)
                    .anyMatch(s -> s.startsWith("ore")) || block instanceof BlockChest;

            if(isOre) {
                blockLayer.add(block);
            }
        }

        if(!blockLayer.isEmpty()) {
            for(Block block : blockLayer){
                Random rand = new Random();
                if (rand.nextInt(8) == 4) { //Entity should give some kind of signal when an ore is found
                    worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 0.0D, 1.0D, 0.0D);
                    worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 0.0D, 1.0D, 0.0D);
                    worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 0.0D, 1.0D, 0.0D);
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
			}
            setDead();
		}
    }

    public int getTicksAlive(){
        return ticksExisted;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
