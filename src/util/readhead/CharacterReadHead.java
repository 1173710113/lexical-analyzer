package util.readhead;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class CharacterReadHead implements ReadHead<Character> {

	private String text;
	private int cursor = 0;
	private Character nextLineChar = '\n';
	
	public CharacterReadHead(List<String> stringList) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String str : stringList) {
			if (stringBuilder.length() != 0) {
				stringBuilder.append(nextLineChar);
			}
			stringBuilder.append(str.trim());
		}
		text = stringBuilder.toString();
	}

	@Override
	public Character next() {
		assertTrue(hasNext());
		Character character = text.charAt(cursor);
		cursor++;
		return character;
	}
	
	@Override
	public boolean hasNext() {
		int textLength = text.length();
		return cursor < textLength;
	}
	

	@Override
	public void rollBack(int stepSize) {
		cursor -= stepSize;
	}

	@Override
	public void skipBlank() {
		while(hasNext() && isBlank()) {
			cursor++;
		}
	}
	
	private boolean isBlank() {
		List<Character> blankCharacterList = new ArrayList<Character>();
		blankCharacterList.add(' ');
		blankCharacterList.add('	');
		blankCharacterList.add('\n');
		return blankCharacterList.contains(text.charAt(cursor));
	}


}
