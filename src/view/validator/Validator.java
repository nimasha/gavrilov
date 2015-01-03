package view.validator;

public interface Validator {
	public boolean validateFIO(String fio);
	public boolean validatePassport(String pass);
	public boolean validateDate(String date);
	public boolean validateBalance(String balance);
}
