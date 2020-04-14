package reader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import util.filereader.InputStrategy;

class BufferStrategyTest {

	private String states = "<states>:0,1,2,3";
	private InputStrategy reader;
	
	@Test
	void test() throws FileNotFoundException {
		File file = new File("G:\\eclipse-workspace\\lexical analyzer\\text\\test1.txt");
		reader = InputStrategy.input(file);
		assertEquals(states, reader.nextLine());
	}

}
