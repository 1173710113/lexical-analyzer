package regulation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import exception.RecognizeEndException;

class RecognizeEndTest{

	private RecognizeEnd r = RecognizeEnd.getInstance();
	
	@Test
	void testOnlyOneEndState() throws RecognizeEndException {
		String str = "<end>:0";
		List<String> expected = Arrays.asList("0");
		List<String> actual = r.recognize(str);
		assertEquals(expected, actual);
	}
	
	@Test
	void testMultipleEndState() throws RecognizeEndException {
		String str = "<end>:0,1";
		List<String> expected = Arrays.asList("0","1");
		List<String> actual = r.recognize(str);
		assertEquals(expected, actual);
	}

}
