package bank;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Bank implements BankProc, Serializable{

	private static final long serialVersionUID = 1L;
	private List<Client> clients = new ArrayList<Client>();
	private List<SpendingAccount> accountsSp = new ArrayList<SpendingAccount>();
	private List<SavingAccount> accountsSa = new ArrayList<SavingAccount>();
	private static HashMap<String,Client> myBank = new HashMap<String, Client>();
	
	public Bank(){
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<SpendingAccount> getSpendingAccounts() {
		return accountsSp;
	}
	
	public List<SavingAccount> getAccountsSa() {
		return accountsSa;
	}
	
	public List<SpendingAccount> getAccountsSp() {
		return accountsSp;
	}

	public static HashMap<String, Client> getMyBank() {
		return myBank;
	}

	public static void put(String key, Client c){
		if (c == null)
			return;
		if (myBank.containsKey(key)) {
			System.out.println("This person already exists!");
			return;
		}
		myBank.put(key, c);
	}
	
	public int findIndex(Account account, String cnp) {
		int index=0;
		for (Account a : myBank.get(cnp).getSavingA()) {
			if (a.getIBAN().equals(account.getIBAN())) 
				return index;
			index++;
		}
		index=0;
		for (Account a : myBank.get(cnp).getSpendingA()) {
			if (a.getIBAN().equals(account.getIBAN())) 
				return index;
			index++;
		}
		return -1;
	}
	
	public boolean isCNPCorrect(Client c) {
		if (c==null) 
			return false;
		int len = c.getCNP().length();
		if (len!=13) {
			System.out.println("Invalid CNP!\n");
			return false;
		}
		for (int i=0; i<len;i++) {
			if (!Character.isDigit(c.getCNP().charAt(i))){
				System.out.println("Invalid CNP!\n");
				return false;
			}
		}
		return true;
	}
	
	public boolean isWellFormed(HashMap<String,Client> hmap) {
		for (Entry<String, Client> c : hmap.entrySet()) {
			if (c.equals(null))
				return false;
			if (c.getKey()==null)
				return false;
			if (!isCNPCorrect(c.getValue()))
				return false;
		}
		return true;
	}

	public int addClient(Client client) {
		assert isWellFormed(myBank);
		if (client == null)
			return -1;	
		if (clients.contains(client) || myBank.containsKey(client.getCNP())) {
			System.out.println("This client is already registered");
			return -1;
		}
		if (this.isCNPCorrect(client)) {
			clients.add(client);
			put(client.getCNP(),client);
			System.out.println("Client succesfully registered!");
			assert isWellFormed(myBank);
			return 1;
		}
		return -1;
	}

	public int removeClient(String cnp, Client client) {
		if (client == null) 
			return -1;
		int i = myBank.get(cnp).getSavingA().size();
		if (i>0)
			myBank.get(cnp).getSavingA().clear();
		i = myBank.get(cnp).getSpendingA().size();
		if (i>0)
			myBank.get(cnp).getSpendingA().clear();
		clients.remove(cnp);
		myBank.remove(cnp, client);
		System.out.println("Client succesfully removed");
		return 1;
	}

	public int addSavingAccount(SavingAccount sa, Client client) {
		if (client == null || sa == null) 
			return -1;
		myBank.get(client.getCNP()).getSavingA().add(sa);
		return 1;
	}
	
	public int addSpendingAccount(SpendingAccount se, Client client) {
		if (client == null || se == null) 
			return -1;
		myBank.get(client.getCNP()).getSpendingA().add(se);
		return 1;
	}
	
	public int removeSavingAccount(SavingAccount sa, Client client) {
		if (client == null || sa == null) 
			return -1;
		myBank.get(client.getCNP()).getSavingA().remove(sa);
		this.getAccountsSa().remove(sa);
		System.out.println("Saving account successfully removed");
		return 1;
	}

	public int removeSpendingAccount(SpendingAccount se, Client client) {
		if (client == null || se == null) 
			return -1;	
		myBank.get(client.getCNP()).getSpendingA().remove(se);
		this.getAccountsSp().remove(se);
		System.out.println("Spending account successfully removed");
		return 1;
	}
	
	public void addMoneyToSaving(Client c, SavingAccount sa, double sum) {
		if (c==null || sa==null)
			return;
		if (myBank.get(c.getCNP()).getSavingA().contains(sa)) {
			int i= findIndex(sa,c.getCNP());
			myBank.get(c.getCNP()).getSavingA().get(i).addMoneySaving(sum);
			return;
		}
		System.out.println("Wrong account");
	}

	public void addMoneyToSpending(Client c, SpendingAccount se, double sum) {
		if (c==null || se==null)
			return;
		if (myBank.get(c.getCNP()).getSpendingA().contains(se)) {
			int i=findIndex(se,c.getCNP());
			myBank.get(c.getCNP()).getSpendingA().get(i).addMoney(sum);
			return;
		}
		System.out.println("Wrong account");
	}
	
	public void withdrawMoneyFromSaving(Client c, SavingAccount sa) {
		if (c==null || sa==null)
			return;
		if (myBank.get(c.getCNP()).getSavingA().contains(sa)) {
			int i=findIndex(sa,c.getCNP());
			myBank.get(c.getCNP()).getSavingA().get(i).withdrawMoneySaving();
			return;
		} 
		System.out.println("Wrong account");
	}
	
	public void withdrawMoneyFromSpending(Client c, SpendingAccount se, double sum) {
		if (c==null || se==null)
			return;
		if (myBank.get(c.getCNP()).getSpendingA().contains(se)) {
			int i=findIndex(se,c.getCNP());
			myBank.get(c.getCNP()).getSpendingA().get(i).withdrawMoney(sum);
			return;
		} 
		System.out.println("Wrong account");
	}
	
	public void serialize() {
		assert isWellFormed(myBank);
		try {
			FileOutputStream outputFile = new FileOutputStream("bankfile.ser");
			ObjectOutputStream output = new ObjectOutputStream(outputFile);
			output.writeObject(myBank);
			output.close();
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		assert isWellFormed(myBank);
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() {
		assert isWellFormed(myBank);
		try {
			FileInputStream inputFile = new FileInputStream("bankfile.ser");
			ObjectInputStream input = new ObjectInputStream(inputFile);
			Bank.myBank = (HashMap<String, Client>) input.readObject();
			input.close();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		assert isWellFormed(myBank);
	}
}
