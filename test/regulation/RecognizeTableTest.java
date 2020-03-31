package regulation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dfa.ConversionTable;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;
import exception.recognize.RecognizeConvertionException;

class RecognizeTableTest {

	private RecognizeConvertion r = new RecognizeConversionImp();
	private List<String> str1 = Arrays.asList("| |a|b|", "|0|1|0|", "|1|1|2|", "|2|1|3|", "|3|1|0|");
	private List<String> states1 = Arrays.asList("0", "1", "2", "3");
	private List<Character> inputs1 = Arrays.asList('a', 'b');
	private String[][] table1 = { { "1", "0" }, { "1", "2" }, { "1", "3" }, { "1", "0" } };
	private List<String> str2 = Arrays.asList("| |split|blank|", "|0|1|0|", "|1|1|2|", "|2|1|3|", "|3|1|0|");
	private List<String> states2 = Arrays.asList("0", "1", "2", "3");
	private List<Character> inputs2 = Arrays.asList('|', ' ');
	private String[][] table2 = { { "1", "0" }, { "1", "2" }, { "1", "3" }, { "1", "0" } };
	private List<String> str3 = Arrays.asList("|      |     split|blank    |", "|0|   1     |0|", "|   1|1|2|", "|2  |  1|3|", "|3|1|0|");
	private List<String> states3 = Arrays.asList("0", "1", "2", "3");
	private List<Character> inputs3 = Arrays.asList('|', ' ');
	private String[][] table3 = { { "1", "0" }, { "1", "2" }, { "1", "3" }, { "1", "0" } };
	private List<String> str4 = Arrays.asList("|      |     split|blank    |", "|0|        |0|", "|   1|1|2|", "|2  |  1|3|", "|3|1|0|");
	private List<String> states4 = Arrays.asList("0", "1", "2", "3");
	private List<Character> inputs4 = Arrays.asList('|', ' ');
	private String[][] table4 = { { null, "0" }, { "1", "2" }, { "1", "3" }, { "1", "0" } };
	

	@Test
	void test1() throws RecognizeConvertionException, InValidInputException, NullConvertionException {
		ConversionTable c = r.recognize(str1);
		int l1 = states1.size();
		int l2 = inputs1.size();
		for (int i = 0; i < l1; i++) {
			for (int j = 0; j < l2; j++) {
				assertEquals(table1[i][j], c.convert(states1.get(i), inputs1.get(j)));
			}
		}
	}

	@Test
	void test2() throws RecognizeConvertionException, InValidInputException, NullConvertionException {
		ConversionTable c = r.recognize(str2);
		int l1 = states2.size();
		int l2 = inputs2.size();
		for (int i = 0; i < l1; i++) {
			for (int j = 0; j < l2; j++) {
				assertEquals(table2[i][j], c.convert(states2.get(i), inputs2.get(j)));
			}
		}
	}
	
	@Test
	void test3() throws RecognizeConvertionException, InValidInputException, NullConvertionException {
		ConversionTable c = r.recognize(str3);
		int l1 = states3.size();
		int l2 = inputs3.size();
		for (int i = 0; i < l1; i++) {
			for (int j = 0; j < l2; j++) {
				assertEquals(table3[i][j], c.convert(states3.get(i), inputs3.get(j)));
			}
		}
	}
	

}
