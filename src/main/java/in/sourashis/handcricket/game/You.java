package in.sourashis.handcricket.game;

import in.sourashis.handcricket.console.Console;
import in.sourashis.handcricket.console.Esc;

import java.util.InputMismatchException;

/**
 * Represents the You
 * @author Sourashis Pal
 */
public class You extends Player {

	/**
	 * Constructor of the class
	 * @param totalWickets Total wickets in the game
	 */
	public You(int totalWickets) {
		super(totalWickets, "You");
	}

	@Override
	public int getChoice() {
		int choice;

		// Accepting choice
		while (true) {
			Console.getConsole().printStyled("\nEnter choice: ", Esc.WHITE_FOREGROUND);
			try {
				choice = Console.getConsole().inputInt(Esc.WHITE_FOREGROUND);
			} catch (InputMismatchException e) {
				Console.getConsole().printlnStyled("Invalid Input", Esc.RED_FOREGROUND);
				continue;
			}
			if (choice < 0 || choice > 6) {
				Console.getConsole().printlnStyled("Invalid Choice", Esc.RED_FOREGROUND);
				continue;
			}
			break;
		}
		Console.getConsole().println();

		return choice;
	}
}
