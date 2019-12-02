
public class reviews {
	protected String username;
	protected int itemID;
	protected String itemTitle;
	protected String score;
	protected String remark;
	
	reviews(String username, int itemID, String itemTitle, String score, String remark) {
		this.username = username;
		this.itemID = itemID;
		this.itemTitle = itemTitle;
		this.score = score;
		this.remark = remark;
		
	}
	reviews(String score, String remark) {
		this.score = score;
		this.remark = remark;
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
	
	public String getscore() {
		return score;
	}
	
	public void setscore(String score) {
		this.score = score;
	}
	
	public String getremark() {
		return remark;
	}
	
	public void setremark(String remark) {
		this.remark = remark;
	}
	
}
