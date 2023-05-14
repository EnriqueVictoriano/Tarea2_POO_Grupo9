import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class PersonView extends Pane {

    public PersonView(Person persona, Color color, double radius) {
        //super(persona.getX(), persona.getY(), radius, color);
        this.persona = persona;
        this.color = color;
        this.radius = radius;

        cabeza = new Circle(10, Color.BLACK);
        bra_der = new Circle(5, Color.BLUE);
        bra_izq = new Circle(5, Color.BLUE);
        cuerpo = new Rectangle(20, 40, Color.GREEN);

        cabeza.setCenterX(cuerpo.getWidth() / 2);
        cabeza.setCenterY(cuerpo.getHeight() / 2);
        bra_der.setCenterX(cuerpo.getWidth() / 2);
        bra_der.setCenterY(-cuerpo.getHeight() + 35);
        bra_izq.setCenterX(cuerpo.getWidth() / 2);
        bra_izq.setCenterY(-cuerpo.getHeight() + 35);

        getChildren().addAll(cuerpo, bra_der, bra_izq, cabeza);

        setOnMouseDragged(event -> {
            cuerpo.setTranslateX(event.getX());
            cuerpo.setTranslateY(event.getY());
            bra_der.setTranslateX(event.getX());
            bra_der.setTranslateY(event.getY());
            bra_izq.setTranslateX(event.getX());
            bra_der.setTranslateY(event.getY());
            cabeza.setTranslateX(event.getX());
            cabeza.setTranslateY(event.getY());

            persona.setX((int) event.getX());
            persona.setY((int) event.getY());

        });

    }

    /*
     * public void updatePosition() {
     * setCenterX(persona.getX());
     * setCenterY(persona.getY());
     * }
     * 
     * public void updateColor() {
     * if (persona.getdetect()) {
     * setFill(Color.RED);
     * } else {
     * setFill(color);
     * }
     * }
     */

    private Person persona;
    private Color color;
    private Circle cabeza;
    private Circle bra_izq;
    private Circle bra_der;
    private Rectangle cuerpo;
    private double radius;
}
