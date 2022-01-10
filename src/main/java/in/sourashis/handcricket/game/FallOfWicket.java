package in.sourashis.handcricket.game;

/**
 * Represents a fall of wicket
 */
public record FallOfWicket(int wicket, int runs, int ballsPlayed) {
	/**
	 * Returns String representation of a fall of wicket
	 * @return String representation of a fall of wicket
	 */
	@Override
	public String toString() {
		return String.format("%d/%d (%d)", runs, wicket, ballsPlayed);
	}
}
