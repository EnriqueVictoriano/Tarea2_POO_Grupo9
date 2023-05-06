public class MagneticSensor extends Sensor {
    public MagneticSensor(int z){
        super(z);
        view= new MagneticSensorView();
    }
    public void setSensorOpen() {
        setState(SwitchState.OPEN);
    }
    public void setSensorClose() {
        setState(SwitchState.OPEN);
    }
    public MagneticSensorView getView(){ return view;}
    private final MagneticSensorView view;
}
