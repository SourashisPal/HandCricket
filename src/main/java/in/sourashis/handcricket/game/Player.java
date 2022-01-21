package in.sourashis.handcricket.game;

import java.util.*;

/**
 * Represents a Player of the game
 * @author Sourashis Pal
 */
public abstract class Player {
	/** Runs scored **/
	private int runs;

	/** Wickets fallen **/
	private int wickets;

	/** Balls played **/
	private int ballsPlayed;

	/** Total wickets in the game **/
	private final int totalWickets;

	/** Name of the Player **/
	private final String name;

	/** List of fall of wickets **/
	private final List<FallOfWicket> fallOfWickets;

	/** Stores the last twenty actual non 0 throws **/
	private final Queue<Integer> last10 = new LinkedList<>(new Random().ints(10, 1, 7).boxed().toList());

	/**
	 * Initializes the Player
	 * @param totalWickets Total wickets in the game
	 * @param name Name of the Player
	 */
	public Player(int totalWickets, String name) {
		this.name = name;
		this.totalWickets = totalWickets;
		fallOfWickets = new ArrayList<>(totalWickets);
	}

	/**
	 * Returns runs scored
	 * @return Runs scored
	 */
	public int getRuns() {
		return runs;
	}

	/**
	 * Returns wickets fallen
	 * @return Wickets fallen
	 */
	public int getWickets() {
		return wickets;
	}

	/**
	 * Returns balls played
	 * @return Balls played
	 */
	public int getBallsPlayed() {
		return ballsPlayed;
	}

	/**
	 * Returns name of the Player
	 * @return Name of the Player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns fall of wickets of the Player
	 * @return Fall of wickets of the Player
	 */
	public List<FallOfWicket> getFallOfWickets() {
		return fallOfWickets;
	}

	/**
	 * Returns the last 20
	 * @return The last 20
	 */
	public Queue<Integer> getLast10() {
		return last10;
	}

	/**
	 * Adds runs to the players score
	 * @param hit The runs to add
	 */
	public void addRuns(int hit) {
		runs += hit;
	}

	/**
	 * Adds the actual score to the last 20
	 * @param actualScore The actual number
	 */
	public void addToLast10(int actualScore) {
		if (actualScore == 0) return;
		last10.poll();
		last10.offer(actualScore);
	}

	/**
	 * Makes a wicket fall
	 */
	public void fallWicket() {
		wickets++;
		fallOfWickets.add(new FallOfWicket(wickets, runs, ballsPlayed));
	}

	/**
	 * Makes the Player play a ball
	 */
	public void playBall() {
		ballsPlayed++;
	}

	/**
	 * Returns whether the Player is all out or not
	 * @return true if all out, else false
	 */
	public boolean isAllOut() {
		return wickets == totalWickets;
	}

	/**
	 * Calculates and returns the strike rate of the Player
	 * @return Strike Rate of the Player
	 */
	public double strikeRate() {
		return ballsPlayed == 0? 0: (double) runs / ballsPlayed * 100;
	}

	/**
	 * Returns the choice of the Player
	 * @return Choice of the Player
	 */
	public abstract int getChoice();
}
