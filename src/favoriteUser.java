public class favoriteUser {

	protected int userID;
	protected int favUserID;
	
	favoriteUser() {
		
	}
	
	favoriteUser(int userID, int favUserID) {
		this.userID = userID;
		this.favUserID = favUserID;
	}
	
	public void setUserID(int userID) {
		this.favUserID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setFavUserID(int favUserID) {
		this.favUserID = favUserID;
	}
	
	public int getFavUserID() {
		return favUserID;
	}
	
}
