package regulation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.RecognizeStartException;

class RecognizeStartTest {

	private RecognizeStart r = RecognizeStart.getInstance();
	
	@Test
	void testOnlyOneStartState() throws RecognizeStartException {
		String str = "<start>:a";
		String expected = "a";
		String actual = r.recognize(str);
		assertEquals(expected, actual);
	}

}
