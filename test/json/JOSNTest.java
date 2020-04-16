package json;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import grammar.production.ProductionFactory;

class JOSNTest {

	@Test
	void test() throws FileNotFoundException {
		ProductionFactory.getProductionsFormJSONFile("text\\grammar.json");
		
	}

}
