import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.sql.Time;

public class SirenView extends Polygon {
    public SirenView() {
        getPoints().addAll(0d,30d,
                0d,50d,
                40d, 80d,
                40d, 0d);
        setFill(Color.WHITE);
        setStroke(Color.RED);
        timeline = new Timeline(new KeyFrame(Duration.millis(250))/*...*/);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void startBlinking(){
        timeline.play();
    }
    public void stopBlinking() {
        timeline.stop();
    }
    private final Timeline timeline;
}
