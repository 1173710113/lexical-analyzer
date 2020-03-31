package regulation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.recognize.RecognizeStatesException;


public class RecognizeStatesImp implements RecognizeStates{

	private String pattern="<states>:(\\w+(,\\w+)*)";
	private Pattern p = Pattern.compile(pattern);
	
	@Override
	public List<String> recognize(String str) throws RecognizeStatesException {
		Matcher m = p.matcher(str);
		if(m.matches()) {
			return Arrays.asList(m.group(1).split(","));
		}
		throw new RecognizeStatesException();
	}


}
