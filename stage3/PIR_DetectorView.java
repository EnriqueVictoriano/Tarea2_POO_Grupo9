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
        Box = new Rectangle(10,10,10,10);
        DetectionArea = new Polygon();
        DetectionArea.getPoints().addAll(
                0d, 0d,
                0d, 100d,
                50d, 75d,
                100d, 100d,
                100d, 0d);
        double x1 = Math.cos(Math.toRadians(senAngle/ 2)) * range;
        double y1 = Math.sin(Math.toRadians(senAngle / 2)) * range;
        double x2 = Math.cos(Math.toRadians(-senAngle / 2)) * range;
        double y2 = Math.sin(Math.toRadians(-senAngle / 2)) * range;
        DetectionArea.getPoints().addAll(x1, y1, x2, y2);
        DetectionArea.setFill(Color.TRANSPARENT);
        DetectionArea.setStroke(Color.RED);
        getChildren().addAll(Box,DetectionArea);

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
        transition.setFromX(-1);
        transition.setToX(0);
        transition.setFromY(-1);
        transition.setToY(0);
        Box.setFill(Color.GREY);
        transition.play();
    }
    private TranslateTransition transition;
    private PIR_Detector pirModel;
    private Polygon DetectionArea;
    private Rectangle Box;
}
