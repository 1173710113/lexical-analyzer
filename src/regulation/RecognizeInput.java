package regulation;

import java.util.List;

import exception.RecognizeInputException;

public interface RecognizeInput {

	/**
	 * ʶ��������Ŵ�
	 * @param str Ҫʶ��Ĵ�
	 * @return ����������Ŵ��ļ���
	 * @throws RecognizeInputException
	 */
	public List<Character> recognize(String str) throws RecognizeInputException;
	
	public static RecognizeInput getInstance() {
		return new RecognizeInputImp();
	}
}
