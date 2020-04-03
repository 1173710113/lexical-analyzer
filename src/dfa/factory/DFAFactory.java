package dfa.factory;

import java.io.FileNotFoundException;

import dfa.DFA;
import dfa.LexicalDFA;
import exception.recognize.RecognizeException;

public interface DFAFactory {

	/**
	 * ��ȡ�ļ�����DFA
	 * @param fileName �ļ�·��
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
