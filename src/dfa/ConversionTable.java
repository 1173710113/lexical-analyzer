package dfa;

import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;

public interface ConversionTable {

	/**
	 * ������ǰ״̬�����룬������ת��״̬
	 * 
	 * @param state  ��ǰ״̬
	 * @param symbol ��ǰ����
	 * @return
	 */
	public String convert(String state, Character symbol) throws InValidInputException, NullConvertionException;

}
