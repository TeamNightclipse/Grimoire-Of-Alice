package arekkuusu.grimoireofalice.common.core.capability;

/**
 * This class was created by Arekkuusu on 08/05/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
public interface IMalletCapability {

	void doAnimation(boolean animate);

	boolean doAnimation();

	int getScaled();

	void setScaled(int scaled);

	void setSmall(boolean small);

	boolean isSmall();

	void markDirty();
}
