package regulation;

import java.util.List;

import dfa.ConversionTable;
import exception.recognize.RecognizeConvertionException;

public interface RecognizeConvertion {


	/**
	 * ʶ��\|\s*(.|blank|split)\s*(\s*(.|blank|split)\s*)*����blank��ʾ�������Ϊ�ո�split��ʾ�������Ϊ|��
	 * @param strs
	 * @return
	 * @throws RecognizeConvertionException
	 */
	public ConversionTable recognize(List<String> strs) throws RecognizeConvertionException;
	
	public static RecognizeConvertion getInstance() {
		return new RecognizeConversionImp();
	}
}
