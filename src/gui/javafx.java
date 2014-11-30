package gui;
import java.io.File;

//unused imports are unused
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
	Button newgame, loadgame;
	
	//all buttons for teams to be made with xml-parser
	Button ADO = new Button ("ADO");
	Button Ajax = new Button ("Ajax");
	Button AZ = new Button ("AZ");
	Button Cambuur = new Button ("Cambuur");
	Button Dordrecht = new Button ("Dordrecht");
	Button Excelsior = new Button ("Excelsior");
	Button Feyenoord = new Button ("Feyenoord");
	Button GAEagles = new Button ("G.A. Eagles");
	Button Groningen = new Button ("Groningen");
	Button Heracles = new Button ("Heracles");
	Button Heerenveen = new Button ("Heerenveen");
	Button NAC = new Button ("NAC");
	Button PSV = new Button ("PSV");
	Button Twente = new Button ("Twente");
	Button Utrecht = new Button ("Utrecht");
	Button Vitesse = new Button ("Vitesse");
	Button WillemII = new Button ("WillemII");
	Button Zwolle = new Button ("Zwolle");
	Button Back = new Button ("Back");
	
	
	//launches the gui
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//song name in file form
		File file = new File("C:/Users/Denver/oop/OOP/bin/fmsong.mp3");
		
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
		
		//set the text for first buttons and label and giving effects
		lbtext = new Label("Footballmanager");
		newgame = new Button("new game");
		loadgame = new Button("load game");
		
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
				teams1.getChildren().addAll(lbtext,ADO,Ajax,AZ,Cambuur,Dordrecht,Excelsior,Feyenoord,GAEagles,Groningen);
				teams2.getChildren().addAll(empty,Heracles,Heerenveen,NAC,PSV,Twente,Utrecht,Vitesse,WillemII,Zwolle);
				vbBack.getChildren().addAll(Back);
				start.getChildren().addAll(teams1,teams2);
				VBox toptext = new VBox();
				toptext.getChildren().addAll(lbtext,start,vbBack);
				Scene ng = new Scene(toptext,500,500);
				ng.getStylesheets().add("mystyle.css");
				stage.setScene(ng);
				
				//back button actions
				Back.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						//sets the old stage back possible error with calling new game screen again(testing)
						VBox back = root;
						back.getChildren().removeAll(newgame,loadgame);
						lbtext.setText("Footballmanager");
						back.getChildren().addAll(lbtext,newgame,loadgame);
						stage.setScene(scene);
					}
					
				});
				
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
				Button Backlg = new Button ("Back");
				backpos.setAlignment(Pos.BOTTOM_RIGHT);
				backpos.getChildren().add(Backlg);
				lgtext.getChildren().addAll(lbtext,save,backpos);
				lgtext.getStylesheets().add("mystyle.css");
				Scene lg = new Scene(lgtext,500,500);
				stage.setScene(lg);
				
				//back button actions
				Backlg.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						//sets the old stage see back button from new game
						VBox backlg = root;
						backlg.getChildren().removeAll(newgame,loadgame);
						lbtext.setText("Footballmanager");
						backlg.getChildren().addAll(lbtext,newgame,loadgame);
						stage.setScene(scene);
					}
					
				});
			}
			
		});
		//first box getting children 
		root.getChildren().addAll(lbtext, newgame, loadgame);
		
	}
}