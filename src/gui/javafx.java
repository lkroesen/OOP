package gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.stage.Stage;


public class javafx extends Application{
	
	Label lbtext;
	Button newgame, loadgame;
	
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
	
	Media BACKGROUND_MEDIA = new Media(MediaTest.class.getResource("Bioshock infinite rap.mp3").toString());
	MediaPlayerBuilder builder;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		HBox start = new HBox();
		
		Reflection reflection = new Reflection();
		reflection.setFraction(0.8);
		reflection.setTopOffset(-20);
		
		
		lbtext = new Label("Footballmanager");
		newgame = new Button("new game");
		loadgame = new Button("load game");
		
		lbtext.setEffect(reflection);
		
		lbtext.getStyleClass().add("mycustomLabel");
		
		Scene scene = new Scene(root,500,500);
		stage.setTitle("Awesome game of awesomeness");
		stage.setScene(scene);
		
		scene.getStylesheets().add("mystyle.css");
		
		
		stage.show();
		
		newgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText("Selection menu");
				
				VBox teams1 = new VBox(10);
				teams1.setAlignment(Pos.CENTER_LEFT);
				VBox teams2 = new VBox(10);
				teams2.setAlignment(Pos.TOP_CENTER);
				VBox vbBack = new VBox();
				vbBack.setAlignment(Pos.BOTTOM_RIGHT);
				Label empty = new Label(" ");
				
				teams1.getChildren().addAll(lbtext,ADO,Ajax,AZ,Cambuur,Dordrecht,Excelsior,Feyenoord,GAEagles,Groningen);
				teams2.getChildren().addAll(empty,Heracles,Heerenveen,NAC,PSV,Twente,Utrecht,Vitesse,WillemII,Zwolle);
				vbBack.getChildren().addAll(Back);
				start.getChildren().addAll(teams1,teams2);
				VBox toptext = new VBox();
				toptext.getChildren().addAll(lbtext,start,vbBack);
				Scene ng = new Scene(toptext,500,500);
				ng.getStylesheets().add("mystyle.css");
				stage.setScene(ng);
				Back.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox back = root;
						back.getChildren().removeAll(newgame,loadgame);
						lbtext.setText("Footballmanager");
						back.getChildren().addAll(lbtext,newgame,loadgame);
						stage.setScene(scene);
					}
					
				});
				
			}
			
		});
		
		loadgame.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0){
				lbtext.setText("Select save game");
				//for(int i = 1; i < 4/*savedgames + 1*/;i++){
				//	Button save = new Button ("save " + i);
				//	root.getChildren().add(save);
				//}
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
				Backlg.setOnAction(new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent arg0){
						VBox backlg = root;
						backlg.getChildren().removeAll(newgame,loadgame);
						lbtext.setText("Footballmanager");
						backlg.getChildren().addAll(lbtext,newgame,loadgame);
						stage.setScene(scene);
					}
					
				});
			}
			
		});
		
		root.getChildren().addAll(lbtext, newgame, loadgame);
		
       this.builder = MediaPlayerBuilder.create().media(BACKGROUND_MEDIA).onEndOfMedia(new Runnable() {

                    public void run() {
                        MediaPlayer player = javafx.this.builder.build();
                        player.play();
                    }
                });



        MediaPlayer player = this.builder.build();
        player.play();
		
		
	}
}