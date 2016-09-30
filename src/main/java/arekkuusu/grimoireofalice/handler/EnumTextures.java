package arekkuusu.grimoireofalice.handler;

public enum EnumTextures {
	BLACK_TRIANGLE_MAGIC_CIRCLE(0),
	BLUE_HEXAGRAM_MAGIC_CIRCLE(1),
	BLACK_OCTAGRAM_MAGIC_CIRCLE(2),
	ORANGE_SQUARE_MAGIC_CIRCLE(3),
	GREEN_TRIANGLE_MAGIC_CIRCLE(4),
	WHITE_CIRCLE_MAGIC_CIRCLE(5),
	FANCY_SYMBOLS_MAGIC_CIRCLE(6);

	private final int num;

	EnumTextures(int num) {
		this.num = num;
	}

	public int getNumber() {
		return num;
	}
}
