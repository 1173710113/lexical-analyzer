package util.readhead.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import util.filereader.InputStrategy;
import util.readhead.ReadHead;
import util.readhead.ReadHeadImp;

public class ReadHeadFactory {

	public static ReadHead createReaderFromFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		InputStrategy input = InputStrategy.input(file);
		return createReadHeadFromStringList(input.getAllStrings());
	};
	
	public static ReadHead createReadHeadFromStringList(List<String> stringList) {
		return new ReadHeadImp(stringList);
	}
}
