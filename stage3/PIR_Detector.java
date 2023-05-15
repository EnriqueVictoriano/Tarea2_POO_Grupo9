/**
*Esta clase representa un detector pir
*/
public class PIR_Detector {
    /**
    *Constructor para crear el obejto PIR_Detector
    *@Param pirSensor El sensor asociado al PIR_Detector
    *@Param state Inicializa el estado del sensor en cerrado
    */
    public PIR_Detector(PIR_Sensor sensor, PIR_DetectorView view){
        pirSensor = sensor;
        state = State.CLOSE;
        pView = view;
        pView.addPirSensorView(pirSensor.getView());
        pView.setPirModel(this);
    }
    public void changeState() {
        if (state == State.CLOSE){
            state = State.OPENING;
            pView.startArming();
            pirSensor.setSensorArmed();
        }
        else if (state == State.OPEN){
            state = State.CLOSING;
            pView.startDisarming();
        }
    }
    public void finishMovement() {
        if (state == State.CLOSING){
            state = State.CLOSE;
            pirSensor.setSensorUnarmed();
        }
        else if (state == State.OPENING)
            state = State.OPEN;
    }
    public PIR_DetectorView getpView() {
        return pView;
    }
    private final PIR_DetectorView pView;
    private final PIR_Sensor pirSensor;
    private State state;
}
