package Tests;

import static org.junit.Assert.*;

import AI.PlayDay;
import AI.PlayRound;
import AI.Schedule;
import model.Game;
import model.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.XML;
import org.junit.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joris on 14-1-2015.
 */

public class XMLTest{

    @Test
    public void testParseXML(){
        try {
            XML xml = new XML("src\\toms_more_teams.xml");
            Game g = xml.parseGame();
            assertNotNull(g);
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
    public void testWriteXML(){
        try {
            XML xml = new XML("src\\toms_more_teams.xml");
            Game g = new Game(0, "test_game", 0, 0, 0, 0, null);
            assertTrue(xml.writeGame(g, "test_write_xml.xml"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseSchedule() throws ParserConfigurationException, IOException, SAXException {
        XML xml = new XML("src\\XML_Schedule_test.xml");

        DocumentBuilder builder = xml.getBuilder();

        Document document = builder.parse(new FileInputStream("src\\XML_Schedule_test.xml"));
        document.normalize();

        Element root = document.getDocumentElement();

        Object schedule = xml.parseSchedule(root);

        assertNotNull(schedule instanceof Schedule);
    }

    @Test
    public void testWriteSchedule() throws ParserConfigurationException, TransformerException {
        XML xml = new XML("src\\XML_Schedule_test.xml");

        DocumentBuilder builder = xml.getBuilder();

        Document doc = builder.newDocument();
        Element rootElement = doc.createElement("root");
        doc.appendChild(rootElement);

        Schedule schedule = new Schedule();
        PlayDay friday = new PlayDay();
        PlayDay saturday = new PlayDay();
        PlayDay sunday = new PlayDay();
        PlayRound playRound = new PlayRound(friday, saturday, sunday, 1);

        schedule.add(playRound);

        assertNotNull(rootElement.appendChild(xml.writeSchedule(schedule, doc)));

    }

    @Test
    public void testGetAttribute() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder(); // Initialize builder

        Document document = builder.parse(new FileInputStream("src/XMLTest.xml"));
        document.normalize();

        Element transferNode = document.getDocumentElement();
        assertEquals(Integer.parseInt(XML.getAttribute(transferNode.getAttributes(), "id")), 0);
    }

    @Test
    public void testGetChildValue() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder(); // Initialize builder

        Document document = builder.parse(new FileInputStream("src/XMLTest.xml"));
        document.normalize();

        Element transferNode = document.getDocumentElement();

        Element transfer = (Element)transferNode;

        assertEquals(XML.getChildValue(transfer, "message"), "Hoi");
    }

    @Test
    public void testConstructor(){
        try {
            XML xml = new XML("src/XMLTest.xml");
            assertEquals("src/XMLTest.xml", xml.getFilename());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}