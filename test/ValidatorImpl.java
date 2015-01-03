import static org.junit.Assert.*;

import org.junit.Test;

import view.validator.Validator;

public class ValidatorImpl {
	view.validator.ValidatorImpl validator = view.validator.ValidatorImpl
			.getInstance();

	@Test
	public void testValidateFIO() {
		//
		boolean actual = validator.validateFIO("Akimenko");
		assertTrue(actual);
		
		actual = validator.validateFIO("Akimenko Alexander");
		assertTrue(actual);		
		
		actual = validator.validateFIO("Akimenko Alexander Alexandrovich");
		assertTrue(actual);
		
		actual = validator.validateFIO("akimenko alexander Alexandrovich");
		assertFalse(actual);
	}

	@Test
	public void testValidatePassport() {
		boolean actual = validator.validatePassport("34 34 343434");
		assertTrue(actual);
		
		actual = validator.validatePassport("34 34 343 434");
		assertFalse(actual);
	}
	
	@Test
	public void testValidateDate() {
		//dd/mm/yyyy
		boolean actual = validator.validateDate("34/78/3000");
		assertTrue(actual);
		
		actual = validator.validatePassport("34 34 343 434");
		assertFalse(actual);
	}
	
	//-34.89
}
