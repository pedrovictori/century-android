import java.util.ArrayList;
import java.util.List;

public class GameState {
	private int goldCoinsLeft;
	private int silverCoinsLeft;
	private int playerCount;
	private int currentTurn;
	private List<PointCard> pointRow;
	private List<MerchantCard> merchantRow;
	private List<Player> players = new ArrayList<>();
	private static List<PlayerInventory> startingInventories = new ArrayList<>();
	static{
		startingInventories.add(0, new PlayerInventory(0,0,0,3));
		startingInventories.add(1, new PlayerInventory(0,0,0,4));
		startingInventories.add(2, new PlayerInventory(0,0,0,4));
		startingInventories.add(3, new PlayerInventory(0,0,1,3));
		startingInventories.add(4, new PlayerInventory(0,0,1,3));
	}

	public GameState(List<PointCard> pointRow, List<MerchantCard> merchantRow, int playerCount) {
		this.pointRow = pointRow;
		this.merchantRow = merchantRow;
		this.playerCount = playerCount;

		goldCoinsLeft = 2 * playerCount;
		silverCoinsLeft = goldCoinsLeft;

		for (int i = 0; i < playerCount; i++) {
			Player newPlayer = new Player(startingInventories.get(i), i);
			players.add(newPlayer);
		}

	}

	public int getGoldCoinsLeft() {
		return goldCoinsLeft;
	}

	public void giveGoldCoin(Player player) {
		if (goldCoinsLeft > 0) {
			goldCoinsLeft--;
			player.addGoldCoin();
		} else throw new IllegalStateException("No gold coins left");
	}

	public int getSilverCoinsLeft() {
		return silverCoinsLeft;
	}

	public void giveSilverCoin(Player player) {
		if (silverCoinsLeft > 0) {
			silverCoinsLeft--;
			player.addSilverCoin();
		} else throw new IllegalStateException("No silver coins left");
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public List<PointCard> getPointRow() {
		return pointRow;
	}

	public List<MerchantCard> getMerchantRow() {
		return merchantRow;
	}

	public List<Player> getPlayers() {
		return players;
	}

	private void nextTurn() {
		currentTurn++;
		if(currentTurn == playerCount) currentTurn = 0;
	}
}
