package gui;

import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** plays an audio in JavaFX 2.x */
public class MediaTest extends Application {
  public static void main(String[] args) { launch(args); }
  
  @Override public void start(Stage stage) throws MalformedURLException {
    final Label status = new Label("Init");
    MediaPlayer mediaPlayer = createMediaPlayer(
      "C:/Users/Denver/oop/OOP/bin/Bioshock infinite rap.mp3", status);
    stage.show();
      mediaPlayer.play();  
  }

  /** 
   * creates a media player using a file from the given filename path 
   * and tracks the status of playing the file via the status label 
   */
  private MediaPlayer createMediaPlayer(final String filename, final Label status) throws MalformedURLException {
    File file = new File("C:/Users/Denver/oop/OOP/bin/Bioshock infinite rap.mp3");
    
    final String mediaLocation = file.toURI().toURL().toExternalForm();
    Media media = new Media(mediaLocation);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setOnError(new Runnable() {
      @Override public void run() {
        status.setText("Error");
      }
    });
    mediaPlayer.setOnPlaying(new Runnable() {
      @Override public void run() {
        status.setText("Playing: " + mediaLocation);
      }
    });
    mediaPlayer.setOnEndOfMedia(new Runnable() {
      @Override public void run() {
        status.setText("Done");
      }
    });
    return mediaPlayer;
  }
}