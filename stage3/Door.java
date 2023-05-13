public class Door {
    public Door(MagneticSensor sensor, DoorView view) {
        magneticSensor = sensor;
        state = State.CLOSE;
        dView = view;
        dView.addMagneticSensorView(magneticSensor.getView());
        dView.setDoorModel(this);
    }
    public void changeState() {
        if (state == State.CLOSE){
            state = State.OPENING;
            dView.startOpening();
            magneticSensor.setSensorOpen();
        }
        else if (state == State.OPEN){
            state = State.CLOSING;
            dView.startClosing();
        }
    }
    public void finishMovement() {
        if (state == State.CLOSING){
            state = State.CLOSE;
            magneticSensor.setSensorClose();
        }
        else if (state == State.OPENING) {
            state = State.OPEN;
        }

    }
    public DoorView getView() {
        return dView;
    }
    private final DoorView dView;
    private final MagneticSensor magneticSensor;
    private State state;
 }