package csc4710_Espere_part1;
import java.util.Date;

public class item {
	protected int itemID;
	protected String itemDescription;
	protected Date date;
	protected int itemPrice;
	protected String itemCategory;
	
	public item() {
	}
	
	public item(int itemID) {
		this.itemID = itemID;
	}
	
	//need itemID since it is primary key
	public item(int itemID, String itemDescription, Date date, int itemPrice, String itemCategory) {
		this(itemDescription, date, itemPrice, itemCategory);
		this.itemID = itemID;
	}
	
	public item(String itemDescription, Date date, int itemPrice, String itemCategory) {
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
	
	public String getitemDescription() {
		return itemDescription;
	}
	
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
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
