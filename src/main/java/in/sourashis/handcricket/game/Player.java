package in.sourashis.handcricket.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Player of the game
 * @author Sourashis Pal
 */
public class Player {
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
	 * Adds runs to the players score
	 * @param hit The runs to add
	 */
	public void addRuns(int hit) {
		runs += hit;
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
}
