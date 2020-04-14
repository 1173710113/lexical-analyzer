package util.regulation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.recognize.RecognizeConvertionException;
import lexer.dfa.ConversionTable;
import lexer.dfa.ConversionTableImp;

public class RecognizeConversionImp implements RecognizeConvertion {

	private String firstRowPattern = "\\|(\\s*(.|" + SpecialSymbolTable.getSpecialSymbolPattern() + ")\\s*\\|(\\s*(.|"
			+ SpecialSymbolTable.getSpecialSymbolPattern() + ")\\s*|\\|)*)";
	private String otherRowPattern = "\\|(\\s*(\\d+|)\\s*\\|(\\s*(\\d+|)\\s*|\\|)*)";

	@Override
	public ConversionTable recognize(List<String> convertionTableRowList) throws RecognizeConvertionException {
		Pattern p = Pattern.compile(firstRowPattern);
		Matcher m = p.matcher(convertionTableRowList.get(0));
		if (m.matches()) {
			List<String> rawInputList = Arrays.asList(m.group(1).split("\\|"));
			int convertionTableRowSize = convertionTableRowList.size() - 1;
			int convertionTableColumnSize = rawInputList.size() - 1;
			ConversionTableImp table = new ConversionTableImp(convertionTableRowSize, convertionTableColumnSize);
			setConvertionTableInputs(table, rawInputList);
			constructConvertionTableByRow(table, convertionTableRowList);
			return table;
		}
		throw new RecognizeConvertionException();
	}

	private void setConvertionTableInputs(ConversionTableImp table, List<String> rawInputList) {
		List<Character> inputList = getInputs(rawInputList);
		putInputsIntoConvertionTable(table, inputList);
	}

	private List<Character> getInputs(List<String> rawInputList) {
		List<Character> inputList = new ArrayList<Character>();
		int rawInputListSize = rawInputList.size();
		for (int i = 0; i < rawInputListSize; i++) {
			String input = rawInputList.get(i).trim();
			if (!input.equals("")) {
				Character inputCharacter = convertToCharacter(input);
				inputList.add(inputCharacter);
			}
		}
		return inputList;
	}

	private void putInputsIntoConvertionTable(ConversionTableImp table, List<Character> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			table.addSymbol(inputList.get(i), i);
		}
	}

	private Character convertToCharacter(String charString) {
		if(SpecialSymbolTable.isSpecialSymbol(charString))return SpecialSymbolTable.getSpecialSymbolCharacter(charString);
		assertTrue(charString.length() == 1);
		return charString.toCharArray()[0];
	}

	private void constructConvertionTableByRow(ConversionTableImp table, List<String> convertionTableRowList)
			throws RecognizeConvertionException {
		int convertionTableRowListSize = convertionTableRowList.size();
		Pattern p = Pattern.compile(otherRowPattern);
		for (int i = 1; i < convertionTableRowListSize; i++) {
			Matcher m = p.matcher(convertionTableRowList.get(i));
			if (m.matches()) {
				List<String> temp = Arrays.asList(m.group(1).split("\\|"));
				table.addState(temp.get(0).trim(), i - 1);
				int convertionTableColumnSize = temp.size();
				for (int j = 1; j < convertionTableColumnSize; j++) {
					String state = temp.get(j).trim();
					table.addConversion(i - 1, j - 1, state.equals("") ? null : state);
				}
			} else {
				throw new RecognizeConvertionException();
			}
		}
	}

}
