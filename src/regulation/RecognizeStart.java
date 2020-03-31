package regulation;

import exception.recognize.RecognizeStartException;

public interface RecognizeStart {

	/**
	 * ʶ�����ʼ״̬
	 * @param str
	 * @return ��ʼ״̬
	 * @throws RecognizeStartException
	 */
	public String recognize(String str) throws RecognizeStartException;
	
	public static RecognizeStart getInstance() {
		return new RecognizeStartImp();
	}
}
