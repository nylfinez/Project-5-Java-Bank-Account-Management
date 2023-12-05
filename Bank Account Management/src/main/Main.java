package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

public class Main {

	public static void main(String[] args) {
		Client[] clients = loadClients(4);
		displayClients(clients);

		List<Account> accounts = loadAccounts(clients);
		displayAccounts(accounts);
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
}
