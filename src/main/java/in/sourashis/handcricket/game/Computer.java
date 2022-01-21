package in.sourashis.handcricket.game;

import java.util.*;

/**
 * Represents the Computer
 * @author Sourashis Pal
 */
public class Computer extends Player {

	/** The Game which the Computer is playing **/
	private final Game game;

	/** Stores the count of previous dots during batting by the Computer **/
	private int previousDots;

	/**
	 * Constructor of the class
	 * @param totalWickets Total wickets in the game
	 * @param game The Game object which is using the Computer object
	 */
	public Computer(int totalWickets, Game game) {
		super(totalWickets, "Computer");
		this.game = game;
		System.out.println();
	}

	@Override
	public int getChoice() {
		int choice;

		List<Integer> choices = new ArrayList<>(150);
		Map<Integer, Integer> frequencies = new HashMap<>();
		for (int i = 1; i <= 6; i++) {
			frequencies.put(i, 0);
		}

		// Putting each valid values in choices so that it may have at least a bit of chance if it's not included in choices
		for (int i = 1; i <= 6; i++) {
			choices.add(i);
		}

		if (game.getBatter() == this) {
			// If Computer is the batter, it will reduce the probability of the scores frequently given by the bowler and increase the others to survive
			// If there is chance of out by consecutive dots, the dot probability will be 5% else it will be 30%

			for (int h: game.getBowler().getLast10()) {
				frequencies.put(h, frequencies.get(h) + 1);
			}

			for (int h: frequencies.keySet()) {
				int times = 4 - frequencies.get(h);
				for (int i = 0; i < times; i++) {
					choices.add(h);
				}
			}
			int dots = previousDots == 2? choices.size()/20 : choices.size()/3;
			for (int i = 0; i < dots; i++) {
				choices.add(0);
			}
			choice = choices.get(game.getRandom().nextInt(choices.size()));
			if (choice == 0 && previousDots != 2) {
				previousDots++;
			} else {
				previousDots = 0;
			}
		} else {
			// If the Computer is the bowler, it will increase the probability of the scores given by the batter and decrease the others to make the batter out
			// The probability of dot will be 5%

			for (int h: game.getBatter().getLast10()) {
				frequencies.put(h, frequencies.get(h) + 1);
			}

			for (int h: frequencies.keySet()) {
				int times = frequencies.get(h);
				for (int i = 0; i < times; i++) {
					choices.add(h);
				}
			}
			int dots = choices.size()/20;
			for (int i = 0; i < dots; i++) {
				choices.add(0);
			}
			choice = choices.get(game.getRandom().nextInt(choices.size()));
			if (choice == 0) choice = choices.get(game.getRandom().nextInt(choices.size()));
		}

		return choice;
	}
}
