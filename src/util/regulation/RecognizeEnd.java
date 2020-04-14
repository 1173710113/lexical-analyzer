package util.regulation;

import java.util.List;

import exception.recognize.RecognizeEndException;

public interface RecognizeEnd {


	/**
	 * 
	 * @param str
	 * @return
	 * @throws RecognizeEndException
	 */
	public List<String> recognize(String str) throws RecognizeEndException;
	
	public static RecognizeEnd getInstance() {
		return new RecognizeEndImp();
	}
	
}
