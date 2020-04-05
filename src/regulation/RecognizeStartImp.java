package regulation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.recognize.RecognizeStartException;

public class RecognizeStartImp implements RecognizeStart {

	private String pattern = "<start>:(\\w+)";
	
	@Override
	public String recognize(String str) throws RecognizeStartException {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		if(m.matches()) {
			return m.group(1);
		}
		throw new RecognizeStartException();
	}

}
