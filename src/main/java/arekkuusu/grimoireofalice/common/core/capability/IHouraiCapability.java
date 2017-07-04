/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of Grimore-Of-Alice. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore-Of-Alice is Open Source and distributed under the
 * MIT Licence: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE
 */
package arekkuusu.grimoireofalice.common.core.capability;

public interface IHouraiCapability {

	/**
	 * Do NOT use unless you want to give an initial amount.
	 *
	 * @param level Initial level, must be more than 0 and less than max
	 */
	void setHouraiLevel(byte level);

	byte getHouraiLevel();

	byte getMaxHouraiLevel();

}
