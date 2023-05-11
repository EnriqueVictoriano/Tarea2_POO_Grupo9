import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ArrayList;

public class Central {
    public Central(Siren siren){
        cView = new CentralView(this);
        zone0 = new ArrayList<Sensor>();
        zone1 = new ArrayList<Sensor>();
        zone2 = new ArrayList<Sensor>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200), e-> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
    }
    public VBox getView (){
        return cView;
    }
    public boolean armAll() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"");
        if(close[0] && close[1] && close[2] ){
            state = CentralState.ALL_ARMED;
            return true;
        }
        else {
            state = CentralState.DISARMED;
            return false;
        }
    }
    public boolean armPerimeter() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"");
        if(close[0] && close[1] ){
            state = CentralState.PERIMETER_ARMED;
            return true;
        }
        else {
            state = CentralState.DISARMED;
            return false;
        }
    }
    public void disarm() {
        state = CentralState.DISARMED;
        siren.stop();
    }
    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        for (Sensor sensor0 : zone0)
            if (!sensor0.isClose()) { close[0] = false; break; }
        for (Sensor sensor1 : zone1)
            if (!sensor1.isClose()) { close[1] = false; break; }
        for (Sensor sensor2 : zone2)
            if (!sensor2.isClose()) { close[2] = false; break; }
        return close;
    }
    public void addNewSensor(Sensor s){
        if (s.getZone() == 0) zone0.add(s);
        else if (s.getZone() == 1) zone1.add(s);
        else if (s.getZone() == 2) zone2.add(s);
    }
    private void checkZones(){
        boolean[] close = checkCloseZones();
        if (!siren.isSounding()){
            if (state == CentralState.ALL_ARMED)
                for (boolean check0 : close)
                    if (!check0) { siren.play(); break; }
            else if (state == CentralState.PERIMETER_ARMED)
                        if (!close[0] || !close[1]) { siren.play(); break;}
        }
    }
    public CentralState getState(){
        return state;
    }
    private final ArrayList<Sensor> zone0, zone1, zone2;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;
    private final CentralView cView;
}
