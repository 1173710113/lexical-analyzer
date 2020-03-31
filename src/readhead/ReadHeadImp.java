package readhead;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import filereader.InputStrategy;

public class ReadHeadImp implements ReadHead {

	private String text;
	private int currentCharPosition = 0;
	private Character nextLineChar = '\n';

	public ReadHeadImp(File file) throws FileNotFoundException {
		InputStrategy reader = InputStrategy.input(file);
		StringBuilder stringBuilder = new StringBuilder();
		List<String> strList = reader.getAllStrings();
		for (String str : strList) {
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
		return (text.charAt(currentCharPosition) == ' ' || text.charAt(currentCharPosition) == '	' || text.charAt(currentCharPosition) == '\n');
	}

	@Override
	public boolean isNextLineChar(Character c) {
		return c==nextLineChar;
	}

}
