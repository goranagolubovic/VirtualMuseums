package dto;

import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private String date;
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOutflow() {
		return outflow;
	}

	public void setOutflow(String outflow) {
		this.outflow = outflow;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private String outflow;
	private int userId;


	public Transaction(String date, String outflow, int userId) {
		super();
		this.date=date;
		this.outflow=outflow;
		this.userId=userId;
	}
}


