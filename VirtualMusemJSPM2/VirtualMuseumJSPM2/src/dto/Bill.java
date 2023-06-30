package dto;



import java.io.Serializable;

public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;
	private int creditCardNumber;
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCreditCardNumber() {
		return creditCardNumber;
	}
	public Bill(int creditCardNumber, String typeOfCreditCard, int pin, double creditScore, int userId, String date,
			boolean isPaymentEnabled) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.typeOfCreditCard = typeOfCreditCard;
		this.pin = pin;
		this.creditScore = creditScore;
		this.userId = userId;
		this.date = date;
		this.isPaymentEnabled = isPaymentEnabled;
	}
	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getTypeOfCreditCard() {
		return typeOfCreditCard;
	}
	public void setTypeOfCreditCard(String typeOfCreditCard) {
		this.typeOfCreditCard = typeOfCreditCard;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public double getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(double creditScore) {
		this.creditScore = creditScore;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isPaymentEnabled() {
		return isPaymentEnabled;
	}
	public void setPaymentEnabled(boolean isPaymentEnabled) {
		this.isPaymentEnabled = isPaymentEnabled;
	}
	private String typeOfCreditCard;
	private int pin;
	private double creditScore;
	private int userId;
	private String date;
	private boolean isPaymentEnabled;
	
}

