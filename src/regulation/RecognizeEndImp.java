package regulation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.RecognizeEndException;

public class RecognizeEndImp implements RecognizeEnd {

	private String pattern = "<end>:(\\w+(,\\w+)*)";
	private Pattern p = Pattern.compile(pattern);
	
	@Override
	public List<String> recognize(String str) throws RecognizeEndException {
		Matcher m = p.matcher(str);
		if(m.matches()) {
			return Arrays.asList(m.group(1).split(","));
		}
		throw new RecognizeEndException();
	}

}
