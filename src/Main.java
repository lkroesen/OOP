import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.XML;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SAXException, IOException, Exception {
		System.out.print("Hello World!");
		System.out.print("/nGIT working");
		System.out.println("hoi");
		
		XML xml = new XML("default.xml");
		
		System.out.println(xml.parseGame());
	}

}
