package regulation;

import java.util.List;

import exception.RecognizeInputException;

public interface RecognizeInput {

	/**
	 * 识别输入符号串
	 * @param str 要识别的串
	 * @return 返回输入符号串的集合
	 * @throws RecognizeInputException
	 */
	public List<Character> recognize(String str) throws RecognizeInputException;
	
	public static RecognizeInput getInstance() {
		return new RecognizeInputImp();
	}
}
