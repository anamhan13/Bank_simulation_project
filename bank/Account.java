package bank;

import java.io.Serializable;
import java.util.Observable;

public class Account  extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;
	private String iban; 
	private double money;
	
	public Account(double money) {
		this.money=money;
		this.iban= generateNewIban();
	}
	
	public Account() {
		this.iban= generateNewIban();
		this.money=0;
	}

	public String getIBAN() {
		return iban;
	}

	public void setIBAN(String iban) {
		this.iban = iban;
	}
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void addMoney(double money) {
		this.setMoney(money+this.getMoney());
		setChanged();
		notifyObservers("Money added to account "+this.getIBAN()+" : "+money+" RON");
	}
	
	public void withdrawMoney(double money) {
		if (this.getMoney()>=money) {
			this.setMoney(this.getMoney()-money);
			setChanged();
			notifyObservers("Money withdrawn from account "+this.getIBAN()+" : "+money+" RON");
		} else {
			System.out.println("Cannot withdraw this sum from account!\n");
		}
	}
	
	private String generateNewIban() {
		String sb = "";
		for (int i=0; i<15;i++) 
			sb += Integer.toString((int)(Math.random()*10));
		return "RO"+sb;
	}
}
