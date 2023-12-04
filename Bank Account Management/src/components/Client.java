package components;

public class Client {
private static int lastClientNumber = 100; //shared for all instances
	
	private int clientNumber;
	private String firstName;
	private String name;
	
	//constructor
	public Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		this.clientNumber = generateClientNumber();
	}
	
	//getters and setters
	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
	public int getClientNumber() {
		return clientNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	
	@Override
	public String toString() {
		return String.format("%d: %s, %s", clientNumber, name, firstName);
	}

	private static int generateClientNumber() {
		return ++lastClientNumber; //adds 1 to the last number
	}
}
