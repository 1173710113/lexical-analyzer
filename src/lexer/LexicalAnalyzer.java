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
	private static final String defaultDFAFile = "text\\text2.txt";
	private static final String defaultReadHeadFile = "text\\’˝»∑≤‚ ‘.txt";
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
	
	public List<Token> getResultToken(){
		return tokenList;
	}
	
	public List<ErrorToken> getErrorToken(){
		return errorTokenList;
	}
	
	private void readToken() {
		StringBuilder stringBuilder = new StringBuilder();
		while (readHead.hasNextChar()) {
			Character c = readHead.nextChar();
			try {
				stringBuilder.append(c);
				dfa.inputChar(c);
			} catch (InValidInputException e) {
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
				errorInput(c);
				break;
			} catch (NullConvertionException e) {
				if (dfa.isCurrentAcceptable()) {
					stringBuilder.deleteCharAt(stringBuilder.length() - 1);
					readHead.rollBack(1);
				}
				break;
			}
		}
		if(dfa.isCurrentAcceptable()) {
			StateConverter stateConverter = new StateConverterImp();
			tokenList.add(stateConverter.stateConverToToken(dfa.getCurrentState(), stringBuilder.toString()));
		} else {
			errorToken(stringBuilder.toString());
		}
	}
	
	private void errorInput(Character c) {
	}
	
	private void errorToken(String errorTokenString) {
		
	}

	private void defaultInit() throws FileNotFoundException, RecognizeException {
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
