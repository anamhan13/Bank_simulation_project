package bank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client implements Observer, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String CNP;
	private String address;
	private List<SpendingAccount> spendingA = new ArrayList<SpendingAccount>();
	private List<SavingAccount> savingA = new ArrayList<SavingAccount>();
	
	public Client(String name, String cnp, String adr) {
		this.name = name;
		this.CNP = cnp;
		this.address =adr;
	}

	public Client() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SpendingAccount> getSpendingA() {
		return spendingA;
	}

	public void setSpendingA(List<SpendingAccount> spendingA) {
		this.spendingA = spendingA;
	}

	public List<SavingAccount> getSavingA() {
		return savingA;
	}

	public void setSavingA(List<SavingAccount> savingA) {
		this.savingA = savingA;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cnp) {
		this.CNP = cnp;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public void update(Observable obs, Object obj) {
		//if (spendingA.contains(obs) || savingA.contains(obs))
			System.out.println(obj.toString());
	}

}
