package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private String filePath;

    @FXML
    private Label labelView;

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider volume;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleButtonClick(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\YOUR_NAME\\Music"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files",
                "*.wav", "*.mp3", "*.mp4"));
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();

            if (filePath != null){
                labelView.setText(file.getName());
                Media media = new Media(filePath);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            }

        volume.setValue(mediaPlayer.getVolume() * 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue() / 100);
            }
        });
    }

    @FXML
    public void playFile(){
        mediaPlayer.play();
    }

    @FXML
    public void stopFile(){
        mediaPlayer.stop();
    }

    @FXML
    public void pauseFile(){
        mediaPlayer.pause();
    }

}


