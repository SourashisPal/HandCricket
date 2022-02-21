package in.sourashis.handcricket.console;

import java.util.Scanner;

/**
 * Represents the console on which the program will be run
 * @author Sourashis Pal
 */
public class Console implements AutoCloseable {

	/** Singleton object of the Console class **/
	private static Console console;

	/** Scanner object for accepting input **/
	private final Scanner sc = new Scanner(System.in);

	/**
	 * Initializes the Console object
	 */
	private Console() {
	}

	/**
	 * Constructs and returns the singleton object of the Console class
	 * @return Singleton instance
	 * @throws IllegalStateException If the Console is not ready for use
	 */
	public static Console getConsole() {
		if (console == null) {
			console = new Console();
		}
		return console;
	}

	/**
	 * Sets the escape sequences
	 * @param flags Sequences to set
	 */
	public void setFlags(Esc... flags) {
		for (Esc flag : flags) {
			System.out.print(flag);
		}
	}

	/**
	 * Resets all the escape sequences
	 */
	public void reset() {
		System.out.print(Esc.RESET);
	}

	/**
	 * Prints styled text
	 * @param o Object to print
	 * @param flags Sequences to set
	 */
	public void printStyled(Object o, Esc... flags) {
		setFlags(flags);
		System.out.print(o);
		reset();
	}

	/**
	 * Prints styled and centered text
	 * @param o Object to print
	 * @param cols Columns to center in
	 * @param flags Sequences to set
	 */
	public void printStyledCentered(Object o, int cols, Esc... flags) {
		cols -= o.toString().length();
		int side = cols/2;
		printStyled(" ".repeat(side));
		printStyled(o, flags);
		printStyled(" ".repeat(cols - side));
	}

	/**
	 * Prints styled text with newline
	 * @param o Object to print
	 * @param flags Sequences to set
	 */
	public void printlnStyled(Object o, Esc... flags) {
		printStyled(o.toString() + '\n', flags);
	}

	/**
	 * Prints styled and centered text with newline
	 * @param o Object to print
	 * @param cols Columns to center in
	 * @param flags Sequences to set
	 */
	public void printlnStyledCentered(Object o, int cols, Esc... flags) {
		printStyledCentered(o, cols, flags);
		println();
	}

	/**
	 * Prints a newline
	 */
	public void println() {
		printlnStyled("");
	}

	/**
	 * Inputs an int value
	 * @return int value
	 * @param flags Sequences to set
	 */
	public int inputInt(Esc... flags) {
		setFlags(flags);
		try {
			return sc.nextInt();
		} finally {
			consumeLine();
		}
	}

	/**
	 * Inputs a word
	 * @return String value
	 * @param flags Sequences to set
	 */
	public String inputWord(Esc... flags) {
		setFlags(flags);
		try {
			String s = sc.next();
			consumeLine();
			return s;
		} finally {
			reset();
		}
	}

	/**
	 * Consumes a line
	 * @param flags Sequences to set
	 */
	public void consumeLine(Esc... flags) {
		setFlags(flags);
		try {
			sc.nextLine();
		} finally {
			reset();
		}
	}

	/**
	 * Closes the Console
	 */
	public void close() {
		sc.close();
		System.out.close();
	}
}
