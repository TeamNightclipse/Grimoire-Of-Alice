package arekkuusu.grimoireofalice.common.core.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHourai {

	public static void init() {
		CapabilityManager.INSTANCE.register(IHouraiCapability.class, new Capability.IStorage<IHouraiCapability>() {

			@Override
			public NBTBase writeNBT(Capability<IHouraiCapability> capability, IHouraiCapability instance, EnumFacing side) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte("HouraiLevel", instance.getHouraiLevel());
				return nbt;
			}

			@Override
			public void readNBT(Capability<IHouraiCapability> capability, IHouraiCapability instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound tag = (NBTTagCompound) nbt;
				instance.setHouraiLevel(tag.getByte("HouraiLevel"));
			}
		}, DefaultHouraiCapability::new);
	}

	public static class DefaultHouraiCapability implements IHouraiCapability {

		private byte maxLevel = 3;
		private byte level = 0;

		@Override
		public byte getHouraiLevel() {
			return level;
		}

		@Override
		public byte getMaxHouraiLevel() {
			return maxLevel;
		}

		@Override
		public void setHouraiLevel(byte level) {
			this.level = level;
			if (level < 0) {
				this.level = 0;
			}
			if (level > maxLevel) {
				this.level = maxLevel;
			}
		}
	}
}
