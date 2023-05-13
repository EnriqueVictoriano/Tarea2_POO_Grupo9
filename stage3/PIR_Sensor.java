public class PIR_Sensor extends Sensor{
    public PIR_Sensor(int z){
        super(z);
        view = new PIR_SensorView();
    }
    public void setSensorUnarmed() {
        setState(true);
        view.setUnArmedView();
    }
    public void setSensorArmed () {
        setState(true);
        view.setArmedView();
    }
    public void setDetected () {
        setState(false);
        view.setSomeoneIn();
    }
    public PIR_SensorView getView() {
        return view;
    }
    private final PIR_SensorView view;
}
