package gui;

//unused imports are unused
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.omg.CORBA.PRIVATE_MEMBER;

import AI.Bet;
import AI.Betting;
import AI.PlayMatch;
import AI.PlayRound;
import AI.Ranking;
import AI.Schedule;
import AI.Scheduler;
import AI.Team_Training_User;
import AI.Training;
import AI.TransferAlgorithm;
import xml.XML;
import model.Game;
import model.League;
import model.Match;
import model.Team;
import model.Player;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaViewBuilder;
import javafx.stage.Stage;


public class javafx extends Application{
	
    //toptext of every scene and button for starting screen
	Label lbtext;
	Button newgame, loadgame, mutesong, mutevideo, backng, backlg, backteam, select, central, team, finance, nextday, next, leagueaction, teamaction, playeraction, train, lighttrain
	, heavytrain, rest, position, showteam, market, sell, buy, bet, selectbudget, upcoming, showrank, savegame, save1, save2, save3, save4, amount1, amount2, amount3, amount4;
	
	Button Back = new Button ("Back");
	int teamchoiceint, playerchoiceint, currentplayround = 0, currentday = 0, positionint = 0,leaguechoice = 0, swapplayer, matchid;
	boolean traintoday = false;
	boolean pastnewgame = false;
	boolean swapfirst = false;
	boolean showboolean = false;
	boolean betmaking = false;
	boolean betmade = false;
	boolean sellplayer = false;
	boolean homematch = false;
	boolean awaymatch = false;
	boolean endofgame = false;
	boolean centralboolean = true;
	boolean financeboolean = false;
	boolean teamboolean = false;
	static String savelocation = "";
	Bet bets;
	Ranking rank;
	ArrayList<Team> teams = new ArrayList<Team>();
	ArrayList<Player> players = new ArrayList<Player>();
	Schedule scheme = new Schedule();
	ArrayList<Button> transferbuttons = new ArrayList<Button>();
	
	
	//launches the gui
	public static void main(String[] args){
		System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").toLowerCase().startsWith("windows")){
			File f = new File(System.getenv("APPDATA") + "/UltimateFootbalManager2014");
			try{
				System.out.println("making dir" + f.mkdirs());
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("win " + f.getAbsolutePath());
			savelocation = f.getAbsolutePath();
		}else if(System.getProperty("os.name").toLowerCase().startsWith("mac")){
			
			File f = new File(System.getProperty("user.home") + "/Library/Application Support/UltimateFootbalManager2014/");
			f.mkdir();
			System.out.println("mac " + f.getAbsolutePath());

			savelocation = f.getAbsolutePath();
		}else{
			File f = new File("saves");
			f.mkdir();
			System.out.println("else " + f.getAbsolutePath());
			savelocation = f.getAbsolutePath();
		}
		File dest = new File(savelocation+"/default.xml");
		copy_save(javafx.class.getResourceAsStream("/toms_more_teams.xml"), savelocation+"/default.xml");
		launch(args);
	}
	
	private static void copy_save(InputStream filein, String fileout) {;
			File f = new File(fileout);
			if(f.exists())
				return;
			FileOutputStream fos = null;
			try {
			    fos = new FileOutputStream(fileout);
			    byte[] buf = new byte[2048];
			    int r = filein.read(buf);
			    while(r != -1) {
			        fos.write(buf, 0, r);
			        r = filein.read(buf);
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			    if(fos != null) {
			        try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			}
	}

	@Override
	public void start(final Stage stage) throws Exception {
		
		stage.setResizable(false);
		final XML xml = new XML(savelocation + "/default.xml");
		final Game game = xml.parseGame();
		final TransferAlgorithm algorithm = new TransferAlgorithm(game);
		final ArrayList<League> leagues = game.getLeagues();
		final ArrayList<Button> leaguebuttons = new ArrayList<Button>();
		final ArrayList<Button> playerbuttons = new ArrayList<Button>();
		for(int i = 0; i < game.getLeagues().size(); i++){
			leaguebuttons.add(new Button(game.getLeagues().get(i).getName()));
		}
		
		//song name in file form
		//File file = new File("src/fmsong.mp3");

		
		//plays the song endless
		final String mediaLocation = javafx.class.getResource("/resources/fmsong.mp3").toExternalForm();
		Media song = new Media(mediaLocation);
		final MediaPlayer audio = new MediaPlayer(song);
		audio.setCycleCount(audio.INDEFINITE);
		
		//first boxes for scene layout
		StackPane test = new StackPane();
		final VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		HBox start = new HBox(10);
		start.setAlignment(Pos.CENTER);
		
		//effects on buttons
		Reflection reflection = new Reflection();
		reflection.setFraction(0.8);
		reflection.setTopOffset(-20);
		
		Media media = new Media(javafx.class.getResource("/resources/Footballvideo.mp4").toExternalForm());
	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setAutoPlay(true);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    
	    Media mediawin = new Media(javafx.class.getResource("/resources/win.mp4").toExternalForm());
	    final MediaPlayer mediaPlayerwin = new MediaPlayer(mediawin);
	    
	    Media mediatie = new Media(javafx.class.getResource("/resources/tie.mp4").toExternalForm());
	    final MediaPlayer mediaPlayertie = new MediaPlayer(mediatie);
	    
	    Media medialose = new Media(javafx.class.getResource("/resources/lose.mp4").toExternalForm());
	    final MediaPlayer mediaPlayerlose = new MediaPlayer(medialose);
		//all video stuff


		final MediaView mediaView = new MediaView(mediaPlayer);
		final MediaView mediaViewwin = new MediaView(mediaPlayerwin);
		final MediaView mediaViewtie = new MediaView(mediaPlayertie);
	    final MediaView mediaViewlose = new MediaView(mediaPlayerlose);
	       
	       mediaView.setFitWidth(1500);
	       mediaViewwin.setFitWidth(400);
	       mediaViewlose.setFitWidth(400);
	       mediaViewtie.setFitWidth(400);
		
		//set the text for first buttons and label and giving effects
		lbtext = new Label("Footballmanager");
		newgame = new Button("New game");
		leagueaction = new Button("Back");
		teamaction = new Button();
		playeraction = new Button();
		selectbudget = new Button();
		loadgame = new Button("Load game");
		mutesong = new Button("Mute/unmute");
		mutevideo = new Button("Mute/unmute");
		backng = new Button("Back");
		backlg = new Button("Back");
		backteam = new Button("Back");
		select = new Button("Choose this team");

		central = new Button("Central");
		central.getStyleClass().add("tab");
		finance = new Button("Finance");
		finance.getStyleClass().add("tab");
		team = new Button("Team");
		team.getStyleClass().add("tab");
		nextday = new Button("Next day");

		next = new Button("Continue");
		train = new Button("Train");
		lighttrain = new Button ("Light training");
		heavytrain = new Button ("Heavy training");
		rest = new Button ("Rest");
		position = new Button ("Change positions");
		showteam = new Button ("Show team");
		bet = new Button ("Bet");
		market = new Button ("Market");
		sell = new Button("Sell");
		buy = new Button("Buy");
		upcoming = new Button ("This weeks matches");
		savegame = new Button("Save game");
		showrank = new Button("Show current rank");
		save1 = new Button("Save 1");
		save2 = new Button("Save 2");
		save3 = new Button("Save 3");
		save4 = new Button("Save 4");
		amount1 = new Button("100000");
		amount2 = new Button("250000");
		amount3 = new Button("500000");
		amount4 = new Button("1000000");

		//Ugly as fuck
		//lbtext.setEffect(reflection);
		
		lbtext.getStyleClass().add("Headline");
		
		//makes a scene with the title setting it in the stage
		final Scene scene = new Scene(test,1500,750);
		stage.setTitle("Footballmanager");
		stage.setScene(scene);
		scene.getStylesheets().add("resources/mystyle.css");

		//shows current scene
		stage.show();
		
		//actions by pressing new game button
		newgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				audio.play();
				mediaPlayer.setVolume(0);
				//changes the text
				lbtext.setText("Leagues");
				
				//makes new boxes for layout new game screen
				HBox container = new HBox(10);
				VBox leagues1 = new VBox(10);
				VBox leagues2 = new VBox(10);
				VBox leagues3 = new VBox(10);
				VBox leagues4 = new VBox(10);
				VBox backlgs = new VBox(10);
				leagues1.getChildren().addAll(lbtext);
				for(int i = 0; i < game.getLeagues().size(); i++){
					if(i < 10){
						leagues1.getChildren().add(leaguebuttons.get(i));
					}
					if(9 < i && i < 20){
						leagues2.getChildren().add(leaguebuttons.get(i));
					}
					if(19 < i && i < 30){
						leagues3.getChildren().add(leaguebuttons.get(i));
					}
					if(29 < i && i < 40){
						leagues4.getChildren().add(leaguebuttons.get(i));
					}
				}
				//adds the buttons and the label and sets the scene in the stage
				backlgs.getChildren().addAll(mutesong,Back);
				container.getChildren().addAll(leagues1,leagues2,leagues3,leagues4);
				container.setTranslateY(100);
				HBox ngtext = new HBox();
				ngtext.getChildren().addAll(lbtext,container,backlgs);
				Scene ng = new Scene(ngtext,1500,750);
				ng.getStylesheets().add("resources/mystyle.css");
				stage.setScene(ng);
				container.setAlignment(Pos.BOTTOM_CENTER);
				backlgs.setAlignment(Pos.BOTTOM_RIGHT);
				
					}
					
				});
		loadgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				audio.play();
				mediaPlayer.setVolume(0);
				lbtext.setText("Select save game");
				
				
				//making boxes and buttons for load game screen
				VBox lgtext = new VBox();
				VBox backpos = new VBox(10);
				backpos.setAlignment(Pos.BOTTOM_RIGHT);
				lbtext.setAlignment(Pos.TOP_LEFT);
				backpos.getChildren().addAll(mutesong,backlg);
				lgtext.getChildren().addAll(lbtext,save1,save2,save3,save4,backpos);
				lgtext.getStylesheets().add("resources/mystyle.css");
				Scene lg = new Scene(lgtext,1500,750);
				stage.setScene(lg);
				
				}
			
		});
		save1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				try {
					final XML xml1 = new XML(savelocation + "/save1");
					final Game game1 = xml1.parseGame();
					leaguechoice = (game1.getCurrentLeague());
					teams = leagues.get(leaguechoice).getTeams();
					teamchoiceint = (game1.getCurrentTeam());
					players = teams.get(teamchoiceint).getPlayers();
					scheme = game1.getSchedule();
					for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
						playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
						final int a = i;
						playerbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent arg0){
								if(sellplayer == false){
									playerchoiceint = a;
									playeraction.fire();
								}
								else{
									algorithm.Sell(teams.get(teamchoiceint).getPlayers().get(a));
									sellplayer = false;
									select.fire();
								}
							}
						});
					}
					game1.setSchedule(scheme);
					currentday = (game1.getCurrentDay());
					currentplayround = (game1.getCurrentPlayRound());
					System.out.print(game1.getSchedule());
					rank = rank.generate(scheme);
				} catch (Exception e) {
					e.printStackTrace();
				}
				select.fire();
			}
		});
		save2.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				try {
					final XML xml2 = new XML("toms_more_teams.xml");
					final Game game2 = xml2.parseGame();
					leaguechoice = (game2.getCurrentLeague());
					teamchoiceint = (game2.getCurrentTeam());
					currentday = (game2.getCurrentDay());
					currentplayround = (game2.getCurrentPlayRound());
				} catch (Exception e) {
					e.printStackTrace();
				}
				select.fire();
			}
		});
		save3.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				try {
					final XML xml3 = new XML("toms_more_teams.xml");
					final Game game3 = xml3.parseGame();
					leaguechoice = (game3.getCurrentLeague());
					teamchoiceint = (game3.getCurrentTeam());
					currentday = (game3.getCurrentDay());
					currentplayround = (game3.getCurrentPlayRound());
				} catch (Exception e) {
					e.printStackTrace();
				}
				select.fire();
			}
		});
		save4.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				try {
					final XML xml4 = new XML("toms_more_teams.xml");
					final Game game4 = xml4.parseGame();
					leaguechoice = (game4.getCurrentLeague());
					teamchoiceint = (game4.getCurrentTeam());
					currentday = (game4.getCurrentDay());
					currentplayround = (game4.getCurrentPlayRound());
				} catch (Exception e) {
					e.printStackTrace();
				}
				select.fire();
			}
		});
		backlg.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				Back.fire();
				
			}
		});
		backng.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				newgame.fire();
				
			}
		});
	teamaction.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(teamchoiceint).getName().toString());
				players = teams.get(teamchoiceint).getPlayers();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				VBox teambox3 = new VBox(10);
				VBox teambox4 = new VBox(10);
				VBox teambox5 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.getChildren().addAll(select,mutesong,leagueaction);
				teambox1.getChildren().addAll(lbtext);
				for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
					final int a = i;
					if(showboolean == false && sellplayer == false){
						playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
					}
					playerbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
						@Override
						public void handle(ActionEvent arg0){
							if(sellplayer == false){
								playerchoiceint = a;
								playeraction.fire();
							}
							else{
								algorithm.Sell(teams.get(teamchoiceint).getPlayers().get(a));
								sellplayer = false;
								select.fire();
							}
						}
					});
					if(0 <= i && i < 10){
						teambox1.getChildren().addAll(playerbuttons.get(i));
					}
					if(10 <= i && i < 20){
						teambox2.getChildren().addAll(playerbuttons.get(i));
					}
					if(20 <= i && i < 30){
						teambox3.getChildren().addAll(playerbuttons.get(i));
					}
					if(30 <= i && i < 40){
						teambox4.getChildren().addAll(playerbuttons.get(i));
					}
					if(40 <= i && i < 50){
						teambox5.getChildren().addAll(playerbuttons.get(i));
					}
				}
				teambox2.translateYProperty().set(70);
				teambox3.translateYProperty().set(70);
				teambox4.translateYProperty().set(70);
				teambox5.translateYProperty().set(70);
				if(sellplayer == true || showboolean == true){
					playerdisplay.getChildren().addAll(teambox1,teambox2,teambox3,teambox4,teambox5,next);
				}
				else{
					playerdisplay.getChildren().addAll(teambox1,teambox2,teambox3,teambox4,teambox5,vbBack);
				}
				Scene teamscreen = new Scene(playerdisplay,1500,750);
				teamscreen.getStylesheets().add("resources/mystyle.css");
				stage.setScene(teamscreen);
				
				
			}
		});	
	playeraction.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			if(pastnewgame == false || showboolean == true){
			Label pos = new Label(null);
			lbtext.setText("stats");
			Label fn = new Label("Firstname: " + players.get(playerchoiceint).getFirstname().toString());
			Label sn = new Label("Surname: " + players.get(playerchoiceint).getSurname().toString());
			Label j = new Label("Jerseynumber: " + players.get(playerchoiceint).getJerseyNumber());
			Label os = new Label("Offensive score: " + players.get(playerchoiceint).getOffensiveScore());
			Label ds = new Label("Defensive score: " + players.get(playerchoiceint).getDefensiveScore());
			Label ss = new Label("Stamina score: " + players.get(playerchoiceint).getStaminaScore());
			if(players.get(playerchoiceint).getType() == 0){
				pos.setText("Position: goalkeeper");
			}
			if(players.get(playerchoiceint).getType() == 1){
				pos.setText("Position: defender");
			}
			if(players.get(playerchoiceint).getType() == 2){
				pos.setText("Position: midfield");
			}
			if(players.get(playerchoiceint).getType() == 3){
				pos.setText("Position: attack");
			}
			Label pr = new Label("Price: " + players.get(playerchoiceint).getPrice());
			VBox playerbox = new VBox(10);
			VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
			HBox screenbox = new HBox();
			playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
			teamback.getChildren().addAll(mutesong,backteam);
			if(showboolean == false){
				screenbox.getChildren().addAll(playerbox,teamback);
			}
			if(showboolean == true){
				screenbox.getChildren().addAll(playerbox,next);
			}
			Scene playerscreen = new Scene(screenbox,1500,750);
			playerscreen.getStylesheets().add("resources/mystyle.css");
			stage.setScene(playerscreen);
			}
			if(swapfirst == false && showboolean == false && pastnewgame == true){
				lbtext.setText("swap out for");
				positionint = players.get(playerchoiceint).getPosition();
				swapplayer = playerchoiceint;
				Label def = new Label("Defense");
				Label def1 = new Label("Defense");
				Label def2 = new Label("Defense");
				Label atk = new Label("Attack");
				Label atk1 = new Label("Attack");
				Label atk2 = new Label("Attack");
				Label mid = new Label("Midfield");
				Label mid1 = new Label("Midfield");
				Label mid2 = new Label("Midfield");
				Label keep = new Label("Goalkeeper");
				Label keep1 = new Label("Goalkeeper");
				Label keep2 = new Label("Goalkeeper");
				Label bench = new Label("Bench");
				Label res = new Label("Reserve");
				HBox totalswap = new HBox();
				VBox swapboxkeep = new VBox(10);
				VBox swapboxdef = new VBox(10);
				VBox swapboxmid = new VBox(10);
				VBox swapboxatk = new VBox(10);
				VBox swapboxbenchkeep = new VBox(10);
				VBox swapboxbenchdef = new VBox(10);
				VBox swapboxbenchmid = new VBox(10);
				VBox swapboxbenchatk = new VBox(10);
				VBox swapboxreskeep = new VBox(10);
				VBox swapboxresdef = new VBox(10);
				VBox swapboxresmid = new VBox(10);
				VBox swapboxresatk = new VBox(10);
				swapboxkeep.getChildren().addAll(lbtext,keep);
				swapboxdef.getChildren().addAll(def);
				swapboxmid.getChildren().addAll(mid);
				swapboxatk.getChildren().addAll(atk);
				swapboxbenchkeep.getChildren().addAll(bench,keep1);
				swapboxbenchdef.getChildren().addAll(def1);
				swapboxbenchmid.getChildren().addAll(mid1);
				swapboxbenchatk.getChildren().addAll(atk1);
				swapboxreskeep.getChildren().addAll(res,keep2);
				swapboxresdef.getChildren().addAll(def2);
				swapboxresmid.getChildren().addAll(mid2);
				swapboxresatk.getChildren().addAll(atk2);
				for(int i = 0; i < players.size();i++){
					if(!(players.get(i).equals(players.get(playerchoiceint)))){
							if(0 == players.get(i).getPosition()){
								swapboxkeep.getChildren().addAll(playerbuttons.get(i));
							}
							if(0 < players.get(i).getPosition() && players.get(i).getPosition() < 11){
								if(players.get(i).getType() == 1 || players.get(i).getType() == 0){
									swapboxdef.getChildren().addAll(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 2){
									swapboxmid.getChildren().addAll(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 3){
									swapboxatk.getChildren().addAll(playerbuttons.get(i));
								}
							}
							if(10 < players.get(i).getPosition() && players.get(i).getPosition() < 18){
								if(players.get(i).getType() == 0){
									swapboxbenchkeep.getChildren().add(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 1){
									swapboxbenchdef.getChildren().addAll(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 2){
									swapboxbenchmid.getChildren().addAll(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 3){
									swapboxbenchatk.getChildren().addAll(playerbuttons.get(i));
								}
							}
							if(-1 == players.get(i).getPosition()){
								if(players.get(i).getType() == 0){
									swapboxreskeep.getChildren().add(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 1){
									swapboxresdef.getChildren().addAll(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 2){
									swapboxresmid.getChildren().addAll(playerbuttons.get(i));
								}
								if(players.get(i).getType() == 3){
									swapboxresatk.getChildren().addAll(playerbuttons.get(i));
								}
							}
						}
					}
				swapboxkeep.getChildren().add(swapboxdef);
				swapboxmid.getChildren().add(swapboxatk);
				swapboxmid.setTranslateX(-50);
				swapboxmid.setTranslateY(70);
				swapboxbenchkeep.getChildren().addAll(swapboxbenchdef,swapboxbenchmid,swapboxbenchatk);
				swapboxbenchkeep.setTranslateX(-50);
				swapboxreskeep.getChildren().addAll(swapboxresdef);
				swapboxresmid.getChildren().addAll(swapboxresatk);
				swapboxreskeep.setTranslateX(-50);
				totalswap.getChildren().addAll(swapboxkeep,swapboxmid,swapboxbenchkeep,swapboxreskeep,swapboxresmid);
				totalswap.getStylesheets().add("resources/mystyle.css");
				Scene swapscreen = new Scene(totalswap,1500,750);
				swapscreen.getStylesheets().add("resources/mystyle.css");
				stage.setScene(swapscreen);
			}
			swapfirst = true;
			if(swapfirst == true && showboolean == false && pastnewgame == true && swapplayer != playerchoiceint){
				players.get(swapplayer).setPosition((players.get(playerchoiceint).getPosition()));
				players.get(playerchoiceint).setPosition(positionint);
				position.fire();
			}
			showboolean = false;
		}
	});
	backteam.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			newgame.fire();
		}
	});
	select.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			Game.setCurrentTeam(teamchoiceint);
			transferbuttons.clear();
			pastnewgame = true;
			HBox tabs = new HBox(10);
			Label currentdaylabel = new Label("Monday");
			if(currentday == 1){
				currentdaylabel.setText("Tuesday");
			}
			if(currentday == 2){
				currentdaylabel.setText("Wednesday");
			}
			if(currentday == 3){
				currentdaylabel.setText("Thursday");
			}
			if(currentday == 4){
				currentdaylabel.setText("Friday");
			}
			if(currentday == 5){
				currentdaylabel.setText("Saturday");
			}
			if(currentday == 6){
				currentdaylabel.setText("Sunday");
			}
			VBox teamchoicebox = new VBox(10);
			HBox imageadd = new HBox(10);
			tabs.getChildren().addAll(central,finance,team);
			teamchoicebox.getStylesheets().add("resources/mystyle.css");
			teamchoicebox.getStyleClass().add("main_menu");
			imageadd.getStylesheets().add("resources/mystyle.css");
			Scene teamchoicescreen = new Scene(imageadd,1500,750);
			lbtext.setText(teams.get(teamchoiceint).getName());
			if(traintoday == false){
				if(betmade == false && currentday < 5){
					if(centralboolean == true){
						teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,nextday,upcoming, showrank,savegame, mutesong);
					}
					if(financeboolean == true){
						teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,bet,market, mutesong);
					}
					if(teamboolean == true){
						teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,train,position,showteam, mutesong);
					}
				}
				else{
					if(centralboolean == true){
						teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,nextday,upcoming, showrank,savegame, mutesong);
					}
					if(financeboolean == true){
						teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,bet,market, mutesong);
					}
					if(teamboolean == true){
						teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,train,position,showteam, mutesong);
					}
				}
			}
			else if(betmade == false && currentday < 5){
				if(centralboolean == true){
					teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,nextday,upcoming, showrank,savegame, mutesong);
				}
				if(financeboolean == true){
					teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,bet,market, mutesong);
				}
				if(teamboolean == true){
					teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,train,position,showteam, mutesong);
				}
			}
			else{
				if(centralboolean == true){
					teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,nextday,upcoming, showrank,savegame, mutesong);
				}
				if(financeboolean == true){
					teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,bet,market, mutesong);
				}
				if(teamboolean == true){
					teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,tabs,train,position,showteam, mutesong);
				}
			}
			imageadd.getChildren().addAll(teamchoicebox);
			stage.setScene(teamchoicescreen);	
		}
	});								
	central.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			centralboolean = true;
			financeboolean = false;
			teamboolean = false;
			select.fire();
		}
	});
	finance.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			centralboolean = false;
			financeboolean = true;
			teamboolean = false;
			select.fire();
		}
	});
	team.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			centralboolean = false;
			financeboolean = false;
			teamboolean = true;
			select.fire();
		}
	});	
	showrank.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			lbtext.setText("Ranking");
			Label place = new Label("current rank");
			VBox rankbox1 = new VBox(10);
			VBox rankbox2 = new VBox(10);
			VBox rankbox3 = new VBox(10);
			VBox rankback = new VBox(10);
			ArrayList<Label> ranks = new ArrayList<Label>();
			rankbox1.getChildren().addAll(lbtext,place);
			for(int i = 0; i <teams.size(); i++){
				ranks.add(new Label(i + 1 + " " + rank.getRanking()[i].getName().toString() + " " + rank.getScoreOfTeam()[i]));
				if(i < 10){
					rankbox1.getChildren().addAll(ranks.get(i));
				}
				if(9 < i && i < 20){
					rankbox2.getChildren().addAll(ranks.get(i));
				}
				if(19 < i && i < 30){
					rankbox3.getChildren().addAll(ranks.get(i));
				}
			}
			if(endofgame == true){
				lbtext.setText("Season is over");
			}
			rankback.setAlignment(Pos.BOTTOM_RIGHT);
			rankbox2.setTranslateY(80);
			rankbox3.setTranslateY(80);
			rankback.getChildren().add(next);
			HBox rankboxtotal = new HBox(10);
			if(endofgame == true){
				for(int i = 0; i < ranks.size(); i++ ){
					if(rank.getRanking()[i].getId() == teams.get(teamchoiceint).getId()){
						place.setText("you finished: " + (i + 1));
					}
				}
				rankboxtotal.getChildren().addAll(rankbox1,rankbox2,rankbox3);
			}
			else{
				rankboxtotal.getChildren().addAll(rankbox1,rankbox2,rankbox3,rankback);
			}
			rankboxtotal.getStylesheets().add("resources/mystyle.css");
			Scene rankscreen = new Scene(rankboxtotal,1500,750);
			stage.setScene(rankscreen);
		}
	});
	bet.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			betmaking = true;
			upcoming.fire();
		}
	});
	selectbudget.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			VBox betamountbox = new VBox(10);
			lbtext.setText("select betting amount");
			betamountbox.getChildren().addAll(lbtext,amount1,amount2,amount3,amount4);
			betamountbox.getStylesheets().add("/resources/mystyle.css");
			Scene betamountscreen = new Scene(betamountbox,1500,750);
			stage.setScene(betamountscreen);
		}
	});
	amount1.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() - 100000);
			select.fire();
		}
	});
	amount2.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() - 250000);
			select.fire();
		}
	});
	amount3.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() - 500000);
			select.fire();
		}
	});
	amount4.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() - 1000000);
			select.fire();
		}
	});
	market.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			Label budget = new Label("Budget:" + teams.get(teamchoiceint).getBudget());
			VBox markettotal = new VBox(10);
			VBox marketbox = new VBox(10);
			VBox marketback = new VBox();
			marketback.setAlignment(Pos.BOTTOM_RIGHT);
			marketbox.getChildren().addAll(lbtext,budget,sell,buy);
			marketback.getChildren().addAll(next);
			markettotal.getChildren().addAll(marketbox,marketback);
			lbtext.setText("Market");
			markettotal.getStylesheets().add("resources/mystyle.css");
			Scene marketscreen = new Scene(markettotal,1500,750);
			stage.setScene(marketscreen);
		}
	});
	sell.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			if(11 < teams.get(teamchoiceint).getPlayers().size()){
				sellplayer = true;
				teamaction.fire();
			}
			else{
				select.fire();
			}
		}
	});
	buy.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0){
			if(!(teams.get(teamchoiceint).getPlayers().size() == 50)){
				lbtext.setText("players for sale");
				final ArrayList<Player> playerstransfer = algorithm.getTransferringplayers();
				VBox transferbox1 = new VBox(10);
				VBox transferbox2 = new VBox(10);
				VBox transferbox3 = new VBox(10);
				VBox transferbox4 = new VBox(10);
				VBox transferbox5 = new VBox(10);
				VBox transferback = new VBox(10);
				HBox transfertotal = new HBox();
				transferbox1.getChildren().add(lbtext);
				transferback.getChildren().add(next);
				transferbox2.setTranslateY(70);
				transferbox3.setTranslateY(70);
				transferbox4.setTranslateY(70);
				transferbox5.setTranslateY(70);
				transfertotal.getChildren().addAll(transferbox1,transferbox2,transferbox3,transferbox4,transferbox5,transferback);
				transfertotal.getStylesheets().add("resources/mystyle.css");
				if( playerstransfer != null){
					for(int i = 0; i < playerstransfer.size(); i++){
						transferbuttons.add(new Button(playerstransfer.get(i).getFirstname().toString() + " " + playerstransfer.get(i).getSurname().toString()));
						if(i < 10){
							transferbox1.getChildren().add(transferbuttons.get(i));
						}
						if(9 < i && i < 20){
							transferbox2.getChildren().add(transferbuttons.get(i));
						}
						if(19 < i && i < 30){
							transferbox3.getChildren().add(transferbuttons.get(i));
						}
						if(29 < i && i < 40){
							transferbox4.getChildren().add(transferbuttons.get(i));
						}
						if(39 < i && i < 50){
							transferbox5.getChildren().add(transferbuttons.get(i));
						}
					}
					for(int i = 0; i < transferbuttons.size(); i++){
						final int a = i;
						transferbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
					
							@Override
							public void handle(ActionEvent arg0){
								if((playerstransfer.get(a).getDefensiveScore()*playerstransfer.get(a).getOffensiveScore()*playerstransfer.get(a).getStaminaScore()) < teams.get(teamchoiceint).getBudget()){
									playerbuttons.add(new Button(playerstransfer.get(a).getFirstname() + " " + playerstransfer.get(a).getSurname()));
									algorithm.TransferPlayer(teams.get(teamchoiceint), playerstransfer.get(a));	
								}
								select.fire();
							}
						});
					}
				}
				Scene transferscreen = new Scene(transfertotal,1500,750);
				stage.setScene(transferscreen);
			}
			else{
				select.fire();
			}
		}
	});
	upcoming.setOnAction(new EventHandler<ActionEvent>(){
		

		@Override
		public void handle(ActionEvent arg0){
			if(betmaking == false){
				lbtext.setText("This weeks matches");
			}
			else{
				lbtext.setText("Betting on teams");
			}
			ArrayList<Button> betbuttons = new ArrayList<Button>();
			ArrayList<HBox> betboxesfri = new ArrayList<HBox>();
			ArrayList<HBox> betboxessat = new ArrayList<HBox>();
			ArrayList<HBox> betboxessun = new ArrayList<HBox>();
			for(int i = 0; i < scheme.getS().get(currentplayround).getFriday().getMatches().size()*2; i++){
				if(i % 2 == 0){
					betbuttons.add(new Button(scheme.getS().get(currentplayround).getFriday().getMatches().get(i/2).getTeam_home().getName().toString()));
				}
				else{
					betbuttons.add(new Button(scheme.getS().get(currentplayround).getFriday().getMatches().get((int) (i/2)).getTeam_away().getName().toString()));
					betboxesfri.add(new HBox(10));
					betboxesfri.get((int) (i/2)).getChildren().addAll(betbuttons.get(i-1),betbuttons.get(i));
				}
			}
			for(int i = 0; i < scheme.getS().get(currentplayround).getSaturday().getMatches().size()*2; i++){
				if(i % 2 == 0){
					betbuttons.add(new Button(scheme.getS().get(currentplayround).getSaturday().getMatches().get(i/2).getTeam_home().getName().toString()));
				}
				else{
					betbuttons.add(new Button(scheme.getS().get(currentplayround).getSaturday().getMatches().get((int) (i/2)).getTeam_away().getName().toString()));
					betboxessat.add(new HBox(10));
					betboxessat.get((int) (i/2)).getChildren().addAll(betbuttons.get(i-1 + betboxesfri.size()*2),betbuttons.get(i + betboxesfri.size()*2));
				}
			}
			for(int i = 0; i < scheme.getS().get(currentplayround).getSunday().getMatches().size()*2; i++){
				if(i % 2 == 0){
					betbuttons.add(new Button(scheme.getS().get(currentplayround).getSunday().getMatches().get(i/2).getTeam_home().getName().toString()));
				}
				else{
					betbuttons.add(new Button(scheme.getS().get(currentplayround).getSunday().getMatches().get((int) (i/2)).getTeam_away().getName().toString()));
					betboxessun.add(new HBox(10));
					betboxessun.get((int) (i/2)).getChildren().addAll(betbuttons.get(i-1 + betboxesfri.size()*2 + betboxessat.size()*2),betbuttons.get(i + betboxesfri.size()*2 + betboxessat.size()*2));
				}
			}
			Label standard0 = new Label("Home - Away");
			Label standard1 = new Label("Home - Away");
			Label standard2 = new Label("Home - Away");
			Label friday = new Label("Friday");
			Label saturday = new Label("Saturday");
			Label sunday = new Label("Sunday");
			HBox totalupcoming = new HBox();
			VBox matchfridayupcoming = new VBox (10);
			VBox matchsaturdayupcoming = new VBox (10);
			VBox matchsundayupcoming = new VBox (10);
			VBox upcomingback = new VBox();
			matchfridayupcoming.getChildren().addAll(lbtext,friday,standard0);
			matchsaturdayupcoming.getChildren().addAll(saturday,standard1);
			matchsundayupcoming.getChildren().addAll(sunday,standard2);
				for(int i = 0;i < scheme.getS().get(currentplayround).getFriday().getMatches().size();i++){
					if(betmaking == false){
						String match = (scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_away().getName());
						Label matchlabel = new Label(match);
						matchfridayupcoming.getChildren().addAll(matchlabel);
					}
					else{
						String match = (scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_away().getName());
						Label matchlabel = new Label(match);
						matchfridayupcoming.getChildren().addAll(matchlabel,betboxesfri.get(i));
					}
				}
				for(int i = 0;i < scheme.getS().get(currentplayround).getSaturday().getMatches().size();i++){
					if(betmaking == false){
						String match = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_away().getName());
						Label matchlabel = new Label(match);
						matchsaturdayupcoming.getChildren().addAll(matchlabel);
					}
					else {
						String match = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_away().getName());
						Label matchlabel = new Label(match);
						matchsaturdayupcoming.getChildren().addAll(matchlabel,betboxessat.get(i));
					}
				}
				for(int i = 0;i < scheme.getS().get(currentplayround).getSunday().getMatches().size();i++){
					if(betmaking == false){
						String match = (scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_away().getName());
						Label matchlabel = new Label(match);
						matchsundayupcoming.getChildren().addAll(matchlabel);
					}
					else{
						String match = (scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_away().getName());
						Label matchlabel = new Label(match);
						matchsundayupcoming.getChildren().addAll(matchlabel,betboxessun.get(i));
					}
				}
				matchsaturdayupcoming.setTranslateY(80);
				matchsundayupcoming.setTranslateY(80);
				upcomingback.getChildren().add(next);
				upcomingback.setAlignment(Pos.BOTTOM_RIGHT);
				totalupcoming.getChildren().addAll(matchfridayupcoming,matchsaturdayupcoming,matchsundayupcoming,upcomingback);
				totalupcoming.getStylesheets().add("resources/mystyle.css");
				Scene upcomingscreen = new Scene(totalupcoming,1500,750);
				stage.setScene(upcomingscreen);
				betmaking = false;
				for(int i = 0; i < betbuttons.size(); i++){
					final int a = i;
					if(i % 2 == 0){
						betbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
					
							@Override
							public void handle(ActionEvent arg0){
								bets = Betting.Bet_Maker(Betting.Before_Match(scheme,currentplayround), teams.get(teamchoiceint).getBudget(), teams, a/2, true);
								betmade = true;
								selectbudget.fire();
							}
						});
					}
					else{
						betbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
					
							@Override
							public void handle(ActionEvent arg0){
								bets = Betting.Bet_Maker(Betting.Before_Match(scheme,currentplayround), teams.get(teamchoiceint).getBudget(), teams, (int) (a/2), false);
								betmade = true;
								selectbudget.fire();
							}
						});
					}
				}
		}
	});
	showteam.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			showboolean = true;
			teamaction.fire();
		}
	});
	position.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			lbtext.setText("Change positions");
			swapfirst = false;
			Label def = new Label("Defense");
			Label def1 = new Label("Defense");
			Label def2 = new Label("Defense");
			Label atk = new Label("Attack");
			Label atk1 = new Label("Attack");
			Label atk2 = new Label("Attack");
			Label mid = new Label("Midfield");
			Label mid1 = new Label("Midfield");
			Label mid2 = new Label("Midfield");
			Label keep = new Label("Goalkeeper");
			Label keep1 = new Label("Goalkeeper");
			Label keep2 = new Label("Goalkeeper");
			Label bench = new Label("Bench");
			Label res = new Label("Reserve");
			HBox totalteam = new HBox();
			VBox teamback = new VBox();
			teamback.setAlignment(Pos.BOTTOM_RIGHT);
			VBox teamboxkeep = new VBox(10);
			VBox teamboxdef = new VBox(10);
			VBox teamboxmid = new VBox(10);
			VBox teamboxatk = new VBox(10);
			VBox teamboxbenchkeep = new VBox(10);
			VBox teamboxbenchdef = new VBox(10);
			VBox teamboxbenchmid = new VBox(10);
			VBox teamboxbenchatk = new VBox(10);
			VBox teamboxreskeep = new VBox(10);
			VBox teamboxresdef = new VBox(10);
			VBox teamboxresmid = new VBox(10);
			VBox teamboxresatk = new VBox(10);
			teamback.getChildren().add(next);
			teamboxkeep.getChildren().addAll(lbtext,keep);
			teamboxdef.getChildren().addAll(def);
			teamboxmid.getChildren().addAll(mid);
			teamboxatk.getChildren().addAll(atk);
			teamboxbenchkeep.getChildren().addAll(bench,keep1);
			teamboxbenchdef.getChildren().addAll(def1);
			teamboxbenchmid.getChildren().addAll(mid1);
			teamboxbenchatk.getChildren().addAll(atk1);
			teamboxreskeep.getChildren().addAll(res,keep2);
			teamboxresdef.getChildren().addAll(def2);
			teamboxresmid.getChildren().addAll(mid2);
			teamboxresatk.getChildren().addAll(atk2);
			for(int i = 0; i < players.size();i++){
				if(0 == players.get(i).getPosition()){
					teamboxkeep.getChildren().addAll(playerbuttons.get(i));
				}
				if(0 < players.get(i).getPosition() && players.get(i).getPosition() < 11){
					if(players.get(i).getType() == 1 || players.get(i).getType() == 0){
						teamboxdef.getChildren().addAll(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 2){
						teamboxmid.getChildren().addAll(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 3){
						teamboxatk.getChildren().addAll(playerbuttons.get(i));
					}
				}
				if(10 < players.get(i).getPosition() && players.get(i).getPosition() < 18){	
					if(players.get(i).getType() == 0){
						teamboxbenchkeep.getChildren().add(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 1){
						teamboxbenchdef.getChildren().addAll(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 2){
						teamboxbenchmid.getChildren().addAll(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 3){
						teamboxbenchatk.getChildren().addAll(playerbuttons.get(i));
					}
				}
				if(-1 == players.get(i).getPosition()){
					if(players.get(i).getType() == 0){
						teamboxreskeep.getChildren().add(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 1){
						teamboxresdef.getChildren().addAll(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 2){
						teamboxresmid.getChildren().addAll(playerbuttons.get(i));
					}
					if(players.get(i).getType() == 3){
						teamboxresatk.getChildren().addAll(playerbuttons.get(i));
					}
				}
			}
			teamboxkeep.getChildren().add(teamboxdef);
			teamboxmid.getChildren().add(teamboxatk);
			teamboxmid.setTranslateX(-100);
			teamboxmid.setTranslateY(70);
			teamboxbenchkeep.getChildren().addAll(teamboxbenchdef,teamboxbenchmid,teamboxbenchatk);
			teamboxbenchkeep.setTranslateX(-100);
			teamboxreskeep.getChildren().addAll(teamboxresdef);
			teamboxresmid.getChildren().addAll(teamboxresatk);
			teamboxreskeep.setTranslateX(-100);
			totalteam.getChildren().addAll(teamboxkeep,teamboxmid,teamboxbenchkeep,teamboxreskeep,teamboxresmid,teamback);
			totalteam.getStylesheets().add("resources/mystyle.css");
			Scene pos = new Scene(totalteam,1500,750);
			stage.setScene(pos);
		}
	});
	savegame.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			lbtext.setText("Save game");
			VBox saves = new VBox();
			saves.getChildren().addAll(lbtext,save1,save2,save3,save4,next);
			saves.getStylesheets().add("resources/mystyle.css");
			Scene savegamescreen = new Scene(saves,1500,750);
			stage.setScene(savegamescreen);
			
			save1.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent arg0){
					game.setCurrentLeague(leaguechoice);
					game.setCurrentPlayRound(currentplayround);
					xml.writeGame(game, savelocation + "/save1");
				}
			});
			save2.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent arg0){
					xml.writeGame(game, savelocation +"/save2");
				}
			});
			save3.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent arg0){
					xml.writeGame(game, savelocation + "/save3");
				}
			});
			save4.setOnAction(new EventHandler<ActionEvent>(){
				
				@Override
				public void handle(ActionEvent arg0){
					xml.writeGame(game, savelocation + "/save4");
				}
			});
		}
	});
	train.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			VBox trainbox = new VBox(10);
			VBox trainback = new VBox();
			VBox traintotal = new VBox();
			trainback.setAlignment(Pos.BOTTOM_RIGHT);
			traintotal.getStylesheets().add("resources/mystyle.css");
			lbtext.setText("Training for whole team");
			trainbox.getChildren().addAll(lbtext,lighttrain,heavytrain,rest);
			trainback.getChildren().addAll(next);
			traintotal.getChildren().addAll(trainbox,trainback);
			Scene trainscreen = new Scene(traintotal,1500,750);
			stage.setScene(trainscreen);
		}
	});
	lighttrain.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			lbtext.setText("changed stats");
			VBox changebox1 = new VBox(10);
			VBox changebox2 = new VBox(10);
			VBox changebox3 = new VBox(10);
			VBox changebox4 = new VBox(10);
			VBox changebox5 = new VBox(10);
			HBox changeboxtotal = new HBox();
			changebox1.getChildren().add(lbtext);
			changeboxtotal.getStylesheets().add("/resources/mystyle.css");
			ArrayList<Player> oldplayers = new ArrayList<Player>();
			ArrayList<Label> changes = new ArrayList<Label>();
			for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
				oldplayers.add(new Player(0, " ", " ",(byte) 0, 0, 0,
						(byte) teams.get(teamchoiceint).getPlayers().get(i).getOffensiveScore(),(byte) teams.get(teamchoiceint).getPlayers().get(i).getDefensiveScore(),
						(byte) teams.get(teamchoiceint).getPlayers().get(i).getStaminaScore(), 0));
			Training.RegularTraining(teams.get(teamchoiceint).getPlayers().get(i));
			int[] numbers = Player.ChangedStats(oldplayers.get(i),teams.get(teamchoiceint).getPlayers().get(i));
			changes.add(new Label(teams.get(teamchoiceint).getPlayers().get(i).getFirstname() + " " + teams.get(teamchoiceint).getPlayers().get(i).getSurname()));
			changes.add(new Label("offensive changed: " + numbers[0]));
			changes.add(new Label("defensive changed: " + numbers[1]));
			changes.add(new Label("stamina changed: " + numbers[2]));
			if(i < 6){
				changebox1.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox1.getChildren().add(new Label("nothing changed"));
				}
			}
			if(5 < i && i < 13){
				changebox2.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox2.getChildren().add(new Label("nothing changed"));
				}
			}
			if(12 < i && i < 19){
				changebox3.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox3.getChildren().add(new Label("nothing changed"));
				}
			}
			if(18 < i && i < 25){
				changebox4.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox4.getChildren().add(new Label("nothing changed"));
				}
			}
			if(24 < i && i < 31){
				changebox5.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox5.getChildren().add(new Label("nothing changed"));
				}
			}
			}
			changebox2.setTranslateY(70);
			changebox3.setTranslateY(70);
			changebox4.setTranslateY(70);
			changebox5.setTranslateY(70);
			changeboxtotal.getChildren().addAll(changebox1,changebox2,changebox3,changebox4,changebox5, next);
			Scene changescreen = new Scene(changeboxtotal,1500,750);
			stage.setScene(changescreen);
			traintoday = true;
		}
	});
	heavytrain.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			lbtext.setText("changed stats");
			VBox changebox1 = new VBox(10);
			VBox changebox2 = new VBox(10);
			VBox changebox3 = new VBox(10);
			VBox changebox4 = new VBox(10);
			VBox changebox5 = new VBox(10);
			HBox changeboxtotal = new HBox();
			changebox1.getChildren().add(lbtext);
			changeboxtotal.getStylesheets().add("/resources/mystyle.css");
			ArrayList<Player> oldplayers = new ArrayList<Player>();
			ArrayList<Label> changes = new ArrayList<Label>();
			for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
				oldplayers.add(new Player(0, " ", " ",(byte) 0, 0, 0,
						(byte) teams.get(teamchoiceint).getPlayers().get(i).getOffensiveScore(),(byte) teams.get(teamchoiceint).getPlayers().get(i).getDefensiveScore(),
						(byte) teams.get(teamchoiceint).getPlayers().get(i).getStaminaScore(), 0));
			Training.HeavyTraining(teams.get(teamchoiceint).getPlayers().get(i));
			int[] numbers = Player.ChangedStats(oldplayers.get(i),teams.get(teamchoiceint).getPlayers().get(i));
			changes.add(new Label(teams.get(teamchoiceint).getPlayers().get(i).getFirstname() + " " + teams.get(teamchoiceint).getPlayers().get(i).getSurname()));
			changes.add(new Label("offensive changed: " + numbers[0]));
			changes.add(new Label("defensive changed: " + numbers[1]));
			changes.add(new Label("stamina changed: " + numbers[2]));
			if(i < 6){
				changebox1.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox1.getChildren().add(new Label("nothing changed"));
				}
			}
			if(5 < i && i < 13){
				changebox2.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox2.getChildren().add(new Label("nothing changed"));
				}
			}
			if(12 < i && i < 19){
				changebox3.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox3.getChildren().add(new Label("nothing changed"));
				}
			}
			if(18 < i && i < 25){
				changebox4.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox4.getChildren().add(new Label("nothing changed"));
				}
			}
			if(24 < i && i < 31){
				changebox5.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox5.getChildren().add(new Label("nothing changed"));
				}
			}
			}
			changebox2.setTranslateY(70);
			changebox3.setTranslateY(70);
			changebox4.setTranslateY(70);
			changebox5.setTranslateY(70);
			changeboxtotal.getChildren().addAll(changebox1,changebox2,changebox3,changebox4,changebox5, next);
			Scene changescreen = new Scene(changeboxtotal,1500,750);
			stage.setScene(changescreen);
			traintoday = true;
		}
	});
	rest.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			lbtext.setText("changed stats");
			VBox changebox1 = new VBox(10);
			VBox changebox2 = new VBox(10);
			VBox changebox3 = new VBox(10);
			VBox changebox4 = new VBox(10);
			VBox changebox5 = new VBox(10);
			HBox changeboxtotal = new HBox();
			changebox1.getChildren().add(lbtext);
			changeboxtotal.getStylesheets().add("/resources/mystyle.css");
			ArrayList<Player> oldplayers = new ArrayList<Player>();
			ArrayList<Label> changes = new ArrayList<Label>();
			for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
				oldplayers.add(new Player(0, " ", " ",(byte) 0, 0, 0,
						(byte) teams.get(teamchoiceint).getPlayers().get(i).getOffensiveScore(),(byte) teams.get(teamchoiceint).getPlayers().get(i).getDefensiveScore(),
						(byte) teams.get(teamchoiceint).getPlayers().get(i).getStaminaScore(), 0));
			Training.rest(teams.get(teamchoiceint).getPlayers().get(i));
			int[] numbers = Player.ChangedStats(oldplayers.get(i),teams.get(teamchoiceint).getPlayers().get(i));
			changes.add(new Label(teams.get(teamchoiceint).getPlayers().get(i).getFirstname() + " " + teams.get(teamchoiceint).getPlayers().get(i).getSurname()));
			changes.add(new Label("offensive changed: " + numbers[0]));
			changes.add(new Label("defensive changed: " + numbers[1]));
			changes.add(new Label("stamina changed: " + numbers[2]));
			if(i < 6){
				changebox1.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox1.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox1.getChildren().add(new Label("nothing changed"));
				}
			}
			if(5 < i && i < 13){
				changebox2.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox2.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox2.getChildren().add(new Label("nothing changed"));
				}
			}
			if(12 < i && i < 19){
				changebox3.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox3.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox3.getChildren().add(new Label("nothing changed"));
				}
			}
			if(18 < i && i < 25){
				changebox4.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox4.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox4.getChildren().add(new Label("nothing changed"));
				}
			}
			if(24 < i && i < 31){
				changebox5.getChildren().addAll(changes.get(i*4));
				if(!(changes.get(i*4 + 1).getText().equals("offensive changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 1));
				}
				if(!(changes.get(i*4 + 2).getText().equals("defensive changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 2));
				}
				if(!(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox5.getChildren().add(changes.get(i*4 + 3));
				}
				if((changes.get(i*4 + 1).getText().equals("offensive changed: " + 0)) && (changes.get(i*4 + 2).getText().equals("defensive changed: " + 0)) && 
						(changes.get(i*4 + 3).getText().equals("stamina changed: " + 0))){
					changebox5.getChildren().add(new Label("nothing changed"));
				}
			}
			}
			changebox2.setTranslateY(70);
			changebox3.setTranslateY(70);
			changebox4.setTranslateY(70);
			changebox5.setTranslateY(70);
			changeboxtotal.getChildren().addAll(changebox1,changebox2,changebox3,changebox4,changebox5, next);
			Scene changescreen = new Scene(changeboxtotal,1500,750);
			stage.setScene(changescreen);
			traintoday = true;
		}
	});
	nextday.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			currentday++;
			traintoday = false;
			algorithm.DailyRoutine(game);
			VBox playmatchbox = new VBox();
			HBox matchresult = new HBox(20);
			VBox matchresultfriday = new VBox();
			VBox matchresultsaturday = new VBox();
			VBox matchresultsunday = new VBox();
			Label standard0 = new Label("Home - Away");
			Label standard1 = new Label("Home - Away");
			Label standard2 = new Label("Home - Away");
			Label friday = new Label("Friday");
			Label saturday = new Label("Saturday");
			Label sunday = new Label("Sunday");
			matchresultfriday.getChildren().addAll(friday,standard0);
			matchresultsaturday.getChildren().addAll(saturday,standard1);
			matchresultsunday.getChildren().addAll(sunday, standard2);
			playmatchbox.getStylesheets().add("resources/mystyle.css");
			Scene playmatchscreen = new Scene(playmatchbox,1500,750);
		if(currentday<5){
			select.fire();
		}
		if (currentday == 5) {
			for(int i = 0;i < scheme.getS().get(currentplayround).getFriday().getMatches().size();i++){
				if(scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_home().getId() == teams.get(teamchoiceint).getId()){
					homematch = true;
					matchid = i;
				}
				if(scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_away().getId() == teams.get(teamchoiceint).getId()){
					awaymatch = true;
					matchid = i;
				}
				String match = (scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_away().getName());
				String result = (PlayMatch.play(scheme.getS().get(currentplayround).getFriday().getMatches().get(i)));
				Label matchlabel = new Label(match);
				Label resultlabel = new Label(result);
				scheme.getS().get(currentplayround).getFriday().getMatches().get(i).setResult(result);
				if(homematch == true){
					homematch = false;
					String r[] = (scheme.getS().get(currentplayround).getFriday().getMatches().get(matchid).getResult().split("-"));
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel,mediaViewlose);
					    mediaPlayerlose.setAutoPlay(true);
					    mediaPlayerlose.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerlose.setVolume(0);
					}
					else if(home > out) {
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel,mediaViewwin);
					    mediaPlayerwin.setAutoPlay(true);
					    mediaPlayerwin.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerwin.setVolume(0);
					}
					else{
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel,mediaViewtie);
						mediaPlayertie.setAutoPlay(true);
						mediaPlayertie.setCycleCount(MediaPlayer.INDEFINITE);
						mediaPlayertie.setVolume(0);
					}
				}
				else if(awaymatch == true){
					awaymatch = false;
					String r[] = (scheme.getS().get(currentplayround).getFriday().getMatches().get(matchid).getResult().split("-"));
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel,mediaViewwin);
					    mediaPlayerwin.setAutoPlay(true);
					    mediaPlayerwin.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerwin.setVolume(0);
					}
					else if(home > out) {
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel,mediaViewlose);
					    mediaPlayerlose.setAutoPlay(true);
					    mediaPlayerlose.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerlose.setVolume(0);
					}
					else{
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel,mediaViewtie);
						mediaPlayertie.setAutoPlay(true);
						mediaPlayertie.setCycleCount(MediaPlayer.INDEFINITE);
						mediaPlayertie.setVolume(0);
					}
				}
				else {
					matchresultfriday.getChildren().addAll(matchlabel,resultlabel);
				}
			}
			matchresult.getChildren().addAll(matchresultfriday);
			playmatchbox.getChildren().addAll(lbtext,matchresult,next);
			stage.setScene(playmatchscreen);
		}
		if (currentday == 6){
			for(int i = 0;i < scheme.getS().get(currentplayround).getSaturday().getMatches().size();i++){
				if(scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_home().getId() == teams.get(teamchoiceint).getId()){
					homematch = true;
					matchid = i;
				}
				if(scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_away().getId() == teams.get(teamchoiceint).getId()){
					awaymatch = true;
					matchid = i;
				}
				String match = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_away().getName());
				String result = (PlayMatch.play(scheme.getS().get(currentplayround).getSaturday().getMatches().get(i)));
				Label matchlabel = new Label(match);
				Label resultlabel = new Label(result);
				scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).setResult(result);
				if(homematch == true){
					homematch = false;
					String r[] = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(matchid).getResult().split("-"));
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel,mediaViewlose);
					    mediaPlayerlose.setAutoPlay(true);
					    mediaPlayerlose.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerlose.setVolume(0);
					}
					else if(home > out) {
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel,mediaViewwin);
					    mediaPlayerwin.setAutoPlay(true);
					    mediaPlayerwin.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerwin.setVolume(0);
					}
					else{
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel,mediaViewtie);
						mediaPlayertie.setAutoPlay(true);
						mediaPlayertie.setCycleCount(MediaPlayer.INDEFINITE);
						mediaPlayertie.setVolume(0);
					}
				}
				else if(awaymatch == true){
					awaymatch = false;
					String r[] = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(matchid).getResult().split("-"));
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel,mediaViewwin);
					    mediaPlayerwin.setAutoPlay(true);
					    mediaPlayerwin.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerwin.setVolume(0);
					}
					else if(home > out) {
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel,mediaViewlose);
					    mediaPlayerlose.setAutoPlay(true);
					    mediaPlayerlose.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerlose.setVolume(0);
					}
					else{
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel,mediaViewtie);
						mediaPlayertie.setAutoPlay(true);
						mediaPlayertie.setCycleCount(MediaPlayer.INDEFINITE);
						mediaPlayertie.setVolume(0);
					}
				}
				else {
					matchresultsaturday.getChildren().addAll(matchlabel,resultlabel);
				}
			}
			matchresult.getChildren().addAll(matchresultsaturday);
			playmatchbox.getChildren().addAll(lbtext,matchresult,next);
			stage.setScene(playmatchscreen);
		}
		if(currentday == 7){
			currentday = 0;
			for(int i = 0;i < scheme.getS().get(currentplayround).getSunday().getMatches().size();i++){

				if(scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_home().getId() == teams.get(teamchoiceint).getId()){
					homematch = true;
					matchid = i;
				}
				if(scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_away().getId() == teams.get(teamchoiceint).getId()){
					awaymatch = true;
					matchid = i;
				}
				String match = (scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_away().getName());
				String result = (PlayMatch.play(scheme.getS().get(currentplayround).getSunday().getMatches().get(i)));
				Label matchlabel = new Label(match);
				Label resultlabel = new Label(result);
				scheme.getS().get(currentplayround).getSunday().getMatches().get(i).setResult(result);
				if(homematch == true){
					homematch = false;
					String r[] = (scheme.getS().get(currentplayround).getSunday().getMatches().get(matchid).getResult().split("-"));
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel,mediaViewlose);
					    mediaPlayerlose.setAutoPlay(true);
					    mediaPlayerlose.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerlose.setVolume(0);
					}
					else if(home > out) {
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel,mediaViewwin);
					    mediaPlayerwin.setAutoPlay(true);
					    mediaPlayerwin.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerwin.setVolume(0);
					}
					else{
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel,mediaViewtie);
						mediaPlayertie.setAutoPlay(true);
						mediaPlayertie.setCycleCount(MediaPlayer.INDEFINITE);
						mediaPlayertie.setVolume(0);
					}
				}
				else if(awaymatch == true){
					awaymatch = false;
					String r[] = (scheme.getS().get(currentplayround).getSunday().getMatches().get(matchid).getResult().split("-"));
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel,mediaViewwin);
					    mediaPlayerwin.setAutoPlay(true);
					    mediaPlayerwin.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerwin.setVolume(0);
					}
					else if(home > out) {
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel,mediaViewlose);
					    mediaPlayerlose.setAutoPlay(true);
					    mediaPlayerlose.setCycleCount(MediaPlayer.INDEFINITE);
					    mediaPlayerlose.setVolume(0);
					}
					else{
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel,mediaViewtie);
						mediaPlayertie.setAutoPlay(true);
						mediaPlayertie.setCycleCount(MediaPlayer.INDEFINITE);
						mediaPlayertie.setVolume(0);
					}
				}
				else {
					matchresultsunday.getChildren().addAll(matchlabel,resultlabel);
				}
			}
			matchresult.getChildren().addAll(matchresultsunday);
			playmatchbox.getChildren().addAll(lbtext,matchresult,next);
			currentplayround ++;
			if(betmade == true){
				long oldbudget = 0;
				if(bets.getMatchid() == ((currentplayround - 1) * 10)){
					System.out.print(scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getResult());
					String r[] = (scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getResult()).split("-");
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						System.out.print("Friday out won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getTeam_home().getId()));
					}
					else if(home > out) {
						System.out.print("Friday home won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getTeam_away().getId()));
					}
					else {
						System.out.print("Friday  won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, bets.getS_won()));
					}
					betmade = false;
				}
				if(bets.getMatchid() > ((currentplayround - 1) * 10) && bets.getMatchid() < ((scheme.getS().get(currentplayround - 1).getSaturday().getMatches().size() + 1) + (currentplayround - 1) * (10))){	
					System.out.print(scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 1).getResult());
					String r[] = (scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 1).getResult()).split("-");
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						System.out.print("sat out won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 1).getTeam_home().getId()));
					}
					else if(home > out) {
						System.out.print("sat home won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 1).getTeam_away().getId()));
					}
					else {
						System.out.print("sat tie won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, bets.getS_won()));
					}
					betmade = false;
				}
				if(bets.getMatchid() > ((scheme.getS().get(currentplayround - 1).getSaturday().getMatches().size()) + (currentplayround - 1) * 10) && bets.getMatchid() < ((scheme.getS().get(currentplayround - 1).getSaturday().getMatches().size() + scheme.getS().get(currentplayround - 1).getSunday().getMatches().size()) + (currentplayround - 1) * 10)){
					System.out.print(scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 5).getResult());
					String r[] = (scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 5).getResult()).split("-");
					int home = Integer.parseInt(r[0]);
					int out = Integer.parseInt(r[1]);
					if(home < out){
						System.out.print("sun out won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 5).getTeam_home().getId()));
					}
					else if(home > out) {
						System.out.print("sun home won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10) - 5).getTeam_away().getId()));
					}
					else {
						System.out.print("sun tie won");
						oldbudget = teams.get(teamchoiceint).getBudget();
						teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, bets.getS_won()));
					}
					betmade = false;
				}
				System.out.print(oldbudget);
				System.out.print(teams.get(teamchoiceint).getBudget());
				if(oldbudget == teams.get(teamchoiceint).getBudget()){
					playmatchbox.getChildren().add(new Label("you lost your bet"));
				}
				else{
					playmatchbox.getChildren().add(new Label("you won your bet"));
				}
			}
			stage.setScene(playmatchscreen);
			for(int i = 0; i < teams.size(); i++){
				AI.Team_Training_User.Core(teams.get(i));
			}
			rank = Ranking.generate(scheme);
		}
		Game.setCurrentDay(currentday);
		if(scheme.getS().size() == currentplayround){
			endofgame = true;
			showrank.fire();
		}
		}
	});
	next.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			select.fire();
		}
	});
		for(int i = 0; i < game.getLeagues().size(); i++){
			final int a = i;
			leaguebuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0){
					leaguechoice = a;
					leagueaction.fire();
				}
				
			});
		}
		leagueaction.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				//changes the text
				lbtext.setText("Selection team");
				teams = leagues.get(leaguechoice).getTeams();
				scheme = Scheduler.scheduler(game.getLeagues().get(leaguechoice));
				game.setSchedule(scheme);
				rank = Ranking.generate(scheme);
				final ArrayList<Button> teambuttons = new ArrayList<Button>();
					for(int i = 0;i < teams.size();i++){
						String teamname =teams.get(i).getName().toString();
						teambuttons.add(new Button(teamname));
							final int a = i;
						teambuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){

								@Override
								public void handle(ActionEvent arg0){
									teamchoiceint = a;
									teamaction.fire();
								}
						});
					}
				//makes new boxes for layout new game screen
				HBox container = new HBox(10);
				VBox teams1 = new VBox(10);
				VBox teams2 = new VBox(10);
				VBox teams3 = new VBox(10);
				VBox vbBack = new VBox(10);
				for(int i = 0; i < teams.size(); i++){
					if(i < 10){
						teams1.getChildren().add(teambuttons.get(i));
					}
					if(9 < i && i < 20){
						teams2.getChildren().add(teambuttons.get(i));
					}
					if(19 < i && i < 30){
						teams3.getChildren().add(teambuttons.get(i));
					}
				}
				//adds the buttons and the label and sets the scene in the stage
				teams1.getChildren().addAll(lbtext);
				vbBack.getChildren().addAll(mutesong,backng);
				container.getChildren().addAll(teams1,teams2,teams3);
				container.setTranslateY(100);
				HBox ngtext = new HBox();
				ngtext.getChildren().addAll(lbtext,container,vbBack);
				Scene ng = new Scene(ngtext,1500,750);
				ng.getStylesheets().add("resources/mystyle.css");
				stage.setScene(ng);
				container.setAlignment(Pos.BOTTOM_CENTER);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				
				//actions for the load game button
				
				}	
		});
		mutesong.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				if(audio.getVolume()==0){
					audio.setVolume(100);
				}
				else{
					audio.setVolume(0);
				}
			}
		});
		Back.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				audio.stop();
				mediaPlayer.setVolume(100);
				//sets the old stage back possible error with calling new game screen again(testing)
				VBox back = root;
				back.getChildren().removeAll(newgame,loadgame,mutevideo);
				lbtext.setText("Footballmanager");
				back.getChildren().addAll(lbtext,newgame,loadgame,mutevideo);
				stage.setScene(scene);
			}
			
		});
		mutevideo.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				if(mediaPlayer.getVolume()==0){
					mediaPlayer.setVolume(100);
				}
				else{
					mediaPlayer.setVolume(0);
				}
			}
		});			
		root.getChildren().addAll(lbtext, newgame, loadgame, mutevideo);
		test.getChildren().addAll(mediaView,root);
	}
}