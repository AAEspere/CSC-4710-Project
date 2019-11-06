public class favoriteItem {

	protected int userID;
	protected int itemID;
	
	public favoriteItem() {
		
	}
	
	public favoriteItem(int userID, int itemID) {
		this.userID = userID;
		this.itemID = itemID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getItemID() {
		return itemID;
	}
	
}
