package components;

import java.util.Date;

public class Debit extends Flow {

	public Debit(String comment, int identifier, double ammount, int targetAccountNumber, boolean effect, Date date) {
		super(comment, identifier, ammount, targetAccountNumber, effect, date);
	}

}
