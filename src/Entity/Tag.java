package Entity;

public class Tag {
	int TagID;
	String tag;
	int ProbID;
	
	public int getProbID() {
		return ProbID;
	}
	public void setProbID(int probID) {
		ProbID = probID;
	}
	public int getTagID() {
		return TagID;
	}
	public void setTagID(int tagID) {
		TagID = tagID;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
