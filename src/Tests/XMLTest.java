package Tests;

import static org.junit.Assert.*;

import model.Game;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.XML;
import org.junit.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Joris on 14-1-2015.
 */

public class XMLTest{

    @Test
    public void testXML(){
        try {
            XML xml = new XML("toms_teams.xml");
            Game g = xml.parseGame();
            assertTrue(XML.writeGame(g, "test_write_xml.xml"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetChildValueAndAttribute() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder(); // Initialize builder

        Document document = builder.parse(new FileInputStream("src/XMLTest.xml"));
        document.normalize();

        Element transferNode = document.getDocumentElement();
        assertEquals(Integer.parseInt(XML.getAttribute(transferNode.getAttributes(), "id")), 0);

        Element transfer = (Element)transferNode;

        assertEquals(XML.getChildValue(transfer, "message"), "Hoi");
    }

}