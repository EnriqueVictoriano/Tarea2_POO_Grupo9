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
        if(close[0] && close[1] && close[2] ) msg = "Unable to arm all.";
        else state = CentralState.ALL_ARMED;
    }
    public void armPerimeter() {
        boolean[] close = checkCloseZones();
        String msg="Open zone(s): ";
        msg+=(!close[0]?"0":"-") + (!close[1]?",1":"");
        if(close[0] && close[1] ) msg = "Unable to arm perimeter";
        else state = CentralState.PERIMETER_ARMED;
    }
    public void disarm() {
        state = CentralState.DISARMED;
    }
    private boolean[] checkCloseZones() {
        boolean[] close = {true, true, true};
        for (Sensor sensor : zones)
            close[sensor.getZone()] &=sensor.isClose();
        return close;
    }
    public void addNewSensor(Sensor s){
        zones.add(s);
    }
    private void checkZones(){
        boolean[] close = checkCloseZones();
        if (state == CentralState.ALL_ARMED){

        }
        else if (state == CentralState.PERIMETER_ARMED){

        }
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
