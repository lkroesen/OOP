package xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Game;
import model.League;
import model.Player;
import model.PlayerStatus;
import model.PlayerType;
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
		
		// Get game data
		//NodeList gameNodeData = document.getElementsByTagName("game");
	    Element gameNode = document.getDocumentElement();

		if (gameNode != null) { // Check for game settings data
			
			int id = Integer.parseInt(getAttribute(gameNode.getAttributes(), "id"));
			String name = getAttribute(gameNode.getAttributes(), "name");
			int currentDay = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentday"));
			int currentTeam = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentteam"));
			
			game = new Game(id, name, currentDay, currentTeam);
			
			// Get transfer data			
			NodeList transferData = gameNode.getElementsByTagName("transfer");
			for (int i = 0; i < transferData.getLength(); i++) {
				Node transferNode = transferData.item(i);
				if (transferNode.getNodeType() == Node.ELEMENT_NODE) {
					Element transfer = (Element)transferNode;
	
					int from = Integer.parseInt(getChildValue(transfer, "from"));
					int to = Integer.parseInt(getChildValue(transfer, "to"));
					int player = Integer.parseInt(getChildValue(transfer, "player"));
					int price = Integer.parseInt(getChildValue(transfer, "price"));
					int day = Integer.parseInt(getChildValue(transfer, "day"));
					
					game.addTransfer(new Transfer(from, to, player, price, day));
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
							byte number = Byte.parseByte(getChildValue(player, "jerseyNumber"));
							
							int player_type = Integer.parseInt(getChildValue(player, "type"));
							short rating_offensive = Short.parseShort(getChildValue(player, "offensiveRating"));
							short rating_def = Short.parseShort(getChildValue(player, "defensiveRating"));
							short rating_stamina = Short.parseShort(getChildValue(player, "staminaRating"));
							
							int player_status = Integer.parseInt(getChildValue(player, "status"));
							int team_id = Integer.parseInt(getChildValue(player, "teamId"));
							int price = Integer.parseInt(getChildValue(player, "price"));
							
							PlayerType playerType = PlayerType.values()[player_type]; // Convert to enum counterparts
							PlayerStatus playerStatus = PlayerStatus.values()[player_status];
							
							team.addPlayer(new Player(pid, pname, surname, number, playerType, rating_offensive, rating_def, rating_stamina, team_id, playerStatus, price));
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
	
	
}