
public class reviews {
	protected int itemID;
	protected int userID;
	protected String score;
	protected String remark;
	
	reviews(int itemID, int userID, String score, String remark) {
		this.itemID = itemID;
		this.userID = userID;
		this.score = score;
		this.remark = remark;
		
	}
	reviews(String score, String remark) {
		this.score = score;
		this.remark = remark;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
