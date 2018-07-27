public class TradeCard extends AbstractMerchantCard{
	private final Inventory input;
	private final Inventory output;

	public TradeCard(Inventory input, Inventory output) {
		this.input = input;
		this.output = output;
	}

	/**
	 * Computes the maximum number of outputs that can be created. See getChange(PlayerInventory inventory, int iterations) for a more complete method.
	 * @param inventory the current player's inventory
	 * @return an InventoryChange with the number of inputs to be detracted and outputs to be added
	 */
	@Override
	public InventoryChange getChange(Inventory inventory) {
		PlayerInventory pInv = (PlayerInventory) inventory;
		int maxIt = getMaxIterations(pInv);
		Inventory thisInput = new Inventory(input);
		thisInput.multiplyInventory(- maxIt); //negative because these quantities need to be removed from player's inventory
		Inventory thisOutput = new Inventory(output);
		thisOutput.multiplyInventory(maxIt);

		InventoryChange change = new InventoryChange(thisInput);
		change.addInventory(output);

		return change;
	}

	public InventoryChange getChange(PlayerInventory inventory, int iterations) {
		if (iterations > getMaxIterations(inventory)) {
			throw new IllegalArgumentException("Number of iterations exceeds possible iterations");
		} else {
			Inventory thisInput = new Inventory(input);
			thisInput.multiplyInventory(- iterations); //negative because these quantities need to be removed from player's inventory
			Inventory thisOutput = new Inventory(output);
			thisOutput.multiplyInventory(iterations);

			InventoryChange change = new InventoryChange(thisInput);
			change.addInventory(output);

			return change;
		}
	}

	public int getMaxIterations(PlayerInventory inventory) {
		int minIt = 100;
		for (int i = 0; i < 4; i++) {
			int s = input.getAt(i);

			if (s != 0) { /*divide spice numbers in inventory by the spice number needed for one iteration.
			Store the minimum across the 4 spices as that will ultimately determine how many iterations can be made in total */
				int maxIt = Math.floorDiv(inventory.getAt(i), s);
				if(maxIt<minIt) minIt = maxIt;
			}
		}

		return minIt;
	}
}
