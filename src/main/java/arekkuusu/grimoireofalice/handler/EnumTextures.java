package arekkuusu.grimoireofalice.handler;

public enum EnumTextures {
	RED_NORMAL(0),
	PURPLE_CIRCLES(1),
	BLUE_STAR(2),
	BLUE_STAR_SMALL(3),
	BLACK_PENTAGRAM(4),
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
