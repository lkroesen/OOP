package gui;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.stage.Stage;

public class MediaTest extends Application {

	private static final Media BACKGROUND_MEDIA = new Media(MediaTest.class.getResource("Bioshock infinite rap.mp3").toString());

    private MediaPlayerBuilder builder;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.builder = MediaPlayerBuilder
                       .create()
                       .media(BACKGROUND_MEDIA)
                       .onEndOfMedia(new Runnable() {

                           public void run() {
                               MediaPlayer player = MediaTest.this.builder.build();
                               player.play();
                           }
                       });

        MediaPlayer player = this.builder.build();
        player.play();

    }
}