import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;

/**
 * A window with its magnetic sensor.
 */
public class Window {
    public Window(MagneticSensor zone, WindowView view) {
        magneticSensor = zone;
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }
    public void changeState() {  // is called when this window's view is clicked
        if (state == State.CLOSE) {
            state = State.OPENING;
            wView.startOpening();
            magneticSensor.setSensorOpen();
        }
        else if (state == State.OPEN){
            state = State.CLOSING;
            wView.startClosing();
        }
    }
    public void finishMovement() { // is called when this window ends closing or opening
        if (state == State.CLOSING) {
            state = State.CLOSE;
            magneticSensor.setSensorClose();
        }
        else if (state == State.OPENING) {
            state = State.OPEN;
        }
    }
    public WindowView getView(){
        return wView;
    }
    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}
