import static org.junit.Assert.*;

import org.junit.Test;

import view.validator.Validator;

public class ValidatorImpl {
	view.validator.ValidatorImpl validator = view.validator.ValidatorImpl
			.getInstance();

	@Test
	public void testValidateFIO() {
		//только фамили€ и им€ с большой буквы в любом пор€дке, латиницей
			
		boolean actual = validator.validateFIO("Akimenko Alexander");
		assertTrue(actual);	
		
		actual = validator.validateFIO("Rimskiy-Korsakov Nikolay");
		assertTrue(actual);
		
		actual = validator.validateFIO("Nikolay Rimskiy-Korsakov");
		assertTrue(actual);
		
		actual = validator.validateFIO("Rimskiy-korsakov Nikolay");
		assertFalse(actual);
		
		actual = validator.validateFIO("Rimskiy - Korsakov Nikolay");
		assertFalse(actual);
		
		actual = validator.validateFIO("Nikolaeva M.V.");
		assertFalse(actual);
		
		actual = validator.validateFIO("Nikolaeva M.");
		assertFalse(actual);
		
		actual = validator.validateFIO("Nikolaeva M");
		assertFalse(actual);
		
		actual = validator.validateFIO("AKimenko Alexander");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko ALexander");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko AlexANder");
		assertFalse(actual);
		
		actual = validator.validateFIO("-Akimenko Alexander");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko-Alexander");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko Alex-der");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko Alexder-");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko Alexander Alexandrovich");
		assertFalse(actual);
		
		actual = validator.validateFIO("akimenko alexander Alexandrovich");
		assertFalse(actual);
		
		actual = validator.validateFIO("Ak4imenko Alexder");
		assertFalse(actual);
		
		actual = validator.validateFIO("AkimenkoAlexder");
		assertFalse(actual);
		
		actual = validator.validateFIO("Ќиколаева ћари€");
		assertFalse(actual);
		
		actual = validator.validateFIO("Nikolaeva, Mariia");
		assertFalse(actual);
		
		actual = validator.validateFIO("Akimenko/Alevander");
		assertFalse(actual);
	}

	@Test
	public void testValidatePassport() {
		boolean actual = validator.validatePassport("34 34 343434");
		assertTrue(actual);
		
		actual = validator.validatePassport("09 15 123456");
		assertTrue(actual);
		
		actual = validator.validatePassport("34 34 343 434");
		assertFalse(actual);
		
		actual = validator.validatePassport("3434343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("3434 343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("3434 343 434");
		assertFalse(actual);
		
		actual = validator.validatePassport("34-34 343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("34 34 343 434");
		assertFalse(actual);
		
		actual = validator.validatePassport("34 34 343O34");
		assertFalse(actual);
		
		actual = validator.validatePassport("34 t4 343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("g4 34 343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("-4 34 343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("3 4 34 3 43 434");
		assertFalse(actual);
		
		actual = validator.validatePassport("3 434 343434");
		assertFalse(actual);
		
		actual = validator.validatePassport("343434 3 434");
		assertFalse(actual);
		
	}
	
	@Test
	public void testValidateDate() {
		//dd/mm/yyyy
		boolean actual = validator.validateDate("01/12/2000");
		assertTrue(actual);
		
		actual = validator.validateDate("05/01/2015");
		assertTrue(actual);
		
		actual = validator.validateDate("05/02/2015");
		assertTrue(actual);
		
		actual = validator.validateDate("31/12/2000");
		assertTrue(actual);
		
		actual = validator.validateDate("31/11/2000");
		assertFalse(actual);
		
		actual = validator.validateDate("29/02/2012");
		assertTrue(actual);
		
		actual = validator.validateDate("29/02/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29/13/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("32/02/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29/03/2016");
		assertFalse(actual);
		
		actual = validator.validateDate("32/32/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("-29/02/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29-02-2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29.02.2013");
		assertFalse(actual);
		
		actual = validator.validateDate("1992/02/12");
		assertFalse(actual);
		
		actual = validator.validateDate("29/1992/08");
		assertFalse(actual);
		
		actual = validator.validateDate("12/31/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29/2/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("9/12/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("dd/02/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29/mm/2013");
		assertFalse(actual);
		
		actual = validator.validateDate("29/02/yyyy");
		assertFalse(actual);
		
		actual = validator.validateDate("dd/mm/yyyy");
		assertFalse(actual);
		
				
	}
	
	
	
	@Test
	public void testValidateBalance() {
		//-34.89
		boolean actual = validator.validateBalance("-35.89");
		assertTrue(actual);
		
		actual = validator.validateBalance("37.89");
		assertTrue(actual);
		
		actual = validator.validateBalance("-35");
		assertTrue(actual);
		
		actual = validator.validateBalance("38");
		assertTrue(actual);
		
		actual = validator.validateBalance("-00.99");
		assertTrue(actual);
		
		actual = validator.validateBalance("-0.99");
		assertTrue(actual);
		
		actual = validator.validateBalance("1.9");
		assertTrue(actual);
		
		actual = validator.validateBalance("125.39");
		assertTrue(actual);
		
		actual = validator.validateBalance("-456.89");
		assertTrue(actual);
		
		actual = validator.validateBalance("-456.189");
		assertFalse(actual);
		
		actual = validator.validateBalance("-00.234");
		assertFalse(actual);
		
		actual = validator.validateBalance("0.189");
		assertFalse(actual);
		
		actual = validator.validateBalance("100.-99");
		assertFalse(actual);
		
		actual = validator.validateBalance(".99");
		assertFalse(actual);
		
		actual = validator.validateBalance("1099.");
		assertFalse(actual);
		
		actual = validator.validateBalance("109,12");
		assertFalse(actual);
		
		actual = validator.validateBalance("-109,12");
		assertFalse(actual);
		
		actual = validator.validateBalance("123 . 123");
		assertFalse(actual);
		
		actual = validator.validateBalance("- 1099.12");
		assertFalse(actual);
		
		actual = validator.validateBalance("109 67");
		assertFalse(actual);
		
		actual = validator.validateBalance("1 099.67");
		assertFalse(actual);
		
		actual = validator.validateBalance("10-99.12");
		assertFalse(actual);
		
		
		
		

	}
}
	
