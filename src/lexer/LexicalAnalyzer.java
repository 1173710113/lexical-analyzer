package lexer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;
import exception.recognize.RecognizeException;
import lexer.dfa.DFA;
import lexer.dfa.StateConverter;
import lexer.dfa.StateConverterImp;
import lexer.dfa.factory.DFAFactory;
import lexer.token.Token;
import lexer.token.errorToken.ErrorToken;
import util.readhead.ReadHead;
import util.readhead.factory.ReadHeadFactory;

public class LexicalAnalyzer {
	private DFA dfa = null;
	private ReadHead<Character> readHead = null;
	List<Token> tokenList = new ArrayList<>();
	List<ErrorToken> errorTokenList = new ArrayList<>();

	public void lexicalAnalyse() throws FileNotFoundException, RecognizeException {
		tokenList.clear();
		errorTokenList.clear();
		defaultInit();
		while (readHead.hasNext()) {
			readHead.skipBlank();
			dfa.setStateToStartState();
			readToken();
		}
	}

	public List<Token> getResultToken() {
		return tokenList;
	}

	public List<ErrorToken> getErrorToken() {
		return errorTokenList;
	}

	private void readToken() {
		StringBuilder stringBuilder = new StringBuilder();
		while (readHead.hasNext()) {
			Character c = readHead.next();
			try {
				dfa.inputChar(c);
			} catch (InValidInputException e) {
				errorInput(c);
				break;
			} catch (NullConvertionException e) {
				readHead.rollBack(1);
				break;
			}
			stringBuilder.append(c);
		}
		if (dfa.isCurrentAcceptable()) {
			StateConverter stateConverter = new StateConverterImp();
			tokenList.add(stateConverter.stateConverToToken(dfa.getCurrentState(), stringBuilder.toString()));
		} else if (stringBuilder.length() > 0) {
			errorToken(stringBuilder.toString());
		}

	}

	private void errorInput(Character c) {
		if (c != '\n')
			errorTokenList.add(new ErrorToken(c.toString()));
	}

	private void errorToken(String errorTokenString) {
		errorTokenList.add(new ErrorToken(errorTokenString));
	}

	private void defaultInit() throws FileNotFoundException, RecognizeException {
		String defaultDFAFile = "text\\text2.txt";
		String defaultReadHeadFile = "text\\’˝»∑≤‚ ‘.txt";
		if (!isDFAInitialized())
			setDFAFromFile(defaultDFAFile);
		if (!isReadHeadInitialized())
			setReadHeadFromFile(defaultReadHeadFile);
	}

	private boolean isDFAInitialized() {
		if (dfa == null)
			return false;
		return true;
	}

	private boolean isReadHeadInitialized() {
		if (readHead == null)
			return false;
		return true;
	}

	public void setDFAFromFile(String dfaFile) throws FileNotFoundException, RecognizeException {
		DFAFactory dfaCreater = DFAFactory.getInstance();
		dfa = dfaCreater.createLexicalDFAByFile(dfaFile);
	}

	public void setReadHeadFromFile(String readHeadFile) throws FileNotFoundException {
		readHead = ReadHeadFactory.createCharacterReadHeadFromFile(readHeadFile);
	}

	public void setReadHeadFromStringList(List<String> stringList) {
		readHead = ReadHeadFactory.createCharacterReadHeadFromStringList(stringList);
	}

}
