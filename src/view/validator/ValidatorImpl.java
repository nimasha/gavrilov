package view.validator;

public class ValidatorImpl implements Validator {

	private static ValidatorImpl instance;
	private final String fio_regex = "^([A-Z]{1}[a-z]*\\s?)*$";
	private final String passport_regex = "^([0-9]{2}\\s){2}[0-9]{6}$";
	private final String date_regex = "";
	private final String balance_regex = "-?\\d+(.\\d+)?";	
	
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
