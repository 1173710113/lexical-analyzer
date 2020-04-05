package readhead;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class ReadHeadImp implements ReadHead {

	private String text;
	private int currentCharPosition = 0;
	private Character nextLineChar = '\n';
	
	public ReadHeadImp(List<String> stringList) {
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
	public Character nextChar() {
		assertTrue(hasNextChar());
		Character character = text.charAt(currentCharPosition);
		currentCharPosition++;
		return character;
	}
	
	@Override
	public boolean hasNextChar() {
		int textLength = text.length();
		return currentCharPosition < textLength;
	}
	

	@Override
	public void rollBack(int stepSize) {
		currentCharPosition -= stepSize;
	}

	@Override
	public void skipBlank() {
		while(hasNextChar() && isBlank()) {
			currentCharPosition++;
		}
	}
	
	private boolean isBlank() {
		List<Character> blankCharacterList = new ArrayList<Character>();
		blankCharacterList.add(' ');
		blankCharacterList.add('	');
		blankCharacterList.add('\n');
		return blankCharacterList.contains(text.charAt(currentCharPosition));
	}


}
