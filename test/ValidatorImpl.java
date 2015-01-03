import static org.junit.Assert.*;

import org.junit.Test;

import view.validator.Validator;

public class ValidatorImpl {
	view.validator.ValidatorImpl validator = view.validator.ValidatorImpl
			.getInstance();

	@Test
	public void testValidateFIO() {
		boolean actual = validator.validateFIO("Akimenko");
		assertTrue(actual);
		
		actual = validator.validateFIO("Akimenko Alexander");
		assertTrue(actual);		
		
		actual = validator.validateFIO("Akimenko Alexander Alexandrovich");
		assertTrue(actual);
	}

	@Test
	public void testValidatePassport() {
		boolean actual = validator.validatePassport("34 34 343434");
		assertTrue(actual);
		
	}
	
}
