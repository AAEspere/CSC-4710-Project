public class favoriteUser {

	protected int userID;
	protected int favUserID;
	protected String favUserUsername;
	
	favoriteUser() {
		
	}
	
	favoriteUser(int userID, int favUserID, String favUserUsername) {
		this.userID = userID;
		this.favUserID = favUserID;
		this.favUserUsername = favUserUsername;
	}
	
	favoriteUser(int favUserID, String favUserUsername) {
		this.favUserID = favUserID;
		this.favUserUsername = favUserUsername;
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
	
	public void setfavUserUsername(String favUserUsername) {
		this.favUserUsername = favUserUsername;
	}
	
	public String getfavUserUsername() {
		return favUserUsername;
	}
	
}
