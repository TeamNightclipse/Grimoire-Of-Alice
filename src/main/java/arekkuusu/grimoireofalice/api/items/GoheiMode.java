package arekkuusu.grimoireofalice.api.items;

/**
 * Enum used to store the different modes for the Hakurei Gohei
 */
public enum GoheiMode {
	PASSIVE("passive", (byte) 0),
	AURA_MANIPULATION("aura_manipulation", (byte) 1),
	YING_YANG_ORB("hakurei_yin_yang_orbs", (byte) 2),
	BARRIER_EXPLODE("exploding_barrier", (byte) 3),
	BARRIER_MOTION("motion_barrier", (byte) 4),
	OFFENSIVE("offensive", (byte) 5);

	private final String name;
	private final byte type;

	/**
	 * Create a new Mode
	 * @param name Name of the Mode, used for translation in the Lang file
	 * @param type The byte stored in the NBT
	 */
	GoheiMode(String name, byte type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Returns the GoheiMode with the correct type
	 * @param type The byte stored in the NBT
	 * @return
	 */
	public static GoheiMode fromType(byte type) {
		switch (type) {
			case 0:
				return PASSIVE;
			case 1:
				return AURA_MANIPULATION;
			case 2:
				return YING_YANG_ORB;
			case 3:
				return BARRIER_EXPLODE;
			case 4:
				return BARRIER_MOTION;
			default:
				return OFFENSIVE;
		}
	}

	public byte getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}
}
