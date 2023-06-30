package beans;


import java.io.Serializable;

import dao.BillDAO;


public class PaymentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean isEnabled = true;

	
	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public  boolean changeState(int isAllowed,int userId) {
		System.out.println(isAllowed+","+userId);
		return BillDAO.changePaymentApprovment(isAllowed, userId);
	}

}
