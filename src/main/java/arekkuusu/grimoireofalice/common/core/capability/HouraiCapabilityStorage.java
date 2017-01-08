/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of Stratoprism. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Stratoprism
 *
 * Stratoprism is Open Source and distributed under the
 * MIT Licence: https://github.com/ArekkuusuJerii/Stratoprism/blob/master/LICENSE
 */
package arekkuusu.grimoireofalice.common.core.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class HouraiCapabilityStorage implements Capability.IStorage<IHouraiCapability> {

	public static final HouraiCapabilityStorage cap = new HouraiCapabilityStorage();

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
}
