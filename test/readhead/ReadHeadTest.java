package readhead;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import util.readhead.ReadHead;
import util.readhead.factory.ReadHeadFactory;

class ReadHeadTest {

	private String filePath  = "G:\\eclipse-workspace\\lexical analyzer\\text\\text3.txt";
	
	@Test
	void test() throws FileNotFoundException {
		
		ReadHead<Character> readHead = ReadHeadFactory.createCharacterReadHeadFromFile(filePath);
		String str = "/*4646*/";
		for(char c : str.toCharArray()) {
			assertEquals(c, readHead.next());
		}
		assertFalse(readHead.hasNext());
	}
	

}
