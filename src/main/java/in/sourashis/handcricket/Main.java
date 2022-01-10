package in.sourashis.handcricket;

import in.sourashis.handcricket.console.Console;
import in.sourashis.handcricket.console.Esc;
import in.sourashis.handcricket.game.Game;
import in.sourashis.handcricket.game.GameSupport;

/**
 * Main class of the game
 */
public class Main {

	/**
	 * Runs the game using the Game class
	 */
	public static void runGame() {
		Game game = GameSupport.constructGame();
		game.toss();
		game.start1st();
		game.start2nd();
		game.showScoreboard();

		Console.getConsole().printStyled("Press enter to continue...", Esc.WHITE_FOREGROUND);
		Console.getConsole().consumeLine();
		Console.getConsole().println();
	}

	/**
	 * main() method
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		try {
			runGame();
		} catch (Exception e) {
			Console.getConsole().println();
		} finally {
			Console.getConsole().close();
		}
	}
}