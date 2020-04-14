package regulation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import exception.recognize.RecognizeStatesException;
import util.regulation.RecognizeStates;

class RecognizeStatesTest {

	private RecognizeStates r = RecognizeStates.getInstance();

	
	@Test
	void testMutipleState() throws RecognizeStatesException {
		String str = "<states>:0,1,2,3";
		List<String> expected = Arrays.asList("0", "1", "2","3");
		List<String> actual = r.recognize(str);
		assertEquals(expected, actual);
	}

}
