package lexer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import exception.recognize.RecognizeException;

class LexicalAnalyzerTest {

	@Test
	void test() throws FileNotFoundException, RecognizeException {
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
		lexicalAnalyzer.lexicalAnalyse();
	}

}
