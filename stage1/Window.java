/**
 * A window with its magnetic sensor.
 */
public class Window {
    public Window(int zone, WindowView view) {
        magneticSensor = new MagneticSensor(zone);
        state = State.CLOSE;
        wView = view;
        wView.addMagneticSensorView(magneticSensor.getView());
        wView.setWindowModel(this);
    }
    public void changeState() {  // is called when this window's view is clicked
        switch (state) {
            case OPEN -> {
                if (magneticSensor.getState() == SwitchState.CLOSE)
                    state = State.CLOSING;
            }
            case CLOSE -> {
                if (magneticSensor.getState() == SwitchState.OPEN)
                    state = State.OPENING;
            }
        }
    }
    public void finishMovement() { // is called when this window ends closing or opening
        ....
    }
    public WindowView getView(){
        return wView;
    }
    private final WindowView wView;
    private final MagneticSensor magneticSensor;
    private State state;
}
