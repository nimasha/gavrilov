package view.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorImpl implements Validator {

	private static ValidatorImpl instance;
	private final String fio_regex = "^([A-Z]{1}[a-z]*)$|^[A-Z]{1}[a-z]*(-[A-Z]{1}[a-z]*)*$";
	private final String passport_regex = "^(([0-9]{2}\\s){2}[0-9]{6})?$";
	private final String date_regex = "^(?:(?:31(/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(/)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:19|[2-9]\\d)?\\d{2})$|^(?:29(/)0?2\\3(?:(?:(?:19|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(/)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:19|[2-9]\\d)?\\d{2})$|^$";
	private final String balance_regex = "^(-?\\d+(\\.([0-9]){1,2})?)?$";	
	
	public static ValidatorImpl getInstance() {
		if (instance == null) {
			instance = new ValidatorImpl();
		}
		return instance;
	}

	@Override
	public boolean validateFIO(String fio) {
		return fio.matches(fio_regex);
	}

	@Override
	public boolean validatePassport(String pass) {
		return pass.matches(passport_regex);
	}

	@Override
	public boolean validateDate(String date) {
		return date.matches(date_regex);
	}

	@Override
	public boolean validateBalance(String balance) {
		return balance.matches(balance_regex);
	}

}
