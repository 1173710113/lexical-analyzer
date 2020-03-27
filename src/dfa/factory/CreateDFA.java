package dfa.factory;

import java.io.FileNotFoundException;

import dfa.DFA;
import exception.RecognizeException;

public interface CreateDFA {

	/**
	 * ��ȡ�ļ�����DFA
	 * @param fileName �ļ�·��
	 * @return DFA
	 */
	public DFA createDFAByFile(String fileName) throws FileNotFoundException, RecognizeException;
	
	/**
	 * 
	 * @return
	 */
	public static CreateDFA getInstance() {
		return new CreateDFAImp();
	}
}
