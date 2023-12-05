package components;

import java.util.Date;

public class Transfer extends Flow {
	
	private int issuingAccountNumber;

	public Transfer(String comment, int identifier, double ammount, int targetAccountNumber, boolean effect,
			Date date, int issuingAccountNumber) {
		super(comment, identifier, ammount, targetAccountNumber, effect, date);
		this.issuingAccountNumber = issuingAccountNumber;
	}

}
