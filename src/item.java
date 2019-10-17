public class item {
	protected int itemID;
	protected String itemTitle;
	protected String itemDescription;
	protected String date;
	protected int itemPrice;
	protected String itemCategory;
	
	public item() {
	}
	
	public item(int itemID) {
		this.itemID = itemID;
	}
	
	//need itemID since it is primary key
	public item(int itemID, String itemTitle, String itemDescription, String date, int itemPrice, String itemCategory) {
		this(itemTitle, itemDescription, date, itemPrice, itemCategory);
		this.itemID = itemID;
	}
	
	public item(String itemTitle, String itemDescription, String date, int itemPrice, String itemCategory) {
		this.itemTitle = itemTitle;
		this.itemDescription = itemDescription;
		this.date = date;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
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
	
	public int getitemPrice() {
		return itemPrice;
	}
	
	public void setitemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getitemCategory() {
		return itemCategory;
	}
	
	public void setitemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
}
