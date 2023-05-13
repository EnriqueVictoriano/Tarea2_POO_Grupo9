import javafx.animation.TranslateTransition;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class PIR_DetectorView extends Group{
    public PIR_DetectorView(int x, int y, int dirAngle, int senAngle, int range){
        makePirViewWithoutSensor(senAngle, range);
        setRotate(dirAngle);
        relocate(x,y);
        prepareArmed_DisarmedTransition();
    }
    private void makePirViewWithoutSensor(int senAngle, int range) {

    }
    public void setPirModel(PIR_Detector model) {
        pirModel = model;
        setOnMouseClicked(e -> model.changeState());
    }
    public void addPirSensorView(PIR_SensorView ps){
        ps.getPirView().setX(Box.getX()+Box.getWidth()-ps.getPirView().getWidth());
        ps.getPirView().translateXProperty().bind(Box.translateXProperty());
    }
    private void prepareArmed_DisarmedTransition() {
        transition = new TranslateTransition(Duration.millis(1000), Box);
        transition.setCycleCount(1);
        transition.setOnFinished(e -> pirModel.finishMovement());
    }
    public void startArming() {
        transition.stop();
        transition.setFromX(0);
        transition.setFromY(0);
        transition.setToX(-1);
        transition.setToY(-1);
        Box.setFill(Color.GREEN);
        transition.play();
    }
    public void startDisarming() {
        transition.stop();
        transition.setFromX(-1); transition.setToX(0);
        transition.setFromY(-1); transition.setToY(0);
        Box.setFill(Color.GREY);
        transition.play();
    }
    private TranslateTransition transition;
    private PIR_Detector pirModel;
    private Polygon DetectionArea;
    private Rectangle Box;
}
