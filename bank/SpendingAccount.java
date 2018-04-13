package bank;

import java.io.Serializable;

public class SpendingAccount extends Account implements Serializable{

	private static final long serialVersionUID = 1L;
	//private static DecimalFormat df2 = new DecimalFormat(".##");
	//notifyObservers(df2.format(money) +" RON have been added in account no. "+this.getIBAN());
	//notifyObservers(df2.format(money) +" RON have been withdrawn from account no. "+this.getIBAN());

	public SpendingAccount() {
		super();
	}

	public SpendingAccount(double money) {
		super(money);
	}	
}
