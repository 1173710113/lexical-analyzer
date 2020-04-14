package lexer.dfa.factory;

import java.io.FileNotFoundException;

import exception.recognize.RecognizeException;
import lexer.dfa.DFA;
import lexer.dfa.LexicalDFA;

public interface DFAFactory {

	/**
	 * 读取文件创建DFA
	 * @param fileName 文件路径
	 * @return DFA
	 */
	public DFA createDFAByFile(String fileName) throws FileNotFoundException, RecognizeException;
	
	public LexicalDFA createLexicalDFAByFile(String fileName) throws FileNotFoundException, RecognizeException;
	
	/**
	 * 
	 * @return
	 */
	public static DFAFactory getInstance() {
		return new DFAFactoryImp();
	}
}
