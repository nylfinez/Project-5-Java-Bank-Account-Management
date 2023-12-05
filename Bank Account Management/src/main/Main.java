package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

public class Main {

	public static void main(String[] args) {
		
		Client[] clients = loadClients(4);
		System.out.println("---Clients---");
		displayClients(clients);
		
		List<Account> accounts = loadAccounts(clients);
		System.out.println("\n" + "---Accounts---");
		displayAccounts(accounts);
		
		Hashtable<Integer, Account> accountsHashTable = createHashTable(accounts);
		System.out.println("\n" + "---Hashtable in ascending order of balance---");
		displayAccountsAscending(accountsHashTable);
		
	}
	
	//generate clients
		private static Client[] loadClients(int numberOfClients) {
			Client[] clients = new Client[numberOfClients];
			for (int i = 0; i < numberOfClients; i++) {
				clients[i] = new Client("name"+(i+1), "firstName"+(i+1));
			}
			return clients;
		}
		
		//display clients
		private static void displayClients(Client[] clients) {
			Arrays.stream(clients)
			.map(Client::toString)
			.forEach(System.out::println);
		}

		//generate accounts
		private static List<Account> loadAccounts(Client[] clients) {
			List<Account> accounts = new ArrayList<>();
			
			for (Client client : clients) {
				CurrentAccount currentAccount = new CurrentAccount("Current", client);
				SavingsAccount savingsAccount = new SavingsAccount("Savings", client);
				
				accounts.add(currentAccount);
				accounts.add(savingsAccount);
			}
			
			return accounts;
		}
		
		//display accounts
		private static void displayAccounts(List<Account> accounts) {
			accounts.stream()
			.map(Account::toString)
			.forEach(System.out::println);
		}
		
		//account hashtable
		private static Hashtable<Integer, Account> createHashTable(List<Account> accounts) {
			Hashtable<Integer, Account> accountHashTable = new Hashtable<>();
			
			for (Account account : accounts) {
				accountHashTable.put(account.getAccountNumber(), account);
			}
			
			return accountHashTable;
		}
		
		//display ascending
		private static void displayAccountsAscending(Hashtable<Integer, Account> accountHash) {
			Map<Integer, Account> sortedMap = accountHash.entrySet().stream() //seperate Hash for sorted accounts
					.sorted(Map.Entry.comparingByValue((val1, val2) -> Double.compare(val1.getBalance(), val2.getBalance())))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (entry1, entry2) -> entry1, Hashtable::new));
			
			sortedMap.forEach((accountNumber, account) -> System.out.println(account));
		}
}
