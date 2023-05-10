import javafx.animation.*;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class DoorView extends Group {
    public DoorView(int x, int y, int angle){
        makeDoorViewWithoutSensor();
        setRotate(angle);  // to rotate at the geometric center.
        relocate(x,y);
        // getTransforms().add(new Rotate(angle,40,50));  // to rotate at anchor pivot (40,50)
        prepareOpen_CloseRotation();
    }
    private void makeDoorViewWithoutSensor(){
        Polygon origenPillar = new Polygon();
        origenPillar.getPoints().addAll(0d,0d,
                0d,20d,
                10d, 20d,
                10d, 10d,
                20d, 10d,
                20d, 0d);
        origenPillar.setFill(Color.BLUE);
        origenPillar.setStroke(Color.BLUE);
        switchPillar = new Polygon(
                160d,0d,
                160d, 10d,
                170d, 10d,
                170d, 20d,
                180d, 20d,
                180d, 0d );
        switchPillar.setFill(Color.BLUE);
        switchPillar.setStroke(Color.BLUE);
        slidingSheet = new Rectangle(10,10,160,10);
        slidingSheet.setFill(Color.BURLYWOOD);
        Rectangle border = new Rectangle(0,0 ,180, 180);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.GRAY);
        border.getStrokeDashArray().addAll(4d,4d );
        getChildren().addAll(border);
        getChildren().addAll(origenPillar, switchPillar,slidingSheet);
    }
    public void setDoorModel(Door model) {
        doorModel = model;
        setOnMouseClicked(e -> model.changeState());
    }
    public void addMagneticSensorView(MagneticSensorView msView){
        placeMagneticSensor(msView);
        //...
        getChildren().add(msView);
    }
    private void placeMagneticSensor( MagneticSensorView mv){
        mv.getMagnetView().setX(slidingSheet.getX()+slidingSheet.getWidth()-mv.getMagnetView().getWidth());
        //...
        mv.getSwitchView().setY(switchPillar.getBoundsInLocal().getHeight());
    }
    private void prepareOpen_CloseRotation() {
        rotation = new RotateTransition(Duration.millis(2000), slidingSheet);
        rotation.setCycleCount(1);
        rotation.setOnFinished(e -> doorModel.finishMovement());
    }
    public void startOpening() {
        rotation.stop();
        slidingSheet.getTransforms().add(new Rotate(90, slidingSheet.getX(), slidingSheet.getY()));
        //rotation.setAxis(slidingSheet.getRotationAxis());
        //rotation.setByAngle(90);
        rotation.play();
    }
    public void startClosing() {
        rotation.stop();

        slidingSheet.getTransforms().add(new Rotate(-90, slidingSheet.getX(), slidingSheet.getY()));
        rotation.play();
    }
    private RotateTransition rotation;
    private Door doorModel;
    private Polygon switchPillar;
    private Rectangle slidingSheet;
}
