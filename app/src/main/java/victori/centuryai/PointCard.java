public class PointCard {
	private boolean hasGoldCoin = false;
	private boolean hasSilverCoin = false;
	private final int points;
	private  final Inventory goal;

	public PointCard(int points, Inventory goal) {
		this.points = points;
		this.goal = goal;
	}

	public boolean hasGoldCoin() {
		return hasGoldCoin;
	}

	public void setHasGoldCoin(boolean hasGoldCoin) {
		this.hasGoldCoin = hasGoldCoin;
	}

	public boolean hasSilverCoin() {
		return hasSilverCoin;
	}

	public void setHasSilverCoin(boolean hasSilverCoin) {
		this.hasSilverCoin = hasSilverCoin;
	}

	public int getPoints() {
		return points;
	}

	public Inventory getGoal() {
		return goal;
	}

	/**
	 * Most cards give points according to a simple rule:
	 * brown cubes are worth 4p, green 3p, red 2p, yellow 1p.
	 * But there are exceptions where the points amount is bigger than the cubes' value.
	 * @return true if points and value are the same, false if not.
	 */
	public boolean pointsAgreeWithValue() {
		return points == goal.getValue();
	}
}
