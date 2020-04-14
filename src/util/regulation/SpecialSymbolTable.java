package util.regulation;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class SpecialSymbolTable {

	private static final Map<String, Character> specialSymbolMap = new HashMap<String,Character>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("blank", ' ');
			put("split", '|');
			put("tab", '	');
		}
	};
	
	private SpecialSymbolTable() {
		
	}
	
	public static String getSpecialSymbolPattern() {
		StringBuilder specialSymbolPatternStringBuilder = new StringBuilder();
		for(Map.Entry<String, Character> entry : specialSymbolMap.entrySet()) {
			if(specialSymbolPatternStringBuilder.length() != 0) {
				specialSymbolPatternStringBuilder.append("|");
			}
			specialSymbolPatternStringBuilder.append(entry.getKey());
		}
		return specialSymbolPatternStringBuilder.toString();
	}
	
	public static Character getSpecialSymbolCharacter(String specialSysmbol) {
		assertTrue(specialSymbolMap.containsKey(specialSysmbol));
		return specialSymbolMap.get(specialSysmbol);
	}
	
	public static boolean isSpecialSymbol(String specialSymbol) {
		return specialSymbolMap.containsKey(specialSymbol);
	}
}
