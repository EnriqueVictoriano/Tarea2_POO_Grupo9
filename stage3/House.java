import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Scanner;

public class House extends Pane {
    public House(Scanner in, Central central) {
        // reading <#_doors> <#_windows> <#_PIRs>
        int numDoors = in.nextInt();
        int numWindows = in.nextInt();
        int numPirs = in.nextInt();
        in.nextInt(); // just to conform the first line format
        for (int i = 0; i < numDoors; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            DoorView doorView = new DoorView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            new Door(sensor, doorView);
            getChildren().add(doorView);
        }
        for (int i = 0; i < numWindows; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int angle = in.nextInt();
            int zone = in.nextInt();
            WindowView windowView= new WindowView(x, y, angle);
            MagneticSensor sensor = new MagneticSensor(zone);
            central.addNewSensor(sensor);
            new Window(sensor, windowView);
            getChildren().add(windowView);
        }
        for (int i = 0; i < numPirs; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int dirAngle = in.nextInt();
            int senAngle = in.nextInt();
            int range = in.nextInt();
            int zone = in.nextInt();
            PIR_DetectorView pirView = new PIR_DetectorView(x,y,dirAngle,senAngle,range);
            PIR_Sensor sensor = new PIR_Sensor(zone);
            central.addNewSensor(sensor);
            new PIR_Detector(sensor, pirView);
            getChildren().add(pirView);
        }
        setMinWidth(700);
    }
}
