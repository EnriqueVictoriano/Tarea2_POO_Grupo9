import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;

public class Window {
    public Window(MagneticSensor sensor, WindowView view) {
        magneticSensor = sensor;
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
