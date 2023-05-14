import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class PersonView extends Circle {
    public PersonView(Person persona, Color color, double radius) {
        super(persona.getX(), persona.getY(), radius, color);
        this.persona = persona;
        this.color = color;
        this.radius = radius;
    }

    public void updatePosition() {
        setCenterX(persona.getX());
        setCenterY(persona.getY());
    }

    public void updateColor() {
        if (persona.getdetect()) {
            setFill(Color.RED);
        } else {
            setFill(color);
        }
    }

    private Person persona;
    private Color color;
    private double radius;
}

