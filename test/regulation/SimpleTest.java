package regulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimpleTest {

	@Test
	void test1() {
		String str = "	";
		assertEquals(1, str.length());
	}
	
	@Test
	void test2(){
		String expected = "blank|split|tab";
		String actual = SpecialSymbolTable.getSpecialSymbolPattern();
		assertEquals(expected, actual);
	}

}
