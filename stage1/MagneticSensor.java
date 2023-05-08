public class MagneticSensor extends Sensor {
    public MagneticSensor(int z){
        super(z);
        view= new MagneticSensorView();
    }
    public void setSensorOpen() {
        setState(SwitchState.OPEN);
        view.setOpenView();
    }
    public void setSensorClose() {
        setState(SwitchState.CLOSE);
        view.setCloseView();
    }
    public MagneticSensorView getView(){ return view;}
    private final MagneticSensorView view;
}
