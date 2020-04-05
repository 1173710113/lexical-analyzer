package regulation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.recognize.RecognizeEndException;

public class RecognizeEndImp implements RecognizeEnd {

	private String pattern = "<end>:(\\w+(,\\w+)*)";
	
	@Override
	public List<String> recognize(String str) throws RecognizeEndException {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		if(m.matches()) {
			return Arrays.asList(m.group(1).split(","));
		}
		throw new RecognizeEndException();
	}

}
