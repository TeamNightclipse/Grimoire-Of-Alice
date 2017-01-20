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
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class HouraiProvider implements ICapabilitySerializable<NBTTagCompound> {

	@CapabilityInject(IHouraiCapability.class)
	public static final Capability<IHouraiCapability> HOURAI_CAPABILITY = null;

	private final IHouraiCapability houraiCapability = new DefaultHouraiCapability();

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return HOURAI_CAPABILITY == capability;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return HOURAI_CAPABILITY == capability ? HOURAI_CAPABILITY.cast(houraiCapability) : null;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound)HOURAI_CAPABILITY.writeNBT(houraiCapability, null);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		HOURAI_CAPABILITY.readNBT(houraiCapability, null, nbt);
	}
}
