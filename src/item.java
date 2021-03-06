public class item {
	protected String username;
	protected int itemID;
	protected String itemTitle;
	protected String itemDescription;
	protected String date;
	protected double itemPrice;
	protected String itemCategory;
	
	public item() {
	}
	
	public item(int itemID) {
		this.itemID = itemID;
	}
	
	//need itemID since it is primary key
	public item(String username, int itemID, String itemTitle, String itemDescription, String date, double itemPrice, String itemCategory) {
		this.username = username;
		this.itemID = itemID;
		this.itemTitle = itemTitle;
		this.itemDescription = itemDescription;
		this.date = date;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
	}
	
	//constructor required when inserting an item: them itemID is generateed, and the date is taken from the time of posting
	//so they are not required just yet.
	public item(String username, String itemTitle, String itemDescription, double itemPrice, String itemCategory) {
		this.username = username;
		this.itemTitle = itemTitle;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;

	}
	
	public item(String itemTitle, String itemDescription, double itemPrice, String itemCategory) {
		this.itemTitle = itemTitle;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
	}
	
	public String getusername() {
		return username;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public int getitemID() {
		return itemID;
	}
	
	public void setitemID(int itemID) {
		this.itemID = itemID;
	}
	
	public String getitemTitle() {
		return itemTitle;
	}
	
	public void setitemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public String getitemDescription() {
		return itemDescription;
	}
	
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getitemPrice() {
		return itemPrice;
	}
	
	public void setitemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getitemCategory() {
		return itemCategory;
	}
	
	public void setitemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
}
