package in.sourashis.handcricket.game;

import in.sourashis.handcricket.console.Console;
import in.sourashis.handcricket.console.Esc;

import java.util.*;
import java.util.function.IntFunction;

/**
 * Represents the hand cricket game
 * @author Sourashis Pal
 */
public class Game {

	/** Player 1 **/
	private final Player player1;

	/** Player 2 **/
	private final Player player2;

	/** Current batter **/
	private Player batter;

	/** Current bowler **/
	private Player bowler;

	/** Total wickets in the game **/
	private final int totalWickets;

	/** States whether the last ball was a no ball or not, as a result this ball will be a free hit **/
	private boolean freeHit;

	/** Contains the runs of the last three deliveries **/
	private final Queue<Integer> last3;

	/** Contains the runs of the last 10 balls **/
	private final LinkedList<String> timeline = new LinkedList<>();

	/** Random number generator **/
	private final Random random = new Random();

	/** Maximum width **/
	public static final int MAX_WIDTH = 55;

	/** Function for returning word representation of a run **/
	private static final IntFunction<String> toWord = n -> n==0? "Dot": n==1? "One": n==2? "Two": n==3? "Three": n==4? "Four": n==5? "Five": n==6? "Six": null;

	/**
	 * Initializes the Game object
	 * @param totalWickets Total wickets in the game
	 */
	public Game(int totalWickets) {
		this.totalWickets = totalWickets;
		last3 = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			last3.offer(6);
		}
		player1 = new You(totalWickets);
		player2 = new Computer(totalWickets, this);
	}

	/**
	 * Draws the given two numbers on the screen side by side
	 * @param one First number
	 * @param two Second number
	 */
	private void drawNumber(int one, int two) {
		for (int i = 0; i < Numbers.NUMBERS[0].length; i++) {
			Console.getConsole().printStyled(" ".repeat(4));
			for (byte b: Numbers.NUMBERS[one][i]) {
				if (b == 1) {
					Console.getConsole().printStyled("  ", Esc.YELLOW_BACKGROUND);
				} else {
					Console.getConsole().printStyled("  ");
				}
			}
			Console.getConsole().printStyled(" ".repeat(6));
			for (byte b: Numbers.NUMBERS[two][i]) {
				if (b == 1) {
					Console.getConsole().printStyled("  ", Esc.YELLOW_BACKGROUND);
				} else {
					Console.getConsole().printStyled("  ");
				}
			}
			Console.getConsole().println();
		}
	}

	/**
	 * Gives the remarks of the ball and updates the scores
	 * @param bat Put of the batter
	 * @param bowl Put of the bowler
	 * @return Remarks of the ball
	 */
	private String remarks(int bat, int bowl) {
		last3.offer(bat);
		last3.poll();

		batter.addToLast10(bat);
		bowler.addToLast10(bowl);

		// If bowl is a dot, it is a no ball
		if (bowl == 0) {
			freeHit = true;
			batter.addRuns(bat + 1);
			addToTimeline(bat + 1 + "NB");
			Console.getConsole().beep();
			return "No Ball, Free Hit upcoming " + toWord.apply(bat) + " and an extra run";
		}

		// B will be counted
		batter.playBall();

		// If bat is same as bowl and is not a free hit, it will be a wicket
		if (bat == bowl) {
			if (freeHit) {
				freeHit = false;
				addToTimeline("0");
				return "Free Hit saved a wicket, No run";
			} else {
				batter.fallWicket();
				addToTimeline("W");
				return "Wicket (Same numbers)";
			}
		}

		// If last 3 balls including this ball are all dots, and it is not a free hit then it is a wicket
		if (last3.stream().mapToInt(Integer::intValue).sum() == 0 && !freeHit) {
			batter.fallWicket();
			last3.clear();
			for (int i = 0; i < 3; i++) {
				last3.offer(6);
			}
			addToTimeline("W");
			return "Wicket (Dot for consecutive 3 times)";
		}

		batter.addRuns(bat);
		freeHit = false;
		addToTimeline(String.valueOf(bat));
		return toWord.apply(bat);
	}

	/**
	 * Regulates the timeline by adding and removing runs from it
	 * @param runs The runs to add
	 */
	private void addToTimeline(String runs) {
		timeline.addFirst(runs);
		if (timeline.size() > 10) {
			timeline.removeLast();
		}
	}

	/**
	 * Makes the toss of the game
	 */
	public void toss() {
		// Asking head or tail
		Console.getConsole().println();
		Console.getConsole().printlnStyledCentered("Toss", MAX_WIDTH, Esc.YELLOW_FOREGROUND, Esc.UNDERLINE);
		Console.getConsole().println();
		Console.getConsole().printStyled("Head or Tail (H/T)? ", Esc.WHITE_FOREGROUND);
		char ch = Console.getConsole().inputWord(Esc.WHITE_FOREGROUND).toLowerCase().charAt(0);

		// Asking choice
		Console.getConsole().println();
		Console.getConsole().printlnStyled("You - %s\t Computer - %s".formatted(ch == 'h'? "Head": "Tail", ch == 'h'? "Tail": "Head"), Esc.GREEN_FOREGROUND);
		int you;
		while (true) {
			Console.getConsole().println();
			Console.getConsole().printStyled("Enter choice: ", Esc.WHITE_FOREGROUND);
			try {
				you = Console.getConsole().inputInt(Esc.WHITE_FOREGROUND);
			} catch (InputMismatchException e) {
				Console.getConsole().beep();
				Console.getConsole().printlnStyled("Invalid Input", Esc.RED_FOREGROUND);
				continue;
			}
			if (you < 0 || you > 6) {
				Console.getConsole().beep();
				Console.getConsole().printlnStyled("Invalid Choice ", Esc.RED_FOREGROUND);
				continue;
			}
			break;
		}
		int computer = random.nextInt(7);
		Console.getConsole().println();
		Console.getConsole().printStyledCentered("You", MAX_WIDTH/2, Esc.MAGENTA_FOREGROUND);
		Console.getConsole().printlnStyledCentered("Computer", MAX_WIDTH/2, Esc.MAGENTA_FOREGROUND);
		Console.getConsole().println();
		drawNumber(you, computer);

		// Deciding bat and bowl
		IntFunction< Player> choice = ch == 'h'? (c -> c=='h'? player1: player2): (c -> c=='h'? player2: player1);
		char winner = ((you + computer) & 1) == 0? 't': 'h';
		Console.getConsole().println();
		Console.getConsole().printlnStyled("%s wins".formatted(winner == 'h'? "Head": "Tail"), Esc.BLUE_FOREGROUND);
		if (choice.apply(winner) == player1) {
			Console.getConsole().println();
			Console.getConsole().printStyled("Bat or Bowl (A/B)? ", Esc.WHITE_FOREGROUND);
			if (Console.getConsole().inputWord(Esc.WHITE_FOREGROUND).toLowerCase().charAt(0) == 'a') {
				batter = player1;
				bowler = player2;
			} else {
				batter = player2;
				bowler = player1;
			}
		} else {
			Console.getConsole().printStyled("Computer chooses to ", Esc.BLUE_FOREGROUND);
			if (random.nextInt(2) == 0) {
				Console.getConsole().printlnStyled("Bat", Esc.BLUE_FOREGROUND);
				batter = player2;
				bowler = player1;
			} else {
				Console.getConsole().printlnStyled("Bowl", Esc.BLUE_FOREGROUND);
				batter = player1;
				bowler = player2;
			}
			Console.getConsole().println();
		}
	}

	/**
	 * Runs the common part of both innings
	 */
	public void common() {
		int bat = batter.getChoice(), bowl = bowler.getChoice();

		// Drawing choices
		Console.getConsole().printStyledCentered(batter.getName(), MAX_WIDTH/2, Esc.MAGENTA_FOREGROUND);
		Console.getConsole().printlnStyledCentered(bowler.getName(), MAX_WIDTH/2, Esc.MAGENTA_FOREGROUND);
		Console.getConsole().println();
		drawNumber(bat, bowl);
		Console.getConsole().println();

		// Printing remarks and score
		Console.getConsole().printlnStyledCentered(remarks(bat, bowl), MAX_WIDTH, Esc.CYAN_FOREGROUND);
		Console.getConsole().println();
		Console.getConsole().printlnStyled("%-20s\tStrike Rate: %.2f".formatted(
				"%s - %d/%d (%d)".formatted(batter.getName(), batter.getRuns(), batter.getWickets(), batter.getBallsPlayed()),
				batter.strikeRate()
		), Esc.GREEN_FOREGROUND);
		Console.getConsole().printStyled("Timeline - ", Esc.GREEN_FOREGROUND);
		Console.getConsole().printlnStyled(String.join(" ", timeline), Esc.CYAN_FOREGROUND);
	}

	/**
	 * Runs the 1st Innings
	 */
	public void start1st() {
		Console.getConsole().printlnStyledCentered("1st Innings", MAX_WIDTH, Esc.YELLOW_FOREGROUND);
		Console.getConsole().println();
		Console.getConsole().printlnStyled("Bat - %s\t\tBowl - %s".formatted(batter.getName(), bowler.getName()), Esc.GREEN_FOREGROUND);

		while (true) {
			common();
			if (batter.isAllOut()) {
				Console.getConsole().println();
				Console.getConsole().printlnStyled("All Out", Esc.CYAN_FOREGROUND);
				Console.getConsole().println();
				break;
			}
		}
	}

	/**
	 * Runs the 2nd innings
	 */
	public void start2nd() {
		// Swapping batter and bowler
		Player temp = batter;
		batter = bowler;
		bowler = temp;

		// Clearing timeline
		timeline.clear();

		Console.getConsole().printlnStyledCentered("2nd Innings", MAX_WIDTH, Esc.YELLOW_FOREGROUND);
		Console.getConsole().println();
		Console.getConsole().printlnStyled("%s need %d runs to with %d wickets in hand".formatted(batter.getName(), bowler.getRuns()+1, totalWickets), Esc.CYAN_FOREGROUND);
		Console.getConsole().println();
		Console.getConsole().printlnStyled("Bat - %s\t\tBowl - %s".formatted(batter.getName(), bowler.getName()), Esc.GREEN_FOREGROUND);

		while (true) {
			common();

			// Checking defend win
			if (batter.isAllOut()) {
				Console.getConsole().println();
				if (batter.getRuns() == bowler.getRuns()) {
					Console.getConsole().printlnStyled("Game tied", Esc.CYAN_FOREGROUND);
				} else {
					Console.getConsole().printlnStyled("%s won by %d runs".formatted(bowler.getName(), bowler.getRuns() - batter.getRuns()), Esc.CYAN_FOREGROUND);
				}
				Console.getConsole().println();
				break;
			}

			// Checking chase win
			if (batter.getRuns() > bowler.getRuns()) {
				Console.getConsole().beep();
				Console.getConsole().println();
				Console.getConsole().printlnStyled("%s won by %d wickets".formatted(batter.getName(), totalWickets - batter.getWickets()), Esc.CYAN_FOREGROUND);
				Console.getConsole().println();
				break;
			}

			Console.getConsole().printlnStyled("%d needed with %d wickets remaining".formatted(bowler.getRuns() - batter.getRuns() + 1, totalWickets - batter.getWickets()), Esc.GREEN_FOREGROUND);
		}
	}

	/**
	 * Shows the scoreboard of the match
	 */
	public void showScoreboard() {
		Console.getConsole().printlnStyledCentered("Scoreboard", MAX_WIDTH, Esc.MAGENTA_FOREGROUND);

		// Scoreboard of bowler
		Console.getConsole().println();
		Console.getConsole().printlnStyledCentered("%s - %d/%d (%d)".formatted(bowler.getName(), bowler.getRuns(), bowler.getWickets(), bowler.getBallsPlayed()), MAX_WIDTH, Esc.GREEN_FOREGROUND);
		Console.getConsole().printlnStyled("Fall of Wickets", Esc.BLUE_FOREGROUND);
		bowler.getFallOfWickets().forEach(f -> Console.getConsole().printlnStyled(f, Esc.RED_FOREGROUND));

		// Scoreboard of batter
		Console.getConsole().println();
		Console.getConsole().printlnStyledCentered("%s - %d/%d (%d)".formatted(batter.getName(), batter.getRuns(), batter.getWickets(), batter.getBallsPlayed()), MAX_WIDTH, Esc.GREEN_FOREGROUND);
		Console.getConsole().printlnStyled("Fall of Wickets", Esc.BLUE_FOREGROUND);
		batter.getFallOfWickets().forEach(f -> Console.getConsole().printlnStyled(f, Esc.RED_FOREGROUND));

		Console.getConsole().println();
	}

	/**
	 * Returns the batter
	 * @return The batter
	 */
	public Player getBatter() {
		return batter;
	}

	/**
	 * Returns the batter
	 * @return The batter
	 */
	public Player getBowler() {
		return bowler;
	}

	/**
	 * Returns the random number generator of the Game
	 * @return The random number generator of the Game
	 */
	public Random getRandom() {
		return random;
	}
}
