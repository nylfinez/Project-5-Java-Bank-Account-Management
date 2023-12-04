package main;

import java.util.Arrays;
import components.Client;

public class Main {

	public static void main(String[] args) {
		Client[] clients = makeClientArray(4);
		displayClient(clients);

	}
	
	//generate clients
		private static Client[] makeClientArray(int numberOfClients) {
			Client[] clients = new Client[numberOfClients];
			for (int i = 0; i < numberOfClients; i++) {
				clients[i] = new Client("name"+(i+1), "firstName"+(i+1));
			}
			return clients;
		}
		
		//display clients
		private static void displayClient(Client[] clients) {
			Arrays.stream(clients)
			.map(Client::toString)
			.forEach(System.out::println);
		}

}
