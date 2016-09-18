package arekkuusu.grimoireofalice.plugin.touhou;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibGuiID;
import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemSpellCardPouch extends Item {

    private static final String TAG_ITEMS = "InvItems";

	public ItemSpellCardPouch() {
		super();
		setRegistryName(LibItemName.POUCH);
		setUnlocalizedName(LibItemName.POUCH);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
		setMaxStackSize(1);
	}

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound oldCapNbt) {
        return new InvProvider();
    }


    private static class InvProvider implements ICapabilitySerializable<NBTBase> {

        @Override
        public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
            return false;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
            return null;
        }

        @Override
        public NBTBase serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {

        }
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(stack.getTagCompound() != null && stack.getTagCompound().hasKey(TAG_ITEMS)) {
            NBTTagList oldData = stack.getTagCompound().getTagList(TAG_ITEMS, Constants.NBT.TAG_COMPOUND);
            IItemHandler newInv = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(newInv, null, oldData);

            stack.getTagCompound().removeTag(TAG_ITEMS);

            if(stack.getTagCompound().getSize() == 0)
                stack.setTagCompound(null);
        }
    }

    @Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}
   
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!playerIn.isSneaking()) {
            playerIn.openGui(GrimoireOfAlice.instance, LibGuiID.POUCH_BAG, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        }
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
}
