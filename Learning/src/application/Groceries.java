package application;

public class Groceries {

	private int id;
	private String groceryName;
	private String quantity;
	private String useBy;
	
	public Groceries(int id, String groceryName, String quantity, String useBy) {
		super();
		this.id = id;
		this.groceryName = groceryName;
		this.quantity = quantity;
		this.useBy = useBy;
	}

	public String getGroceryName() {
		return groceryName;
	}

	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUseBy() {
		return useBy;
	}

	public void setUseBy(String useBy) {
		this.useBy = useBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
