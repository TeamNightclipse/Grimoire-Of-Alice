/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.core.capability;

public interface IMalletCapability {

	void doAnimation(boolean animate);

	boolean doAnimation();

	int getScaled();

	void setScaled(int scaled);

	void setSmall(boolean small);

	boolean isSmall();

	void markDirty();
}
