package components;

public abstract class Account {
protected static int lastAccountNumber = 0; //initial value available for all instances
	
	protected String label;
	protected double balance;
	protected int accountNumber;
	protected Client client;
	
	//constructor
	public Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.balance = 0.0;
		this.accountNumber = generateAccountNumber();
	}


	//getters and setters
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(Flow flow) {
		if (flow instanceof Credit) {
			this.balance += flow.getAmmount();
		} else if (flow instanceof Debit) {
			this.balance -= flow.getAmmount();
		} else if (flow instanceof Transfer) {
			Transfer transferFlow = (Transfer) flow;
			if (this.accountNumber == transferFlow.getTargetAccountNumber()) {
				this.balance += transferFlow.getAmmount();
			} else if (this.accountNumber == transferFlow.getIssuingAccountNumber()) {
				this.balance -= transferFlow.getAmmount();
			}
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public String toString() {
		return String.format("Account: %d - %s, Client: %s, Balance: %.2f", accountNumber, label, client.toString(), balance);
	}

	private int generateAccountNumber() {
		return ++lastAccountNumber;
	}
	
}
