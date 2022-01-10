package in.sourashis.handcricket.game;

import in.sourashis.handcricket.console.Console;
import in.sourashis.handcricket.console.Esc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Objects;

import static java.lang.Character.isWhitespace;

/**
 * Supports the game by providing utility methods
 */
public class GameSupport {

	/**
	 * Shows the rules with the width of each line not being more than the max width
	 * @throws IOException Any error while fetching the rules
	 */
	private static void showRules() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(GameSupport.class.getResourceAsStream("/rules.txt"))));

		String format = "%-" + (Game.MAX_WIDTH - 7) + 's';
		String line;
		int lineCount = 1;
		boolean firstLine = true;

		// Reading each line of the file into line
		while ((line = reader.readLine()) != null) {
			boolean firstPart = true;

			// Printing empty line between rules
			if (!firstLine) {
				Console.getConsole().printStyled("| ", Esc.BLUE_FOREGROUND);
				Console.getConsole().printStyled(" ".repeat(Game.MAX_WIDTH - 4));
				Console.getConsole().printlnStyled(" |", Esc.BLUE_FOREGROUND);
			}

			// Storing each display part of a line
			while (!line.isEmpty()) {
				int length = Math.min(line.length(), Game.MAX_WIDTH - 7);
				if (length != line.length() && !isWhitespace(line.charAt(length))) {
					length--;
					while (!isWhitespace(line.charAt(length))) {
						length--;
					}
				}

				// Displaying part of line
				Console.getConsole().printStyled("| ", Esc.BLUE_FOREGROUND);
				if (firstPart) {
					Console.getConsole().printStyled(lineCount + ". ", Esc.CYAN_FOREGROUND);
				} else {
					Console.getConsole().printStyled("   ");
				}
				Console.getConsole().printStyled(format.formatted(line.substring(0, length)), Esc.GREEN_FOREGROUND);
				Console.getConsole().printlnStyled(" |", Esc.BLUE_FOREGROUND);

				line = line.substring(length).strip();
				firstPart = false;
			}

			firstLine = false;
			lineCount++;
		}

		reader.close();
	}

	/**
	 * Displays rules, asks total wickets and constructs a Game object
	 * @return Constructed Game object
	 */
	public static Game constructGame() {
		// Displaying heading
		String heading = "| HAND CRICKET |";
		Console.getConsole().println();
		Console.getConsole().printlnStyledCentered("-".repeat(heading.length()), Game.MAX_WIDTH, Esc.ORANGE_FOREGROUND);
		Console.getConsole().printlnStyledCentered(heading, Game.MAX_WIDTH, Esc.ORANGE_FOREGROUND);
		Console.getConsole().printlnStyledCentered("-".repeat(heading.length()), Game.MAX_WIDTH, Esc.ORANGE_FOREGROUND);

		// Displaying game rules
		Console.getConsole().println();
		Console.getConsole().printStyled("Read rules (Y/n)? ", Esc.WHITE_FOREGROUND);
		if (Console.getConsole().inputWord(Esc.WHITE_FOREGROUND).toLowerCase().charAt(0) == 'y') {
			Console.getConsole().println();
			Console.getConsole().printlnStyledCentered("Rules", Game.MAX_WIDTH, Esc.UNDERLINE, Esc.YELLOW_FOREGROUND);
			Console.getConsole().printlnStyled("-".repeat(Game.MAX_WIDTH), Esc.BLUE_FOREGROUND);
			try {
				showRules();
			} catch (IOException e) {
				Console.getConsole().printlnStyled("Could not fetch rules", Esc.RED_FOREGROUND);
			}
			Console.getConsole().printlnStyled("-".repeat(Game.MAX_WIDTH), Esc.BLUE_FOREGROUND);
		}

		// Asking total wickets
		int totalWickets;
		while (true) {
			Console.getConsole().println();
			Console.getConsole().printStyled("Enter no. of wickets: ", Esc.WHITE_FOREGROUND);
			try {
				totalWickets = Console.getConsole().inputInt(Esc.WHITE_FOREGROUND);
			} catch (InputMismatchException e) {
				Console.getConsole().printlnStyled("Invalid Input", Esc.RED_FOREGROUND);
				continue;
			}
			if (totalWickets <= 0) {
				Console.getConsole().printlnStyled("Wickets must be greater than to 0", Esc.RED_FOREGROUND);
				continue;
			}
			break;
		}

		// Returning Game object
		return new Game(totalWickets);
	}
}
