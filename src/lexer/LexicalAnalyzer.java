package lexer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dfa.DFA;
import dfa.StateConverter;
import dfa.StateConverterImp;
import dfa.factory.DFAFactory;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;
import exception.recognize.RecognizeException;
import readhead.ReadHead;
import readhead.factory.ReadHeadFactory;
import token.Token;
import token.errorToken.ErrorToken;

public class LexicalAnalyzer {
	private DFA dfa = null;
	private ReadHead readHead = null;
	List<Token> tokenList = new ArrayList<>();
	List<ErrorToken> errorTokenList = new ArrayList<>();

	public void lexicalAnalyse() throws FileNotFoundException, RecognizeException {
		tokenList.clear();
		errorTokenList.clear();
		defaultInit();
		while (readHead.hasNextChar()) {
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
		while (readHead.hasNextChar()) {
			Character c = readHead.nextChar();
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
		String defaultReadHeadFile = "text\\��ȷ����.txt";
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
		readHead = ReadHeadFactory.createReaderFromFile(readHeadFile);
	}

	public void setReadHeadFromStringList(List<String> stringList) {
		readHead = ReadHeadFactory.createReadHeadFromStringList(stringList);
	}

}
