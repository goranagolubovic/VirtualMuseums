package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.TransactionDAO;
import dto.Transaction;

public class TransactionBean implements Serializable {

	/**
	 svi atributi unutar beana moraju da budu privatni i da imaju javne getere i setere
	 */
	private static final long serialVersionUID = -2970261924900411892L;
	/*public boolean addTransaction(Transaction transaction) {
		return TransactionDAO.insert(transaction);
	}*/

	public ArrayList<Transaction> getAll() {
		return TransactionDAO.selectAll();
	}

	public TransactionBean() {
		// TODO Auto-generated constructor stub
	}
	

}
