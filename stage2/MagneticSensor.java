public class MagneticSensor extends Sensor {
    public MagneticSensor(int z){
        super(z);
        view = new MagneticSensorView();
    }
    public void setSensorOpen() {
        if (!isClose()) {
            setClose(false);
            view.setOpenView();
        }
    }
    public void setSensorClose() {
        if (isClose()) {
            setClose(true);
            view.setCloseView();
        }
    }
    public MagneticSensorView getView(){ return view;}
    private final MagneticSensorView view;
}
