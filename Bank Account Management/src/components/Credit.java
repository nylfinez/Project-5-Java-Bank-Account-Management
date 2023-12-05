package components;

import java.util.Date;

public class Credit extends Flow {

	public Credit(String comment, int identifier, double ammount, int targetAccountNumber, boolean effect, Date date) {
		super(comment, identifier, ammount, targetAccountNumber, effect, date);
	}

}
