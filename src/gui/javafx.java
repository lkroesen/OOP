package gui;

//unused imports are unused
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import AI.PlayMatch;
import AI.PlayRound;
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
	Button newgame, loadgame, mutesong, mutevideo, backng, backteam, select, playmatch, next, teamaction, playeraction, train;
	ImageView imagechoice;
	
	
	Button Back = new Button ("Back");
	int teamchoiceint, playerchoiceint, currentplayround;
	
	
	
	//launches the gui
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		
		stage.setResizable(false);
		XML xml = new XML("toms_teams.xml");
		Game game = xml.parseGame();
		ArrayList<League> leagues = game.getLeagues();
		League league = leagues.get(0);
		final ArrayList<Team> teams = league.getTeams();
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		final Schedule scheme = Scheduler.scheduler(leaguee);
		
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
		playmatch = new Button("Play match");
		next = new Button("continue");
		train = new Button("train");
		
		final ImageView image0 = new ImageView(new Image("/ADO_Den_Haag 200px.png", true));
		final ImageView image1 = new ImageView(new Image("/AFC_AJAX 200px.png", true));
		final ImageView image2 = new ImageView(new Image("/AZ 200px.png", true));
		final ImageView image3 = new ImageView(new Image("/FC_Dordrecht 200px.png", true));
		final ImageView image4 = new ImageView(new Image("/FC_Groningen 200px.png", true));
		final ImageView image5 = new ImageView(new Image("/FC_Twente 200px.png", true));
		final ImageView image6 = new ImageView(new Image("/FC_Utrecht 200px.png", true));
		final ImageView image7 = new ImageView(new Image("/Feyenoord 200px.png", true));
		final ImageView image8 = new ImageView(new Image("/Go_Ahead_Eagles 200px.png", true));
		final ImageView image9 = new ImageView(new Image("/Heracles 200px.png", true));
		final ImageView image10 = new ImageView(new Image("/NAC_Breda 200px.png", true));
		final ImageView image11 = new ImageView(new Image("/PEC_Zwolle 200px.png", true));
		final ImageView image12 = new ImageView(new Image("/PSV 200px.png", true));
		final ImageView image13 = new ImageView(new Image("/SBV_Excelsior 200px.png", true));
		final ImageView image14 = new ImageView(new Image("/SC_Cambuur 200px.png", true));
		final ImageView image15 = new ImageView(new Image("/Heerenveen 200px.png", true));
		final ImageView image16 = new ImageView(new Image("/Vitesse 200px.png", true));
		final ImageView image17 = new ImageView(new Image("/Willem_II 200px.png", true));
		
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
				VBox imagerow1 = new VBox(10);
				VBox imagerow2 = new VBox(10);
				VBox teams1 = new VBox(10);
				VBox teams2 = new VBox(10);
				VBox vbBack = new VBox(10);
				//adds the buttons and the label and sets the scene in the stage
				image0.setFitWidth(21);image0.setFitHeight(21);
				image1.setFitWidth(21);image1.setFitHeight(21);
				image2.setFitWidth(21);image2.setFitHeight(21);
				image3.setFitWidth(21);image3.setFitHeight(21);
				image4.setFitWidth(21);image4.setFitHeight(21);
				image5.setFitWidth(21);image5.setFitHeight(21);
				image6.setFitWidth(21);image6.setFitHeight(21);
				image7.setFitWidth(21);image7.setFitHeight(21);
				image8.setFitWidth(21);image8.setFitHeight(21);
				image9.setFitWidth(21);image9.setFitHeight(21);
				image10.setFitWidth(21);image10.setFitHeight(21);
				image11.setFitWidth(21);image11.setFitHeight(21);
				image12.setFitWidth(21);image12.setFitHeight(21);
				image13.setFitWidth(21);image13.setFitHeight(21);
				image14.setFitWidth(21);image14.setFitHeight(21);
				image15.setFitWidth(21);image15.setFitHeight(21);
				image16.setFitWidth(21);image16.setFitHeight(21);
				image17.setFitWidth(21);image17.setFitHeight(21);
				imagerow1.getChildren().addAll(image0,image1,image2,image3,image4,image5,image6,image7,image8);
				imagerow2.getChildren().addAll(image9,image10,image11,image12,image13,image14,image15,image16,image17);
				teams1.getChildren().addAll(lbtext,teambuttons.get(0),teambuttons.get(1),teambuttons.get(2),teambuttons.get(3),teambuttons.get(4),teambuttons.get(5),teambuttons.get(6),teambuttons.get(7),teambuttons.get(8));
				teams2.getChildren().addAll(teambuttons.get(9),teambuttons.get(10),teambuttons.get(11),teambuttons.get(12),teambuttons.get(13),teambuttons.get(14),teambuttons.get(15),teambuttons.get(16),teambuttons.get(17));
				vbBack.getChildren().addAll(mutesong,Back);
				container.getChildren().addAll(imagerow1,teams1,teams2,imagerow2);
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
				save.setAlignment(Pos.CENTER_LEFT);
				lbtext.setAlignment(Pos.TOP_LEFT);
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
				teamchoiceint = 0;
				imagechoice = image0;
				teamaction.fire();
			}
	});
	teambuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 1;
			imagechoice = image1;
			teamaction.fire();
		}
});
	teambuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 2;
			imagechoice = image2;
			teamaction.fire();
		}
	});
	teambuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 3;
			imagechoice = image3;
			teamaction.fire();
		}
	});
	teambuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 4;
			imagechoice = image4;
			teamaction.fire();
		}
	});
	teambuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 5;
			imagechoice = image5;
			teamaction.fire();
		}
	});
	teambuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 6;
			imagechoice = image6;
			teamaction.fire();
		}
	});
	teambuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 7;
			imagechoice = image7;
			teamaction.fire();
		}
});
	teambuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 8;
			imagechoice = image8;
			teamaction.fire();
		}
});
	teambuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 9;
			imagechoice = image9;
			teamaction.fire();
		}
});
	teambuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 10;
			imagechoice = image10;
			teamaction.fire();
		}
});
	teambuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 11;
			imagechoice = image11;
			teamaction.fire();
		}
});
	teambuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 12;
			imagechoice = image12;
			teamaction.fire();
		}
});
	teambuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 13;
			imagechoice = image13;
			teamaction.fire();
		}
});
	teambuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 14;
			imagechoice = image14;
			teamaction.fire();
		}
});
	teambuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 15;
			imagechoice = image15;
			teamaction.fire();
		}
});
	teambuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 16;
			imagechoice = image16;
			teamaction.fire();
		}
});
	teambuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 17;
			imagechoice = image17;
			teamaction.fire();
		}
});
	teamaction.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText(teams.get(teamchoiceint).getName().toString());
				final ArrayList<Player> players = teams.get(teamchoiceint).getPlayers();
				ArrayList<Button> playerbuttons = new ArrayList<Button>();
				VBox teambox1 = new VBox(10);
				VBox teambox2 = new VBox(10);
				HBox playerdisplay = new HBox();
				VBox vbBack = new VBox(10);
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				imagechoice.setFitWidth(60);imagechoice.setFitHeight(60);
				vbBack.getChildren().addAll(select,mutesong,backng);
				for(int i = 0; i < teams.get(teamchoiceint).getPlayers().size();i++){
				playerbuttons.add(new Button(players.get(i).getFirstname().toString() + " " + players.get(i).getSurname().toString()));
				}
				teambox1.getChildren().addAll(lbtext,playerbuttons.get(0),playerbuttons.get(1),playerbuttons.get(2),playerbuttons.get(3),playerbuttons.get(4),playerbuttons.get(5)
						,playerbuttons.get(6),playerbuttons.get(7),playerbuttons.get(8),playerbuttons.get(9),playerbuttons.get(10));
				teambox2.getChildren().addAll(imagechoice,playerbuttons.get(11),playerbuttons.get(12),playerbuttons.get(13),playerbuttons.get(14),playerbuttons.get(15),playerbuttons.get(16)
						,playerbuttons.get(17),playerbuttons.get(18),playerbuttons.get(19),playerbuttons.get(20),playerbuttons.get(21));
				teambox2.translateYProperty().set(10);
				playerdisplay.getChildren().addAll(teambox1,teambox2,vbBack);
				Scene teamscreen = new Scene(playerdisplay,1000,500);
				teamscreen.getStylesheets().add("mystyle.css");
				stage.setScene(teamscreen);
				
				
				playerbuttons.get(0).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 0;
						playeraction.fire();
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 1;
						playeraction.fire();
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 2;
						playeraction.fire();
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 3;
						playeraction.fire();
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 4;
						playeraction.fire();
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 5;
						playeraction.fire();
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 6;
						playeraction.fire();
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 7;
						playeraction.fire();
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 8;
						playeraction.fire();
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 9;
						playeraction.fire();
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 10;
						playeraction.fire();
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 11;
						playeraction.fire();
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 12;
						playeraction.fire();
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 13;
						playeraction.fire();
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 14;
						playeraction.fire();
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 15;
						playeraction.fire();
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 16;
						playeraction.fire();
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 17;
						playeraction.fire();
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 18;
						playeraction.fire();
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 19;
						playeraction.fire();
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 20;
						playeraction.fire();
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 21;
						playeraction.fire();
					}
				});
				playeraction.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						lbtext.setText("stats");
						Label fn = new Label("Firstname: " + players.get(playerchoiceint).getFirstname().toString());
						Label sn = new Label("Surname: " + players.get(playerchoiceint).getSurname().toString());
						Label j = new Label("Jerseynumber: " + players.get(playerchoiceint).getJerseyNumber());
						Label os = new Label("Offensive score: " + players.get(playerchoiceint).getOffensiveScore());
						Label ds = new Label("Defensive score: " + players.get(playerchoiceint).getDefensiveScore());
						Label ss = new Label("Stamina score: " + players.get(playerchoiceint).getStaminaScore());
						Label pos = new Label("Position: " + players.get(playerchoiceint).getPosition());
						Label pr = new Label("Price: " + players.get(playerchoiceint).getPrice());
						VBox playerbox = new VBox(10);
						VBox teamback = new VBox(10);teamback.setAlignment(Pos.BOTTOM_RIGHT);
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
						teambuttons.get(teamchoiceint).fire();
					}
				});
				select.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox teamchoicebox = new VBox(10);
						HBox imageadd = new HBox(10);
						teamchoicebox.getStylesheets().add("mystyle.css");
						imageadd.getStylesheets().add("mystyle.css");
						Scene teamchoicescreen = new Scene(imageadd,1000,500);
						lbtext.setText(teams.get(teamchoiceint).getName());
						teamchoicebox.getChildren().addAll(lbtext,playmatch,train);
						imageadd.getChildren().addAll(teamchoicebox,imagechoice);
						stage.setScene(teamchoicescreen);
						
					}
				});
				playmatch.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
					lbtext.setText("Results");
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
					for(int i = 0;i < scheme.getS().get(currentplayround).getFriday().getMatches().size();i++){
						String match = (scheme.getS().get(currentplayround).getFriday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(0).getFriday().getMatches().get(i).getTeam_away().getName());
						String result = (PlayMatch.play(scheme.getS().get(currentplayround).getFriday().getMatches().get(i)));
						Label matchlabel = new Label(match);
						Label resultlabel = new Label(result);
						matchresultfriday.getChildren().addAll(matchlabel,resultlabel);
					}
					for(int i = 0;i < scheme.getS().get(currentplayround).getSaturday().getMatches().size();i++){
						String match = (scheme.getS().get(currentplayround).getSaturday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(0).getSaturday().getMatches().get(i).getTeam_away().getName());
						String result = (PlayMatch.play(scheme.getS().get(currentplayround).getSaturday().getMatches().get(i)));
						Label matchlabel = new Label(match);
						Label resultlabel = new Label(result);
						matchresultsaturday.getChildren().addAll(matchlabel,resultlabel);
					}
					for(int i = 0;i < scheme.getS().get(currentplayround).getSunday().getMatches().size();i++){
						String match = (scheme.getS().get(currentplayround).getSunday().getMatches().get(i).getTeam_home().getName() + " vs " + scheme.getS().get(0).getSunday().getMatches().get(i).getTeam_away().getName());
						String result = (PlayMatch.play(scheme.getS().get(currentplayround).getSunday().getMatches().get(i)));
						Label matchlabel = new Label(match);
						Label resultlabel = new Label(result);
						matchresultsunday.getChildren().addAll(matchlabel,resultlabel);
					}
					playmatchbox.getStylesheets().add("mystyle.css");
					Scene playmatchscreen = new Scene(playmatchbox,1000,500);
					matchresult.getChildren().addAll(matchresultfriday,matchresultsaturday,matchresultsunday);
					playmatchbox.getChildren().addAll(lbtext,matchresult,next);
					stage.setScene(playmatchscreen);
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
		