package json;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import grammar.production.ProductionFactory;
import util.filereader.InputStrategy;

class JOSNTest {

	@Test
	void test() throws FileNotFoundException {
		ProductionFactory.getProductionsFormJSONFile("text\\grammar.json");
		
	}

}
