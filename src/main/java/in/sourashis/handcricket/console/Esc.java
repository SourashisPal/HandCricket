package in.sourashis.handcricket.console;

/**
 * Represents escape sequences for the Console
 * @author Sourashis Pal
 */
public enum Esc {
	// Miscellaneous
	/** Reset **/
	RESET("0m"),

	// Graphics
	/** Underline **/
	UNDERLINE("4m"),

	// Foreground colors
	/** Red foreground **/
	RED_FOREGROUND("91m"),
	/** Green foreground **/
	GREEN_FOREGROUND("92m"),
	/** Yellow foreground **/
	YELLOW_FOREGROUND("93m"),
	/** Blue foreground **/
	BLUE_FOREGROUND("94m"),
	/** Magenta foreground **/
	MAGENTA_FOREGROUND("95m"),
	/** Cyan foreground **/
	CYAN_FOREGROUND("96m"),
	/** White foreground **/
	WHITE_FOREGROUND("97m"),
	/** Orange foreground **/
	ORANGE_FOREGROUND("38;5;208m"),

	// Background colors
	/** Yellow background **/
	YELLOW_BACKGROUND("103m");

	/**
	 * Escape code
	 */
	private final String code;

	/**
	 * Enum constructor
	 * @param code Escape code
	 */
	Esc(String code) {
		this.code = code;
	}

	/**
	 * Returns String representation of the escape sequence
	 * @return String representation of the escape sequence
	 */
	@Override
	public String toString() {
		return "\u001b[" + code;
	}
}
