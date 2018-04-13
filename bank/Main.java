package bank;

public class Main {

	public static void main(String[] args) {
		
		
		Client client = new Client("Ana Banana","245a78","Bucuresti");
		Client client2 = new Client("Anca Banana","1367867412356","Cluj Napoca");
		Client client3 = new Client("Anca Bananca","3678774122359","Alba Iulia");
		
		/*for (Entry<String, Client> c : hmap.entrySet()) {
			System.out.println(c.getValue().g2etName());
		}*/
		
		SpendingAccount spen = new SpendingAccount(220);

		Bank bank = new Bank();
		bank.serialize();
		bank.addClient(client);
		bank.addClient(client2);
		bank.addClient(client3);

		bank.addSpendingAccount(spen, client3);
		bank.addMoneyToSpending(client3,spen,34);
		spen.withdrawMoney(38);
		
		SavingAccount save = new SavingAccount(10);

		bank.addSavingAccount(save, client2);
		bank.addSavingAccount(save, client2);
		bank.addMoneyToSaving(client2,save,600);
		bank.addMoneyToSaving(client2,save,600);
		bank.withdrawMoneyFromSaving(client2, save);
		bank.deserialize();
		
	}

}
