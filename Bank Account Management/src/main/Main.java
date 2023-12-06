package main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;

public class Main {

	public static void main(String[] args) {
		
		Client[] clients = loadClients(3);
		System.out.println("---Clients---");
		displayClients(clients);
		
		Account[] accounts = loadAccounts(clients);
		System.out.println("\n" + "---Accounts---");
		displayAccounts(accounts);
		
		Hashtable<Integer, Account> accountsHashTable = createHashTable(accounts);
		System.out.println("\n" + "---Hashtable in ascending order of balance---");
		displayAccountsAscending(accountsHashTable);
		
		Flow[] flows = loadFlows(accounts);
		System.out.println("\n" + "---Flows---");
		displayFlows(flows);
		
		System.out.println("\n" + "---Updated balances---");
		updateBalances(accountsHashTable, flows);
		
	}
	
		/*-----------GENERATE CLIENTS-----------*/
		private static Client[] loadClients(int numberOfClients) {
			Client[] clients = new Client[numberOfClients];
			for (int i = 0; i < numberOfClients; i++) {
				clients[i] = new Client("name"+(i+1), "firstName"+(i+1));
			}
			return clients;
		}
		
		/*-----------DISPLAY CLIENTS-----------*/
		private static void displayClients(Client[] clients) {
			Arrays.stream(clients)
			.map(Client::toString)
			.forEach(System.out::println);
		}

		/*-----------GENERATE ACCOUNTS-----------*/
		private static Account[] loadAccounts(Client[] clients) {
			List<Account> accountsList = new ArrayList<>();
			
			for (Client client : clients) {
				CurrentAccount currentAccount = new CurrentAccount("Current", client);
				SavingsAccount savingsAccount = new SavingsAccount("Savings", client);
				
				accountsList.add(currentAccount);
				accountsList.add(savingsAccount);
			}
			
			return accountsList.toArray(new Account[0]);
		}
		
		/*-----------DISPLAY ACCOUNTS-----------*/
		private static void displayAccounts(Account[] accounts) {
			Arrays.stream(accounts)
			.map(Account::toString)
			.forEach(System.out::println);
		}
		
		/*-----------ACCOUNT HASHTABLE-----------*/
		private static Hashtable<Integer, Account> createHashTable(Account[] accounts) {
			Hashtable<Integer, Account> accountHashTable = new Hashtable<>();
			
			for (Account account : accounts) {
				accountHashTable.put(account.getAccountNumber(), account);
			}
			
			return accountHashTable;
		}
		
		/*-----------DISPLAY ACCOUNTS IN ASCENDING ORDER BY BALANCE-----------*/
		private static void displayAccountsAscending(Hashtable<Integer, Account> accountHash) {
			accountHash.entrySet().stream()
			.sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(Account::getBalance)))
			.map(Map.Entry::getValue)
			.forEach(System.out::println);
		}
		
		/*-----------GENERATE FLOWS-----------*/
		private static Flow[] loadFlows(Account[] accounts) {
			List<Flow> flowsList = new ArrayList<Flow>();
			LocalDate currentDate = LocalDate.now();
			
			//Add debit to first account
			flowsList.add(new Debit("Debit of 50€ from account no.1", flowsList.size()+1, 50, accounts[0].getAccountNumber(), true, getDate(currentDate.plusDays(2))));
			
			//Add credit to savings and credits account
			for (Account account : accounts) {
				if (account instanceof CurrentAccount) {
					flowsList.add(new Credit("Credit of 100.50€ on all current accounts", flowsList.size()+1, 100.50, account.getAccountNumber(), true, getDate(currentDate.plusDays(2))));
				} else if (account instanceof SavingsAccount) {
					flowsList.add(new Credit("Credit of 1500€ on all savings accounts", flowsList.size()+1, 1500, account.getAccountNumber(), true, getDate(currentDate.plusDays(2))));
				}
			}
			
			//Transfer from account no.1 to no.2
			flowsList.add(new Transfer("Transfer of 50€ from account no.1 to account no.2", flowsList.size()+1, 50, accounts[1].getAccountNumber(), false, getDate(currentDate.plusDays(2)), accounts[0].getAccountNumber()));
			
			//convert to array
			return flowsList.toArray(new Flow[0]);
		}
		
		/*-----------DISPLAY FLOWS-----------*/
		private static void displayFlows(Flow[] flows) {
			for (Flow flow : flows) {
				System.out.println(flow.toString());
			}
		}
		
		/*-----------TURN LocalDate INTO Date-----------*/
		private static Date getDate(LocalDate localDate) {
			return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		
		/*-----------UPDATE ALL ACCOUNTS-----------*/
		public static void updateBalances(Hashtable<Integer, Account> accountHash, Flow[] flows) {
			for (Flow flow : flows) {
				accountHash.values().forEach(account -> account.setBalance(flow));
			}
			
			Optional<Account> negativeAccounts = accountHash.values().stream()
					.filter(account -> account.getBalance() < 0)
					.findFirst();
			
			negativeAccounts.ifPresent(account -> 
				System.out.println("WARNING! Account: " + account.getAccountNumber() + " has a negative balance"));
		
			displayAccountsAscending(accountHash);
		}
}
