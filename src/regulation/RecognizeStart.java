package regulation;

import exception.recognize.RecognizeStartException;

public interface RecognizeStart {

	/**
	 * 识别出开始状态
	 * @param str
	 * @return 开始状态
	 * @throws RecognizeStartException
	 */
	public String recognize(String str) throws RecognizeStartException;
	
	public static RecognizeStart getInstance() {
		return new RecognizeStartImp();
	}
}
