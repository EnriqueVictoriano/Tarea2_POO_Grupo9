import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.io.File;
import java.net.URL;

public class Siren {
    public Siren (String mediaURL){
        Media media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        sView = new SirenView();
    }
    public void play(){
        sView.startBlinking();
        mediaPlayer.play();
    }
    public void stop(){
        sView.stopBlinking();
        mediaPlayer.stop();
    }
    public Polygon getView() {
        return sView;
    }
    private SirenView sView;
    private MediaPlayer mediaPlayer;
}
