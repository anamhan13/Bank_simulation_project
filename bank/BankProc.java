package bank;

public interface BankProc {

	/*
	 * @pre : client!=null
	 * @pre : !bank.clients.contains(client)
	 * @pre : !bank.hashMap.contains(client)
	 * @pre : isCNPCorrect(client.CNP)
	 * 
	 * @post : bank.clients.size++
	 * @post : bank.clients.contains(client)
	 * @post : bank.hashMap.contains(client)
	 */
	int addClient(Client client);
	
	/*
	 * @pre : client!=null
	 * @pre : isCNPCorrect(cnp)
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.hashMap.contains(client)
	 * 
	 * @post : bank.clients.size--
	 * @post : !bank.clients.contains(client)
	 * @post : !bank.hashMap.contains(client)
	 */
	int removeClient(String cnp, Client client);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : bank.clients.contains(client)
	 * @pre : !bank.savingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : !client.savingaccounts.contains(sa);
	 * 
	 * @post : bank.savingaccounts.size++
	 * @post : bank.savingaccount.contains(sa)
	 * @post : client.savingaccount.size++
	 * @post : client.savingaccount.contains(sa)
	 */
	int addSavingAccount(SavingAccount sa, Client client);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.savingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : client.savingaccounts.contains(sa);
	 * 
	 * @post : bank.savingaccounts.size--
	 * @post : !bank.savingaccount.contains(sa)
	 * @post : client.savingaccount.size--
	 * @post : !client.savingaccount.contains(sa)
	 */
	int removeSavingAccount(SavingAccount sa, Client client);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : bank.clients.contains(client)
	 * @pre : !bank.spendingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : !client.spendingaccounts.contains(sa);
	 * 
	 * @post : bank.spendingaccounts.size++
	 * @post : bank.spendingaccount.contains(sa)
	 * @post : client.spendingaccount.size++
	 * @post : client.spendingaccount.contains(sa)
	 */
	int addSpendingAccount(SpendingAccount sa, Client client);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.spendingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : client.spendingaccounts.contains(sa);
	 * 
	 * @post : bank.spendingaccounts.size--
	 * @post : !bank.spendingaccount.contains(sa)
	 * @post : client.spendingaccount.size--
	 * @post : !client.spendingaccount.contains(sa)
	 */
	int removeSpendingAccount(SpendingAccount sa, Client client);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : sum>0
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.savingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : client.savingaccounts.contains(sa);
	 * @pre : !sa.moneyDeposited
	 * 
	 * @post : sa.moneyDeposited
	 * @post : sa.money+=sum
	 */
	void addMoneyToSaving(Client c, SavingAccount sa, double sum);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : sum>0
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.savingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : client.savingaccounts.contains(sa);
	 * 
	 * @post : sa.money+=sum
	 */
	void addMoneyToSpending(Client c, SpendingAccount se, double sum);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : sum>0
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.savingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : client.savingaccounts.contains(sa);
	 * @pre : sum<=se.money
	 * 
	 * @post : sa.money -=sum
	 */
	void withdrawMoneyFromSpending(Client c, SpendingAccount se, double sum);
	
	/*
	 * @pre : client!=null
	 * @pre : sa!=null
	 * @pre : sum>0
	 * @pre : bank.clients.contains(client)
	 * @pre : bank.savingaccounts.contains(sa)
	 * @pre : bank.hashMap.contains(client)
	 * @pre : client.savingaccounts.contains(sa);
	 * @pre : !sa.moneyWithdrawn
	 * 
	 * @post : sa.moneyWithdrawn
	 * @post : sa.money=0
	 */
	void withdrawMoneyFromSaving(Client c, SavingAccount sa);
}
