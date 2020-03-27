package readhead.factory;

import readhead.ReadHead;

public interface ReadHeadFactory {

	public ReadHead createReaderFromFile(String filePath);
}
