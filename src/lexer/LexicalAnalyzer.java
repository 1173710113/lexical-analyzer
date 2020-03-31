package lexer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dfa.DFA;
import dfa.StateConverter;
import dfa.StateConverterImp;
import dfa.factory.CreateDFA;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;
import exception.recognize.RecognizeException;
import readhead.ReadHead;
import readhead.factory.ReadHeadFactory;
import token.Token;

public class LexicalAnalyzer {
	private static final String defaultDFAFile = "G:\\eclipse-workspace\\lexical analyzer\\text\\text2.txt";
	private static final String defaultReadHeadFile = "G:\\eclipse-workspace\\lexical analyzer\\text\\’˝»∑≤‚ ‘.txt";
	private DFA dfa = null;
	private ReadHead readHead = null;
	List<Token> tokenList = new ArrayList<Token>();

	public void lexicalAnalyse() throws FileNotFoundException, RecognizeException {
		tokenList.clear();
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
	
	private void readToken() {
		StringBuilder stringBuilder = new StringBuilder();
		while (readHead.hasNextChar()) {
			Character c = readHead.nextChar();
			if (readHead.isNextLineChar(c))break;
			try {
				stringBuilder.append(c);
				dfa.inputChar(c);
			} catch (InValidInputException e) {
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
			
		} 
	}
	
	private void errorInput(Character c) {
		System.out.println("error input" + c);
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
		CreateDFA dfaCreater = CreateDFA.getInstance();
		dfa = dfaCreater.createDFAByFile(dfaFile);
	}

	public void setReadHeadFromFile(String readHeadFile) throws FileNotFoundException {
		readHead = ReadHeadFactory.createReaderFromFile(readHeadFile);
	}

}
