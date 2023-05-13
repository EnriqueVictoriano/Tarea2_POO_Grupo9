import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class PIR_SensorView extends Group {
    public PIR_SensorView () {
        switchView = new Rectangle(6,6,6,6);
        pirView = new Rectangle(4,3,10,2);
        pirView.setFill(Color.DARKGREY);
        setUnArmedView();
        getChildren().addAll(switchView, pirView);
    }
    public void setUnArmedView() {
        switchView.setFill(Color.WHITE);
    }
    public void setArmedView () {
        switchView.setFill(Color.GREEN);
    }
    public void setSomeoneIn () {
        switchView.setFill(Color.RED);
    }
    public Rectangle getPirView(){
        return switchView;
    }
    private final Rectangle switchView;
    private final Rectangle pirView;
}
