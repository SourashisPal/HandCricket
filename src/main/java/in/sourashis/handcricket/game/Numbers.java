package in.sourashis.handcricket.game;

/**
 * Contains byte diagrams of numbers
 * @author Sourashis Pal
 */
public class Numbers {

	/** Byte diagram of a dot **/
	private static final byte[][] DOT = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};

	/** Byte diagram of one **/
	private static final byte[][] ONE = {
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
	};

	/** Byte diagram of a two **/
	private static final byte[][] TWO = {
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
	};

	/** Byte diagram of a three **/
	private static final byte[][] THREE = {
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0}
	};

	/** Byte diagram of a four **/
	private static final byte[][] FOUR = {
			{0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}
	};

	/** Byte diagram of a five **/
	private static final byte[][] FIVE = {
			{0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 1, 1, 1, 1, 1, 0, 0, 0}
	};

	/** Byte diagram of a six **/
	private static final byte[][] SIX = {
			{0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0}
	};

	/** Array of byte diagrams **/
	public static final byte[][][] NUMBERS = {
			DOT,  ONE,  TWO,  THREE,  FOUR,  FIVE,  SIX
	};
}
