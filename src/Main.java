import java.io.IOException;

import org.xml.sax.SAXException;

import xml.XML;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SAXException, IOException, Exception {
		
		XML xml = new XML("default.xml");
		
		System.out.println(xml.parseGame());
	}

}
