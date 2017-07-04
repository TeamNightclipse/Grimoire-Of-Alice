/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.core.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class MalletProvider implements ICapabilitySerializable<NBTTagCompound> {

	@CapabilityInject(IMalletCapability.class)
	public static final Capability<IMalletCapability> MALLET_CAPABILITY = null;

	private final IMalletCapability houraiCapability = new CapabilityMallet.DefaultMalletCapability();

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return MALLET_CAPABILITY == capability;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return MALLET_CAPABILITY == capability ? MALLET_CAPABILITY.cast(houraiCapability) : null;
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) MALLET_CAPABILITY.writeNBT(houraiCapability, null);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		MALLET_CAPABILITY.readNBT(houraiCapability, null, nbt);
	}
}
