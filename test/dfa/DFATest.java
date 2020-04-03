package dfa;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dfa.factory.DFAFactory;
import exception.dfa.InValidInputException;
import exception.recognize.RecognizeException;

class DFATest {

	private DFAFactory c = DFAFactory.getInstance();

	@Test
	void test() throws FileNotFoundException, RecognizeException, InValidInputException {
		String fileName = "text\\test1.txt";
		String str = "abb";
		DFA dfa = c.createDFAByFile(fileName);
		assertTrue(dfa.testify(str));
	}

	@Test
	void test2() throws FileNotFoundException, RecognizeException, InValidInputException {
		String fileName = "text\\text2.txt";
		List<String> strList = Arrays.asList("=", ";", "[", "]", "{", "}", "(", ")", ",", "+", "-", "*", "/", "%", "++",
				"--", "+=", "-=", "/=", "%=", "&", "^", "|", "&=", "^=", "|=", "==", "&&", "||", "!", "!=", ">", ">>",
				">>=", "<", "<<", "<<=","0","012","0102","0x4d","0.5","0.5E-2","hda","?","'a'","/*45	646*    ***/","\"hdkaj456F	FF?>>>>hd\"","Adda","4556",">=","<=");
		DFA dfa = c.createDFAByFile(fileName);
		for (String str : strList) {
			assertTrue(dfa.testify(str));
		}
	}

	@Test
	void test3() throws FileNotFoundException, RecognizeException, InValidInputException {
		String fileName = "text\\text2.txt";
		List<String> strList = Arrays.asList("===","00","4gig","'dfdf'","0x5p");
		DFA dfa = c.createDFAByFile(fileName);
		for (String str : strList) {
			assertFalse(dfa.testify(str));
		}
	}

}
