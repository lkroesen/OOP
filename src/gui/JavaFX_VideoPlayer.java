package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_VideoPlayer extends Application {
     
    private static final String MEDIA_URL = "http://ak5.picdn.net/shutterstock/videos/3340358/preview/stock-footage-spinning-football-d-animation-stock-footage-clip-isolated-on-white-background-full-hd-video.mp4";
     private static String arg1;
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
    	Group root = new Group();
    	Scene scene = new Scene(root, 500, 500);
 
        // create media player
        Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
         
        // create mediaView and add media player to the viewer
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group)scene.getRoot()).getChildren().add(mediaView);
         
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}