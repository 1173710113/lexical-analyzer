package dfa;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dfa.factory.CreateDFA;
import exception.dfa.InValidInputException;
import exception.recognize.RecognizeException;

class DFATest {

	private CreateDFA c = CreateDFA.getInstance();

	@Test
	void test() throws FileNotFoundException, RecognizeException, InValidInputException {
		String fileName = "G:\\eclipse-workspace\\lexical analyzer\\text\\test1.txt";
		String str = "abb";
		DFA dfa = c.createDFAByFile(fileName);
		assertTrue(dfa.testify(str));
	}

	@Test
	void test2() throws FileNotFoundException, RecognizeException, InValidInputException {
		String fileName = "G:\\eclipse-workspace\\lexical analyzer\\text\\text2.txt";
		List<String> strList = Arrays.asList("=", ";", "[", "]", "{", "}", "(", ")", ",", "+", "-", "*", "/", "%", "++",
				"--", "+=", "-=", "/=", "%=", "&", "^", "|", "&=", "^=", "|=", "==", "&&", "||", "!", "!=", ">", ">>",
				">>=", "<", "<<", "<<=","0","012","0102","0x4d","0.5","0.5E-2","hda","?","'a'","/*45	646*    ***/","\"hdkaj456F	FF?>>>>hd\"");
		DFA dfa = c.createDFAByFile(fileName);
		for (String str : strList) {
			System.out.println(str);
			assertTrue(dfa.testify(str));
		}
	}

	@Test
	void test3() throws FileNotFoundException, RecognizeException, InValidInputException {
		String fileName = "G:\\eclipse-workspace\\lexical analyzer\\text\\text2.txt";
		List<String> strList = Arrays.asList("===","00","4gig","'dfdf'","0x5p");
		DFA dfa = c.createDFAByFile(fileName);
		for (String str : strList) {
			assertFalse(dfa.testify(str));
		}
	}

}
