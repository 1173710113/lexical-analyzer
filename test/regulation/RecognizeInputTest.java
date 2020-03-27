package regulation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import exception.RecognizeInputException;

class RecognizeInputTest {

	private RecognizeInput r = RecognizeInput.getInstance();
	
	private String str3 = "<input>:a,,";
	private List<Character> expect3 = Arrays.asList('a', ',');
	private String str4 = "<input>:a,b,,";
	private List<Character> expect4 = Arrays.asList('a', 'b', ',');
	private String str6 = "<input>:a,b,,,c,d";
	private List<Character> expect6 = Arrays.asList('a', 'b', ',', 'c', 'd');
	private String str7 = "<input>:a,b,,,c,d, ";
	private List<Character> expect7 = Arrays.asList('a', 'b', ',', 'c', 'd', ' ');
	private String str8 = "<input>:a,b,,,c, ,d";
	private List<Character> expect8 = Arrays.asList('a', 'b', ',', 'c',' ', 'd');
	private String str9 = "<input>: ,a,b,,,c,d";
	private List<Character> expect9 = Arrays.asList(' ', 'a', 'b', ',', 'c', 'd');
		
	@Test
	void testOneInputAndNotComma() throws RecognizeInputException {
		String str = "<input>:a";
		List<Character> expected = Arrays.asList('a');
		List<Character> actual = r.recognize(str);
		assertEquals(expected, actual);
	}
	
	@Test
	void testMutipleInputAndWithoutComma() throws RecognizeInputException {
		String str = "<input>:a,b";
		List<Character> expected = Arrays.asList('a', 'b');
		List<Character> actual = r.recognize(str);
		assertEquals(expected, actual);
	}
	
	@Test
	void test3() throws RecognizeInputException {
		assertEquals(expect3, r.recognize(str3));
	}
	
	@Test
	void test4() throws RecognizeInputException {
		assertEquals(expect4, r.recognize(str4));
	}
	
	@Test
	void testMutipleInputAndCommaInMiddle() throws RecognizeInputException {
		String str = "<input>:a,b,,,c";
		List<Character> expected = Arrays.asList('a', 'b', ',', 'c');
		List<Character> actual = r.recognize(str);
		assertEquals(expected, actual);
	}
	
	@Test
	void test6() throws RecognizeInputException {
		assertEquals(expect6, r.recognize(str6));
	}
	
	@Test
	void test7() throws RecognizeInputException {
		assertEquals(expect7, r.recognize(str7));
	}
	
	@Test
	void test8() throws RecognizeInputException {
		assertEquals(expect8, r.recognize(str8));
	}
	
	@Test
	void test9() throws RecognizeInputException {
		assertEquals(expect9, r.recognize(str9));
	}
	
	@Test
	void testOnlyOneBlank() throws RecognizeInputException {
		String str = "<input>: ";
		List<Character> expected = Arrays.asList(' ');
		List<Character> actual = r.recognize(str);
		assertEquals(expected, actual);
	}
	
	@Test
	void testOnlyOneComma() throws RecognizeInputException {
		String str = "<input>:,";
		List<Character> expected = Arrays.asList(',');
		List<Character> actual = r.recognize(str);
		assertEquals(expected, actual);
	}
	
	@Test
	void testMutipleInputsAndCommaInFront() throws RecognizeInputException {
		String str = "<input>:,,a";
		List<Character> expected = Arrays.asList(',', 'a');
		List<Character> actual = r.recognize(str);
		assertEquals(expected, actual);
	}

}
