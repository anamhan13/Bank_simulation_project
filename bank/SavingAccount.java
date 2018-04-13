package bank;

import java.io.Serializable;
import java.text.DecimalFormat;

public class SavingAccount extends Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private double interestRate;
	private double interest;
	private int periodOfTime; //the deposit period
	private boolean moneyDeposited;
	private boolean moneyWithdrawn;
	private static DecimalFormat df2 = new DecimalFormat("###.##");

	public SavingAccount() {
		this.moneyDeposited=false;
		this.moneyWithdrawn=false;
		this.interestRate=0.07;
		this.interest=0;
	}

	public SavingAccount(double money,int period) {
		super(money);
		this.interestRate=0.07;
		this.periodOfTime=period;
		this.interest=0;
		this.moneyDeposited=true;
		this.moneyWithdrawn=false;
	}
	
	public SavingAccount(int period) {
		super(0);
		this.interestRate=0.07;
		this.periodOfTime=period;
		this.interest=0;
		this.moneyDeposited=false;
		this.moneyWithdrawn=false;
	}
	
	public boolean isMoneyDeposited() {
		return moneyDeposited;
	}

	public void setMoneyDeposited(boolean moneyDeposited) {
		this.moneyDeposited = moneyDeposited;
	}

	public boolean isMoneyWithdrawn() {
		return moneyWithdrawn;
	}

	public void setMoneyWithdrawn(boolean moneyWithdrawn) {
		this.moneyWithdrawn = moneyWithdrawn;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double rate) {
		this.interestRate = rate;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getPeriodOfTime() {
		return periodOfTime;
	}

	public void setPeriodOfTime(int periodOfTime) {
		this.periodOfTime = periodOfTime;
	}
	
	public double computeInterest() {
		double interest=0;
		interest = this.getPeriodOfTime()*this.getInterestRate()*this.getMoney();
		this.setInterest(interest);
		super.setMoney(super.getMoney()+this.interest);
		return interest;
	}
	
	public void addMoneySaving(double money)
	{
		if (!this.isMoneyDeposited()) {
			this.addMoney(money);
			this.setMoneyDeposited(true);
		} else {
			System.out.println("Cannot add any more money to deposit \n");
		}
	}
	
	public void withdrawMoneySaving()
	{
		if (!this.isMoneyWithdrawn()) {
			this.computeInterest();
			System.out.println("Money withdrawn from account "+this.getIBAN()+" : "+df2.format(super.getMoney())+" RON");
			this.withdrawMoney(super.getMoney());
			this.setMoneyWithdrawn(true);
		} else {
			System.out.println("Cannot withdraw money anymore from the deposit \n");
		}
	}
	
}
