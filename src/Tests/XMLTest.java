package Tests;

import static org.junit.Assert.*;

import model.Game;
import org.xml.sax.SAXException;
import xml.XML;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
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
            XML.writeGame(g, "test_write_xml");
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

}