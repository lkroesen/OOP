package gui;

//unused imports are unused
import java.io.File;
import java.util.Scanner;
import model.Team;
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
	//looking into xml parser and video filling the background 
	
	//possible image for video still creating
	//private static final String MEDIA_URL = "http://ak5.picdn.net/shutterstock/videos/3340358/preview/stock-footage-spinning-football-d-animation-stock-footage-clip-isolated-on-white-background-full-hd-video.mp4";
    //private static String arg1;
	
    //toptext of every scene and button for starting screen
	Label lbtext;
	Button newgame, loadgame, mute;
	
	Button team_1, team_2, team_3, team_4, team_5, team_6, team_7, team_8, team_9,
	team_10, team_11, team_12, team_13, team_14, team_15, team_16, team_17, team_18;
	
	Button Back = new Button ("Back");
	
	
	
	//launches the gui
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//song name in file form
		File file = new File("fmsong.mp3");
		//File file = new File("C:/Users/Remi/Music/Punch-Out!!.mp3"); //Dan werkt ie wel bij mij - Remi
		
		//plays the song endless
		final String mediaLocation = file.toURI().toURL().toExternalForm();
		Media song = new Media(mediaLocation);
		MediaPlayer audio = new MediaPlayer(song);
		audio.play();
		audio.setCycleCount(audio.INDEFINITE);
		
		//first boxes for scene layout
		VBox root = new VBox(10);
		HBox start = new HBox();
		//StackPane fm = new StackPane(); //for video
		
		//effects on buttons
		Reflection reflection = new Reflection();
		reflection.setFraction(0.8);
		reflection.setTopOffset(-20);
		
		//Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL);
	    //MediaPlayer mediaPlayer = new MediaPlayer(media);
	    //mediaPlayer.setAutoPlay(true);
	    //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//all video stuff
	        
	       
	       /*MediaView mediaView = new MediaView(mediaPlayer);
	       final DoubleProperty width = mediaView.fitWidthProperty();
	       final DoubleProperty height = mediaView.fitHeightProperty();
	       mediaView.setScaleY(1.5);
	       
	       width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
	       height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
	       also video stuff
		*/
		
		team_1 = createButton1();
		team_2 = createButton2();
		team_3 = createButton3();
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
		mute = new Button("Mute/resume");
		
		lbtext.setEffect(reflection);
		
		lbtext.getStyleClass().add("mycustomLabel");
		
		//makes a scene with the title setting it in the stage
		Scene scene = new Scene(root,500,500);
		stage.setTitle("Footballmanager");
		stage.setScene(scene);
		//fm.getChildren().addAll(mediaView,root); // video stuff
		scene.getStylesheets().add("mystyle.css");
		
		//shows current scene
		stage.show();
		
		//actions by pressing new game button
		newgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				//changes the text
				lbtext.setText("Selection menu");
				
				//makes new boxes for layout new game screen
				VBox teams1 = new VBox(10);
				teams1.setAlignment(Pos.CENTER_LEFT);
				VBox teams2 = new VBox(10);
				teams2.setAlignment(Pos.TOP_CENTER);
				VBox vbBack = new VBox();
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				Label empty = new Label(" ");
				
				//adds the buttons and the label and sets the scene in the stage
				teams1.getChildren().addAll(lbtext,team_1,team_2,team_3,team_4,team_5,team_6,team_7,team_8,team_9);
				teams2.getChildren().addAll(empty,team_10,team_11,team_12,team_13,team_14,team_15,team_16,team_17,team_18);
				vbBack.getChildren().addAll(Back);
				start.getChildren().addAll(teams1,teams2);
				VBox ngtext = new VBox();
				ngtext.getChildren().addAll(lbtext,start,vbBack);
				Scene ng = new Scene(ngtext,500,500);
				ng.getStylesheets().add("mystyle.css");
				stage.setScene(ng);
				}
			
		});
		//back button actions
		Back.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				//sets the old stage back possible error with calling new game screen again(testing)
				VBox back = root;
				back.getChildren().removeAll(newgame,loadgame,mute);
				lbtext.setText("Footballmanager");
				back.getChildren().addAll(lbtext,newgame,loadgame,mute);
				stage.setScene(scene);
			}
			
		});
		
		//actions for the load game button
		loadgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText("Select save game");
				//makes the amount of saves visable as buttons
				//for(int i = 1; i < 4/*savedgames + 1*/;i++){
				//	Button save = new Button ("save " + i);
				//	root.getChildren().add(save);
				//}
				
				//making boxes and buttons for load game screen
				Button save = new Button ("save 1");
				VBox lgtext = new VBox();
				VBox backpos = new VBox();
				backpos.setAlignment(Pos.BOTTOM_RIGHT);
				backpos.getChildren().add(Back);
				lgtext.getChildren().addAll(lbtext,save,backpos);
				lgtext.getStylesheets().add("mystyle.css");
				Scene lg = new Scene(lgtext,500,500);
				stage.setScene(lg);
				}
			
		});
		
		//actions for mute or resume music
		mute.setOnAction(new EventHandler<ActionEvent>(){
			
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
		//first box getting children 
		root.getChildren().addAll(lbtext, newgame, loadgame, mute);
	}
	
	//These 18 methods create the team buttons for the team selection screen
		public static Button createButton1(){

			String team1 = "ADO";
			Button team_1 = new Button (team1);
			return team_1;	
		}
		
		
		public static Button createButton2(){
			
			String team2 = "Ajax";
			Button team_2 = new Button (team2);
			return team_2;
			
		}
		public static Button createButton3(){
			
			String team3 = "AZ";
			Button team_3 = new Button (team3);
			return team_3;
			
		}
		public static Button createButton4(){
			
			String team4 = "Cambuur";
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