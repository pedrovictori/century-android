package victori.centuryai;

public class SpiceCard extends AbstractMerchantCard {
	private InventoryChange change;

	public SpiceCard(InventoryChange change) {
		this.change = change;
	}

	/**
	 * Returns just a number of spices to be added.
	 * @param inventory Included to comply with the interface, but it is not used, can be null.
	 * @return an InventoryChange with the spices to be added
	 */
	@Override
	public InventoryChange getChange(Inventory inventory) {
		return change;
	}
}
