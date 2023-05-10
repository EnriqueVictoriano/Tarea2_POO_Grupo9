public class Door {
    public Door(MagneticSensor sensor, DoorView view) {
        magneticSensor = sensor;
        state = State.CLOSE;
        dView = view;
        dView.addMagneticSensorView(magneticSensor.getView());
        dView.setDoorModel(this);
        dView.setOnMouseClicked((e -> changeState()));
    }
    public void changeState() {
        if (state == State.CLOSE){
            state = State.OPENING;
            magneticSensor.setSensorOpen();
        }
        else if (state == State.OPEN){
            state = State.CLOSING;
        }
    }
    public void finishMovement() {
        if (state == State.OPENING) state = State.OPEN;
        else if (state == State.CLOSING){
            state = State.CLOSE;
            magneticSensor.setSensorClose();
        }
    }
    private final DoorView dView;
    private final MagneticSensor magneticSensor;
    private State state;
 }