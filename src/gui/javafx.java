package gui;

//unused imports are unused
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import AI.PlayMatch;
import AI.Schedule;
import AI.Scheduler;
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
	Button newgame, loadgame, mutesong, mutevideo, backng, backteam, select, playmatch;
	
	Button team_4, team_5, team_6, team_7, team_8, team_9,
	team_10, team_11, team_12, team_13, team_14, team_15, team_16, team_17, team_18;
	
	Button Back = new Button ("Back");
	
	
	
	//launches the gui
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		
		stage.setResizable(false);
		XML xml = new XML("teams.xml");
		Game game = xml.parseGame();
		ArrayList<League> leagues = game.getLeagues();
		League league = leagues.get(0);
		ArrayList<Team> teams = league.getTeams();
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//Schedule s = Scheduler.scheduler(leaguee);
		
		//song name in file form
		File file = new File("src/fmsong.mp3");

		
		//plays the song endless
		final String mediaLocation = file.toURI().toURL().toExternalForm();
		Media song = new Media(mediaLocation);
		MediaPlayer audio = new MediaPlayer(song);
		audio.setCycleCount(audio.INDEFINITE);
		
		//first boxes for scene layout
		StackPane test = new StackPane();
		VBox root = new VBox(10);
		HBox start = new HBox();
		start.setAlignment(Pos.CENTER);
		//StackPane fm = new StackPane(); //for video
		
		//effects on buttons
		Reflection reflection = new Reflection();
		reflection.setFraction(0.8);
		reflection.setTopOffset(-20);
		
		Media media = new Media(new File("src/Footballvideo.mp4").toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setAutoPlay(true);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//all video stuff
	        
	       
	       MediaView mediaView = new MediaView(mediaPlayer);
	       
	       mediaView.setFitWidth(1000);
	       //also video stuff
	       ArrayList<Button> teambuttons = new ArrayList<Button>();
		for(int i = 0;i < teams.size();i++){
			String teamname =teams.get(i).getName().toString();
			teambuttons.add(new Button(teamname));
			
		}
		team_4 = createButton4();
		team_5 = createButton5();
		team_6 = createButton6();
		team_7 = createButton7();
		team_8 = createButton8();
		team_9 = createButton9();
		team_10 = createButton10();
		team_11 = createButton11();
		team_12 = createButton12();
		team_13 = createButton13();
		team_14 = createButton14();
		team_15 = createButton15();
		team_16 = createButton16();
		team_17 = createButton17();
		team_18 = createButton18();
		
		//set the text for first buttons and label and giving effects
		lbtext = new Label("Footballmanager");
		newgame = new Button("new game");
		loadgame = new Button("load game");
		mutesong = new Button("Mute/resume");
		mutevideo = new Button("Mute/resume");
		backng = new Button("back");
		backteam = new Button("back");
		select = new Button("choose this team");
		playmatch = new Button("Play match");
		
		ImageView image0 = new ImageView(new Image("/AFC_AJAX 200px.png", true));
		ImageView image1 = new ImageView(new Image("/Feyenoord 200px.png", true));
		ImageView image2 = new ImageView(new Image("/AZ 200px.png", true));
		
		lbtext.setEffect(reflection);
		
		lbtext.getStyleClass().add("Headline");
		
		//makes a scene with the title setting it in the stage
		Scene scene = new Scene(test,1000,500);
		stage.setTitle("Footballmanager");
		stage.setScene(scene);
		scene.getStylesheets().add("mystyle.css");
		
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
				VBox imagerow1 = new VBox(10);
				imagerow1.setTranslateY(23);
				VBox teams1 = new VBox(10);
				teams1.setAlignment(Pos.CENTER_LEFT);
				VBox teams2 = new VBox(10);
				teams2.setAlignment(Pos.CENTER);
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateY(-40);
				Label empty = new Label(" ");
				//adds the buttons and the label and sets the scene in the stage
				image0.setFitWidth(21);
				image0.setFitHeight(21);
				image1.setFitWidth(21);
				image1.setFitHeight(21);
				image2.setFitWidth(21);
				image2.setFitHeight(21);
				imagerow1.getChildren().addAll(image0,image1,image2);
				teams1.getChildren().addAll(lbtext,teambuttons.get(0),teambuttons.get(1),teambuttons.get(2),team_4,team_5,team_6,team_7,team_8,team_9);
				teams2.getChildren().addAll(empty,team_10,team_11,team_12,team_13,team_14,team_15,team_16,team_17,team_18);	
				//teams1.getChildren().addAll(lbtext,teambuttons.get(0),teambuttons.get(1),teambuttons.get(2),teambuttons.get(3),teambuttons.get(4),teambuttons.get(5),teambuttons.get(6),teambuttons.get(7),teambuttons.get(8));
				//teams2.getChildren().addAll(empty,teambuttons.get(9),teambuttons.get(10),teambuttons.get(11),teambuttons.get(12),teambuttons.get(13),teambuttons.get(14),teambuttons.get(15),teambuttons.get(16),teambuttons.get(17));
				vbBack.getChildren().addAll(mutesong,Back);
				start.getChildren().addAll(imagerow1,teams1,teams2);
				VBox ngtext = new VBox();
				ngtext.getChildren().addAll(lbtext,start,vbBack);
				Scene ng = new Scene(ngtext,1000,500);
				ng.getStylesheets().add("mystyle.css");
				stage.setScene(ng);
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
				//makes the amount of saves visable as buttons
				//for(int i = 1; i < 4/*savedgames + 1*/;i++){
				//	Button save = new Button ("save " + i);
				//	root.getChildren().add(save);
				//}
				
				//making boxes and buttons for load game screen
				Button save = new Button ("save 1");
				VBox lgtext = new VBox();
				VBox backpos = new VBox(10);
				backpos.setAlignment(Pos.BOTTOM_RIGHT);
				backpos.getChildren().addAll(mutesong,Back);
				lgtext.getChildren().addAll(lbtext,save,backpos);
				lgtext.getStylesheets().add("mystyle.css");
				Scene lg = new Scene(lgtext,1000,500);
				stage.setScene(lg);
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
		
		teambuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				image0.setFitWidth(60);
				image0.setFitHeight(60);
				image0.setSmooth(true);
				lbtext.setText(teams.get(0).getName().toString());
				ArrayList<Player> players = teams.get(0).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(select,mutesong,backng);
				for(int i = 0; i < teams.get(0).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(image0,playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(10);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(0).fire();
					}
				});
				select.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox teamchoicebox = new VBox(10);
						HBox imageadd = new HBox(10);
						teamchoicebox.getStylesheets().add("mystyle.css");
						Scene teamchoicescreen = new Scene(imageadd,1000,500);
						lbtext.setText(teams.get(0).getName());
						teamchoicebox.getChildren().addAll(lbtext,playmatch);
						imageadd.getChildren().addAll(teamchoicebox,image0);
						stage.setScene(teamchoicescreen);
						
					}
				});
				playmatch.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
					lbtext.setText("Results");
					VBox playmatchbox = new VBox();
					Label teamnamematch = new Label(teams.get(0).getName() + " vs " + teams.get(1).getName());
					playmatchbox.getStylesheets().add("mystyle.css");
					Scene playmatchscreen = new Scene(playmatchbox,1000,500);
					Match match = new Match(1,1,teams.get(0),teams.get(1));
					String result = PlayMatch.play(match);
					Label stats = new Label("goals home - away: " + result);
					playmatchbox.getChildren().addAll(lbtext,teamnamematch,stats);
					stage.setScene(playmatchscreen);
					}
				});
			}
		});
		
		teambuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(1).getName().toString());
				ArrayList<Player> players = teams.get(1).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(1).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(1).fire();
					}
				});
			}
		});
		
		teambuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(2).getName().toString());
				ArrayList<Player> players = teams.get(2).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(2).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(2).fire();
					}
				});
			}
		});
		
		
		/*
		teambuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(3).getName().toString());
				ArrayList<Player> players = teams.get(3).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(3).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(3).fire();
					}
				});
			}
		});
		*/
		
		
		/*
		teambuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(4).getName().toString());
				ArrayList<Player> players = teams.get(4).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(4).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(4).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(5).getName().toString());
				ArrayList<Player> players = teams.get(5).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(5).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(5).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(6).getName().toString());
				ArrayList<Player> players = teams.get(6).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(6).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(6).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(7).getName().toString());
				ArrayList<Player> players = teams.get(7).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(7).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(7).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(8).getName().toString());
				ArrayList<Player> players = teams.get(8).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(8).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(8).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(9).getName().toString());
				ArrayList<Player> players = teams.get(9).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(9).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(9).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(10).getName().toString());
				ArrayList<Player> players = teams.get(10).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(10).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(10).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(11).getName().toString());
				ArrayList<Player> players = teams.get(11).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(11).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(11).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(12).getName().toString());
				ArrayList<Player> players = teams.get(12).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(12).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(12).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(13).getName().toString());
				ArrayList<Player> players = teams.get(13).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(13).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(13).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(14).getName().toString());
				ArrayList<Player> players = teams.get(14).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(14).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(14).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(15).getName().toString());
				ArrayList<Player> players = teams.get(15).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(15).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(15).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(16).getName().toString());
				ArrayList<Player> players = teams.get(16).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(550);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(16).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(16).fire();
					}
				});
			}
		});
		*/
		
		/*
		teambuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(17).getName().toString());
				ArrayList<Player> players = teams.get(17).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				vbBack.setTranslateX(500);
				vbBack.getChildren().addAll(mutesong,backng);
				for(int i = 0; i < teams.get(17).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(70);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(0).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(0).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(0).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(0).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(0).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(0).getStaminaScore());
						Label pos = new Label("Position: " + players.get(0).getPosition());
						Label pr = new Label("Price: " + players.get(0).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(1).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(1).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(1).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(1).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(1).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(1).getStaminaScore());
						Label pos = new Label("Position: " + players.get(1).getPosition());
						Label pr = new Label("Price: " + players.get(1).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg2){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(2).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(2).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(2).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(2).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(2).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(2).getStaminaScore());
						Label pos = new Label("Position: " + players.get(2).getPosition());
						Label pr = new Label("Price: " + players.get(2).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg3){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(3).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(3).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(3).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(3).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(3).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(3).getStaminaScore());
						Label pos = new Label("Position: " + players.get(3).getPosition());
						Label pr = new Label("Price: " + players.get(3).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg4){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(4).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(4).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(4).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(4).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(4).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(4).getStaminaScore());
						Label pos = new Label("Position: " + players.get(4).getPosition());
						Label pr = new Label("Price: " + players.get(4).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg5){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(5).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(5).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(5).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(5).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(5).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(5).getStaminaScore());
						Label pos = new Label("Position: " + players.get(5).getPosition());
						Label pr = new Label("Price: " + players.get(5).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg6){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(6).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(6).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(6).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(6).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(6).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(6).getStaminaScore());
						Label pos = new Label("Position: " + players.get(6).getPosition());
						Label pr = new Label("Price: " + players.get(6).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg7){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(7).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(7).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(7).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(7).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(7).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(7).getStaminaScore());
						Label pos = new Label("Position: " + players.get(7).getPosition());
						Label pr = new Label("Price: " + players.get(7).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg8){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(8).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(8).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(8).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(8).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(8).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(8).getStaminaScore());
						Label pos = new Label("Position: " + players.get(8).getPosition());
						Label pr = new Label("Price: " + players.get(8).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg9){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(9).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(9).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(9).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(9).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(9).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(9).getStaminaScore());
						Label pos = new Label("Position: " + players.get(9).getPosition());
						Label pr = new Label("Price: " + players.get(9).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg10){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(10).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(10).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(10).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(10).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(10).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(10).getStaminaScore());
						Label pos = new Label("Position: " + players.get(10).getPosition());
						Label pr = new Label("Price: " + players.get(10).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg11){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(11).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(11).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(11).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(11).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(11).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(11).getStaminaScore());
						Label pos = new Label("Position: " + players.get(11).getPosition());
						Label pr = new Label("Price: " + players.get(11).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg12){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(12).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(12).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(12).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(12).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(12).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(12).getStaminaScore());
						Label pos = new Label("Position: " + players.get(12).getPosition());
						Label pr = new Label("Price: " + players.get(12).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg13){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(13).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(13).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(13).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(13).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(13).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(13).getStaminaScore());
						Label pos = new Label("Position: " + players.get(13).getPosition());
						Label pr = new Label("Price: " + players.get(13).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg14){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(14).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(14).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(14).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(14).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(14).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(14).getStaminaScore());
						Label pos = new Label("Position: " + players.get(14).getPosition());
						Label pr = new Label("Price: " + players.get(14).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg15){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(15).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(15).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(15).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(15).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(15).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(15).getStaminaScore());
						Label pos = new Label("Position: " + players.get(15).getPosition());
						Label pr = new Label("Price: " + players.get(15).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg16){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(16).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(16).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(16).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(16).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(16).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(16).getStaminaScore());
						Label pos = new Label("Position: " + players.get(16).getPosition());
						Label pr = new Label("Price: " + players.get(16).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg17){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(17).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(17).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(17).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(17).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(17).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(17).getStaminaScore());
						Label pos = new Label("Position: " + players.get(17).getPosition());
						Label pr = new Label("Price: " + players.get(17).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg18){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(18).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(18).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(18).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(18).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(18).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(18).getStaminaScore());
						Label pos = new Label("Position: " + players.get(18).getPosition());
						Label pr = new Label("Price: " + players.get(18).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg19){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(19).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(19).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(19).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(19).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(19).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(19).getStaminaScore());
						Label pos = new Label("Position: " + players.get(19).getPosition());
						Label pr = new Label("Price: " + players.get(19).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg20){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(20).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(20).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(20).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(20).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(20).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(20).getStaminaScore());
						Label pos = new Label("Position: " + players.get(20).getPosition());
						Label pr = new Label("Price: " + players.get(20).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg21){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(21).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(21).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(21).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(21).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(21).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(21).getStaminaScore());
						Label pos = new Label("Position: " + players.get(21).getPosition());
						Label pr = new Label("Price: " + players.get(21).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
						HBox screenbox = new HBox();
						playerbox.getChildren().addAll(fn,sn,j,os,ds,ss,pos,pr);
						teamback.getChildren().addAll(mutesong,backteam);
						screenbox.getChildren().addAll(playerbox,teamback);
						Scene playerscreen = new Scene(screenbox,1000,500);
						playerscreen.getStylesheets().add("mystyle.css");
						stage.setScene(playerscreen);
						
					}
				});
				backteam.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						teambuttons.get(17).fire();
					}
				});
			}
		});
		*/
		
		//first box getting children
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
	
	//These 18 methods create the team buttons for the team selection screen
		public static Button createButton4(){
		
			String team4 = "Dordrecht";
			Button team_4 = new Button (team4);
			return team_4;
		
		}
		public static Button createButton5(){
			
			String team5 = "Dordrecht";
			Button team_5 = new Button (team5);
			return team_5;
			
		}
		public static Button createButton6(){
			
			String team6 = "Excelsior";
			Button team_6 = new Button (team6);
			return team_6;
			
		}
		public static Button createButton7(){
			
			String team7 = "Feyenoord";
			Button team_7 = new Button (team7);
			return team_7;
			
		}
		public static Button createButton8(){
			
			String team8 = "G.A. Eagles";
			Button team_8 = new Button (team8);
			return team_8;
			
		}
		public static Button createButton9(){
			
			String team9 = "Groningen";
			Button team_9 = new Button (team9);
			return team_9;
			
		}
		public static Button createButton10(){
			
			String team10 = "Heracles";
			Button team_10 = new Button (team10);
			return team_10;
			
		}
		public static Button createButton11(){
			
			String team11 = "Heerenveen";
			Button team_11 = new Button (team11);
			return team_11;
			
		}
		public static Button createButton12(){
			
			String team12 = "NAC";
			Button team_12 = new Button (team12);
			return team_12;
			
		}
		public static Button createButton13(){
			
			String team13 = "PSV";
			Button team_13 = new Button (team13);
			return team_13;
			
		}
		public static Button createButton14(){
			
			String team14 = "Twente";
			Button team_14 = new Button (team14);
			return team_14;
			
		}
		public static Button createButton15(){
			
			String team15 = "Utrecht";
			Button team_15 = new Button (team15);
			return team_15;
			
		}
		public static Button createButton16(){
			
			String team16 = "Vitesse";
			Button team_16 = new Button (team16);
			return team_16;
			
		}
		public static Button createButton17(){
			
			String team17 = "WillemII";
			Button team_17 = new Button (team17);
			return team_17;
			
		}
		public static Button createButton18(){
			
			String team18 = "Zwolle";
			Button team_18 = new Button (team18);
			return team_18;
			
		}
}