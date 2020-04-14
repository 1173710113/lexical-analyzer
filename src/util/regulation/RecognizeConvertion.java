package util.regulation;

import java.util.List;

import exception.recognize.RecognizeConvertionException;
import lexer.dfa.ConversionTable;

public interface RecognizeConvertion {


	/**
	 * 识别\|\s*(.|blank|split)\s*(\s*(.|blank|split)\s*)*其中blank表示输入符号为空格，split表示输入符号为|。
	 * @param strs
	 * @return
	 * @throws RecognizeConvertionException
	 */
	public ConversionTable recognize(List<String> strs) throws RecognizeConvertionException;
	
	public static RecognizeConvertion getInstance() {
		return new RecognizeConversionImp();
	}
}
