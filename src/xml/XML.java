package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import AI.PlayDay;
import AI.PlayRound;
import AI.Schedule;

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
	private DocumentBuilderFactory factory;
	private String filename;

	/**
	 * Initializing Documentbuilder for parsing.
	 * @param filename File to be parsed
	 * @throws ParserConfigurationException
	 */
	public XML(String filename) throws ParserConfigurationException {
		this.filename = filename;
		
		factory = DocumentBuilderFactory.newInstance(); // Open factory
		factory.setIgnoringElementContentWhitespace(true);
		builder = factory.newDocumentBuilder(); // Initialize builder
		
	}

	public DocumentBuilder getBuilder() {
		return builder;
	}

	public String getFilename() {
		return filename;
	}

	/**
	 * Parses the Game element in the XML-file and all child nodes and puts them into a Game object.
	 * @return Game to be used by application
	 * @throws SAXException
	 * @throws IOException
	 * @throws Exception
	 */
	public Game parseGame() throws SAXException, IOException, Exception {		
		//Document document = builder.parse(ClassLoader.getSystemResourceAsStream(filename));
		Document document = builder.parse(new FileInputStream(filename));
		document.normalize();
		
		Game game = null;
		
	    Element gameNode = document.getDocumentElement();

		if (gameNode != null) {

			// Check for game settings data
			int id = Integer.parseInt(getAttribute(gameNode.getAttributes(), "id"));
			String name = getAttribute(gameNode.getAttributes(), "name");
			int currentDay = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentday"));
			int currentTeam = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentteam"));
			int currentLeague = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentleague"));
			int currentPlayRound = Integer.parseInt(getAttribute(gameNode.getAttributes(), "currentplayround"));

			// Create instance of Game with appropriate settings
			game = new Game(id, name, currentDay, currentTeam, currentLeague, currentPlayRound, null);

			game.setSchedule(parseSchedule(gameNode));
			
			// Get transfer data			
			NodeList transferData = gameNode.getElementsByTagName("transfer");
			for (int i = 0; i < transferData.getLength(); i++) {
				//Loop through transfers
				Node transferNode = transferData.item(i);
				if (transferNode.getNodeType() == Node.ELEMENT_NODE) {
					Element transfer = (Element)transferNode;

					// Get Transfer attributes
					int tid = Integer.parseInt(getAttribute(transfer.getAttributes(), "id"));
	
					int from = Integer.parseInt(getChildValue(transfer, "from"));
					int to = Integer.parseInt(getChildValue(transfer, "to"));
					int player = Integer.parseInt(getChildValue(transfer, "player"));
					int price = Integer.parseInt(getChildValue(transfer, "price"));
					int day = Integer.parseInt(getChildValue(transfer, "day"));

					// Add new Transfer to game with appropriate attributes
					game.addTransfer(new Transfer(tid, from, to, player, price, day));
				}
			}
			
			// Get leagues
			NodeList lData = document.getElementsByTagName("league");
			if (lData != null && lData.getLength() > 0) { // Check for game settings data
				for (int i = 0; i < lData.getLength(); i++) {
					// Loop through leagues

					Node league = lData.item(i);

					// Get attributes of league
					int lid = Integer.parseInt(getAttribute(league.getAttributes(), "id"));
					String lname = getAttribute(league.getAttributes(), "name");
					String country = getAttribute(league.getAttributes(), "country");

					// Create instance of league
					League leagueObject = new League(lid, lname, country);

					for (int a = 0; a < league.getChildNodes().getLength(); a++) {
						// Loop through teams

						if (league.getChildNodes().item(a).getNodeType() != Node.ELEMENT_NODE)
							continue;
						
						Element teamNode = (Element)league.getChildNodes().item(a);

						// Get attributes of team
						int tid = Integer.parseInt(getAttribute(teamNode.getAttributes(), "id"));
						String tname = getAttribute(teamNode.getAttributes(), "name");
						long budget = Long.parseLong(getAttribute(teamNode.getAttributes(), "budget"));
						String stadium = getAttribute(teamNode.getAttributes(), "stadium");

						// create instance of Team.
						Team team = new Team(tid, tname, budget, stadium);

						NodeList playerData = teamNode.getElementsByTagName("player");
						for (int b = 0; b < playerData.getLength(); b++) {
							// Loop through players
							Element player = (Element)playerData.item(b);

							// Get player attributes
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

							// Create instance of player and add him to the current team
							team.addPlayer(new Player(pid, pname, surname, number, player_type, player_position, rating_offensive, rating_def, stamina, price));
						}

						// Add current team to current league
						leagueObject.addTeam(team);
					}

					// Add current league to current game
					game.addLeague(leagueObject);
					
				}
			}
		} else {
			throw new Exception("XML: No game data element found.");
		}
				
		return game;
	}
	
	public Schedule parseSchedule(Element root){

		// Get Schedule
		NodeList scheduleData = root.getElementsByTagName("schedule");

		Schedule mSchedule = new Schedule();

		for (int i = 0; i < scheduleData.getLength(); i++){
			Node scheduleNode = scheduleData.item(i);
			if(scheduleNode.getNodeType() == Node.ELEMENT_NODE){
				Element schedule = (Element) scheduleNode;
				// Get PlayRound data
				NodeList playRoundData = schedule.getElementsByTagName("playround");


				//Loop trough PlayRounds
				for (int y = 0; y < playRoundData.getLength(); y++){
					Node playRoundNode = playRoundData.item(y);
					if (playRoundNode.getNodeType() == Node.ELEMENT_NODE) {
						Element playRound = (Element) playRoundNode;
						int roundnumber = Integer.parseInt(getAttribute(playRound.getAttributes(), "roundnumber"));

						PlayRound mPlayRound = new PlayRound(roundnumber);
						//Get PlayRound attribute

						NodeList fridayData = playRound.getElementsByTagName("friday");
						PlayDay pFriday = new PlayDay();

						for(int a = 0; a < fridayData.getLength(); a++){
							Node fridayNode = fridayData.item(a);
							if(fridayNode.getNodeType() == Node.ELEMENT_NODE){
								Element friday = (Element)fridayNode;

								// Get match data
								NodeList matchData = friday.getElementsByTagName("match");
								for (int b = 0; b < matchData.getLength(); b++){
									// Looping through matches
									Node matchNode = matchData.item(b);
									if (matchNode.getNodeType() == Node.ELEMENT_NODE) {
										Element match = (Element)matchNode;

										// Getting match attributes
										int mid = Integer.parseInt(getAttribute(match.getAttributes(), "id"));
										int day = Integer.parseInt(getAttribute(match.getAttributes(), "day"));

										// Create match instance with appropriate settings
										Match matchObject = new Match(mid,day);
										// Get home team data
										NodeList homeData = match.getElementsByTagName("team_home");
										for (int c = 0; c < homeData.getLength(); c++) {
											Node homeNode = homeData.item(c);
											if(homeNode.getNodeType() == Node.ELEMENT_NODE){
												Element home = (Element)homeNode;

												// Get home team id
												int team = Integer.parseInt(getAttribute(home.getAttributes(), "id"));
												matchObject.setTeam_home(team);

												NodeList homeEvents = home.getElementsByTagName("event");
												for(int d = 0; d < homeEvents.getLength(); d++){
													// Loop through events
													Node eventNode = homeEvents.item(d);
													if(eventNode.getNodeType() == Node.ELEMENT_NODE){
														// Create instance of Event
														Element event = (Element)eventNode;

														// Get Event attributes
														int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));

														int type = Integer.parseInt(getChildValue(event, "type"));
														int minute = Integer.parseInt(getChildValue(event, "minute"));
														int outfor = Integer.parseInt(getChildValue(event, "outfor"));

														// Add Event to match object
														matchObject.addEventHome(new Event(player, type, minute, outfor));
													}
												}
											}
										}

										// Get away team data
										NodeList awayData = match.getElementsByTagName("team_away");
										for (int e = 0; e < awayData.getLength(); e++) {
											Node awayNode = awayData.item(e);
											if(awayNode.getNodeType() == Node.ELEMENT_NODE){
												Element away = (Element)awayNode;

												// Get away team id
												int team = 0;

												NodeList awayEvents = away.getElementsByTagName("event");
												for(int f = 0; f < awayEvents.getLength(); f++){
													// Loop through events
													Node eventNode = awayEvents.item(b);
													if(eventNode.getNodeType() == Node.ELEMENT_NODE){
														// Create instance of Event
														Element event = (Element)eventNode;

														// Get Event attributes
														int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));

														int type = Integer.parseInt(getChildValue(event, "type"));
														int minute = Integer.parseInt(getChildValue(event, "minute"));
														int outfor = Integer.parseInt(getChildValue(event, "outfor"));

														// Add Event to match object
														matchObject.addEventAway(new Event(player, type, minute, outfor));
													}
												}
											}
										}
										pFriday.addMatch(matchObject);
									}
								}
							}

						}

						mPlayRound.setFriday(pFriday);

						NodeList saturdayData = playRound.getElementsByTagName("saturday");
						PlayDay pSaturday = new PlayDay();

						for(int a = 0; a < saturdayData.getLength(); a++){
							Node saturdayNode = saturdayData.item(a);
							if(saturdayNode.getNodeType() == Node.ELEMENT_NODE){
								Element saturday = (Element)saturdayNode;

								// Get match data
								NodeList matchData = saturday.getElementsByTagName("match");
								for (int b = 0; b < matchData.getLength(); b++){
									// Looping through matches
									Node matchNode = matchData.item(b);
									if (matchNode.getNodeType() == Node.ELEMENT_NODE) {
										Element match = (Element)matchNode;

										// Getting match attributes
										int mid = Integer.parseInt(getAttribute(match.getAttributes(), "id"));
										int day = Integer.parseInt(getAttribute(match.getAttributes(), "day"));

										// Create match instance with appropriate settings
										Match matchObject = new Match(mid,day);

										// Get home team data
										NodeList homeData = match.getElementsByTagName("team_home");
										for (int c = 0; c < homeData.getLength(); c++) {
											Node homeNode = homeData.item(c);
											if(homeNode.getNodeType() == Node.ELEMENT_NODE){
												Element home = (Element)homeNode;

												// Get home team id
												int team = 0;

												NodeList homeEvents = home.getElementsByTagName("event");
												for(int d = 0; d < homeEvents.getLength(); d++){
													// Loop through events
													Node eventNode = homeEvents.item(d);
													if(eventNode.getNodeType() == Node.ELEMENT_NODE){
														// Create instance of Event
														Element event = (Element)eventNode;

														// Get Event attributes
														int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));

														int type = Integer.parseInt(getChildValue(event, "type"));
														int minute = Integer.parseInt(getChildValue(event, "minute"));
														int outfor = Integer.parseInt(getChildValue(event, "outfor"));

														// Add Event to match object
														matchObject.addEventHome(new Event(player, type, minute, outfor));
													}
												}
											}
										}

										// Get away team data
										NodeList awayData = match.getElementsByTagName("team_away");
										for (int e = 0; e < awayData.getLength(); e++) {
											Node awayNode = awayData.item(e);
											if(awayNode.getNodeType() == Node.ELEMENT_NODE){
												Element away = (Element)awayNode;

												// Get away team id
												int team = 0;

												NodeList awayEvents = away.getElementsByTagName("event");
												for(int f = 0; f < awayEvents.getLength(); f++){
													// Loop through events
													Node eventNode = awayEvents.item(b);
													if(eventNode.getNodeType() == Node.ELEMENT_NODE){
														// Create instance of Event
														Element event = (Element)eventNode;

														// Get Event attributes
														int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));

														int type = Integer.parseInt(getChildValue(event, "type"));
														int minute = Integer.parseInt(getChildValue(event, "minute"));
														int outfor = Integer.parseInt(getChildValue(event, "outfor"));

														// Add Event to match object
														matchObject.addEventAway(new Event(player, type, minute, outfor));
													}
												}
											}
										}

										pSaturday.addMatch(matchObject);

									}
								}
							}

						}
						mPlayRound.setSaturday(pSaturday);

						NodeList sundayData = playRound.getElementsByTagName("sunday");
						PlayDay pSunday = new PlayDay();

						for(int a = 0; a < sundayData.getLength(); a++){
							Node sundayNode = saturdayData.item(a);
							if(sundayNode.getNodeType() == Node.ELEMENT_NODE){
								Element sunday = (Element)sundayNode;

								// Get match data
								NodeList matchData = sunday.getElementsByTagName("match");
								for (int b = 0; b < matchData.getLength(); b++){
									// Looping through matches
									Node matchNode = matchData.item(b);
									if (matchNode.getNodeType() == Node.ELEMENT_NODE) {
										Element match = (Element)matchNode;

										// Getting match attributes
										int mid = Integer.parseInt(getAttribute(match.getAttributes(), "id"));
										int day = Integer.parseInt(getAttribute(match.getAttributes(), "day"));

										// Create match instance with appropriate settings
										Match matchObject = new Match(mid,day);

										// Get home team data
										NodeList homeData = match.getElementsByTagName("team_home");
										for (int c = 0; c < homeData.getLength(); c++) {
											Node homeNode = homeData.item(c);
											if(homeNode.getNodeType() == Node.ELEMENT_NODE){
												Element home = (Element)homeNode;

												// Get home team id
												int team = 0;

												NodeList homeEvents = home.getElementsByTagName("event");
												for(int d = 0; d < homeEvents.getLength(); d++){
													// Loop through events
													Node eventNode = homeEvents.item(d);
													if(eventNode.getNodeType() == Node.ELEMENT_NODE){
														// Create instance of Event
														Element event = (Element)eventNode;

														// Get Event attributes
														int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));

														int type = Integer.parseInt(getChildValue(event, "type"));
														int minute = Integer.parseInt(getChildValue(event, "minute"));
														int outfor = Integer.parseInt(getChildValue(event, "outfor"));

														// Add Event to match object
														matchObject.addEventHome(new Event(player, type, minute, outfor));
													}
												}
											}
										}

										// Get away team data
										NodeList awayData = match.getElementsByTagName("team_away");
										for (int e = 0; e < awayData.getLength(); e++) {
											Node awayNode = awayData.item(e);
											if(awayNode.getNodeType() == Node.ELEMENT_NODE){
												Element away = (Element)awayNode;

												// Get away team id
												int team = 0;

												NodeList awayEvents = away.getElementsByTagName("event");
												for(int f = 0; f < awayEvents.getLength(); f++){
													// Loop through events
													Node eventNode = awayEvents.item(b);
													if(eventNode.getNodeType() == Node.ELEMENT_NODE){
														// Create instance of Event
														Element event = (Element)eventNode;

														// Get Event attributes
														int player = Integer.parseInt(getAttribute(event.getAttributes(), "player"));

														int type = Integer.parseInt(getChildValue(event, "type"));
														int minute = Integer.parseInt(getChildValue(event, "minute"));
														int outfor = Integer.parseInt(getChildValue(event, "outfor"));

														// Add Event to match object
														matchObject.addEventAway(new Event(player, type, minute, outfor));
													}
												}
											}
										}

										pSunday.addMatch(matchObject);

									}
								}
							}

						}
						mPlayRound.setSunday(pSunday);
						mSchedule.add(mPlayRound);
					}
				}

			}
		}

		return mSchedule;

	}

	public static String getChildValue(Node p, String name) {
		for (int i = 0; i < p.getChildNodes().getLength(); i++) {
			if (p.getChildNodes().item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			if (p.getChildNodes().item(i).getNodeName().equals(name)) {
				return p.getChildNodes().item(i).getTextContent().trim();
			}
		}
		return null;
	}
	
	public static String getAttribute(NamedNodeMap attrs, String name) {
		return attrs.getNamedItem(name).getNodeValue();
	}

	public Element writeSchedule(Schedule schedule, Document doc){

		Element eSchedule = doc.createElement("schedule");

		for (PlayRound pr : schedule.getS()) {
			Element ePlayRound = doc.createElement("playround");
			ePlayRound.setAttribute("roundnumber", pr.getRoundNumber() + "");

			PlayDay friday = pr.getFriday();
			Element eFriday = doc.createElement("friday");
			for (Match m : friday.getMatches()) {
				Element eMatch = doc.createElement("match");

				// Set Match attributes
				eMatch.setAttribute("id", m.getId() + "");
				eMatch.setAttribute("day", m.getDay() + "");

				// Initialize HomeEvents
				Element eHomeEvents = doc.createElement("team_home");

				// Set team_home attributes
				eHomeEvents.setAttribute("id", m.getTeam_home() + "");

				// Loop through HomeEvents
				for (Event e : m.getEvents_home()) {
					Element eEvent = doc.createElement("event");

					// Set attributes for event
					eEvent.setAttribute("player", e.getPlayer() + "");

					Element eType = doc.createElement("type");
					eType.setTextContent(e.getPlayer() + "");
					eEvent.appendChild(eType);

					Element eMinute = doc.createElement("minute");
					eMinute.setTextContent(e.getMinute() + "");
					eEvent.appendChild(eMinute);

					Element eOutfor = doc.createElement("outfor");
					eOutfor.setTextContent(e.getOutfor() + "");
					eEvent.appendChild(eOutfor);

					eHomeEvents.appendChild(eEvent);
				}

				eMatch.appendChild(eHomeEvents);


				// Initialize AwayEvents
				Element eAwayEvents = doc.createElement("team_away");

				// Set team_home attributes
				eAwayEvents.setAttribute("id", m.getTeam_away() + "");

				// Loop through HomeEvents
				for (Event e : m.getEvents_away()) {
					Element eEvent = doc.createElement("event");

					// Set attributes for event
					eEvent.setAttribute("player", e.getPlayer() + "");

					Element eType = doc.createElement("type");
					eType.setTextContent(e.getPlayer() + "");
					eEvent.appendChild(eType);

					Element eMinute = doc.createElement("minute");
					eMinute.setTextContent(e.getMinute() + "");
					eEvent.appendChild(eMinute);

					Element eOutfor = doc.createElement("outfor");
					eOutfor.setTextContent(e.getOutfor() + "");
					eEvent.appendChild(eOutfor);

					eAwayEvents.appendChild(eEvent);
				}

				eMatch.appendChild(eAwayEvents);

				eFriday.appendChild(eMatch);
			}
			ePlayRound.appendChild(eFriday);

			PlayDay saturday = pr.getSaturday();
			Element eSaturday = doc.createElement("saturday");
			for (Match m : saturday.getMatches()) {
				Element eMatch = doc.createElement("match");

				// Set Match attributes
				eMatch.setAttribute("id", m.getId() + "");
				eMatch.setAttribute("day", m.getDay() + "");

				// Initialize HomeEvents
				Element eHomeEvents = doc.createElement("team_home");

				// Set team_home attributes
				eHomeEvents.setAttribute("id", m.getTeam_home() + "");

				// Loop through HomeEvents
				for (Event e : m.getEvents_home()) {
					Element eEvent = doc.createElement("event");

					// Set attributes for event
					eEvent.setAttribute("player", e.getPlayer() + "");

					Element eType = doc.createElement("type");
					eType.setTextContent(e.getPlayer() + "");
					eEvent.appendChild(eType);

					Element eMinute = doc.createElement("minute");
					eMinute.setTextContent(e.getMinute() + "");
					eEvent.appendChild(eMinute);

					Element eOutfor = doc.createElement("outfor");
					eOutfor.setTextContent(e.getOutfor() + "");
					eEvent.appendChild(eOutfor);

					eHomeEvents.appendChild(eEvent);
				}

				eMatch.appendChild(eHomeEvents);


				// Initialize AwayEvents
				Element eAwayEvents = doc.createElement("team_away");

				// Set team_home attributes
				eAwayEvents.setAttribute("id", m.getTeam_away() + "");

				// Loop through HomeEvents
				for (Event e : m.getEvents_away()) {
					Element eEvent = doc.createElement("event");

					// Set attributes for event
					eEvent.setAttribute("player", e.getPlayer() + "");

					Element eType = doc.createElement("type");
					eType.setTextContent(e.getPlayer() + "");
					eEvent.appendChild(eType);

					Element eMinute = doc.createElement("minute");
					eMinute.setTextContent(e.getMinute() + "");
					eEvent.appendChild(eMinute);

					Element eOutfor = doc.createElement("outfor");
					eOutfor.setTextContent(e.getOutfor() + "");
					eEvent.appendChild(eOutfor);

					eAwayEvents.appendChild(eEvent);
				}

				eMatch.appendChild(eAwayEvents);

				eSaturday.appendChild(eMatch);
			}
			ePlayRound.appendChild(eSaturday);

			PlayDay sunday = pr.getSunday();
			Element eSunday = doc.createElement("sunday");
			for (Match m : sunday.getMatches()) {
				Element eMatch = doc.createElement("match");

				// Set Match attributes
				eMatch.setAttribute("id", m.getId() + "");
				eMatch.setAttribute("day", m.getDay() + "");

				// Initialize HomeEvents
				Element eHomeEvents = doc.createElement("team_home");

				// Set team_home attributes
				eHomeEvents.setAttribute("id", m.getTeam_home() + "");

				// Loop through HomeEvents
				for (Event e : m.getEvents_home()) {
					Element eEvent = doc.createElement("event");

					// Set attributes for event
					eEvent.setAttribute("player", e.getPlayer() + "");

					Element eType = doc.createElement("type");
					eType.setTextContent(e.getPlayer() + "");
					eEvent.appendChild(eType);

					Element eMinute = doc.createElement("minute");
					eMinute.setTextContent(e.getMinute() + "");
					eEvent.appendChild(eMinute);

					Element eOutfor = doc.createElement("outfor");
					eOutfor.setTextContent(e.getOutfor() + "");
					eEvent.appendChild(eOutfor);

					eHomeEvents.appendChild(eEvent);
				}

				eMatch.appendChild(eHomeEvents);


				// Initialize AwayEvents
				Element eAwayEvents = doc.createElement("team_away");

				// Set team_away attributes
				eAwayEvents.setAttribute("id", m.getTeam_away() + "");

				// Loop through AwayEvents
				for (Event e : m.getEvents_away()) {
					Element eEvent = doc.createElement("event");

					// Set attributes for event
					eEvent.setAttribute("player", e.getPlayer() + "");

					Element eType = doc.createElement("type");
					eType.setTextContent(e.getPlayer() + "");
					eEvent.appendChild(eType);

					Element eMinute = doc.createElement("minute");
					eMinute.setTextContent(e.getMinute() + "");
					eEvent.appendChild(eMinute);

					Element eOutfor = doc.createElement("outfor");
					eOutfor.setTextContent(e.getOutfor() + "");
					eEvent.appendChild(eOutfor);

					eAwayEvents.appendChild(eEvent);
				}

				eMatch.appendChild(eAwayEvents);

				eSunday.appendChild(eMatch);
			}
			ePlayRound.appendChild(eSunday);

			eSchedule.appendChild(ePlayRound);
		}

		return eSchedule;
	}

	/**
	 * Writes Game object to prescribed XML file.
	 * @param g Game to be writed to XML file.
	 * @param filename Name of file to be writed to.
	 * @return Whether writing succeeded
	 */
	public boolean writeGame(Game g, String filename) {
        try {

			// Create root element game
			Document doc = builder.newDocument();
			Element rootElement = doc.createElement("game");
			doc.appendChild(rootElement);

			// Set game settings
			rootElement.setAttribute("id", g.getId()+"");
			rootElement.setAttribute("name", g.getName()+"");
			rootElement.setAttribute("currentday", g.getCurrentDay()+"");
			rootElement.setAttribute("currentteam", g.getCurrentTeam()+"");
			rootElement.setAttribute("currentleague", g.getCurrentLeague()+"");
			rootElement.setAttribute("currentplayround", g.getCurrentPlayRound()+"");

			if(g.getSchedule() != null) {

				Schedule schedule = g.getSchedule();

				rootElement.appendChild(writeSchedule(schedule, doc));

			}

			// Loop through leagues
			for (League l : g.getLeagues()) {

				Element eLeague = doc.createElement("league");

				// Set league attributes
				eLeague.setAttribute("id", l.getId()+"");
				eLeague.setAttribute("name", l.getName());
				eLeague.setAttribute("country", l.getCountry());

				// Loop through teams
				for (Team t : l.getTeams()) {
					// Set team attributes
					Element eTeam = doc.createElement("team");
					eTeam.setAttribute("id", t.getId()+"");
					eTeam.setAttribute("name", t.getName());
				   	eTeam.setAttribute("stadium", t.getStadium());
					eTeam.setAttribute("budget", t.getBudget()+"");

					// Loop trough players
					for (Player p : t.getPlayers()) {
						Element ePlayer = doc.createElement("player");

						// Set Player attributes
						ePlayer.setAttribute("id", p.getId()+"");

						Element eName = doc.createElement("name");
						eName.setTextContent(p.getFirstname());
						ePlayer.appendChild(eName);

						Element eLastName = doc.createElement("surname");
						eLastName.setTextContent(p.getSurname());
						ePlayer.appendChild(eLastName);

						Element eNumber = doc.createElement("number");
						eNumber.setTextContent(p.getJerseyNumber() + "");
						ePlayer.appendChild(eNumber);

						Element eType = doc.createElement("type");
						eType.setTextContent(p.getType() + "");
						ePlayer.appendChild(eType);

						Element offensive = doc.createElement("offensiveRating");
						offensive.setTextContent(p.getOffensiveScore() + "");
						ePlayer.appendChild(offensive);

						Element defensive = doc.createElement("defensiveRating");
						defensive.setTextContent(p.getDefensiveScore() + "");
						ePlayer.appendChild(defensive);

						Element stamina = doc.createElement("stamina");
						stamina.setTextContent(p.getStaminaScore() + "");
						ePlayer.appendChild(stamina);

						Element price = doc.createElement("price");
						price.setTextContent(p.getPrice() + "");
						ePlayer.appendChild(price);
						
						Element position = doc.createElement("position");
						position.setTextContent(p.getPosition() + "");
						ePlayer.appendChild(position);

						// Add player to team
						eTeam.appendChild(ePlayer);
					}
					// Add team to league
					eLeague.appendChild(eTeam);
				}
				// Add league to game
				rootElement.appendChild(eLeague);

			}

			// Loop through transfers
			for (Transfer t : g.getTransfers()) {
				Element eTransfer = doc.createElement("transfer");

				// Set Transfer attributes
				eTransfer.setAttribute("id", t.getId() + "");

				Element eFrom = doc.createElement("from");
				eFrom.setTextContent(t.getFrom() + "");
				eTransfer.appendChild(eFrom);

				Element eTo = doc.createElement("to");
				eTo.setTextContent(t.getTo() + "");
				eTransfer.appendChild(eTo);

				Element ePrice = doc.createElement("price");
				ePrice.setTextContent(t.getPrice() + "");
				eTransfer.appendChild(ePrice);

				Element ePlayer = doc.createElement("player");
				ePlayer.setTextContent(t.getPlayer() + "");
				eTransfer.appendChild(ePlayer);

				Element eDay = doc.createElement("day");
				eDay.setTextContent(t.getDay() + "");
				eTransfer.appendChild(eDay);

				rootElement.appendChild(eTransfer);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));

			transformer.transform(source, result);

			System.out.println("File saved!");

			return true;
          }  catch (TransformerException tfe) {
                tfe.printStackTrace();
          }
        
		return false;
	}
	
	
}