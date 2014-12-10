package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Event;
import model.Game;
import model.League;
import model.Match;
import model.Player;
import model.Team;
import model.Transfer;

public class XML {
	
	private DocumentBuilder builder;
	private String filename;
	
	public XML(String filename) throws ParserConfigurationException {
		this.filename = filename;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
		factory.setIgnoringElementContentWhitespace(true);
		builder = factory.newDocumentBuilder(); // Initialize builder
		
	}
	
	public Game parseGame() throws SAXException, IOException, Exception {		
		Document document = builder.parse(ClassLoader.getSystemResourceAsStream(filename));
		document.normalize();
		
		Game game = null;
		
	    Element gameNode = document.getDocumentElement();

		if (gameNode != null) { // Check for game settings data
			
			int id = Integer.parseInt(getAttribute(gameNode.getAttributes(), "id"));
			String name = getAttribute(gameNode.getAttributes(), "name");
			int currentDay = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentday"));
			int currentTeam = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentteam"));
			
			game = new Game(id, name, currentDay, currentTeam);
			
			// Get match data
			
			NodeList matchData = gameNode.getElementsByTagName("match");
			for (int i = 0; i < matchData.getLength(); i++){
				Node matchNode = matchData.item(i);
				if (matchNode.getNodeType() == Node.ELEMENT_NODE) {
					Element match = (Element)matchNode;

					int mid = Integer.parseInt(getAttribute(match.getAttributes(), "id"));
					int day = Integer.parseInt(getAttribute(match.getAttributes(), "day"));
					
					Match matchObject = new Match(mid,day);
					
					// Get home team data
					NodeList homeData = match.getElementsByTagName("team_home");
					for (int a = 0; a < homeData.getLength(); a++) {
						Node homeNode = homeData.item(a);
						if(homeNode.getNodeType() == Node.ELEMENT_NODE){
							Element home = (Element)homeNode;
							
							int team = Integer.parseInt(getAttribute(home.getAttributes(), "id"));
							
							NodeList homeEvents = home.getElementsByTagName("event");
							for(int b = 0; b < homeEvents.getLength(); b++){
								Node eventNode = homeEvents.item(b);
								if(eventNode.getNodeType() == Node.ELEMENT_NODE){
									Element event = (Element)eventNode;
									
									int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));
									
									int type = Integer.parseInt(getChildValue(event, "type"));
									int minute = Integer.parseInt(getChildValue(event, "minute"));
									int outfor = Integer.parseInt(getChildValue(event, "outfor"));
									
									matchObject.addEventHome(new Event(player, type, minute, outfor));
								}
							}
						}
					}
					
					// Get away team data
					NodeList homeData = match.getElementsByTagName("team_away");
					for (int a = 0; a < awayData.getLength(); a++) {
						Node awayNode = awayData.item(a);
						if(awayNode.getNodeType() == Node.ELEMENT_NODE){
							Element away = (Element)awayNode;
							
							int team = Integer.parseInt(getAttribute(away.getAttributes(), "id"));
							
							NodeList awayEvents = away.getElementsByTagName("event");
							for(int b = 0; b < awayEvents.getLength(); b++){
								Node eventNode = awayEvents.item(b);
								if(eventNode.getNodeType() == Node.ELEMENT_NODE){
									Element event = (Element)eventNode;
									
									int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));
									
									int type = Integer.parseInt(getChildValue(event, "type"));
									int minute = Integer.parseInt(getChildValue(event, "minute"));
									int outfor = Integer.parseInt(getChildValue(event, "outfor"));
									
									matchObject.addEventAway(new Event(player, type, minute, outfor));
								}
							}
						}
					}
					
					game.addMatch(matchObject);
					
				}
			}
			
			// Get transfer data			
			NodeList transferData = gameNode.getElementsByTagName("transfer");
			for (int i = 0; i < transferData.getLength(); i++) {
				Node transferNode = transferData.item(i);
				if (transferNode.getNodeType() == Node.ELEMENT_NODE) {
					Element transfer = (Element)transferNode;
					
					int tid = Integer.parseInt(getAttribute(transfer.getAttributes(), "id"));
	
					int from = Integer.parseInt(getChildValue(transfer, "from"));
					int to = Integer.parseInt(getChildValue(transfer, "to"));
					int player = Integer.parseInt(getChildValue(transfer, "player"));
					int price = Integer.parseInt(getChildValue(transfer, "price"));
					int day = Integer.parseInt(getChildValue(transfer, "day"));
					
					game.addTransfer(new Transfer(tid, from, to, player, price, day));
				}
			}
			
			// Get leagues
			NodeList lData = document.getElementsByTagName("league");
			if (lData != null && lData.getLength() > 0) { // Check for game settings data
				for (int i = 0; i < lData.getLength(); i++) {
					Node league = lData.item(i);
					
					int lid = Integer.parseInt(getAttribute(league.getAttributes(), "id"));
					String lname = getAttribute(league.getAttributes(), "name");
					String country = getAttribute(league.getAttributes(), "country");
					
					League leagueObject = new League(lid, lname, country);
					
					// Get teams
					for (int a = 0; a < league.getChildNodes().getLength(); a++) {
						if (league.getChildNodes().item(a).getNodeType() != Node.ELEMENT_NODE)
							continue;
						
						Element teamNode = (Element)league.getChildNodes().item(a);
						
						int tid = Integer.parseInt(getAttribute(teamNode.getAttributes(), "id"));
						String tname = getAttribute(teamNode.getAttributes(), "name");
						
						Team team = new Team(tid, tname);
						
						// Get players
						NodeList playerData = teamNode.getElementsByTagName("player");
						for (int b = 0; b < playerData.getLength(); b++) {
							// Single player
							Element player = (Element)playerData.item(b);
							
							int pid = Integer.parseInt(getAttribute(player.getAttributes(), "id"));
							String pname = getChildValue(player, "name");
							String surname = getChildValue(player, "surname");
							byte number = Byte.parseByte(getChildValue(player, "number"));

							int player_type = Integer.parseInt(getChildValue(player, "type"));
							int player_position = Integer.parseInt(getChildValue(player, "position"));
							byte rating_offensive = Byte.parseByte(getChildValue(player, "offensiveRating"));
							byte rating_def = Byte.parseByte(getChildValue(player, "defensiveRating"));
							byte stamina = Byte.parseByte(getChildValue(player, "stamina"));
							
							int price = Integer.parseInt(getChildValue(player, "price"));
							
							team.addPlayer(new Player(pid, pname, surname, number, player_type, player_position, rating_offensive, rating_def, stamina, price));
						}
						
						leagueObject.addTeam(team);
					}
					
					game.addLeague(leagueObject);
					
				}
			}
		} else {
			throw new Exception("XML: No game data element found.");
		}
				
		return game;
	}
	
	
	public String getChildValue(Node p, String name) {
		for (int i = 0; i < p.getChildNodes().getLength(); i++) {
			if (p.getChildNodes().item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (p.getChildNodes().item(i).getNodeName().equals(name)) {
				return p.getChildNodes().item(i).getTextContent().trim();
			}
		}
		return null;
	}
	
	public String getAttribute(NamedNodeMap attrs, String name) {
		return attrs.getNamedItem(name).getNodeValue();
	}
	
	public boolean writeGame(Game g, String filename) {
        try {
                 
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("game");
                doc.appendChild(rootElement);
               
                rootElement.setAttribute("id", g.getId()+"");
                rootElement.setAttribute("name", g.getName()+"");
                rootElement.setAttribute("currentday", g.getCurrentDay()+"");
                rootElement.setAttribute("currentteam", g.getUserTeam().getId()+"");
               
                for (League l : g.getLeagues()) {
                        Element eLeague = doc.createElement("league");
                        eLeague.setAttribute("id", l.getId()+"");
                        eLeague.setAttribute("name", l.getName());
                        eLeague.setAttribute("country", l.getCountry());
                        rootElement.appendChild(eLeague);
                       
                        for (Team t : l.getTeams()) {
                                Element eTeam = doc.createElement("team");
                                eTeam.setAttribute("id", t.getId()+"");
                                eTeam.setAttribute("name", t.getName());
                                eTeam.setAttribute("formation", t.getFormation());
                               
                                for (Player p : t.getPlayers()) {
                                        Element ePlayer = doc.createElement("player");
                                        ePlayer.setAttribute("id", p.getId()+"");
                                       
                                        Element eName = doc.createElement("name");
                                        Element eLastName = doc.createElement("surname");
                                        Element eNumber = doc.createElement("number");
                                        Element eType = doc.createElement("type");
                                        Element offensive = doc.createElement("offensiveRating");
                                        Element defensive = doc.createElement("defensiveRating");
                                        Element stamina = doc.createElement("stamina");
                                        Element price = doc.createElement("price");
                                       
                                        eTeam.appendChild(ePlayer);
                                }
                               
                                eLeague.appendChild(eTeam);
                        }
                }
                
        		// write the content into xml file
        		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        		Transformer transformer = transformerFactory.newTransformer();
        		DOMSource source = new DOMSource(doc);
        		StreamResult result = new StreamResult(new File(filename));
 
                transformer.transform(source, result);
 
                System.out.println("File saved!");
 
                return true;
          } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
          } catch (TransformerException tfe) {
                tfe.printStackTrace();
          }
        
		return false;
	}
	
	
}