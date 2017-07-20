package arekkuusu.grimoireofalice.common.core.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityMallet {

	public static void init() {
		CapabilityManager.INSTANCE.register(IMalletCapability.class, new Capability.IStorage<IMalletCapability>() {

			@Override
			public NBTBase writeNBT(Capability<IMalletCapability> capability, IMalletCapability instance, EnumFacing side) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("small", instance.isSmall());
				return nbt;
			}

			@Override
			public void readNBT(Capability<IMalletCapability> capability, IMalletCapability instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound tag = (NBTTagCompound) nbt;
				instance.setSmall(tag.getBoolean("small"));
			}
		}, DefaultMalletCapability::new);
	}

	public static class DefaultMalletCapability implements IMalletCapability {

		private boolean animate;
		private int scaled;
		private boolean small;

		@Override
		public void doAnimation(boolean animate) {
			this.animate = animate;
		}

		@Override
		public boolean doAnimation() {
			return animate;
		}

		@Override
		public int getScaled() {
			return scaled;
		}

		@Override
		public void setScaled(int scaled) {
			this.scaled = scaled;
		}

		@Override
		public boolean isSmall() {
			return small;
		}

		@Override
		public void setSmall(boolean small) {
			this.small = small;
		}

		@Override
		public void markDirty() {
			this.scaled = small ? 10 : 0;
			this.animate = true;
		}
	}
}
