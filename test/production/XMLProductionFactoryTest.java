package production;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import grammar.production.Production;
import grammar.production.ProductionFactory;

public class XMLProductionFactoryTest {

	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException {
		Set<Production> productions = ProductionFactory.getProductionsFromXMLFile("text\\SDT.xml");
		System.out.println(productions);
	}

}
