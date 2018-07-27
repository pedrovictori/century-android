package victori.centuryai;

public class PlayerInventory extends Inventory {


	public PlayerInventory(int b, int g, int r, int y) {
		super(b, g, r, y);
		if (b < 0 || g < 0 || r < 0 || y < 0) {
			throw new IllegalArgumentException("Quantities need to be positive");
		}
		checkTotal();
	}

	public PlayerInventory(Inventory inventory) {
		this(inventory.getB(), inventory.getG(), inventory.getR(), inventory.getY());
		checkTotal();
	}

	public void change(InventoryChange change) {
		setB(getB() + change.getB());
		setG(getG() + change.getG());
		setR(getR() + change.getR());
		setY(getY() + change.getY());
	}

	private void checkTotal() {
		if (getTotal() > 10) {
			throw new IllegalArgumentException("Inventory can't have more than ten elements");
		}
	}

}
