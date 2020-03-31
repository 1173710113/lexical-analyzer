package readhead;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class ReadHeadTest {

	private String filePath  = "G:\\eclipse-workspace\\lexical analyzer\\text\\text3.txt";
	
	@Test
	void test() throws FileNotFoundException {
		ReadHead readHead = new ReadHeadImp(new File(filePath));
		String str = "/*4646*/";
		for(char c : str.toCharArray()) {
			assertEquals(c, readHead.nextChar());
		}
		assertFalse(readHead.hasNextChar());
	}
	

}
