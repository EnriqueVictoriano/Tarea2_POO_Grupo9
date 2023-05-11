import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.io.File;
import java.net.URL;

public class Siren {
    public Siren (String mediaURL){
        isSounding = false;
        Media media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        sView = new SirenView();
    }
    public void play(){
        mediaPlayer.play();
        sView.startBlinking();
        isSounding = true;
    }
    public void stop(){
        mediaPlayer.stop();
        sView.stopBlinking();
        isSounding = false;
    }
    public Polygon getView() {
        return sView;
    }
    public boolean isSounding() {
        return isSounding;
    }
    private SirenView sView;
    private final MediaPlayer mediaPlayer;
    private boolean isSounding;
}
