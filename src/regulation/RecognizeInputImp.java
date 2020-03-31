package regulation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.recognize.RecognizeInputException;

public class RecognizeInputImp implements RecognizeInput {

	private String pattern = "<input>:(.(,.)*)";
	private Pattern p = Pattern.compile(pattern);
	private Matcher m;

	@Override
	public List<Character> recognize(String str) throws RecognizeInputException {
		m = p.matcher(str);
		if (m.matches()) {
			if (isInputsAreMultipleAndCommaExistInMiddle()) {
				return recognizeWhenInputsAreMutipleAndCommaExistInMiddle();
			} else if (isInputsAreOnlyOneComma()) {
				return recognizeWhenInputsAreOnlyOneComma();
			} else {
				return recognizeWhenInputAreMultipleAndCommaNotExistInMiddle();
			}
		}
		throw new RecognizeInputException();
	}

	private boolean isInputsAreMultipleAndCommaExistInMiddle() {
		return m.group(1).contains(",,,");
	}

	private List<Character> recognizeWhenInputsAreMutipleAndCommaExistInMiddle() {
		List<Character> inputList = new ArrayList<>();
		List<String> temp = Arrays.asList(m.group(1).split(",,,"));
		for (String s : Arrays.asList(temp.get(0).split(","))) {
			assertTrue(s.length() == 1);
			inputList.add(s.charAt(0));
		}
		inputList.add(',');
		for (String s : Arrays.asList(temp.get(1).split(","))) {
			assertTrue(s.length() == 1);
			inputList.add(s.charAt(0));

		}
		return inputList;
	}

	private List<Character> recognizeWhenInputAreMultipleAndCommaNotExistInMiddle() {
		List<Character> inputList = new ArrayList<Character>();
		String str = m.group(1);
		if (str.indexOf(',') == 0) {
			inputList.add(',');
			str = str.substring(2);
		}
		for (String s : Arrays.asList(str.split(","))) {
			assertTrue(s.length() == 1);
			inputList.add(s.charAt(0));
		}
		if (str.contains(",,") && !inputList.contains(',')) {
			inputList.add(',');
		}
		return inputList;
	}

	private boolean isInputsAreOnlyOneComma() {
		return m.group(1).equals(",");
	}

	private List<Character> recognizeWhenInputsAreOnlyOneComma() {
		List<Character> inputList = new ArrayList<Character>();
		inputList.add(',');
		return inputList;
	}

}
