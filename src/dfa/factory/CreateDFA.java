package dfa.factory;

import java.io.FileNotFoundException;

import dfa.DFA;
import exception.RecognizeException;

public interface CreateDFA {

	/**
	 * 读取文件创建DFA
	 * @param fileName 文件路径
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
