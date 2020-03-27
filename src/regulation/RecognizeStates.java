package regulation;

import java.util.List;

import exception.RecognizeStatesException;

public interface RecognizeStates {

	/**
	 * 
	 * @param str
	 * @return
	 * @throws RecognizeStatesException
	 */
	public List<String> recognize(String str) throws RecognizeStatesException;
	
	public static RecognizeStates getInstance() {
		return new RecognizeStatesImp();
	}
	
}
