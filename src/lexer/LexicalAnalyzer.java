package lexer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dfa.DFA;
import dfa.factory.CreateDFA;
import exception.RecognizeException;
import readhead.ReadHead;
import readhead.factory.ReadHeadFactory;
import token.Token;

public class LexicalAnalyzer {
	private static final String defaultDFAFile = "";
	private static final String defaultReadHeadFile = "";
	private DFA dfa = null;
	private ReadHead readHead = null;

	public List<Token> lexicalAnalyse() throws FileNotFoundException, RecognizeException{
		defaultInit();
		List<Token> tokenList = new ArrayList<>();
		//TODO ´Ê·¨·ÖÎö
		return tokenList;
	}
	
	public void setDFAFromFile(String dfaFile) throws FileNotFoundException, RecognizeException  {
		CreateDFA dfaCreater = CreateDFA.getInstance();
		dfa = dfaCreater.createDFAByFile(dfaFile);
	}
	
	public void setReadHeadFromFile(String readHeadFile) {
		readHead = ReadHeadFactory.createReaderFromFile(readHeadFile);
	}
	
	private void defaultInit() throws FileNotFoundException, RecognizeException {
		if(!isDFAInitialized())setDFAFromFile(defaultDFAFile);
		if(!isReadHeadInitialized())setReadHeadFromFile(defaultReadHeadFile);
	}
	
	private boolean isDFAInitialized() {
		if(dfa == null)return false;
		return true;
	}
	
	private boolean isReadHeadInitialized() {
		if(readHead == null)return false;
		return true;
	}
	
	
}
