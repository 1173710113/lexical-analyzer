package util.readhead.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import util.filereader.InputStrategy;
import util.readhead.ReadHead;
import util.readhead.CharacterReadHead;

public class ReadHeadFactory {

	public static ReadHead<Character> createCharacterReadHeadFromFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		InputStrategy input = InputStrategy.input(file);
		return createCharacterReadHeadFromStringList(input.getAllStrings());
	};
	
	public static ReadHead<Character> createCharacterReadHeadFromStringList(List<String> stringList) {
		return new CharacterReadHead(stringList);
	}
}
