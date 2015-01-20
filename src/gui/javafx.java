package gui;

//unused imports are unused
import java.io.File;
import java.util.ArrayList;

import AI.Bet;
import AI.Betting;
import AI.PlayMatch;
import AI.PlayRound;
import AI.Ranking;
import AI.Schedule;
import AI.Scheduler;
import AI.Training;
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
	Button newgame, loadgame, mutesong, mutevideo, backng, backteam, select, nextday, next, teamaction, playeraction, train, lighttrain
	, heavytrain, rest, position, showteam, market, bet, upcoming, showrank, savegame, save1, save2, save3, save4;
	
	Button Back = new Button ("Back");
	int teamchoiceint, playerchoiceint, currentplayround = 0, currentday = 0, positionint = 0, swapplayer;
	boolean traintoday = false;
	boolean pastnewgame = false;
	boolean swapfirst = false;
	boolean showboolean = false;
	boolean betmaking = false;
	boolean betmade = false;
	Bet bets;
	Ranking rank;
	
	
	
	//launches the gui
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		
		//stage.setResizable(false);
		final XML xml = new XML("toms_teams.xml");
		final Game game = xml.parseGame();
		ArrayList<League> leagues = game.getLeagues();
		League league = leagues.get(0);
		final ArrayList<Team> teams = league.getTeams();
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		final Schedule scheme = Scheduler.scheduler(leaguee);
		rank = Ranking.generate(scheme);
		
		//song name in file form
		File file = new File("src/fmsong.mp3");

		
		//plays the song endless
		final String mediaLocation = file.toURI().toURL().toExternalForm();
		Media song = new Media(mediaLocation);
		final MediaPlayer audio = new MediaPlayer(song);
		audio.setCycleCount(audio.INDEFINITE);
		
		//first boxes for scene layout
		StackPane test = new StackPane();
		final VBox root = new VBox(10);
		HBox start = new HBox(10);
		start.setAlignment(Pos.CENTER);
		
		//effects on buttons
		Reflection reflection = new Reflection();
		reflection.setFraction(0.8);
		reflection.setTopOffset(-20);
		
		Media media = new Media(new File("src/Footballvideo.mp4").toURI().toString());
	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setAutoPlay(true);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//all video stuff
	        
	       
	       MediaView mediaView = new MediaView(mediaPlayer);
	       
	       mediaView.setFitWidth(1000);
	       //also video stuff
	       final ArrayList<Button> teambuttons = new ArrayList<Button>();
		for(int i = 0;i < teams.size();i++){
			String teamname =teams.get(i).getName().toString();
			teambuttons.add(new Button(teamname));
			
		}
		
		//set the text for first buttons and label and giving effects
		lbtext = new Label("Footballmanager");
		newgame = new Button("new game");
		teamaction = new Button();
		playeraction = new Button();
		loadgame = new Button("load game");
		mutesong = new Button("Mute/resume");
		mutevideo = new Button("Mute/resume");
		backng = new Button("back");
		backteam = new Button("back");
		select = new Button("choose this team");
		nextday = new Button("Next day");
		next = new Button("continue");
		train = new Button("train");
		lighttrain = new Button ("light training");
		heavytrain = new Button ("heavy training");
		rest = new Button ("rest");
		position = new Button ("change positions");
		showteam = new Button ("show team");
		bet = new Button ("bet");
		market = new Button ("market");
		upcoming = new Button ("this weeks matches");
		savegame = new Button("save game");
		showrank = new Button("show current rank");
		save1 = new Button("save 1");
		save2 = new Button("save 2");
		save3 = new Button("save 3");
		save4 = new Button("save 4");
		
		lbtext.setEffect(reflection);
		
		lbtext.getStyleClass().add("Headline");
		
		//makes a scene with the title setting it in the stage
		final Scene scene = new Scene(test,1000,500);
		stage.setTitle("Footballmanager");
		stage.setScene(scene);
		scene.getStylesheets().add("mystyle.css");
		
		//konami code
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent ke) {
	            if (ke.getCode() == KeyCode.UP) {
	            	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	        @Override
	        	        public void handle(KeyEvent ke) {
	        	            if (ke.getCode() == KeyCode.UP) {
	        	                System.out.println("space pressed");
	        	            }
	        	        }
	        	    });
	            }
	            else;
	        }
	    });
		//shows current scene
		stage.show();
		
		//actions by pressing new game button
		newgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				audio.play();
				mediaPlayer.setVolume(0);
				//changes the text
				lbtext.setText("Selection menu");
				
				//makes new boxes for layout new game screen
				HBox container = new HBox(10);
				VBox teams1 = new VBox(10);
				VBox teams2 = new VBox(10);
				VBox teams3 = new VBox(10);
				VBox vbBack = new VBox(10);
				for(int i = 0; i < league.getTeams().size(); i++){
					if(i < 10){
						teams1.getChildren().add(teambuttons.get(i));
					}
					if(9 < i && i < 20){
						teams2.getChildren().add(teambuttons.get(i));
					}
					if(19 < i && i < 30){
						teams2.getChildren().add(teambuttons.get(i));
					}
				}
				//adds the buttons and the label and sets the scene in the stage
				teams1.getChildren().addAll(lbtext);
				vbBack.getChildren().addAll(mutesong,Back);
				container.getChildren().addAll(teams1,teams2,teams3);
				container.setTranslateY(100);
				HBox ngtext = new HBox();
				ngtext.getChildren().addAll(lbtext,container,vbBack);
				Scene ng = new Scene(ngtext,1000,500);
				ng.getStylesheets().add("mystyle.css");
				stage.setScene(ng);
				container.setAlignment(Pos.BOTTOM_CENTER);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				}
			
		});
		//back button actions
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
		
		//actions for the load game button
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
				backpos.getChildren().addAll(mutesong,Back);
				lgtext.getChildren().addAll(lbtext,save1,save2,save3,save4,backpos);
				lgtext.getStylesheets().add("mystyle.css");
				Scene lg = new Scene(lgtext,1000,500);
				stage.setScene(lg);
				save1.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						//take scheduler
						select.fire();
					}
				});
				
				}
			
		});
		
		//actions for mute or resume music
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
		
		backng.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				newgame.fire();
				
			}
		});
	for(int i = 0; i < teams.size(); i++){
		final int a = i;
	teambuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0){
				teamchoiceint = a;
				teamaction.fire();
			}
	});
	}
	teamaction.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(teamchoiceint).getName().toString());
				final ArrayList<Player> players = teams.get(teamchoiceint).getPlayers();
				final ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				VBox teambox3 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.getChildren().addAll(select,mutesong,backng);
				teambox1.getChildren().addAll(lbtext);
				for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
					playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
					if(0 <= i && i < 10){
						teambox1.getChildren().addAll(playerbuttons.get(i));
					}
					if(10 <= i && i < 20){
						teambox2.getChildren().addAll(playerbuttons.get(i));
					}
					if(20 <= i && i < 30){
						teambox3.getChildren().addAll(playerbuttons.get(i));
					}
				}
				teambox2.translateYProperty().set(70);
				teambox3.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,teambox3,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
					final int a = i;
				playerbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = a;
						playeraction.fire();
					}
				});
				}
				
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
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						}
						if(swapfirst == false && showboolean == false && pastnewgame == true){
							lbtext.setText("swap out for");
							positionint = players.get(playerchoiceint).getPosition();
							swapplayer = playerchoiceint;
							Label def = new Label("defense");
							Label def1 = new Label("defense");
							Label def2 = new Label("defense");
							Label atk = new Label("attack");
							Label atk1 = new Label("attack");
							Label atk2 = new Label("attack");
							Label mid = new Label("midfield");
							Label mid1 = new Label("midfield");
							Label mid2 = new Label("midfield");
							Label keep = new Label("goalkeeper");
							Label keep1 = new Label("goalkeeper");
							Label keep2 = new Label("goalkeeper");
							Label bench = new Label("bench");
							Label res = new Label("reserve");
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
											if(players.get(i).getType() == 1){
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
							swapboxmid.setTranslateX(-100);
							swapboxmid.setTranslateY(70);
							swapboxbenchkeep.getChildren().addAll(swapboxbenchdef,swapboxbenchmid,swapboxbenchatk);
							swapboxbenchkeep.setTranslateX(-100);
							swapboxreskeep.getChildren().addAll(swapboxresdef,swapboxresmid,swapboxresatk);
							swapboxreskeep.setTranslateX(-100);
							totalswap.getChildren().addAll(swapboxkeep,swapboxmid,swapboxbenchkeep,swapboxreskeep);
							totalswap.getStylesheets().add("mystyle.css");
							Scene swapscreen = new Scene(totalswap,1000,500);
							stage.setScene(swapscreen);
						}
						if(swapfirst == true && showboolean == false && pastnewgame == true){
							players.get(swapplayer).setPosition((players.get(playerchoiceint).getPosition()));
							players.get(playerchoiceint).setPosition(positionint);
							select.fire();
						}
						showboolean = false;
						swapfirst = true;
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(teamchoiceint).fire();
					}
				});
				select.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						pastnewgame = true;
						swapfirst = false;
						Label currentdaylabel = new Label("monday");
						if(currentday == 1){
							currentdaylabel.setText("tuesday");
						}
						if(currentday == 2){
							currentdaylabel.setText("wednesday");
						}
						if(currentday == 3){
							currentdaylabel.setText("thursday");
						}
						if(currentday == 4){
							currentdaylabel.setText("friday");
						}
						if(currentday == 5){
							currentdaylabel.setText("saturday");
						}
						if(currentday == 6){
							currentdaylabel.setText("sunday");
						}
						VBox teamchoicebox = new VBox(10);
						HBox imageadd = new HBox(10);
						teamchoicebox.getStylesheets().add("mystyle.css");
						imageadd.getStylesheets().add("mystyle.css");
						Scene teamchoicescreen = new Scene(imageadd,1000,500);
						lbtext.setText(teams.get(teamchoiceint).getName());
						if(traintoday == false){
							if(betmade == false && currentday < 5){
								teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,nextday,train,position,showteam,bet,market,upcoming, showrank,savegame);
							}
							else{
								teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,nextday,train,position,showteam,market,upcoming, showrank,savegame);							}
						}
						else if(betmade == false && currentday < 5){
							teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,nextday,position,showteam,bet,market,upcoming, showrank,savegame);
						}
						else{
							teamchoicebox.getChildren().addAll(lbtext,currentdaylabel,nextday,position,showteam,market,upcoming, showrank,savegame);
						}
						imageadd.getChildren().addAll(teamchoicebox);
						stage.setScene(teamchoicescreen);	
					}
				});
				showrank.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("ranking");
						VBox rankbox1 = new VBox(10);
						VBox rankbox2 = new VBox(10);
						VBox rankbox3 = new VBox(10);
						VBox rankback = new VBox(10);
						ArrayList<Label> ranks = new ArrayList<Label>();
						rankbox1.getChildren().addAll(lbtext);
						for(int i = 0; i < league.getTeams().size(); i++){
							ranks.add(new Label(rank.getRanking()[i].getName().toString() + " " + rank.getScoreOfTeam()[i]));
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
						rankback.setAlignment(Pos.BOTTOM_RIGHT);
						rankbox2.setTranslateY(80);
						rankbox3.setTranslateY(80);
						rankback.getChildren().add(next);
						HBox rankboxtotal = new HBox(10);
						rankboxtotal.getChildren().addAll(rankbox1,rankbox2,rankbox3,rankback);
						rankboxtotal.getStylesheets().add("mystyle.css");
						Scene rankscreen = new Scene(rankboxtotal,1000,500);
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
				market.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						//transfers
					}
				});
				upcoming.setOnAction(new EventHandler<ActionEvent>(){
					

					@Override
					public void handle(ActionEvent arg0){
						if(betmaking == false){
							lbtext.setText("this weeks matches");
						}
						else{
							lbtext.setText("betting on teams");
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
						Label standard0 = new Label("home - away");
						Label standard1 = new Label("home - away");
						Label standard2 = new Label("home - away");
						Label friday = new Label("Friday");
						Label saturday = new Label("saturday");
						Label sunday = new Label("sunday");
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
							totalupcoming.getStylesheets().add("mystyle.css");
							Scene upcomingscreen = new Scene(totalupcoming,1000,500);
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
											System.out.print(a);
											teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() - 500000);
											select.fire();
										}
									});
								}
								else{
									betbuttons.get(i).setOnAction(new EventHandler<ActionEvent>(){
								
										@Override
										public void handle(ActionEvent arg0){
											bets = Betting.Bet_Maker(Betting.Before_Match(scheme,currentplayround), teams.get(teamchoiceint).getBudget(), teams, (int) (a/2), false);
											betmade = true;
											teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() - 500000);
											select.fire();
										}
									});
								}
							}
					}
				});
				showteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox showteambox1 = new VBox(10);
						VBox showteambox2 = new VBox(10);
						VBox showteambox3 = new VBox(10);
						HBox showplayerdisplay = new HBox();
						VBox showback = new VBox(10);
						showback.setAlignment(Pos.BOTTOM_RIGHT);
						showback.getChildren().addAll(next);
						showteambox1.getChildren().addAll(lbtext);
						for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
							playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
							if(0 <= i && i < 10){
								showteambox1.getChildren().addAll(playerbuttons.get(i));
							}
							if(10 <= i && i < 20){
								showteambox2.getChildren().addAll(playerbuttons.get(i));
							}
							if(20 <= i && i < 30){
								showteambox3.getChildren().addAll(playerbuttons.get(i));
							}
						}
						showteambox2.translateYProperty().set(10);
						showteambox3.translateYProperty().set(70);
						showplayerdisplay.getChildren().addAll(showteambox1,showteambox2,showteambox3,showback);
						Scene showteamscreen = new Scene(showplayerdisplay,1000,500);
						showteamscreen.getStylesheets().add("mystyle.css");
						stage.setScene(showteamscreen);
						showboolean = true;
					}
				});
				position.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("change positions");
						Label def = new Label("defense");
						Label def1 = new Label("defense");
						Label def2 = new Label("defense");
						Label atk = new Label("attack");
						Label atk1 = new Label("attack");
						Label atk2 = new Label("attack");
						Label mid = new Label("midfield");
						Label mid1 = new Label("midfield");
						Label mid2 = new Label("midfield");
						Label keep = new Label("goalkeeper");
						Label keep1 = new Label("goalkeeper");
						Label keep2 = new Label("goalkeeper");
						Label bench = new Label("bench");
						Label res = new Label("reserve");
						HBox totalteam = new HBox();
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
								if(players.get(i).getType() == 1){
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
						teamboxreskeep.getChildren().addAll(teamboxresdef,teamboxresmid,teamboxresatk);
						teamboxreskeep.setTranslateX(-100);
						totalteam.getChildren().addAll(teamboxkeep,teamboxmid,teamboxbenchkeep,teamboxreskeep);
						totalteam.getStylesheets().add("mystyle.css");
						Scene pos = new Scene(totalteam,1000,500);
						stage.setScene(pos);
					}
				});
				savegame.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("save game");
						VBox saves = new VBox();
						saves.getChildren().addAll(lbtext,save1,save2,save3,save4,next);
						saves.getStylesheets().add("mystyle.css");
						Scene savegamescreen = new Scene(saves,1000,500);
						stage.setScene(savegamescreen);
						
						save1.setOnAction(new EventHandler<ActionEvent>(){
							
							@Override
							public void handle(ActionEvent arg0){
								Game.setCurrentTeam(teamchoiceint);
								xml.writeGame(game, "save1");
							}
						});
					}
				});
				train.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox trainbox = new VBox();
						trainbox.getStylesheets().add("mystyle.css");
						lbtext.setText("Training");
						trainbox.getChildren().addAll(lbtext,lighttrain,heavytrain,rest);
						Scene trainscreen = new Scene(trainbox,1000,500);
						stage.setScene(trainscreen);
					}
				});
				lighttrain.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
						Training.RegularTraining(teams.get(teamchoiceint).getPlayers().get(i));
						}
						traintoday = true;
						select.fire();
					}
				});
				heavytrain.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
						Training.HeavyTraining(teams.get(teamchoiceint).getPlayers().get(i));
						}
						traintoday = true;
						select.fire();
					}
				});
				rest.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
						Training.rest(teams.get(teamchoiceint).getPlayers().get(i));
						}
						traintoday = true;
						select.fire();
					}
				});
				nextday.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox playmatchbox = new VBox();
						HBox matchresult = new HBox(20);
						VBox matchresultfriday = new VBox();
						VBox matchresultsaturday = new VBox();
						VBox matchresultsunday = new VBox();
						Label standard0 = new Label("home - away");
						Label standard1 = new Label("home - away");
						Label standard2 = new Label("home - away");
						Label friday = new Label("Friday");
						Label saturday = new Label("saturday");
						Label sunday = new Label("sunday");
						matchresultfriday.getChildren().addAll(friday,standard0);
						matchresultsaturday.getChildren().addAll(saturday,standard1);
						matchresultsunday.getChildren().addAll(sunday,standard2);
						playmatchbox.getStylesheets().add("mystyle.css");
						Scene playmatchscreen = new Scene(playmatchbox,1000,500);
					if(currentday<4){
						playmatchbox.getChildren().addAll(lbtext,matchresult,next);
						stage.setScene(playmatchscreen);
					}
					if (currentday == 4) {
						for(int i = 0;i < scheme.getS().get(currentplayround).getFriday().getMatches().size();i++){
							String match = (scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_away().getName());
							String result = (PlayMatch.play(scheme.getS().get(currentplayround).getFriday().getMatches().get(i)));
							Label matchlabel = new Label(match);
							Label resultlabel = new Label(result);
							scheme.getS().get(currentplayround).getFriday().getMatches().get(i).setResult(result);
							matchresultfriday.getChildren().addAll(matchlabel,resultlabel);
						}
						matchresult.getChildren().addAll(matchresultfriday);
						playmatchbox.getChildren().addAll(lbtext,matchresult,next);
						stage.setScene(playmatchscreen);
					}
					if (currentday == 5){
						for(int i = 0;i < scheme.getS().get(currentplayround).getSaturday().getMatches().size();i++){
							String match = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_away().getName());
							String result = (PlayMatch.play(scheme.getS().get(currentplayround).getSaturday().getMatches().get(i)));
							Label matchlabel = new Label(match);
							Label resultlabel = new Label(result);
							scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).setResult(result);
							matchresultsaturday.getChildren().addAll(matchlabel,resultlabel);
						}
						matchresult.getChildren().addAll(matchresultsaturday);
						playmatchbox.getChildren().addAll(lbtext,matchresult,next);
						stage.setScene(playmatchscreen);
					}
					if(currentday == 6){
						currentday = 0;
						for(int i = 0;i < scheme.getS().get(currentplayround).getSunday().getMatches().size();i++){
							String match = (scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_away().getName());
							String result = (PlayMatch.play(scheme.getS().get(currentplayround).getSunday().getMatches().get(i)));
							Label matchlabel = new Label(match);
							Label resultlabel = new Label(result);
							scheme.getS().get(currentplayround).getSunday().getMatches().get(i).setResult(result);
							matchresultsunday.getChildren().addAll(matchlabel,resultlabel);
						}
						matchresult.getChildren().addAll(matchresultsunday);
						playmatchbox.getChildren().addAll(lbtext,matchresult,next);
						stage.setScene(playmatchscreen);
						currentplayround ++;
						if(betmade == true){
							if(bets.getMatchid() == ((currentplayround - 1) * 10)){
								String r[] = (scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getResult()).split("-");
								int home = Integer.parseInt(r[0]);
								int out = Integer.parseInt(r[1]);
								if(home < out){
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - 1 - ((currentplayround - 1)*10)).getTeam_home().getId()));
								}
								else if(home > out) {
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getFriday().getMatches().get(bets.getMatchid() - 1 - ((currentplayround - 1)*10)).getTeam_away().getId()));
								}
								else {
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, bets.getS_won()));
								}
								betmade = false;
							}
							if(bets.getMatchid() > ((currentplayround - 1) * 10) && bets.getMatchid() < (5 + (currentplayround - 1) * 10)){
								String r[] = (scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getResult()).split("-");
								int home = Integer.parseInt(r[0]);
								int out = Integer.parseInt(r[1]);
								if(home < out){
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - 1 - ((currentplayround - 1)*10)).getTeam_home().getId()));
								}
								else if(home > out) {
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSaturday().getMatches().get(bets.getMatchid() - 1 - ((currentplayround - 1)*10)).getTeam_away().getId()));
								}
								else {
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, bets.getS_won()));
								}
								betmade = false;
							}
							if(bets.getMatchid() > (4 + (currentplayround - 1) * 10) && bets.getMatchid() < (9 + (currentplayround - 1) * 10)){
								String r[] = (scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - ((currentplayround - 1)*10)).getResult()).split("-");
								int home = Integer.parseInt(r[0]);
								int out = Integer.parseInt(r[1]);
								if(home < out){
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - 1 - ((currentplayround - 1)*10)).getTeam_home().getId()));
								}
								else if(home > out) {
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, scheme.getS().get(currentplayround - 1).getSunday().getMatches().get(bets.getMatchid() - 1 - ((currentplayround - 1)*10)).getTeam_away().getId()));
								}
								else {
									teams.get(teamchoiceint).setBudget(teams.get(teamchoiceint).getBudget() + Betting.After_Match(0, bets, bets.getS_won()));
								}
								betmade = false;
							}
						}
						rank = Ranking.generate(scheme);
					}
					else{
						currentday++;
					}
					traintoday = false;
					Game.setCurrentDay(currentday);
					}
				});
				next.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						select.fire();
					}
				});
			}
		});
	root.getChildren().addAll(lbtext, newgame, loadgame, mutevideo);
	test.getChildren().addAll(mediaView,root);
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
	}
}
		