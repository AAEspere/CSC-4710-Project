//class just for Query 10 on Project Part 3
public class userPair {

	protected String userGive;
	protected String userReceive;
	
	public userPair() {
	
	}
	
	public userPair(String userGive, String userReceive) {
		this.userGive = userGive;
		this.userReceive = userReceive;
	}
	
	public void setuserGive(String userGive) {
		this.userGive = userGive;
	}
	
	public String getuserGive() {
		return userGive;
	}
	
	public void setuserRecieve(String userReceive) {
		this.userReceive = userReceive;
	}
	
	public String getuserReceive() {
		return userReceive;
	}
	
}
