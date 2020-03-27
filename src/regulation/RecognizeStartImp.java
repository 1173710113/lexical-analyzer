package regulation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.RecognizeStartException;

public class RecognizeStartImp implements RecognizeStart {

	private String pattern = "<start>:(\\w+)";
	private Pattern p = Pattern.compile(pattern);
	
	@Override
	public String recognize(String str) throws RecognizeStartException {
		Matcher m = p.matcher(str);
		if(m.matches()) {
			return m.group(1);
		}
		throw new RecognizeStartException();
	}

}
