/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of Stratoprism. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Stratoprism
 *
 * Stratoprism is Open Source and distributed under the
 * MIT Licence: https://github.com/ArekkuusuJerii/Stratoprism/blob/master/LICENSE
 */
package arekkuusu.grimoireofalice.common.core.capability;

import net.minecraft.nbt.NBTTagCompound;

public class DefaultHouraiCapability implements IHouraiCapability {

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

	@Override
	public NBTTagCompound saveNBTData() {
		return (NBTTagCompound) HouraiCapabilityStorage.cap.writeNBT(HouraiProvider.HOURAI_CAPABILITY, this, null);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		HouraiCapabilityStorage.cap.readNBT(HouraiProvider.HOURAI_CAPABILITY, this, null, compound);
	}
}
