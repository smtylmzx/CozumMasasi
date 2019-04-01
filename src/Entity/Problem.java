package Entity;

import java.sql.Date;

public class Problem {
	int ProbID;
	String problem;
	Date date;

	public int getProbID() {
		return ProbID;
	}

	public void setProbID(int probID) {
		this.ProbID = probID;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
