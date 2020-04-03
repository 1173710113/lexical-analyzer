package readhead.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import readhead.ReadHead;
import readhead.ReadHeadImp;

public class ReadHeadFactory {

	public static ReadHead createReaderFromFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		return new ReadHeadImp(file);
	};
	
	public static ReadHead createReadHeadFromStringList(List<String> stringList) {
		return new ReadHeadImp(stringList);
	}
}
