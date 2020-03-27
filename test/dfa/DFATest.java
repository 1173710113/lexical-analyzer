package dfa;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import dfa.factory.CreateDFA;
import exception.RecognizeException;

class DFATest {

	private String fileName = "G:\\eclipse-workspace\\lexical analyzer\\text\\test1.txt";
	private CreateDFA c = CreateDFA.getInstance();
	private String str = "abb";

	
	@Test
	void test() throws FileNotFoundException, RecognizeException {
		DFA dfa = c.createDFAByFile(fileName);
		assertTrue(dfa.testify(str));
	}

}
