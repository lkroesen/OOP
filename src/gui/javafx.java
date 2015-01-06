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
	Button newgame, loadgame, mutesong, mutevideo, backng, backteam, select, playmatch, next;
	
	
	Button Back = new Button ("Back");
	int teamchoiceint, playerchoiceint;
	
	
	
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
		next = new Button("continue");
		
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
				VBox imagerow1 = new VBox(10);
				VBox teams1 = new VBox(10);
				VBox teams2 = new VBox(10);
				VBox vbBack = new VBox(10);
				Label empty = new Label(" ");
				//adds the buttons and the label and sets the scene in the stage
				image0.setFitWidth(21);
				image0.setFitHeight(21);
				image1.setFitWidth(21);
				image1.setFitHeight(21);
				image2.setFitWidth(21);
				image2.setFitHeight(21);
				imagerow1.getChildren().addAll(image0,image1,image2);
				teams1.getChildren().addAll(lbtext,teambuttons.get(0),teambuttons.get(1),teambuttons.get(2),teambuttons.get(3),teambuttons.get(4),teambuttons.get(5),teambuttons.get(6),teambuttons.get(7),teambuttons.get(8));
				teams2.getChildren().addAll(empty,teambuttons.get(9),teambuttons.get(10),teambuttons.get(11),teambuttons.get(12),teambuttons.get(13),teambuttons.get(14),teambuttons.get(15),teambuttons.get(16),teambuttons.get(17));
				vbBack.getChildren().addAll(mutesong,Back);
				start.getChildren().addAll(imagerow1,teams1,teams2);
				HBox ngtext = new HBox();
				ngtext.getChildren().addAll(lbtext,start,vbBack);
				Scene ng = new Scene(ngtext,1000,500);
				ng.getStylesheets().add("mystyle.css");
				stage.setScene(ng);
				start.setAlignment(Pos.BOTTOM_CENTER);
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
			}
	});
	teambuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 1;
		}
});
	teambuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 2;
		}
	});
	teambuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 3;
		}
	});
	teambuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 4;
		}
	});
	teambuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 5;
		}
	});
	teambuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 6;
		}
	});
	teambuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 7;
		}
});
	teambuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 8;
		}
});
	teambuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 9;
		}
});
	teambuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 10;
		}
});
	teambuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 11;
		}
});
	teambuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 12;
		}
});
	teambuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 13;
		}
});
	teambuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 14;
		}
});
	teambuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 15;
		}
});
	teambuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 16;
		}
});
	teambuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent arg0){
			teamchoiceint = 17;
		}
});
	teambuttons.get(teamchoiceint).setOnAction(new EventHandler<ActionEvent>(){
			
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
						playerchoiceint = 0;
					}
				});
				playerbuttons.get(1).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 1;
					}
				});
				playerbuttons.get(2).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 2;
					}
				});
				playerbuttons.get(3).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 3;
					}
				});
				playerbuttons.get(4).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 4;
					}
				});
				playerbuttons.get(5).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 5;
					}
				});
				playerbuttons.get(6).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 6;
					}
				});
				playerbuttons.get(7).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 7;
					}
				});
				playerbuttons.get(8).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 8;
					}
				});
				playerbuttons.get(9).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 9;
					}
				});
				playerbuttons.get(10).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 10;
					}
				});
				playerbuttons.get(11).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 11;
					}
				});
				playerbuttons.get(12).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 12;
					}
				});
				playerbuttons.get(13).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 13;
					}
				});
				playerbuttons.get(14).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 14;
					}
				});
				playerbuttons.get(15).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 15;
					}
				});
				playerbuttons.get(16).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 16;
					}
				});
				playerbuttons.get(17).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 17;
					}
				});
				playerbuttons.get(18).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 18;
					}
				});
				playerbuttons.get(19).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 19;
					}
				});
				playerbuttons.get(20).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 20;
					}
				});
				playerbuttons.get(21).setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						playerchoiceint = 21;
					}
				});
				playerbuttons.get(playerchoiceint).setOnAction(new EventHandler<ActionEvent>(){
					
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
						imageadd.getStylesheets().add("mystyle.css");
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
					playmatchbox.getChildren().addAll(lbtext,teamnamematch,stats,next);
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
	}
}
		