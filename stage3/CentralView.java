import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CentralView extends VBox {
    public CentralView (Central central) {
        super(20);
        display = new Label("Disarmed");
        display.setStyle("-fx-border-color: black; -fx-background-color: white");
        display.setMinWidth(200);
        display.setAlignment(Pos.CENTER);
        //setCenter(display);
        Font controlFont = new Font("Arial", 24);
        display.setFont(controlFont);
        Button aBtn, pBtn, dBtn;
        aBtn = new Button("A");
        pBtn = new Button("P");
        dBtn = new Button("D");
        aBtn.setFont(controlFont);
        aBtn.setOnMouseClicked(e -> {
            isArmed = central.armAll();
            if (isArmed) setDisplay("A Armed");
        });
        pBtn.setOnMouseClicked(e -> {
            isArmed = central.armPerimeter();
            if (isArmed) setDisplay("P Armed");
        });
        dBtn.setOnMouseClicked(e -> {
            central.disarm();
            setDisplay("Disarmed");
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(aBtn,pBtn,dBtn);
        hBox.setAlignment(Pos.CENTER);
        getChildren().addAll(display,hBox);
    }
    public void setDisplay (String msg) {
        display.setText(msg);
    }
    private Label display;
    private boolean isArmed;
}
