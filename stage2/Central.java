import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ArrayList;

public class Central {
    public Central(Siren siren){
        cView = new CentralView(this);
        zones = new ArrayList<Sensor>();
        state = CentralState.DISARMED;
        this.siren = siren;
        periodicCheck = new Timeline(new KeyFrame(Duration.millis(200), e-> checkZones()));
        periodicCheck.setCycleCount(Animation.INDEFINITE);
    }
    public VBox getView (){
        return cView;
    }
    public void armAll() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"-") + (!close[2]?",2":"");
        if(close[0] && close[1] && close[2] ) state = CentralState.ALL_ARMED;
        else {
            System.out.println(msg);
            state = CentralState.DISARMED;
        }
    }
    public void armPerimeter() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"");
        if(close[0] && close[1] ) state = CentralState.PERIMETER_ARMED;
        else {
            System.out.println(msg);
            state = CentralState.DISARMED;
        }
    }
    public void disarm() {
        state = CentralState.DISARMED;
        siren.stop();
    }
    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        boolean flag0 = true, flag1 = true, flag2 = true;
        for (Sensor sensor : zones) {
            int i = sensor.getZone();
            if (sensor.isClose() && i == 0) flag0 = false;
            else if (sensor.isClose() && i == 1) flag1 = false;
            else if (sensor.isClose() && i == 2) flag2 = false;
        }
        close[0] = flag0; close[1] = flag1; close[2] = flag2;
        return close;
    }
    public void addNewSensor(Sensor s){
        zones.add(s);
    }
    private void checkZones(){
        boolean[] close = checkCloseZones();
        if (state == CentralState.ALL_ARMED) {
            for (boolean all : close)
                if (!all)
                    siren.play();
        }
        else if (state == CentralState.PERIMETER_ARMED) {
            for (boolean perimeter : close)
                if (!perimeter)
                    siren.play();
        }
    }
    public CentralState getState(){
        return state;
    }
    enum CentralState {
        ALL_ARMED, PERIMETER_ARMED, DISARMED
    }
    private final ArrayList<Sensor> zones;
    private CentralState state;
    private final Siren siren;
    private final Timeline periodicCheck;
    private final CentralView cView;
}
