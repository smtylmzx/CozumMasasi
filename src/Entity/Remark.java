package Entity;

public class Remark {
	int RemarkID;
	String remark;
	int probID;
	
	public int getProbID() {
		return probID;
	}
	public void setProbID(int probID) {
		this.probID = probID;
	}
	public int getRemarkID() {
		return RemarkID;
	}
	public void setRemarkID(int remarkID) {
		RemarkID = remarkID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
